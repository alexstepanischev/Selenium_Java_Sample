package com.home.cucumber.runners;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
 
@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:build/reports/cucumber/html", "json:build/reports/cucumber/cucumber.json"},
				 features = {"features"},
				 glue = "com.home.cucumber.stepdefs",
				 monochrome=true)
public class RunTests {
	
}
