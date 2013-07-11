package com.home.pages;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;

public class GoogleSearchPage {
    @FindBy(how = How.NAME, using = "q")
    @CacheLookup //Use for really static elements
    private WebElement searchBox;
    
	/**
	 * Performs search by defined query
	 * @param query - search query
	 */
    public void searchFor(String query) {
        searchBox.sendKeys(query);
        searchBox.submit();
    }
    
} 
