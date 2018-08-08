package com.automation.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.automation.helper.resource.ResourcePath;

public class LoggerHelper {

private static boolean root=false;
	
	public static Logger getLogger(@SuppressWarnings("rawtypes") Class cls)
	{
		if(root)
			return Logger.getLogger(cls);
		
		PropertyConfigurator.configure(ResourcePath.getResourcePath("src/main/resources/config/log4j.properties"));
		
		root = true;
		return Logger.getLogger(cls);
	}
}
