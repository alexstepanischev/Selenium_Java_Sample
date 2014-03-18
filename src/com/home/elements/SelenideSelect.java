package com.home.elements;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

public class SelenideSelect extends ElementsContainer {
	
	private org.openqa.selenium.support.ui.Select getSelect() {
        return new org.openqa.selenium.support.ui.Select(getSelf());
    }
	
	public SelenideSelect() {
		
	}
	
	public SelenideSelect(SelenideElement element) {
		setSelf(element);
	}

    /**
     * Indicates whether this select element support selecting multiple options at the same time.
     * This is done by checking the value of the "multiple" attribute.
     *
     * @return {@code true} if select element support selecting multiple options and {@code false} otherwise.
     */
    public boolean isMultiple() {
        return getSelect().isMultiple();
    }

    /**
     * Returns all options belonging to this select tag.
     *
     * @return A list of {@code WebElements} representing options.
     */
    public List<WebElement> getOptions() {
        return getSelect().getOptions();
    }

    /**
     * Returns all selected options belonging to this select tag.
     *
     * @return A list of {@code WebElements} representing selected options.
     */
    public List<WebElement> getAllSelectedOptions() {
        return getSelect().getAllSelectedOptions();
    }

    /**
     * The first selected option in this select tag (or the currently selected option in a normal select).
     *
     * @return A {@code WebElement} representing selected option.
     */
    public WebElement getFirstSelectedOption() {
        return getSelect().getFirstSelectedOption();
    }

    /**
     * Indicates if select has at least one selected option.
     *
     * @return {@code true} if select has at least one selected option and {@code false} otherwise.
     */
    public boolean hasSelectedOption() {
        for (WebElement option : getOptions()) {
            if (option.isSelected()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Select all options that display text matching the argument. That is, when given "Bar" this
     * would select an option like:
     * <p/>
     * &lt;option value="foo"&gt;Bar&lt;/option&gt;
     *
     * @param text The visible text to match against
     */
    public void selectByVisibleText(String text) {
        getSelect().selectByVisibleText(text);
    }

    /**
     * Select the option at the given index. This is done by examing the "index" attribute of an
     * element, and not merely by counting.
     *
     * @param index The option at this index will be selected
     */
    public void selectByIndex(int index) {
        getSelect().selectByIndex(index);
    }

    /**
     * Select all options that have a value matching the argument. That is, when given "foo" this
     * would select an option like:
     * <p/>
     * &lt;option value="foo"&gt;Bar&lt;/option&gt;
     *
     * @param value The value to match against
     */
    public void selectByValue(String value) {
        getSelect().selectByValue(value);
    }

    /**
     * Clear all selected entries. This is only valid when the SELECT supports multiple selections.
     *
     * @throws UnsupportedOperationException If the SELECT does not support multiple selections
     */
    public void deselectAll() {
        getSelect().deselectAll();
    }

    /**
     * Deselect all options that have a value matching the argument. That is, when given "foo" this
     * would deselect an option like:
     * <p/>
     * &lt;option value="foo"&gt;Bar&lt;/option&gt;
     *
     * @param value The value to match against
     */
    public void deselectByValue(String value) {
        getSelect().deselectByValue(value);
    }

    /**
     * Deselect the option at the given index. This is done by examing the "index" attribute of an
     * element, and not merely by counting.
     *
     * @param index The option at this index will be deselected
     */
    public void deselectByIndex(int index) {
        getSelect().deselectByIndex(index);
    }

    /**
     * Deselect all options that display text matching the argument. That is, when given "Bar" this
     * would deselect an option like:
     * <p/>
     * &lt;option value="foo"&gt;Bar&lt;/option&gt;
     *
     * @param text The visible text to match against
     */
    public void deselectByVisibleText(String text) {
        getSelect().deselectByVisibleText(text);
    }
}
