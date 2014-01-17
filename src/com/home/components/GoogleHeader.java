package com.home.components;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.SelenideElement;

import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Google header")
@Block(@FindBy(id = "mngb"))
public class GoogleHeader extends HtmlElement {
	
	@FindBy(name = "q")
	public SelenideElement requestInput;

	@FindBy(name = "btnG")
	public Button searchButton;
	
	public void search(String request) {
		requestInput.clear();
	    requestInput.sendKeys(request);
	    searchButton.click();
	}
	    
}
