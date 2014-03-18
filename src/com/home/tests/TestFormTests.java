package com.home.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.home.core.BaseTest;
import com.home.pages.RadioTestPage;
import com.home.pages.SelectTestPage;

@Listeners({BrowserPerTest.class})
public class TestFormTests extends BaseTest {
	
	//Test SelenideRadio
	@Test
	public void testRadio() throws Exception {
		
		RadioTestPage formPage = RadioTestPage.openPage();		
		Assert.assertEquals(formPage.radio.hasSelectedButton(), true, "Wrong value");    
		formPage.radio.selectByValue("Milk");
		formPage.radio.selectByIndex(2);
		Assert.assertEquals(formPage.radio.getSelectedValue(), "Cheese", "Wrong value");    
		
	}
	
	//Test SelenideSelect
	@Test
	public void testSelect() throws Exception {
		SelectTestPage selectPage = SelectTestPage.openPage();	
		Assert.assertEquals(selectPage.selectPopup.isMultiple(), false, "Wrong value");    
		selectPage.selectPopup.selectByVisibleText("Third Value.");
		Assert.assertEquals(selectPage.selectPopup.hasSelectedOption(), true, "Wrong value"); 
		selectPage.selectPopup.selectByIndex(1);
		selectPage.selectPopup.selectByValue("value 4");
		Assert.assertEquals(selectPage.selectPopup.getFirstSelectedOption().getText(), "Fourth Value.", "Wrong value");
		
		Assert.assertEquals(selectPage.selectList.isMultiple(), true, "Wrong value");
		Assert.assertEquals(selectPage.selectList.getOptions().size(), 5, "Wrong value");
		selectPage.selectList.selectByIndex(1);
		selectPage.selectList.selectByVisibleText("Third Value.");
		selectPage.selectList.selectByValue("value 4");
		Assert.assertEquals(selectPage.selectList.getFirstSelectedOption().getText(), "First Value.", "Wrong value");
		Assert.assertEquals(selectPage.selectList.getAllSelectedOptions().size(), 4, "Wrong value");
		selectPage.selectList.deselectByIndex(1);
		selectPage.selectList.deselectByIndex(0);
		selectPage.selectList.deselectByVisibleText("Third Value.");
		selectPage.selectList.deselectByValue("value 4");
		Assert.assertEquals(selectPage.selectList.getAllSelectedOptions().size(), 0, "Wrong value");
		selectPage.selectList.selectByIndex(1);
		selectPage.selectList.selectByVisibleText("Third Value.");
		selectPage.selectList.deselectAll();
		Assert.assertEquals(selectPage.selectList.getAllSelectedOptions().size(), 0, "Wrong value");	
	}
	
	@Test
	public void testTextBlock() throws Exception {
		SelectTestPage selectPage = SelectTestPage.openPage();	
		Assert.assertEquals(selectPage.introText.getText(), "This page contains several HTML forms, which are linked to a CGI program designed to display the results of form submission, along with the values of any internal variables available. Examples of most of the types of forms element (text field, checkbox, etc.) that are possible have been included. This page is for testing purposes only. Users other than Lehigh University Information Resources staff should not link to or attempt to use the CGI program except via this page.", "Wrong value");	
	}
	
	
	
	
}
