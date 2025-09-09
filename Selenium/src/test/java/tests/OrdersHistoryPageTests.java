package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static config.OrdersHistoryPageConstants.*;
import static utils.AssertHelper.*;

public class OrdersHistoryPageTests extends BaseDriver {

    private OrdersHistoryPageElements ordersHistoryElements;

    @BeforeEach
    public void setUpTest() {
        myAccountPage.open()
                .getAccountElements()
                .login()
                .navigateToMainPage();

        ordersHistoryElements = ordersHistoryPage.open().getOrdersHistoryElements();
    }

    @Test
    @DisplayName("Проверка что последний заказ был на сумму 435,00₽")
    public void shouldShowLastOrderAmount() {
        // Assert
        assertTextContains(LAST_ORDER_AMOUNT, ordersHistoryElements.getLastOrderAmount(), ERROR_ORDER_AMOUNT_MISMATCH);
    }

    @Test
    @DisplayName("Проверка отображения таблицы заказов")
    public void shouldDisplayOrdersTable() {
        // Assert
        assertElementDisplayed(ordersHistoryElements.isOrdersTableDisplayed(), ERROR_ORDERS_TABLE_NOT_DISPLAYED);
    }

    @Test
    @DisplayName("Проверка наличия заказов в истории")
    public void shouldHaveOrdersInHistory() {
        // Assert
        assertResultsNotEmpty(ordersHistoryElements.getOrdersCount(), ERROR_NO_ORDERS_IN_HISTORY);
    }

    @Test
    @DisplayName("Проверка структуры таблицы заказов")
    public void shouldHaveCorrectTableStructure() {
        // Assert
        assertAll(
                () -> assertListContainsIgnoreCase(ORDER_COLUMN, ordersHistoryElements.getTableHeaderTexts(), ERROR_ORDER_COLUMN_NOT_FOUND),
                () -> assertListContainsIgnoreCase(DATE_COLUMN, ordersHistoryElements.getTableHeaderTexts(), ERROR_DATE_COLUMN_NOT_FOUND),
                () -> assertListContainsIgnoreCase(STATUS_COLUMN, ordersHistoryElements.getTableHeaderTexts(), ERROR_STATUS_COLUMN_NOT_FOUND),
                () -> assertListContainsIgnoreCase(AMOUNT_COLUMN, ordersHistoryElements.getTableHeaderTexts(), ERROR_AMOUNT_COLUMN_NOT_FOUND),
                () -> assertListContainsIgnoreCase(ACTIONS_COLUMN, ordersHistoryElements.getTableHeaderTexts(), ERROR_ACTIONS_COLUMN_NOT_FOUND)
        );
    }

    @Test
    @DisplayName("Проверка статусов заказов")
    public void shouldHaveDifferentOrderStatuses() {
        // Assert
        assertAll(
                () -> assertResultsNotEmpty(ordersHistoryElements.getStatusCount(), ERROR_MULTIPLE_STATUSES_NOT_FOUND),
                () -> assertListContainsIgnoreCase(PROCESSING_STATUS, ordersHistoryElements.getAllOrderStatuses(), ERROR_PROCESSING_STATUS_NOT_FOUND),
                () -> assertListContainsIgnoreCase(ON_HOLD_STATUS, ordersHistoryElements.getAllOrderStatuses(), ERROR_ON_HOLD_STATUS_NOT_FOUND)
        );
    }

    @Test
    @DisplayName("Проверка навигации по страницам заказов")
    public void shouldHavePagination() {
        // Assert
        assertElementDisplayed(ordersHistoryElements.isPaginationDisplayed(), ERROR_PAGINATION_NOT_DISPLAYED);
    }

    @Test
    @DisplayName("Проверка перехода к деталям заказа")
    public void shouldNavigateToOrderDetails() {
        // Act
        ordersHistoryElements.viewFirstOrderDetails();

        // Assert
        assertUrlContains(VIEW_ORDER_URL_PART, ERROR_ORDER_DETAILS_NOT_NAVIGATED);
    }

    @Test
    @DisplayName("Проверка формата дат заказов")
    public void shouldHaveCorrectDateFormats() {
        // Assert
        assertAllTextsMatchPattern(DATE_PATTERN, ordersHistoryElements.getAllOrderDates(), ERROR_INVALID_DATE_FORMAT);
    }

    @Test
    @DisplayName("Проверка сумм заказов")
    public void shouldHaveCorrectOrderAmounts() {
        // Assert
        assertAllTextsMatchPattern(AMOUNT_PATTERN, ordersHistoryElements.getAllOrderAmounts(), ERROR_INVALID_AMOUNT_FORMAT);
    }

    @ParameterizedTest
    @ValueSource(strings = {PROCESSING_STATUS, ON_HOLD_STATUS})
    @DisplayName("Параметризированная проверка статусов заказов")
    public void shouldHaveOrderWithStatus(String status) {
        // Assert
        assertAll(
                () -> assertResultsNotEmpty(ordersHistoryElements.getOrdersCount(), ERROR_MULTIPLE_STATUSES_NOT_FOUND),
                () -> assertListContainsIgnoreCase(status, ordersHistoryElements.getAllOrderStatuses(), ERROR_ORDER_STATUS_NOT_FOUND + ": " + status)
        );
    }

    @Test
    @DisplayName("Проверка номеров заказов")
    public void shouldHaveValidOrderNumbers() {
        // Assert
        assertAll(
                () -> assertResultsNotEmpty(ordersHistoryElements.getAllOrderNumbers(), ERROR_EMPTY_ORDER_NUMBERS_LIST),
                () -> assertAllTextsMatchPattern(ORDER_NUMBER_PATTERN, ordersHistoryElements.getAllOrderNumbersTexts(), ERROR_INVALID_ORDER_NUMBER_FORMAT)
        );
    }
}