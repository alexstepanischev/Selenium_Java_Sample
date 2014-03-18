package com.home.tests;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.home.connector.SelenideHtmlElement.newPage;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.home.core.BaseTest;
import com.home.pages.TwitterLoginPage;

@Listeners({BrowserPerTest.class})
public class TwitterTests extends BaseTest {
	
	//Testing SelenideCheckBox
	@Test
	public void testCheckBox() throws Exception {
		
		stepInfo("Opening twitter login");
		open("http://www.twitter.com");
		
		TwitterLoginPage loginPage = newPage(TwitterLoginPage.class);
		
		stepInfo("Filling out form");
		
		loginPage.loginForm.emailInput.sendKeys("test");
		loginPage.loginForm.passwordInput.sendKeys("test");

		loginPage.loginForm.buttonSubmit.getSelf().shouldBe(visible);
		
		loginPage.loginForm.allFormInputs.get(1).clear();

		Assert.assertEquals(loginPage.loginForm.rememberMeCheckBox.isSelected(), true, "Wrong value");
		loginPage.loginForm.rememberMeCheckBox.deselect();
		Assert.assertEquals(loginPage.loginForm.rememberMeCheckBox.isSelected(), false, "Wrong value");
		loginPage.loginForm.rememberMeCheckBox.select();
		Assert.assertEquals(loginPage.loginForm.rememberMeCheckBox.isSelected(), true, "Wrong value");
		loginPage.loginForm.rememberMeCheckBox.set(false);
		Assert.assertEquals(loginPage.loginForm.rememberMeCheckBox.isSelected(), false, "Wrong value");
		loginPage.loginForm.rememberMeCheckBox.set(true);
		Assert.assertEquals(loginPage.loginForm.rememberMeCheckBox.isSelected(), true, "Wrong value");
		
		loginPage.loginForm.buttonSubmit.click();
	}

}
