package com.home.core;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class BaseTestListener extends TestListenerAdapter {
	
	/**
	 * Method injects link to failure screenshot into result report and logs error
	 */
	@Override
	  public void onTestFailure(ITestResult tr) {
		String fullClassName = tr.getTestClass().getName();
		String methodName = tr.getName();
		
		String message = Util.formatLogMessage(fullClassName, methodName, "TEST FAILED");
		String path = "../tests/" + (fullClassName + "." + methodName).replace(".", "/") + ".html" ;
	    
		Reporter.log(String.format("<font color='red'>%s: <a href = '%s'>Failure screenshot</a></font><br/>", message, path));	    
	    Util.log.error(message);	    
	    Util.logStackTrace(tr.getThrowable());
	}
	
	@Override
	  public void onTestSuccess(ITestResult tr) {		
		String message = Util.formatLogMessage(tr.getTestClass().getName(), tr.getName(), "TEST PASSED");
		
		Reporter.log(String.format("<font color='green'>%s</font><br/>", message));		
		Util.log.info(message);
	}
	
	@Override
	  public void onTestStart(ITestResult tr) {		
		Util.log.info(Util.formatLogMessage(tr.getTestClass().getName(), tr.getName(), "TEST STARTED"));
	}

}
