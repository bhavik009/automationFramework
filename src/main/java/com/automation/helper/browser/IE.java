package com.automation.helper.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.ElementScrollBehavior;
//import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.automation.helper.resource.ResourcePath;

public class IE {
	public InternetExplorerOptions getIECapabilities() {

		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();

		cap.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		cap.setJavascriptEnabled(true);

		InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions(cap);

		return internetExplorerOptions;
	}

	/*
	 * public WebDriver getIEDriver(InternetExplorerOptions cap) {
	 * 
	 * System.setProperty("webdriver.ie.driver",
	 * ResourcePath.getResourcePath("src/main/resources/drivers/IEDriverServer.exe")
	 * ); return new InternetExplorerDriver(cap); }
	 */

	public WebDriver getIEDriver() {

		System.setProperty("webdriver.ie.driver",
				ResourcePath.getResourcePath("src/main/resources/drivers/IEDriverServer.exe"));
		return new InternetExplorerDriver();
	}
}
