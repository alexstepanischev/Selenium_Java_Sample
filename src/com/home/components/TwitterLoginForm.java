package com.home.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.SelenideElement;

import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Twitter Login Form")
@Block(@FindBy(css = "div.front-signin"))
public class TwitterLoginForm extends HtmlElement {
	
	@FindBy(id = "signin-email")
	public SelenideElement emailInput;
	
	@FindBy(id = "signin-password")
	public WebElement passwordInput;
	
	@FindBy(css = ".submit")
	public Button buttonSubmit;
	
	
	
}
