package com.home.cucumber.stepdefs;

import org.testng.Assert;

import com.home.pages.Rails42Home;
import com.home.pages.Rails42Login;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.After;

import com.codeborne.selenide.WebDriverRunner;

public class SmokeStepDefs {
	
	Rails42Login loginPage;
	Rails42Home homePage;
	
	@Given("^I am on the website \"([^\"]*)\"$")
	public void I_am_on_the_website(String url) throws Throwable {	    
		loginPage = Rails42Login.openPage();
	}

	@When("^I enter \"([^\"]*)\" into login field$")
	public void I_enter_into_login_field(String username) throws Throwable {
		loginPage.login.sendKeys(username);
	}

	@When("^enter \"([^\"]*)\" into password field$")
	public void enter_into_password_field(String password) throws Throwable {
		loginPage.password.sendKeys(password);
	}

	@When("^submit form$")
	public void submit_form() throws Throwable {
		homePage = loginPage.submit();
	}

	@Then("^I should see \"([^\"]*)\" message$")
	public void I_should_see_message(String notification) throws Throwable {
		Assert.assertTrue(homePage.notification.getText().contains(notification), "Wrong notification text");		
	}
	
	@After
	public void afterScenario() {
		WebDriverRunner.closeWebDriver();
	}

}
