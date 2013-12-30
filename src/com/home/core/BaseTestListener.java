package com.home.core;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class BaseTestListener extends TestListenerAdapter {
	
	/**
	 * Method injects link to failure screenshot into result report
	 */
	@Override
	  public void onTestFailure(ITestResult tr) {
		String fullClassName = tr.getTestClass().getName();
		String methodName = tr.getName();
		String path = "../tests/" + (fullClassName + "." + methodName).replace(".", "/") + ".html" ;
	    Reporter.log(String.format("<font color='red'>%s.%s - TEST FAILED: <a href = '%s'>Failure screenshot</a></font><br/>", Util.getClassNameFromFullName(fullClassName), methodName, path));
	  }

}
