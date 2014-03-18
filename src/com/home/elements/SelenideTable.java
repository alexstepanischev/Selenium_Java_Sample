package com.home.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.htmlelements.exceptions.HtmlElementsException;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

import ch.lambdaj.Lambda;
import ch.lambdaj.function.convert.Converter;

import static ch.lambdaj.Lambda.convert;
import static ch.lambdaj.Lambda.convertMap;

import static com.home.elements.SelenideTable.ListConverter.toListsConvertingEachItem;
import static com.home.elements.SelenideTable.MapConverter.toMapsConvertingEachValue;
import static com.home.elements.SelenideTable.WebElementToTextConverter.toText;
import static com.home.elements.SelenideTable.WebElementToTextConverter.toTextValues;

public class SelenideTable extends ElementsContainer {
	
	public SelenideTable(){
		
	}
	
	public SelenideTable(SelenideElement element) {
		setSelf(element);
	}
	
	 /**
     * Returns table heading elements (contained in "th" tags).
     *
     * @return List with table heading elements.
     */
    public ElementsCollection getHeadings() {
        return getSelf().findAll(By.xpath(".//th"));
    }

    /**
     * Returns text values of table heading elements (contained in "th" tags).
     *
     * @return List with text values of table heading elements.
     */
    public List<String> getHeadingsAsString() {
        return convert(getHeadings(), toTextValues());
    }

    /**
     * Returns table cell elements grouped by rows.
     *
     * @return List where each item is a table row.
     */
    public List<List<SelenideElement>> getRows() {
        List<List<SelenideElement>> rows = new ArrayList<List<SelenideElement>>();
        List<SelenideElement> rowElements = getSelf().findAll(By.xpath(".//tr"));
        for (SelenideElement rowElement : rowElements) {
            rows.add(rowElement.findAll(By.xpath(".//td")));
        }
        return rows;
    }

    /**
     * Returns text values of table cell elements grouped by rows.
     *
     * @return List where each item is text values of a table row.
     */
    public List<List<String>> getRowsAsString() {
        return convert(getRows(), toListsConvertingEachItem(toTextValues()));
    }

    /**
     * Returns table cell elements grouped by columns.
     *
     * @return List where each item is a table column.
     */
    public List<List<SelenideElement>> getColumns() {
        List<List<SelenideElement>> columns = new ArrayList<List<SelenideElement>>();
        List<List<SelenideElement>> rows = getRows();

        if (rows.isEmpty()) {
            return columns;
        }

        int columnsNumber = rows.get(0).size();
        for (int i = 0; i < columnsNumber; i++) {
            List<SelenideElement> column = new ArrayList<SelenideElement>();
            for (List<SelenideElement> row : rows) {
                column.add(row.get(i));
            }
            columns.add(column);
        }

        return columns;
    }

    /**
     * Returns text values of table cell elements grouped by columns.
     *
     * @return List where each item is text values of a table column.
     */
    public List<List<String>> getColumnsAsString() {
        return convert(getColumns(), toListsConvertingEachItem(toTextValues()));
    }

    /**
     * Returns table cell element at i-th row and j-th column.
     *
     * @param i Row number
     * @param j Column number
     * @return Cell element at i-th row and j-th column.
     */
    public SelenideElement getCellAt(int i, int j) {
        return getRows().get(i).get(j);
    }

    /**
     * Returns list of maps where keys are table headings and values are table row elements.
     */
    public List<Map<String, SelenideElement>> getRowsMappedToHeadings() {
        return getRowsMappedToHeadings(getHeadingsAsString());
    }

    /**
     * Returns list of maps where keys are passed headings and values are table row elements.
     *
     * @param headings List containing strings to be used as table headings.
     */
    public List<Map<String, SelenideElement>> getRowsMappedToHeadings(List<String> headings) {
        List<Map<String, SelenideElement>> rowsMappedToHeadings = new ArrayList<Map<String, SelenideElement>>();
        List<List<SelenideElement>> rows = getRows();

        if (rows.isEmpty()) {
            return rowsMappedToHeadings;
        }

        for (List<SelenideElement> row : rows) {
            if (row.size() != headings.size()) {
                throw new HtmlElementsException("Headings count is not equal to number of cells in row");
            }

            Map<String, SelenideElement> rowToHeadingsMap = new HashMap<String, SelenideElement>();
            int cellNumber = 0;
            for (String heading : headings) {
                rowToHeadingsMap.put(heading, row.get(cellNumber));
                cellNumber++;
            }
            rowsMappedToHeadings.add(rowToHeadingsMap);
        }

        return rowsMappedToHeadings;
    }

    /**
     * Same as {@link #getRowsMappedToHeadings()} but retrieves text from row elements.
     */
    public List<Map<String, String>> getRowsAsStringMappedToHeadings() {
        return getRowsAsStringMappedToHeadings(getHeadingsAsString());
    }

    /**
     * Same as {@link #getRowsMappedToHeadings(java.util.List)} but retrieves text from row elements.
     */
    public List<Map<String, String>> getRowsAsStringMappedToHeadings(List<String> headings) {
        return convert(getRowsMappedToHeadings(headings), toMapsConvertingEachValue(toText()));
    }


    /* Inner utility converters */

    /**
     * Converts {@link WebElement} to text contained in it
     */
    static final class WebElementToTextConverter implements Converter<WebElement, String> {

        public static Converter<WebElement, String> toText() {
            return new WebElementToTextConverter();
        }

        public static Converter<WebElement, String> toTextValues() {
            return new WebElementToTextConverter();
        }

        private WebElementToTextConverter() {
        }

        @Override
        public String convert(WebElement element) {
            return element.getText();
        }
    }

    /**
     * Converts {@code List&lt;F&gt;} to {@code List&lt;T&gt;} by applying specified converter to each list element.
     */
    static final class ListConverter<F, T> implements Converter<List<F>, List<T>> {
        private final Converter<F, T> itemsConverter;

        public static <F, T> Converter<List<F>, List<T>> toListsConvertingEachItem(Converter<F, T> itemsConverter) {
            return new ListConverter<F, T>(itemsConverter);
        }

        private ListConverter(Converter<F, T> itemsConverter) {
            this.itemsConverter = itemsConverter;
        }

        @Override
        public List<T> convert(List<F> list) {
            return Lambda.convert(list, itemsConverter);
        }
    }

    /**
     * Converts {@code Map&lt;K, F&gt;} to {@code Map&lt;K, T&gt;} by applying specified converter to each value
     * in a map.
     */
    static final class MapConverter<K, F, T> implements Converter<Map<K, F>, Map<K, T>> {
        private final Converter<F, T> valueConverter;

        public static <F, T> Converter<Map<String, F>, Map<String, T>> toMapsConvertingEachValue(Converter<F, T> valueConverter) {
            return new MapConverter<String, F, T>(valueConverter);
        }

        private MapConverter(Converter<F, T> valueConverter) {
            this.valueConverter = valueConverter;
        }

        @Override
        public Map<K, T> convert(Map<K, F> map) {
            return convertMap(map, valueConverter);
        }
    }

}
