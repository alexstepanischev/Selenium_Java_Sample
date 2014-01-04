package com.home.core;

import java.io.PrintWriter;
import java.io.StringWriter;

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
		String path = "../tests/" + (fullClassName + "." + methodName).replace(".", "/") + ".html" ;
	    Reporter.log(String.format("<font color='red'>%s.%s - TEST FAILED: <a href = '%s'>Failure screenshot</a></font><br/>", Util.getClassNameFromFullName(fullClassName), methodName, path));
	    Util.log.error(String.format("%s.%s - TEST FAILED", Util.getClassNameFromFullName(fullClassName), methodName));
	    Util.log.error(tr.getThrowable().getMessage());	
	    
	    StringWriter errors = new StringWriter();
	    tr.getThrowable().printStackTrace(new PrintWriter(errors));
	    Util.log.trace(errors.toString());
	}
	
	@Override
	  public void onTestSuccess(ITestResult tr) {
		String fullClassName = tr.getTestClass().getName();
		String methodName = tr.getName();
		
		Reporter.log(String.format("<font color='green'>%s.%s - TEST PASSED</font><br/>", Util.getClassNameFromFullName(fullClassName), methodName));
		Util.log.info(String.format("%s.%s - TEST PASSED", Util.getClassNameFromFullName(fullClassName), methodName));
	}
	
	@Override
	  public void onTestStart(ITestResult tr) {
		String fullClassName = tr.getTestClass().getName();
		String methodName = tr.getName();
		
		Util.log.info(String.format("%s.%s - TEST STARTED", Util.getClassNameFromFullName(fullClassName), methodName));
	}

}
