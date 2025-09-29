package elements;

import org.openqa.selenium.*;

import static utils.ActionHelper.*;
import static utils.WaitHelper.*;
import static utils.WaitHelper.*;

public class MainPageSearchElements {

    private static final By SEARCH_INPUT = By.xpath("//input[@type='text']");
    private static final By SEARCH_BUTTON = By.xpath("//button[@type='submit']");
    private static final By SEARCH_RESULTS_TITLE = By.xpath("//h1[@class='entry-title ak-container']");
    private static final By SEARCH_RESULTS = By.xpath("//*[@class='products columns-4']/li");

    // Методы работы с поиском
    public MainPageSearchElements enterSearchQuery(String query) {
        WebElement searchInput = waitForVisibility(SEARCH_INPUT);
        searchInput.clear();
        searchInput.sendKeys(query);
        return this;
    }

    public MainPageSearchElements clickSearchButton() {
        moveToAndClickElement(SEARCH_BUTTON);
        return this;
    }

    public MainPageSearchElements performSearch(String query) {
        return enterSearchQuery(query)
                .clickSearchButton();
    }

    // Методы получения информации о результатах поиска
    public String getSearchResultsTitle() {
        return waitForVisibility(SEARCH_RESULTS_TITLE).getText();
    }

    public int getSearchResultsCount() {
        return waitForAllElementsPresent(SEARCH_RESULTS).size();
    }

    public boolean hasSearchResults() {
        return getSearchResultsCount() > 0;
    }
}