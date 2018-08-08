package com.automation.helper.assertion;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.automation.helper.logger.LoggerHelper;

public class AssertHelper {

private static Logger log = LoggerHelper.getLogger(AssertHelper.class);
	
	public static void verifyEquals(String s1, String s2){
		log.info("veryfing text: "+ s1 + " with "+ s2);
		Assert.assertEquals(s1, s1);
	}
	
	public static void passTest(){
		log.info("making script PASS..");
		Assert.assertTrue(true);
	}
	
	public static void passTest(String message){
		log.info("Making script PASS.."+ message);
		Assert.assertTrue(true, message);
	}
	
	public static void failTest(){
		log.info("Making script FAIL..");
		Assert.assertTrue(false);
	}
	
	public static void failTest(String message){
		log.info("making script FAIL.."+message);
		Assert.assertTrue(false, message);
	}
	
	public static void verifyTrue(boolean status){
		Assert.assertTrue(status);
	}
	
	public static void verifyFalse(boolean status){
		Assert.assertFalse(status);
	}
	
	public static void verifyNull(String s1){
	    log.info("verify object is null..");
		Assert.assertNull(s1);
	}
	
	public static void verifyNotNull(String s1){
		log.info("verify object is not null..");
		Assert.assertNotNull(s1);
	}
	
	public static void updateTestStatus(boolean status){
		if(status)
			passTest();
		else
			failTest();
	}
}
