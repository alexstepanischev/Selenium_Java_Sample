package com.home.elements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

public class SelenideTextBlock extends ElementsContainer {
	
	public SelenideTextBlock(){
		
	}
	
	public SelenideTextBlock(SelenideElement element) {
		setSelf(element);
	}
	
    /**
     * Returns text contained in text block.
     */
    public String getText() {
        return getSelf().getText();
    }

}
