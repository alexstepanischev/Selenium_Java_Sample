package com.home.elements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

public class SelenideLink extends ElementsContainer {
	
	public SelenideLink(){
		
	}
	
	public SelenideLink(SelenideElement element) {
		setSelf(element);
	}
	
	 /**
     * Retrieves reference from "href" tag.
     *
     * @return Reference associated with hyperlink.
     */
    public String getReference() {
        return getSelf().getAttribute("href");
    }

    /**
     * Clicks the link.
     */
    public void click() {
        getSelf().click();
    }

    /**
     * Returns text of the link.
     */
    public String getText() {
        return getSelf().getText();
    }

}
