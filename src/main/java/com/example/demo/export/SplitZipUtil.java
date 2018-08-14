package com.example.demo.export;

import java.io.File;
import java.util.ArrayList;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qsky on 2018/2/28
 */
public class SplitZipUtil {

	private static final Logger logger = LoggerFactory.getLogger(SplitZipUtil.class);

	/**
	 * 使用zip4j，实现分卷压缩
	 * @param filesToZip 待压缩的文件列表
	 * @param destZipPathName 拆分后zip路径全名
	 * @param size  拆分的大小, 以M为单位
	 * @return
	 */
	public static Boolean splitAndZip(ArrayList<File> filesToZip, String destZipPathName,
		Integer size) {
		try {
			FileUtil.deleteExistZipFile(destZipPathName);
			ZipFile zip = new ZipFile(destZipPathName);
			zip.setFileNameCharset("GBK");
			ZipParameters para = new ZipParameters();
			para.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			para.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			zip.createZipFile(filesToZip, para, true, size * 1048576);
			logger.debug("分卷压缩成功!");
			return true;
		} catch (Exception e) {
			logger.error("分卷压缩:{}遇到错误!", destZipPathName);
			e.printStackTrace();
		}
		return false;
	}
}
