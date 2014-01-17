package com.home.connector;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.codeborne.selenide.Selenide;

public class SelenideHtmlElement extends Selenide {
	
	  /**
	   * Create a Page Object instance.
	   * @see PageFactory#initElements(WebDriver, Class)
	   */
	  
	public static <PageObjectClass> PageObjectClass newPage(Class<PageObjectClass> pageObjectClass) {
	    try {
	      return newPage(pageObjectClass.getConstructor().newInstance());
	    } catch (Exception e) {
	      throw new RuntimeException("Failed to create new instance of " + pageObjectClass, e);
	    }
	  }

	  /**
	   * Create a Page Object instance.
	   * @see PageFactory#initElements(WebDriver, Class)
	   */
	  public static <PageObjectClass, T extends PageObjectClass> PageObjectClass newPage(T pageObject) {
	    PageFactory.initElements(new SelenideHtmlElementDecorator(getWebDriver()), pageObject);
	    return pageObject;
	  }

}
