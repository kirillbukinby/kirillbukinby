package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static config.PizzaPageConstants.*;
import static utils.AssertionHelper.*;

public class PizzaPageTests extends BaseDriver {
    private PizzaPageElements pizzaElements;

    @BeforeEach
    public void setUpTest() {
        mainPage.open();
        pizzaElements = pizzaPage.open().getPizzaElements();
    }

    @Test
    @DisplayName("Проверка фильтрации пицц по цене")
    public void shouldFilterPizzasByPrice() {
        // Act
        String initialPriceRange = pizzaElements.getPriceRangeText();
        pizzaElements.filterByDefaultPriceRange();
        String filteredPriceRange = pizzaElements.getPriceRangeText();

        // Assert
        assertAll(
                () -> assertTextEquals(INITIAL_PRICE_RANGE, initialPriceRange, ERROR_INITIAL_PRICE_RANGE_MISMATCH),
                () -> assertTextEquals(FILTERED_PRICE_RANGE, filteredPriceRange, ERROR_FILTERED_PRICE_RANGE_MISMATCH)
        );
    }

    @Test
    @DisplayName("Проверка добавления пиццы в корзину")
    public void shouldAddPizzaToCart() {
        // Act
        String expectedPizzaName = pizzaElements.getFirstPizzaName();
        String expectedPizzaPrice = pizzaElements.getFirstPizzaPrice();

        pizzaElements.addFirstPizzaToCart()
                .goToCart();

        String actualPizzaName = pizzaElements.getCartItemName();
        String actualPizzaPrice = pizzaElements.getCartItemPrice();

        // Assert
        assertAll(
                () -> assertUrlContains(CART_URL, ERROR_NOT_REDIRECTED_TO_CART),
                () -> assertTextEquals(expectedPizzaName, actualPizzaName, ERROR_PIZZA_NAME_MISMATCH),
                () -> assertTextEquals(expectedPizzaPrice, actualPizzaPrice, ERROR_PIZZA_PRICE_MISMATCH)
        );
    }

    @ParameterizedTest(name = "Проверка сортировки: {1}")
    @DisplayName("Параметризованная проверка сортировки пицц")
    @CsvSource({
            MENU_ORDER_VALUE + ", " + DEFAULT_SORTING,
            POPULARITY_VALUE + ", " + POPULARITY_SORTING,
            DATE_VALUE + ", " + NEWEST_SORTING,
            PRICE_VALUE + ", " + PRICE_ASC_SORTING,
            PRICE_DESC_VALUE + ", " + PRICE_DESC_SORTING
    })
    public void shouldSortPizzasParameterized(String optionValue, String sortName) {
        // Act
        pizzaElements.selectSorting(optionValue);

        // Assert
        assertTextEquals(optionValue, pizzaElements.getSelectedSortOptionValue(), ERROR_SORTING_NOT_SELECTED + sortName);
    }

    @Test
    @DisplayName("Проверка начального состояния сортировки")
    public void shouldHaveDefaultSortingInitially() {
        // Assert
        assertTextEquals(MENU_ORDER_VALUE, pizzaElements.getSelectedSortOptionValue(), ERROR_DEFAULT_SORTING_NOT_SELECTED);
    }
}
