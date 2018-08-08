package com.automation.helper.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.automation.helper.resource.ResourcePath;

public class Chrome {

	public ChromeOptions getChromeOptions() {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--test-type");
		option.addArguments("--disable-popup-blocking");

		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);

		option.setCapability(ChromeOptions.CAPABILITY, chrome);
		// Linux
		if (System.getProperty("os.name").contains("Linux")) {
			option.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return option;
	}

	/*
	 * public WebDriver getChromeDriver(ChromeOptions cap) {
	 * 
	 * if (System.getProperty("os.name").contains("Mac")){
	 * System.setProperty("webdriver.chrome.driver",
	 * ResourcePath.getResourcePath("src/main/resources/drivers/chromedriver"));
	 * return new ChromeDriver(cap); } else
	 * if(System.getProperty("os.name").contains("Window")){
	 * System.setProperty("webdriver.chrome.driver",
	 * ResourcePath.getResourcePath("src/main/resources/drivers/chromedriver.exe"));
	 * return new ChromeDriver(cap); } else
	 * if(System.getProperty("os.name").contains("Linux")){
	 * System.setProperty("webdriver.chrome.driver", "/usr/bin/chrome"); return new
	 * ChromeDriver(cap); } return null; }
	 */

	public WebDriver getChromeDriver() {

		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.chrome.driver",
					ResourcePath.getResourcePath("src/main/resources/drivers/chromedriver"));
			return new ChromeDriver();
		} else if (System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.chrome.driver",
					ResourcePath.getResourcePath("src/main/resources/drivers/chromedriver.exe"));
			return new ChromeDriver();
		} else if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chrome");
			return new ChromeDriver();
		}
		return null;
	}
}
