package com.automation.testBase;

import com.automation.helper.browser.BrowserName;
import com.automation.helper.browser.Chrome;
import com.automation.helper.browser.Edge;
import com.automation.helper.browser.Firefox;
import com.automation.helper.browser.IE;
import com.automation.helper.browser.config.ObjectReader;
import com.automation.helper.browser.config.PropertyReader;
import com.automation.helper.logger.LoggerHelper;
import com.automation.helper.resource.ResourcePath;
import com.automation.helper.spreadsheet.SheetHelper;
import com.automation.helper.wait.WaitHelper;
import com.automation.reports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.jopendocument.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	public static ExtentReports report;
	public static ExtentTest logger;

	public WebDriver driver;
	private static Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File screenshots;

	@BeforeSuite
	public void beforeSuite() {
		report = ExtentReporter.getInstance(getClass().getSimpleName());
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		ObjectReader.reader = new PropertyReader();
		screenshots = new File(ResourcePath.getResourcePath("src/main/resources/reports/screenshots"));

		setUpDriver(ObjectReader.reader.getBrowserName());
		logger = report.createTest(getClass().getSimpleName());
	}

	public void setUpDriver(BrowserName browserName) throws Exception {
		driver = getBrowserObject(browserName);
		log.info("Initializing Web Driver: " + driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImpliciteWait());
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime());
		driver.manage().window().maximize();

	}

	@SuppressWarnings("deprecation")
	public WebDriver getBrowserObject(BrowserName browserName) throws Exception {
		try {
			switch (browserName) {
			case Chrome:
				Chrome chrome = Chrome.class.newInstance();
				return chrome.getChromeDriver();

			case Firefox:
				Firefox firefox = Firefox.class.newInstance();
				return firefox.getFirefoxDriver();
			case IE:
				IE ie = IE.class.newInstance();
				return ie.getIEDriver();
			case Edge:
				Edge edge = Edge.class.newInstance();
				return edge.getEdgeDriver();
			default:
				throw new Exception("Browser Driver not found: " + browserName.name());
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		logger.log(Status.INFO, method.getName() + " => Test Started");
		//logger = report.createTest(method.getName());
		log.info("************* " + method.getName() + " => Test Started ****************");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.SUCCESS)
			logger.log(Status.PASS, result.getName() + " => PASS");
		else if(result.getStatus() == ITestResult.FAILURE)
		{
			logger.log(Status.FAIL, result.getName());
			logger.log(Status.ERROR, "=> Error URL: " + driver.getCurrentUrl());
			logger.log(Status.FAIL, result.getThrowable());
			
			String imagePath = captureScreenshot(result, driver);
			logger.addScreenCaptureFromPath(imagePath);
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			logger.log(Status.WARNING, result.getName());
			logger.log(Status.SKIP, result.getThrowable());
		}
		log.info("**************"+result.getName()+" - Finished***************");
	}

	@AfterTest
	public void afterTest() {
		logger.log(Status.INFO, "Reports Folder: <a href=\".\\\" target='_blank'>Click to open All Test Reports</a>");
		
		report.flush();
		
		try {
			EmailReport.sendExtentEmailReport(getClass().getSimpleName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(driver!=null)
			driver.quit();
	}
	
	public String captureScreenshot(ITestResult result, WebDriver driver)
	{
		if(driver == null){
			log.info("driver is null..");
			return null;
		}
		File pathName = null;
		
		Reporter.log("captureScreen method called", true);
		try 
		{
			String path = screenshots + "/" + result.getTestClass() + "/";
			boolean success = (new File(path)).mkdirs();
			if (!success)
				Reporter.log("Unable to create Directory", true);
			
			// Create refernce of TakesScreenshot
			TakesScreenshot ts=(TakesScreenshot)driver;
			 
			// Call method to capture screenshot
			File source=ts.getScreenshotAs(OutputType.FILE);
			 
			
			LocalDateTime timenow = LocalDateTime.now();
			// Copy files to specific location here it will save all screenshot in our project home directory and
			// result.getName() will return name of test case so that screenshot name will be same
			pathName = new File(path + result.getName()+ DateTimeFormatter.ofPattern("ddMMMyyyyhhmmssSSS").format(timenow) +".png");
			FileUtils.copyFile(source, pathName);
			 
			Reporter.log("<a href='" + pathName.getAbsolutePath()+ "'><img src='" +pathName.getAbsolutePath()+ 
					"'height='100' width='100'/></a>");
			log.info("Screenshot taken");
			logger.addScreenCaptureFromPath(pathName.toString());
		} 
		catch (Exception e)
		{
			Reporter.log("Exception while taking screenshot "+e.getMessage(), true);
		} 
		
		return pathName.toString();
	}
	public String captureScreenshot(String imageName, WebDriver driver)
	{
		if(driver == null){
			log.info("driver is null..");
			return null;
		}
		File pathName = null;
		
		Reporter.log("captureScreen method called", true);
		try 
		{
			String path = screenshots + "/" + "Others/";
			boolean success = (new File(path)).mkdirs();
			if (!success)
				Reporter.log("Unable to create Directory", true);
			
			// Create refernce of TakesScreenshot
			TakesScreenshot ts=(TakesScreenshot)driver;
			 
			// Call method to capture screenshot
			File source=ts.getScreenshotAs(OutputType.FILE);
			
			LocalDateTime timenow = LocalDateTime.now();
			// Copy files to specific location here it will save all screenshot in our project home directory and
			// result.getName() will return name of test case so that screenshot name will be same
			pathName = new File(path + imageName+ DateTimeFormatter.ofPattern("ddMMMyyyyhhmmssSSS").format(timenow) +".png");
			FileUtils.copyFile(source, pathName);
			 
			Reporter.log("<a href='" + pathName.getAbsolutePath()+ "'><img src='" +pathName.getAbsolutePath()+ 
					"'height='100' width='100'/></a>");
			log.info("Screenshot taken");
			logger.addScreenCaptureFromPath(pathName.toString());
		} 
		catch (Exception e)
		{
			Reporter.log("Exception while taking screenshot "+e.getMessage(), true);
		} 
		
		return pathName.toString();
	}
	
	public static void logReport(String s1){
		logger.log(Status.INFO, s1);
		Reporter.log(s1, true);
		log.info(s1);
	}
	
	public void getApplicationUrl(String url){
		driver.get(url);
		logReport("Navigating to ..."+url);
	}
	
	public Object[][] getExcelData(String spreadsheetName, String sheetName){
		String sheetPath = ResourcePath.getResourcePath("src/main/resources/Spreadsheets/")+spreadsheetName;
		log.info("Spreadsheet Path: "+ sheetPath);
		Reporter.log("Spreadsheet Path: "+ sheetPath, true);
		SheetHelper sheetHelper = new SheetHelper();
		Object[][] data = sheetHelper.getExcelData(sheetPath, sheetName);
		return data;
	}

}
