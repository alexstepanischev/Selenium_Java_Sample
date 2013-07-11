package com.home.core;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
	
	protected WebDriver driver;
	
	@Before
	public void setUp() {
		driver = new FirefoxDriver();
	}

	@After
	public void tearDown() {
		if (driver == null) {
			return;
		}
		else {
			driver.quit();
			driver = null;
		}
	}
	
	/**
	 * Opens defined URL in browser
	 * @param url - target page URL
	 */
	protected void open(String url) {
		driver.get(url);
	}
}
