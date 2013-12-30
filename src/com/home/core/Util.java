package com.home.core;

public class Util {
	
	/**
	 * Returns class name extracted from full class name string
	 * @param fullName - full class name string with package info
	 */
	public static String getClassNameFromFullName(String fullName) {		
		String[] lexems = fullName.split("\\.");
		return lexems[lexems.length - 1];
	}

}
