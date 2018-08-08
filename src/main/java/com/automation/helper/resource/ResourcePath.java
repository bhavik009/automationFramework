package com.automation.helper.resource;

public class ResourcePath {

	public static String getResourcePath(String filepath)
	{
        String basePath = System.getProperty("user.dir");
        
        System.out.println(basePath +"/"+ filepath);
		return basePath +"/"+ filepath;
	}
}
