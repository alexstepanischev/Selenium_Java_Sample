package com.home.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import com.home.components.GoogleHeader;
import com.home.core.BasePage;

public class GoogleSearchResultPage extends BasePage{
    
    public GoogleHeader googleHeader;
    
	@FindBy(css = "#search h2")
    public SelenideElement resultsHeader;
    
	/**
	 * Checking if defined link present on defined search result position
	 * @param position - position in search results from top of the page
	 * @param linkText - text of target link
	 */
	public void verifySRLinkText(int position, String linkText) {		
		String xpath = String.format("//ol[@id='rso']/li[%s]//h3/a", position);	
		$(By.xpath(xpath)).shouldHave(text(linkText));
	}

}
