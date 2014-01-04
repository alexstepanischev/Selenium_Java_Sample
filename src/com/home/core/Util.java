package com.home.core;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Util {
	public static Logger log = LogManager.getLogger(Util.class.getName());
	
	/**
	 * Returns class name extracted from full class name string
	 * @param fullName - full class name string with package info
	 */
	public static String getClassNameFromFullName(String fullName) {		
		String[] lexems = fullName.split("\\.");
		return lexems[lexems.length - 1];
	}
	
	public static String formatLogMessage(String fullClassName, String methodName, String message) {
		return String.format("%s.%s - %s", getClassNameFromFullName(fullClassName), methodName, message);
	}
	
	public static String formatLogMessage(StackTraceElement stackElement, String message) {
		return formatLogMessage(stackElement.getClassName(), stackElement.getMethodName(), message);
	}
	
	public static void logStackTrace (Throwable thr) {
	    log.error(thr.getMessage());	    
	    StringWriter errors = new StringWriter();
	    thr.printStackTrace(new PrintWriter(errors));
	    log.trace(errors.toString());
	}

}
