package com.home.connector;

import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.getElementName;
import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.getGenericParameterClass;
import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.isHtmlElement;
import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.isHtmlElementList;
import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.isTypifiedElement;
import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.isTypifiedElementList;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.SelenideElementListProxy;
import com.codeborne.selenide.impl.SelenideFieldDecorator;
import com.codeborne.selenide.impl.WaitingSelenideElement;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class SelenideHtmlElementDecorator extends DefaultFieldDecorator {
	
	private final SearchContext searchContext;
	
	public SelenideHtmlElementDecorator(SearchContext searchContext) {
		super(new HtmlElementLocatorFactory(searchContext));
		this.searchContext = searchContext;
	}
	
	@Override
	  public Object decorate(ClassLoader loader, Field field) {
	    By selector = new Annotations(field).buildBy();
	    
	    ElementLocator locator = factory.createLocator(field);
        if (locator == null) {
            return null;
        }

        String elementName = getElementName(field);
	    
        if (isTypifiedElement(field)) {
            @SuppressWarnings("unchecked")
            Class<TypifiedElement> typifiedElementClass = (Class<TypifiedElement>) field.getType();
            return decorateTypifiedElement(typifiedElementClass, loader, locator, elementName);
        } else if (isHtmlElement(field)) {
            @SuppressWarnings("unchecked")
            Class<HtmlElement> htmlElementClass = (Class<HtmlElement>) field.getType();
            return decorateHtmlElement(htmlElementClass, loader, locator, elementName);
        } else if (WebElement.class.isAssignableFrom(field.getType())) {
	      return WaitingSelenideElement.wrap(searchContext, selector, 0);
	    } else if (isTypifiedElementList(field)) {
            @SuppressWarnings("unchecked")
            Class<TypifiedElement> typifiedElementClass = (Class<TypifiedElement>) getGenericParameterClass(field);
            return decorateTypifiedElementList(typifiedElementClass, loader, locator, elementName);
        } else if (isHtmlElementList(field)) {
            @SuppressWarnings("unchecked")
            Class<HtmlElement> htmlElementClass = (Class<HtmlElement>) getGenericParameterClass(field);
            return decorateHtmlElementList(htmlElementClass, loader, locator, elementName);
        } else if (ElementsContainer.class.isAssignableFrom(field.getType())) {
	      return createElementsContainer(selector, field);
	    } else if (isDecoratableList(field, ElementsContainer.class)) {
	      return createElementsContainerList(field, selector);
	    } else if (isDecoratableList(field, SelenideElement.class)) {
	      return SelenideElementListProxy.wrap(factory.createLocator(field));
	    }

	    return super.decorate(loader, field);
	  }

	private List<ElementsContainer> createElementsContainerList(Field field, By selector) {
	    try {
	      List<ElementsContainer> result = new ArrayList<ElementsContainer>();
	      Class<?> listType = getListGenericType(field);
	      List<SelenideElement> selfList = SelenideElementListProxy.wrap(factory.createLocator(field));
	      for (SelenideElement element : selfList) {
	        result.add(initElementsContainer(listType, element));
	      }
	      return result;  //To change body of created methods use File | Settings | File Templates.
	    } catch (Exception e) {
	      throw new RuntimeException("Failed to create elements container list for field " + field.getName(), e);
	    }
	  }

	  private ElementsContainer createElementsContainer(By selector, Field field) {
	    try {
	      SelenideElement self = WaitingSelenideElement.wrap(searchContext, selector, 0);
	      return initElementsContainer(field.getType(), self);
	    } catch (Exception e) {
	      throw new RuntimeException("Failed to create elements container for field " + field.getName(), e);
	    }
	  }

	  private ElementsContainer initElementsContainer(Class<?> type, SelenideElement self) throws InstantiationException, IllegalAccessException {
	    ElementsContainer result = (ElementsContainer) type.newInstance();
	    PageFactory.initElements(new SelenideFieldDecorator(self), result);
	    result.setSelf(self);
	    return result;
	  }

	  private boolean isDecoratableList(Field field, Class<?> type) {
	    if (!List.class.isAssignableFrom(field.getType())) {
	      return false;
	    }

	    Class<?> listType = getListGenericType(field);

	    return listType != null && type.isAssignableFrom(listType)
	        && (field.getAnnotation(FindBy.class) != null || field.getAnnotation(FindBys.class) != null);
	  }

	  private Class<?> getListGenericType(Field field) {
	    Type genericType = field.getGenericType();
	    if (!(genericType instanceof ParameterizedType)) return null;

	    return (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
	  }
	  
	  private <T extends TypifiedElement> T decorateTypifiedElement(Class<T> elementClass, ClassLoader loader,
              ElementLocator locator, String elementName) {
		// Create typified element and initialize it with WebElement proxy
		WebElement elementToWrap = HtmlElementFactory.createNamedProxyForWebElement(loader, locator, elementName);
		T typifiedElementInstance = HtmlElementFactory.createTypifiedElementInstance(elementClass, elementToWrap);
		typifiedElementInstance.setName(elementName);
		return typifiedElementInstance;
	  }
	  
	  private <T extends HtmlElement> T decorateHtmlElement(Class<T> elementClass, ClassLoader loader,
	      ElementLocator locator, String elementName) {
		// Create block and initialize it with WebElement proxy
		WebElement elementToWrap = HtmlElementFactory.createNamedProxyForWebElement(loader, locator, elementName);
		T htmlElementInstance = HtmlElementFactory.createHtmlElementInstance(elementClass);
		htmlElementInstance.setWrappedElement(elementToWrap);
		htmlElementInstance.setName(elementName);
		// Recursively initialize elements of the block
		PageFactory.initElements(new SelenideHtmlElementDecorator(elementToWrap), htmlElementInstance);
		return htmlElementInstance;
	  }
	  
	  private <T extends TypifiedElement> List<T> decorateTypifiedElementList(Class<T> elementClass, ClassLoader loader,
              ElementLocator locator, String listName) {
		return HtmlElementFactory.createNamedProxyForTypifiedElementList(elementClass, loader, locator, listName);
	  }

	  private <T extends HtmlElement> List<T> decorateHtmlElementList(Class<T> elementClass, ClassLoader loader,
	      ElementLocator locator, String listName) {
		  return HtmlElementFactory.createNamedProxyForHtmlElementList(elementClass, loader, locator, listName);
	  }

}
