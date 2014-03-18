package com.home.pages;

import org.openqa.selenium.support.FindBy;

import com.home.core.BasePage;
import com.home.elements.SelenideSelect;

public class Rails42BookForm extends BasePage {
	
	@FindBy(name="author_id")
	public SelenideSelect authorSelect;
	
	@FindBy(name="book_genre_ids")
	public SelenideSelect genreSelect;

}
