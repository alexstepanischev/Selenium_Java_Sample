package com.home.tests;

import static com.codeborne.selenide.Selenide.open;
import static com.home.connector.SelenideHtmlElement.newPage;

import org.testng.annotations.Test;

import com.home.core.BaseTest;
import com.home.pages.TwitterLoginPage;

public class Script004 extends BaseTest {
	
	@Test
	public void test006() throws Exception {
		
		stepInfo("Opening twitter login");
		open("http://www.twitter.com");
		
		TwitterLoginPage loginPage = newPage(TwitterLoginPage.class);
		
		stepInfo("Filling out form");
		
		loginPage.loginForm.emailInput.sendKeys("test");
		loginPage.loginForm.passwordInput.sendKeys("test");
		loginPage.loginForm.buttonSubmit.click();
		loginPage.loginForm.emailInput.clear();
		
		
	}

}
