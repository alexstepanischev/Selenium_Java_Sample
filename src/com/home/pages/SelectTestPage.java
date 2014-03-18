package com.home.pages;

import static com.codeborne.selenide.Selenide.open;
import static com.home.connector.SelenideHtmlElement.newPage;

import org.openqa.selenium.support.FindBy;

import com.home.core.BasePage;
import com.home.elements.SelenideSelect;
import com.home.elements.SelenideTextBlock;

public class SelectTestPage extends BasePage {
	
	@FindBy(name="select-pop-up")
	public SelenideSelect selectPopup;
	
	@FindBy(name="select-list")
	public SelenideSelect selectList;

	@FindBy(xpath="//body/p")
	public SelenideTextBlock introText;
	
	public static SelectTestPage openPage() {
		open("https://www.lehigh.edu/~inwww/form-test.html");
		return newPage(SelectTestPage.class);
	}

}
