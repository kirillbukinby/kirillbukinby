package elements;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;
import static utils.ElementHelper.*;

public class MainPageSearchElements {

    private SelenideElement searchInput() { return $x("//input[@type='text']"); }
    private SelenideElement searchButton() { return $x("//button[@type='submit']"); }
    private SelenideElement searchResultsTitle() { return $x("//h1[@class='entry-title ak-container']"); }
    private ElementsCollection searchResults() { return $$x("//*[@class='products columns-4']/li"); }

    // Методы ввода поискового запроса
    public MainPageSearchElements enterSearchQuery(String query) {
        clearAndSetElementValue(searchInput(), query);
        return this;
    }

    // Методы выполнения поиска
    public MainPageSearchElements clickSearchButton() {
        clickElement(searchButton());
        return this;
    }

    public MainPageSearchElements performSearch(String query) {
        return enterSearchQuery(query)
                .clickSearchButton();
    }

    // Методы получения результатов поиска
    public String getSearchResultsTitle() {
        return getElementText(searchResultsTitle());
    }

    public int getSearchResultsCount() {
        return getElementsCount(searchResults());
    }
}