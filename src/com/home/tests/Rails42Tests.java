package com.home.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.home.core.BaseTest;
import com.home.pages.Rails42Home;
import com.home.pages.Rails42Login;

@Listeners({BrowserPerTest.class})
public class Rails42Tests extends BaseTest {

	//Using SelenideTextInput and SelenideButton
	@Test
	public void testInputsAndButton() throws Exception {
		Rails42Login loginPage = Rails42Login.openPage();
		
		loginPage.login.sendKeys("test");
		Assert.assertEquals(loginPage.login.getText(), "test", "Wrong value");
		
		loginPage.login.clear();
		Assert.assertEquals(loginPage.login.getSelf().getText(), "", "Wrong value");
		
		loginPage.login.getSelf().sendKeys("test");
		Assert.assertEquals(loginPage.login.getText(), "test", "Wrong value");
		
		loginPage.login.getSelf().clear();
		Assert.assertEquals(loginPage.login.getText(), "", "Wrong value");
		
		loginPage.login.sendKeys("admin@example.com");
		loginPage.password.sendKeys("admin123");
		loginPage.signin.click();		
		
	}
	
	//Testing SelenideLink
	@Test
	public void testLink() throws Exception {
		Rails42Login loginPage = Rails42Login.openPage();
		Rails42Home homePage = loginPage.login();
		Assert.assertEquals(homePage.sidebarMenu.booksLink.getText(), "Books", "Wrong value");
		Assert.assertEquals(homePage.sidebarMenu.booksLink.getReference(), "http://rails42sample.herokuapp.com/books", "Wrong value");
		homePage.sidebarMenu.booksLink.click();
		$(By.linkText("Add Book")).click();	
	}
	
	
	
}
