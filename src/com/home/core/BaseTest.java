package com.home.core;

import com.codeborne.selenide.testng.ScreenShooter;

import org.testng.Reporter;
import org.testng.annotations.Listeners;

@Listeners({ ScreenShooter.class, BaseTestListener.class })
public abstract class BaseTest {

	/**
	 * Reports and logs step information message
	 * @param message - text of step information message
	 */
	protected static void stepInfo(String message) {
		StackTraceElement stackElement = Thread.currentThread().getStackTrace()[2];
		String className = Util.getClassNameFromFullName(stackElement.getClassName());

		Reporter.log(String.format("%s.%s - %s <br/>", className, stackElement.getMethodName(), message));
	}

	
}
