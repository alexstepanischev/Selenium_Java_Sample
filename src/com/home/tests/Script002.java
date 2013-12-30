package com.home.tests;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import org.testng.annotations.Test;

import com.home.core.BaseTest;
import com.home.pages.GoogleSearchPage;
import com.home.pages.GoogleSearchResultPage;

public class Script002 extends BaseTest {
	
	@Test
	public void test003() throws Exception {
		
		stepInfo("Opening google search");
		open("http://www.google.com/?hl=en");
		
		GoogleSearchPage searchPage = page(GoogleSearchPage.class);
		
		stepInfo("Checking page title");
		searchPage.checkTitle("Google");
	   
		stepInfo("Performing search");
		GoogleSearchResultPage searchResultPage = searchPage.searchFor("Cookies");
		
		searchResultPage.resultsHeader.shouldNotBe(visible);
		
		stepInfo("Checking result");
		searchResultPage.verifySRLinkText(2, "Cookie Recipes - Allrecipes.com");
	        
	}
	
	@Test
	public void test004() throws Exception {
		
		stepInfo("Opening google search");
		open("http://www.google.com/?hl=en");
		
		GoogleSearchPage searchPage = page(GoogleSearchPage.class);
		
		stepInfo("Checking page title");
		searchPage.checkTitle("Google");
	   
		stepInfo("Performing search");
		GoogleSearchResultPage searchResultPage = searchPage.searchFor("Biscuits");
		
		searchResultPage.resultsHeader.shouldNotBe(visible);
		
		stepInfo("Checking result");
		searchResultPage.verifySRLinkText(2, "Cookie Recipes - Allrecipes.com");
	        
	}

}
