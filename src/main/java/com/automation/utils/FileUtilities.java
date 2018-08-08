package com.automation.utils;

import java.io.File;

import org.apache.log4j.Logger;

import com.automation.helper.logger.LoggerHelper;

public class FileUtilities {

	private static Logger log = LoggerHelper.getLogger(FileUtilities.class);
	
	public FileUtilities()
	{
		log.info("FileUtilities Helper Initialized.");
	}
	
	public static File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}
		
		log.info("Searching last modified file in : " + dirPath);

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		log.info("Last Modified File: " + lastModifiedFile.toString());
		return lastModifiedFile;
	}

}
