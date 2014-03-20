package com.home.pages;

import org.openqa.selenium.support.FindBy;

import com.home.core.BasePage;
import com.home.elements.SelenideButton;
import com.home.elements.SelenideTextInput;

import static com.home.connector.SelenideHtmlElement.newPage;
import static com.codeborne.selenide.Selenide.*;

public class Rails42Login extends BasePage {
	
	@FindBy(id="user_email")
	public SelenideTextInput login;
	
	@FindBy(id="user_password")
	public SelenideTextInput password;
	
	@FindBy(name="commit")
	public SelenideButton signin;
	
	public static Rails42Login openPage() {
		open("http://rails42sample.herokuapp.com");
		return newPage(Rails42Login.class);
	}
	
	public Rails42Home submit() {
		signin.click();
		return newPage(Rails42Home.class);
	}
	
	public Rails42Home login() {
		login.sendKeys("admin@example.com");
		password.sendKeys("admin123");
		return submit();
	}
	
}
