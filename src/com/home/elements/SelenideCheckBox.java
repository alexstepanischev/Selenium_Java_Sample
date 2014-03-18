package com.home.elements;

import org.openqa.selenium.By;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

public class SelenideCheckBox extends ElementsContainer {
	
	public SelenideCheckBox() {

	}
	
	public SelenideCheckBox(SelenideElement element) {
		setSelf(element);
	}
	
	 /**
     * Finds label corresponding to this checkbox using "following-sibling::label" xpath.
     *
     * @return {@code SelenideElement} representing label or {@code null} if no label has been found.
     */
    public SelenideElement getLabel() {
    	return getSelf().find(By.xpath("following-sibling::label"));
    }

    /**
     * Finds a text of the checkbox label.
     *
     * @return Label text or {@code null} if no label has been found.
     */
    public String getLabelText() {
        SelenideElement label = getLabel();
        return label == null ? null : label.getText();
    }

    /**
     * The same as {@link #getLabelText()}.
     *
     * @return Text of the checkbox label or {@code null} if no label has been found.
     */
    public String getText() {
        return getLabelText();
    }

    /**
     * Selects checkbox if it is not already selected.
     */
    public void select() {
        if (!isSelected()) {
            getSelf().click();
        }
    }

    /**
     * Deselects checkbox if it is not already deselected.
     */
    public void deselect() {
        if (isSelected()) {
            getSelf().click();
        }
    }

    /**
     * Selects checkbox if passed value is {@code true} and deselects otherwise.
     */
    public void set(boolean value) {
        if (value) {
            select();
        } else {
            deselect();
        }
    }

    /**
     * Indicates whether checkbox is selected.
     */
    public boolean isSelected() {
        return getSelf().isSelected();
    }

}
