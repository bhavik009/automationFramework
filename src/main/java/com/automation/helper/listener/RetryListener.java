package com.automation.helper.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener {
	
	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg3)
	{
		IRetryAnalyzer retry = arg0.getRetryAnalyzer();
		
		if(retry == null)
			arg0.setRetryAnalyzer(RetryHelper.class);
	}
}
