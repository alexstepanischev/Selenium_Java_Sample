package com.home.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.LocalFileDetector;




import java.io.File;

import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.*;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

public class SelenideFileInput extends ElementsContainer {
	
	public SelenideFileInput(){
		
	}
	
	public SelenideFileInput(SelenideElement element) {
		setSelf(element);
	}
	
	public void setFileToUpload(final String fileName) {
        // Proxy can't be used to check the element class, so find real WebElement
        WebElement fileInputElement = getNotProxiedInputElement();
        // Set local file detector in case of remote driver usage
        if (isOnRemoteWebDriver(fileInputElement)) {
            setLocalFileDetector((RemoteWebElement) fileInputElement);
        }

        String filePath = getFilePath(fileName);
        fileInputElement.sendKeys(filePath);
    }

    /**
     * Submits selected file by simply submitting the whole form, which contains this file input.
     */
    public void submit() {
        getSelf().submit();
    }

    private WebElement getNotProxiedInputElement() {
        return getSelf().findElement(By.xpath("."));
    }

    private void setLocalFileDetector(RemoteWebElement element) {
        element.setFileDetector(new LocalFileDetector());
    }

    private String getFilePath(final String fileName) {
        if (existsInClasspath(fileName)) {
            return getPathForResource(fileName);
        }
        return getPathForSystemFile(fileName);
    }

    private String getPathForResource(final String fileName) {
        return getResourceFromClasspath(fileName).getPath();
    }

    private String getPathForSystemFile(final String fileName) {
        File file = new File(fileName);
        return file.getPath();
    }

}
