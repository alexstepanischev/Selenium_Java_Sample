package com.home.core;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import org.testng.Assert;

public abstract class BasePage {
	
	/**
	 * Checks if page title is correct
	 * @param titleText - expected page title
	 */
    public void checkTitle(String titleText) {   	
		Assert.assertEquals(getWebDriver().getTitle(), titleText, "Page title is wrong");    	   
    }

}
