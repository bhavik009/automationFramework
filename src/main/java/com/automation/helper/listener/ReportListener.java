package com.automation.helper.listener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.automation.helper.logger.LoggerHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ReportListener implements ITestListener{

private Logger log = LoggerHelper.getLogger(ReportListener.class);
    
public static ExtentReports report;
public static ExtentTest logger;

	public void onFinish(ITestContext arg0) {
       // report.flush();
        Reporter.log(arg0.getName()+" Test Finished..", true);
        log.info(arg0.getName()+" Test Finished..");
	}

	public void onStart(ITestContext arg0) {
		//report = ExtentManager.getInstance();
		//logger = report.createTest(arg0.getName());
		//logger = report.createTest(arg0.getCurrentXmlTest().getName());
		Reporter.log(arg0.getCurrentXmlTest().getName()+" Class Started..");
		log.info(arg0.getCurrentXmlTest().getName()+" Class Started..");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		//logger.log(Status.FAIL, arg0.getThrowable());
		Reporter.log(arg0.getMethod().getMethodName()+" Test Failed.."+arg0.getThrowable());
		log.error(arg0.getMethod().getMethodName()+" Test Failed.."+arg0.getThrowable());
	}

	public void onTestSkipped(ITestResult arg0) {
		//logger.log(Status.SKIP, arg0.getThrowable());
		Reporter.log(arg0.getMethod().getMethodName()+" Test Skipped.."+arg0.getThrowable());
		log.warn(arg0.getMethod().getMethodName()+" Test Skipped.."+arg0.getThrowable());
	}

	public void onTestStart(ITestResult arg0) {
		//logger.log(Status.INFO, arg0.getName()+" started..");
		Reporter.log(arg0.getMethod().getMethodName()+" Test Started..");
		log.info(arg0.getMethod().getMethodName()+" Test Started..");
	}

	public void onTestSuccess(ITestResult arg0) {
		//logger.log(Status.INFO, arg0.getName()+" Passed..");
		Reporter.log(arg0.getMethod().getMethodName()+" Test Passed..");
		log.info(arg0.getMethod().getMethodName()+" Test Passed..");
	}
}
