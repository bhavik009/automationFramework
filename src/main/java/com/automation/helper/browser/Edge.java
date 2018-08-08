package com.automation.helper.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.automation.helper.resource.ResourcePath;

public class Edge {
	public WebDriver getEdgeDriver() {

		System.setProperty("webdriver.edge.driver",
				ResourcePath.getResourcePath("src/main/resources/drivers/MicrosoftWebDriver.exe"));
		return new EdgeDriver();
	}
}
