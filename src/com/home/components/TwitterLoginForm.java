package com.home.components;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.SelenideElement;
import com.home.elements.SelenideButton;
import com.home.elements.SelenideCheckBox;

import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@Name("Twitter Login Form")
@Block(@FindBy(css = "div.front-signin"))
public class TwitterLoginForm extends HtmlElement {
	
	@FindBy(id = "signin-email")
	public SelenideElement emailInput;
	
	@FindBy(id = "signin-password")
	public WebElement passwordInput;
	
	@FindBy(css = ".submit")
	public SelenideButton buttonSubmit;
	
	@FindBy(css = "input")
    public List<TextInput> allFormInputs;
	
	public void checkInputs() {
		$$("input").shouldHave(size(6));
	}
	
	@FindBy(name="remember_me")
	public SelenideCheckBox rememberMeCheckBox;
}
