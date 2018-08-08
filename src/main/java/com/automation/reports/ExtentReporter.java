package com.automation.reports;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.automation.helper.resource.ResourcePath;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReporter {
	private static ExtentReports report;

	public static ExtentReports getInstance(String className) {
		if (report == null) {

			DateFormat dateFormat = new SimpleDateFormat(" dd-MM-yy HH-mm-ss");
			Date date = new Date();
			String reportPath = ResourcePath
					.getResourcePath("src/main/resources/reports/" + className + dateFormat.format(date) + ".html");
			return createInstance(reportPath);
		} else {
			return report;
		}
	}

	/*
	 * public static ExtentReports createInstance(String fileName){
	 * ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
	 * htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
	 * htmlReporter.config().setChartVisibilityOnOpen(true);
	 * htmlReporter.config().setTheme(Theme.STANDARD);
	 * htmlReporter.config().setDocumentTitle(fileName);
	 * htmlReporter.config().setEncoding("utf-8");
	 * htmlReporter.config().setReportName("Automation Report"); extent = new
	 * ExtentReports(); extent.attachReporter(htmlReporter); return extent; }
	 */

	public static ExtentReports createInstance(String reportPath) {

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		return report;
	}
}
