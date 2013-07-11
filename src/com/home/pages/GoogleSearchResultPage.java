package com.home.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.home.core.BasePage;

public class GoogleSearchResultPage extends BasePage{
	
	private final WebDriver driver;

	public GoogleSearchResultPage(WebDriver driver) {
	    this.driver = driver;
	    
	    // Waits for page load and checks that we are on the right page.
	    Assert.assertEquals("This is not the search result page", "Search Results", waitForElement(By.xpath("//div[@id='search']/h2"), driver).getText());	   
	}
	
	/**
	 * Checking if defined link present on defined search result position
	 * @param position - position in search results from top of the page
	 * @param linkText - text of target link
	 */
	public void verifySearchResult(int position, String linkText) {			
		Assert.assertEquals("Search result link not matched", linkText, driver.findElement(By.xpath("//ol[@id='rso']/li[" + position + "]//h3/a")).getText());		   
	}

}
