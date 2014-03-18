package com.home.pages;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.element.TextInput;

import com.codeborne.selenide.ElementsCollection;
import com.home.components.TwitterLoginForm;
import com.home.core.BasePage;
import com.home.elements.SelenideSelect;

public class TwitterLoginPage extends BasePage {
	
	public TwitterLoginForm loginForm;	
	
	@FindBy(css = "input")
	public ElementsCollection all_inputs;
	
	public void checkInputs() {
		$$("input").shouldHave(size(6));
	}
	
	@FindBy(css = "input")
    public List<TextInput> allInputs;
	
	
	@FindBy(css = "input")
    public SelenideSelect select;

}
