package com.example.demo.export;

import com.example.demo.util.DateUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qsky on 2018/2/5
 */
public class FileUtil {

  private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

  public static String getCreateTime(String filePath) {
    Path path = Paths.get(filePath);
    BasicFileAttributeView basicView = Files
        .getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
    BasicFileAttributes attr;
    try {
      attr = basicView.readAttributes();
      Date createDate = new Date(attr.creationTime().toMillis());
      return DateUtil.format(createDate, DateUtil.DATETIME_FORMAT_PATTERN);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return StringUtils.EMPTY;
  }

	public static void handleFolder(String absolutePath, String relativePath) {
		File folder = new File(absolutePath);
		if (folder.exists() && folder.isDirectory()) {
			logger.debug("Folder : {} exist.", absolutePath);
			return;
		} else if (folder.mkdirs()) {
			folder.setExecutable(true, false);
			logger.debug("Folder : {} create success!", absolutePath);
			File parentFolder = folder.getParentFile();
			while (parentFolder != null && relativePath.contains(parentFolder.getName())) {
				parentFolder.setExecutable(true, false);
				parentFolder = parentFolder.getParentFile();
			}
		} else {
			logger.error("Folder : {} create failed!", absolutePath);
		}
	}

  public static FileVO searchAllZipFile(String basePath, String matchStr) {
    File root = new File(basePath);
    List<String> nameList = new ArrayList<>(16);
    Long size = 0L;
    FileVO fileVO = new FileVO();
    DecimalFormat format = new DecimalFormat("0.00");
    if (root != null && root.listFiles() != null) {
      for (File file : root.listFiles()) {
        if (file.isDirectory()) {
          continue;
        } else {
          String fileName = file.getName().contains(".") ? file.getName()
              .substring(0, file.getName().lastIndexOf(".")) : file.getName();
          if (fileName.toUpperCase().contains(matchStr.toUpperCase())) {
            nameList.add(file.getName());
            size += file.length();
          }
        }
      }
      fileVO.setName(CollectionUtils.isNotEmpty(nameList) ? String.join("||", nameList) : "");
      fileVO.setSize(format.format(size / 1024.00));
      fileVO.setType("zip");
    }
    return fileVO;
  }

	public static String addFilePath(String sourceFileName, String prefix) {
		if (StringUtils.isBlank(prefix)) {
			return sourceFileName;
		}
		String[] sourceFileNameList = sourceFileName.split("\\|\\|");
		if (sourceFileNameList.length < 1) {
			return sourceFileName;
		}
		List<String> targetFileNameList = new ArrayList<>(10);
		for (String sourceName : sourceFileNameList) {
			if (prefix.endsWith(File.separator)) {
				targetFileNameList.add(prefix + sourceName);
			} else {
				targetFileNameList.add(prefix + File.separator + sourceName);
			}
		}
		return CollectionUtils.isNotEmpty(targetFileNameList) ? String.join("||", targetFileNameList) : "";
	}

  public static void deleteExistZipFile(String filePath) {
    //zip根路径
    String rootPath = filePath.substring(0, filePath.lastIndexOf(File.separator) + 1);
    String fileName = filePath
        .substring(filePath.lastIndexOf(File.separator) + 1, filePath.length());
    String csvName = fileName.substring(0, fileName.lastIndexOf("."));
    File root = new File(rootPath);
    if (root != null && root.listFiles() != null) {
      for (File file : root.listFiles()) {
        if (file.isDirectory()) {
          continue;
        } else {
          if (file.getName().contains(csvName)) {
            file.delete();
          }
        }
      }
    }
  }

	public static String getMD5(File file) {
		FileInputStream fileInputStream = null;
		try {
			MessageDigest MD5 = MessageDigest.getInstance("MD5");
			fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length;
			while ((length = fileInputStream.read(buffer)) != -1) {
				MD5.update(buffer, 0, length);
			}
			return new String(Hex.encodeHex(MD5.digest()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
