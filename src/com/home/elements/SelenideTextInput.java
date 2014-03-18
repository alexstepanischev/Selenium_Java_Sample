package com.home.elements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

public class SelenideTextInput extends ElementsContainer {
	
	public SelenideTextInput() {
		
	}
	
	public SelenideTextInput(SelenideElement element) {
		setSelf(element);
	}

	public void clear() {
        getSelf().clear();
    }

    /**
     * Prints specified char sequence into this text input.
     *
     * @param keys Text to print.
     */
    public void sendKeys(CharSequence... keys) {
        getSelf().sendKeys(keys);
    }

    /**
     * Retrieves the text entered into this text input.
     *
     * @return Text entered into the text input.
     */
    public String getText() {
        if ("textarea".equals(getSelf().getTagName())) {
            return getSelf().getText();
        }

        String enteredText = getSelf().getAttribute("value");
        if (enteredText == null) {
            return "";
        }
        return enteredText;
    }
}
