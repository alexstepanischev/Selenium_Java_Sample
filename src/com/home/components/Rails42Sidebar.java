package com.home.components;

import org.openqa.selenium.support.FindBy;

import com.home.elements.SelenideLink;

import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Sidebar menu")
@Block(@FindBy(css = ".sidebar-nav"))
public class Rails42Sidebar extends HtmlElement {
	
	@FindBy(linkText="Books")
	public SelenideLink booksLink;

}
