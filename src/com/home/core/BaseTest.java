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
		
		String logMessage = Util.formatLogMessage(stackElement, message);
		
		Reporter.log(String.format("%s<br/>", logMessage));
		Util.log.info(logMessage);
	}
	
}
