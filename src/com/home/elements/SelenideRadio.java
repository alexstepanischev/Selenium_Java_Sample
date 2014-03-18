package com.home.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

public class SelenideRadio extends ElementsContainer {
	
	public SelenideRadio(){
		
	}
	
	public SelenideRadio(SelenideElement element) {
		setSelf(element);
	}
	
	 /**
     * Returns all radio buttons belonging to this group.
     *
     * @return {@code ElementsCollection} representing radio buttons.
     */
    public ElementsCollection getButtons() {
        String radioName = getSelf().getAttribute("name");
   
        String xpath;
        if (radioName == null) {
            xpath = "self::* | following::input[@type = 'radio'] | preceding::input[@type = 'radio']";
        } else {
            xpath = String.format(
                    "self::* | following::input[@type = 'radio' and @name = '%s'] | " +
                            "preceding::input[@type = 'radio' and @name = '%s']",
                    radioName, radioName);
        }
        return getSelf().findAll(By.xpath(xpath));
    }

    /**
     * Returns selected radio button value.
     *
     * @return {@code SelenideElement} representing selected radio button or {@code null} if no radio buttons are selected.
     */
    public String getSelectedValue() {
    	
    	if (hasSelectedButton()) {
    	       for (SelenideElement button : getButtons()) {
    	            if (button.isSelected()) {
    	                return button.getAttribute("value");
    	            }
    	        }
    	}

        throw new NoSuchElementException("No selected button");
    }

    /**
     * Indicates if radio has selected button.
     *
     * @return {@code true} if radio has selected button and {@code false} otherwise.
     */
    public boolean hasSelectedButton() {
        for (SelenideElement button : getButtons()) {
            if (button.isSelected()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Selects radio button that have a value matching the specified argument.
     *
     * @param value The value to match against.
     */
    public void selectByValue(String value) {
    	
        for (SelenideElement button : getButtons()) {
            String buttonValue = button.getAttribute("value");
            if (value.equals(buttonValue)) {
                selectButton(button);
                return;
            }
        }

        throw new NoSuchElementException(String.format("Cannot locate radio button with value: %s", value));
    }

    /**
     * Selects radio button by the given index.
     *
     * @param index Index of a radio button to be selected.
     */
    public void selectByIndex(int index) {
        ElementsCollection buttons = getButtons();

        if (index < 0 || index >= buttons.size()) {
            throw new NoSuchElementException(String.format("Cannot locate radio button with index: %d", index));
        }
        selectButton(buttons.get(index));
    }

    /**
     * Selects radio button if it's not already selected.
     *
     * @param button {@code WebElement} representing radio button to be selected.
     */
    private void selectButton(SelenideElement button) {
        if (!button.isSelected()) {
            button.click();
        }
    }

}
