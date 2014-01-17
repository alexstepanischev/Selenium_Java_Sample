package com.home.tests;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.home.connector.SelenideHtmlElement.newPage;

import org.testng.annotations.Test;

import com.home.core.BaseTest;
import com.home.pages.GoogleSearchPage;
import com.home.pages.GoogleSearchResultPage;

public class Script003 extends BaseTest {
	
	@Test
	public void test005() throws Exception {
		
		stepInfo("Opening google search");
		open("http://www.google.com/?hl=en");
		
		GoogleSearchPage searchPage = newPage(GoogleSearchPage.class);
		
		stepInfo("Checking page title");
		searchPage.checkTitle("Google");
	   
		stepInfo("Performing search");
		GoogleSearchResultPage searchResultPage = searchPage.searchFor("Cookies");
		
		searchResultPage.resultsHeader.shouldNotBe(visible);
		
		stepInfo("Second search attempt");
		searchResultPage.googleHeader.requestInput.clear();
		searchResultPage.googleHeader.search("test");
		
		stepInfo("Checking result");
		searchResultPage.verifySRLinkText(2, "Cookie Recipes - Allrecipes.com");	
		
	}

}
