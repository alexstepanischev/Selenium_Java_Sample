#language: en
@login
Feature: Log into Rails42sample app

  Scenario: login successfully with correct user
    Given I am on the website "http://rails42sample.herokuapp.com"
    When I enter "admin@example.com" into login field
    And enter "admin123" into password field 
    And submit form
    Then I should see "Signed in successfully." message