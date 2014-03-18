package com.home.pages;

import static com.codeborne.selenide.Selenide.open;
import static com.home.connector.SelenideHtmlElement.newPage;

import org.openqa.selenium.support.FindBy;

import com.home.core.BasePage;
import com.home.elements.SelenideRadio;

public class RadioTestPage extends BasePage {
	
	@FindBy(name="group1")
	public SelenideRadio radio;
	
	public static RadioTestPage openPage() {
		open("http://www.echoecho.com/htmlforms10.htm");
		return newPage(RadioTestPage.class);
	}

}
