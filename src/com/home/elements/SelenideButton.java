package com.home.elements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

/**
 * Represents web page button control.
 */
public class SelenideButton extends ElementsContainer {
	
	public SelenideButton() {

	}
	
	public SelenideButton(SelenideElement element) {
		setSelf(element);
	}

	/**
     * Clicks the button.
     */
    public void click() {
        getSelf().click();
    }
    

}
