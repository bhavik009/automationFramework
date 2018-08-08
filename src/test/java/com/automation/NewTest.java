package com.automation;

import org.testng.annotations.Test;

import com.automation.testBase.TestBase;

public class NewTest extends TestBase{
  @Test
  public void f() {
	  driver.get("http://www.google.com");
	  captureScreenshot("Google", driver);
	  
	  
  }
}
