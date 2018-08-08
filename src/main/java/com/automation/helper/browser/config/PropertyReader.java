package com.automation.helper.browser.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.automation.helper.browser.BrowserName;
import com.automation.helper.resource.ResourcePath;

public class PropertyReader implements ConfigReader {

	private static FileInputStream file;
	public static Properties OR;

	public PropertyReader() {
		try {
			String filePath = ResourcePath.getResourcePath("src/main/resources/config/config.properties");
			file = new FileInputStream(new File(filePath));
			OR = new Properties();
			OR.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getImpliciteWait() {
		return Integer.parseInt(OR.getProperty("implicitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitWait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(OR.getProperty("pageLoadTime"));
	}

	public BrowserName getBrowserName() {
		return BrowserName.valueOf(OR.getProperty("browserName"));
	}

	public String getUrl() {
		if (System.getProperty("url") != null) {
			return System.getProperty("url");
		}
		return OR.getProperty("applicationUrl");
	}

	public String getUserName() {
		if (System.getProperty("userName") != null) {
			return System.getProperty("userName");
		}
		return OR.getProperty("userName");
	}

	public String getPassword() {
		if (System.getProperty("password") != null) {
			return System.getProperty("password");
		}
		return OR.getProperty("password");
	}

	public String getDBUrl() {
		if (System.getProperty("dbURL") != null) {
			return System.getProperty("dbURL");
		}
		return OR.getProperty("dbURL");
	}

	public String getDBUsername() {
		if (System.getProperty("dbUsername") != null) {
			return System.getProperty("dbUsername");
		}
		return OR.getProperty("dbUsername");
	}

	public String getDBPassword() {
		if (System.getProperty("dbPassword") != null) {
			return System.getProperty("dbPassword");
		}
		return OR.getProperty("dbPassword");
	}

	public String getGmailUsername() {
		if (System.getProperty("gmailUsername") != null) {
			return System.getProperty("gmailUsername");
		}
		return OR.getProperty("gmailUsername");
	}

	public String getGmailPassword() {
		if (System.getProperty("gmailPassword") != null) {
			return System.getProperty("gmailPassword");
		}
		return OR.getProperty("gmailPassword");
	}

	public String getReceiverEmail() {
		if (System.getProperty("receiverEmail") != null) {
			return System.getProperty("receiverEmail");
		}
		return OR.getProperty("receiverEmail");
	}

	public String getServer() {
		if (System.getProperty("server") != null) {
			return System.getProperty("server");
		}
		return OR.getProperty("server");
	}

}
