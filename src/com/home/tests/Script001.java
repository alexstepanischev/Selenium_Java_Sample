package com.home.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.home.core.BaseTest;
import com.home.pages.GoogleSearchPage;
import com.home.pages.GoogleSearchResultPage;

public class Script001 extends BaseTest {

	@Test
	public void testSearch() throws Exception {
		
		open("http://www.google.com/?hl=en");
		
		Assert.assertEquals("Title not correct", "Google", driver.getTitle());
		
		GoogleSearchPage searchPage = PageFactory.initElements(driver, GoogleSearchPage.class);
	   
		searchPage.searchFor("Cookies");
		
		GoogleSearchResultPage searchResultPage = new GoogleSearchResultPage(driver);
		
		searchResultPage.verifySearchResult(3, "Cookie Recipes - Allrecipes.com");
	        
	}

}
