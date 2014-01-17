package com.home.pages;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.SelenideElement;
import static com.home.connector.SelenideHtmlElement.*;

import com.home.core.BasePage;

public class GoogleSearchPage extends BasePage {
    @FindBy(name = "q")
    @CacheLookup //Use for really static elements
    private SelenideElement searchBox;
    
	/**
	 * Performs search by defined query
	 * @param query - search query
	 */
    public GoogleSearchResultPage searchFor(String query) { 	
        searchBox.setValue(query);
        searchBox.pressEnter();
        return newPage(GoogleSearchResultPage.class);        
    }    
} 
