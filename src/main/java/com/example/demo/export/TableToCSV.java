package com.example.demo.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TableToCSV {

	private static Logger logger = LoggerFactory.getLogger(TableToCSV.class);

	@Resource
	private DataSource oracleDataSource;

	// 创建一个空文件
	private File createEmptyFile(String filename) throws Exception {
		File file = new File(filename);
		try {
			if (file.exists()) {
				file.delete();
				file.createNewFile();
			} else {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return file;
	}

	public Connection getConnection() throws SQLException {
		Connection conn = oracleDataSource.getConnection();
		conn.setAutoCommit(false);
		return conn;
	}

	public boolean jdbcExport(String csvPathName, String zipPathName, String zhTitle, ResultSet rs)
			throws Exception {
		File file = createEmptyFile(csvPathName);
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
		//写入中文头
		osw.write(zhTitle);
		//写完文件头后换行
		osw.write("\r\n");
		ResultSetMetaData rd = rs.getMetaData();
		int fields = rd.getColumnCount();
		String[] row = new String[fields];
		//写内容
		CSVPrinter printer = new CSVPrinter(osw, CSVFormat.DEFAULT);
		while (rs.next()) {
			for (int i = 1; i <= fields; i++) {
				String temp = rs.getString(i);
				row[i - 1] = StringUtils.isBlank(temp) ? "" : (temp.matches("^[0-9]*$") && temp.length() > 11) ? temp + "\t" : temp;
			}
			printer.printRecord(row);
		}
		printer.flush();
		printer.close();

		ArrayList<File> files = new ArrayList<>();
		files.add(file);
		File md5File = writeMd5File(csvPathName, FileUtil.getMD5(file));
		files.add(md5File);
		SplitZipUtil.splitAndZip(files, zipPathName + ".zip", 20);

		for (File deleteFile : files) {
			if (deleteFile.exists() && deleteFile.isFile()) {
				deleteFile.delete();
			}
		}
		return true;
	}

	private File writeMd5File(String fileName, String md5) {
		File file = new File(fileName.substring(0, fileName.lastIndexOf(".")) + ".md5");
		try {
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(md5);
			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	public void closeConnection(ResultSet rs, PreparedStatement preState, Connection conn)
			throws Exception {
		conn.rollback();
		if (rs != null) {
			rs.close();
		}
		if (preState != null) {
			preState.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public void closeConnection(ResultSet rs, List<PreparedStatement> preStates, Connection conn)
			throws Exception {
		conn.rollback();
		if (rs != null) {
			rs.close();
		}
		if (CollectionUtils.isNotEmpty(preStates)) {
			for (PreparedStatement preState : preStates) {
				if (preState != null) {
					preState.close();
				}
			}
		}
		if (conn != null) {
			conn.close();
		}
	}
}
