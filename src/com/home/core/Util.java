package com.home.core;

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

}
