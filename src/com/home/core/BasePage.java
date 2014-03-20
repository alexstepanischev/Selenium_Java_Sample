package com.home.core;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.home.elements.SelenideTextBlock;

public abstract class BasePage {
	
	@FindBy(id="flash")
	public SelenideTextBlock notification;
	
	/**
	 * Checks if page title is correct
	 * @param titleText - expected page title
	 */
    public void checkTitle(String titleText) {   	
		Assert.assertEquals(getWebDriver().getTitle(), titleText, "Page title is wrong");    	   
    }

}
