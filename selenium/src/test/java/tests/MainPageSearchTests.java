package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static config.MainPageSearchConstants.*;
import static utils.AssertHelper.*;

public class MainPageSearchTests extends BaseDriver {

    private MainPageSearchElements searchElements;

    @BeforeEach
    public void setUpTest() {
        searchElements = mainPage.open().getSearchElements();
    }

    @Test
    @DisplayName("Проверка поиска по сайту")
    public void shouldSearchForProducts() {
        // Act
        searchElements.performSearch(SEARCH_INPUT_PIZZA);

        // Assert
        assertAll(
                () -> assertTextEquals(SEARCH_RESULT_TITLE_PIZZA, searchElements.getSearchResultsTitle(), ERROR_SEARCH_TITLE_MISMATCH),
                () -> assertTrue(searchElements.hasSearchResults(), ERROR_NO_SEARCH_RESULTS)
        );
    }

    @ParameterizedTest(name = "Проверка поиска для запроса: {0}")
    @CsvSource({
            SEARCH_INPUT_PIZZA + ", " + SEARCH_RESULT_TITLE_PIZZA,
            SEARCH_INPUT_DESSERT + ", " + SEARCH_RESULT_TITLE_DESSERT,
            SEARCH_INPUT_DRINK + ", " + SEARCH_RESULT_TITLE_DRINK
    })
    @DisplayName("Параметризированная проверка поиска")
    public void shouldSearchForDifferentQueries(String query, String expectedTitle) {
        // Act
        searchElements.performSearch(query);

        // Assert
        assertAll(
                () -> assertTextEquals(expectedTitle, searchElements.getSearchResultsTitle(), ERROR_SEARCH_TITLE_MISMATCH),
                () -> assertTrue(searchElements.hasSearchResults(), String.format(ERROR_NO_RESULTS_FOR_QUERY, query))
        );
    }

    @ParameterizedTest(name = "Проверка что заголовок содержит: {1}")
    @CsvSource({
            SEARCH_INPUT_PIZZA + ", ПИЦЦА",
            SEARCH_INPUT_DESSERT + ", ДЕСЕРТ",
            SEARCH_INPUT_DRINK + ", НАПИТОК"
    })
    @DisplayName("Проверка содержания текста в заголовке результатов поиска")
    public void shouldSearchTitleContainExpectedText(String query, String expectedTitlePart) {
        // Act
        searchElements.performSearch(query);

        // Assert
        assertAll(
                () -> assertTextContainsIgnoreCase(expectedTitlePart, searchElements.getSearchResultsTitle(), ERROR_SEARCH_TITLE_DOES_NOT_CONTAIN),
                () -> assertTrue(searchElements.hasSearchResults(), String.format(ERROR_NO_RESULTS_FOR_QUERY, query))
        );
    }
}