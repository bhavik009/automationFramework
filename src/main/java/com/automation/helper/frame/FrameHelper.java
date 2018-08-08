package com.automation.helper.frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.helper.logger.LoggerHelper;

public class FrameHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(FrameHelper.class);
	
	public FrameHelper(WebDriver driver)
	{
		this.driver = driver;
	}
	
	// This method will switchToFrame based on frame index
	//* @param frameIndex
	public void switchToFrame(int frameIndex)
	{
		driver.switchTo().frame(frameIndex);
		log.info("switched to :"+ frameIndex+" frame");
	}
	
	// This method will switchToFrame based on frame name
	// * @param frameName
	public void switchToFrame(String frameName)
	{
		driver.switchTo().frame(frameName);
		log.info("switched to :"+ frameName+" frame");
	}
	
	// Tthis method will switchToFrame based on frame WebElement
	// * @param element
	public void switchToFrame(WebElement element)
	{
		driver.switchTo().frame(element);
		log.info("Switched to frame "+element.toString());
	}
}
