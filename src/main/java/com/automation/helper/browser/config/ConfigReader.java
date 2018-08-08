package com.automation.helper.browser.config;

import com.automation.helper.browser.BrowserName;

public interface ConfigReader {
	public int getImpliciteWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserName getBrowserName();
	public String getUrl();
	public String getUserName();
	public String getPassword();
	
	public String getDBUrl();
	public String getDBUsername();
	public String getDBPassword();
	
	public String getGmailUsername();
	public String getGmailPassword();
	public String getReceiverEmail();
	
	public String getServer();
}
