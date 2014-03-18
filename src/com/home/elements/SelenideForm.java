package com.home.elements;

import java.util.Map;

import org.openqa.selenium.By;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

public class SelenideForm extends ElementsContainer {
	
	    private static final String TEXT_INPUT_TYPE = "text";
	    private static final String PASSWORD_INPUT_TYPE = "password";
	    private static final String CHECKBOX_TYPE = "checkbox";
	    private static final String RADIO_TYPE = "radio";
	    
	    public SelenideForm(){
	    	
	    }
	    
	    public SelenideForm(SelenideElement element) {
			setSelf(element);
		}

	    /**
	     * Fills form with data contained in passed map.
	     * For each map entry an input with a name coincident with entry key is searched and then found input
	     * is filled with string representation of entry value (toString() method is called). If input with such a name
	     * is not found corresponding entry is skipped.
	     *
	     * @param data Map containing data to fill form inputs with.
	     */
	    public void fill(Map<String, Object> data) {
	        for (String key : data.keySet()) {
	            SelenideElement elementToFill = findElementByKey(key);
	            if (elementToFill != null) {
	                fillElement(elementToFill, data.get(key));
	            }
	        }
	    }

	    /**
	     * Submits represented form.
	     */
	    public void submit() {
	        getSelf().submit();
	    }

	    protected SelenideElement findElementByKey(String key) {
	        ElementsCollection elements = getSelf().findAll(By.name(key));
	        if (elements.isEmpty()) {
	            return null;
	        }
	        return elements.get(0);
	    }

	    protected void fillElement(SelenideElement element, Object value) {
	        if (value == null) {
	            return;
	        }

	        if (isInput(element)) {
	            String inputType = element.getAttribute("type");
	            if (inputType == null || inputType.equals(TEXT_INPUT_TYPE) || inputType.equals(PASSWORD_INPUT_TYPE)) {
	                element.sendKeys(value.toString());
	            } else if (inputType.equals(CHECKBOX_TYPE)) {
	                SelenideCheckBox checkBox = new SelenideCheckBox(element);
	                checkBox.set(Boolean.parseBoolean(value.toString()));
	            } else if (inputType.equals(RADIO_TYPE)) {
	                SelenideRadio radio = new SelenideRadio(element);
	                radio.selectByValue(value.toString());
	            }
	        } else if (isSelect(element)) {
	            SelenideSelect select = new SelenideSelect(element);
	            select.selectByValue(value.toString());
	        } else if (isTextArea(element)) {
	            element.sendKeys(value.toString());
	        }
	    }

	    private boolean isInput(SelenideElement element) {
	        return "input".equals(element.getTagName());
	    }

	    private boolean isSelect(SelenideElement element) {
	        return "select".equals(element.getTagName());
	    }

	    private boolean isTextArea(SelenideElement element) {
	        return "textarea".equals(element.getTagName());
	    }

}
