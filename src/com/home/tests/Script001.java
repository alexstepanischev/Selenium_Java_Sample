package com.home.tests;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import org.testng.annotations.Test;

import com.home.core.BaseTest;
import com.home.pages.GoogleSearchPage;
import com.home.pages.GoogleSearchResultPage;

public class Script001 extends BaseTest {

	@Test
	public void test001() throws Exception {
		
		stepInfo("Opening google search");
		open("http://www.google.com/?hl=en");
		
		GoogleSearchPage searchPage = page(GoogleSearchPage.class);
		
		stepInfo("Checking page title");
		searchPage.checkTitle("Google");
	   
		stepInfo("Performing search");
		GoogleSearchResultPage searchResultPage = searchPage.searchFor("Cookies");
		
		searchResultPage.resultsHeader.shouldNotBe(visible);
		
		stepInfo("Checking result");
		searchResultPage.verifySRLinkText(1, "HTTP cookie - Wikipedia, the free encyclopedia");
	        
	}
	
	@Test
	public void test002() throws Exception {
		
		stepInfo("Opening google search");
		open("http://www.google.com/?hl=en");
		
		GoogleSearchPage searchPage = page(GoogleSearchPage.class);
		
		stepInfo("Checking page title");
		searchPage.checkTitle("Google");
	   
		stepInfo("Performing search");
		GoogleSearchResultPage searchResultPage = searchPage.searchFor("Sweets");
		
		searchResultPage.resultsHeader.shouldNotBe(visible);
		
		stepInfo("Checking result");
		searchResultPage.verifySRLinkText(1, "HTTP cookie - Wikipedia, the free encyclopedia");
	        
	}
}
