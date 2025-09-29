package elements;

import org.openqa.selenium.*;
import java.util.*;
import java.util.stream.*;

import static utils.ElementHelper.*;

public class OrdersHistoryPageElements {

    private static final By ORDERS_TABLE = By.xpath("//table[contains(@class, 'woocommerce-orders-table')]");
    private static final By ORDER_ROWS = By.xpath("//table[contains(@class, 'woocommerce-orders-table')]//tr[contains(@class, 'woocommerce-orders-table__row')]");
    private static final By ORDER_AMOUNTS = By.xpath("//table[contains(@class, 'woocommerce-orders-table')]//td[contains(@class, 'order-total')]");
    private static final By ORDER_STATUSES = By.xpath("//table[contains(@class, 'woocommerce-orders-table')]//td[contains(@class, 'order-status')]");
    private static final By ORDER_DATES = By.xpath("//table[contains(@class, 'woocommerce-orders-table')]//td[contains(@class, 'order-date')]");
    private static final By VIEW_DETAILS_BUTTONS = By.xpath("//table[contains(@class, 'woocommerce-orders-table')]//a[contains(@class, 'view')]");
    private static final By PAGINATION = By.xpath("//div[contains(@class, 'woocommerce-pagination')]");
    private static final By TABLE_HEADERS = By.xpath("//table[contains(@class, 'woocommerce-orders-table')]//thead//th");
    private static final By ORDER_NUMBERS = By.xpath("//table[contains(@class, 'woocommerce-orders-table')]//td[contains(@class, 'order-number')]//a");

    // Методы получения количества заказов
    public int getOrdersCount() {
        return getElementsCount(ORDER_ROWS);
    }

    public int getStatusCount() {
        return getElementsCount(ORDER_STATUSES);
    }

    public int getAllOrderNumbers() {
        return getElementsCount(ORDER_NUMBERS);
    }

    // Методы получения текстовой информации
    public String getLastOrderAmount() {
        List<String> amounts = getAllOrderAmounts();
        return amounts.isEmpty() ? "" : amounts.get(0);
    }

    public List<String> getAllOrderAmounts() {
        return getElementsTexts(ORDER_AMOUNTS).stream()
                .map(text -> text.split(" ")[0])
                .collect(Collectors.toList());
    }

    public List<String> getAllOrderStatuses() {
        return getElementsTexts(ORDER_STATUSES).stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getAllOrderDates() {
        return getElementsTexts(ORDER_DATES);
    }

    public List<String> getAllOrderNumbersTexts() {
        return getElementsTexts(ORDER_NUMBERS).stream()
                .map(text -> text.replace("№", ""))
                .collect(Collectors.toList());
    }

    public List<String> getTableHeaderTexts() {
        return getElementsTexts(TABLE_HEADERS);
    }

    // Методы взаимодействия с заказами
    public OrdersHistoryPageElements viewFirstOrderDetails() {
        if (getElementsCount(VIEW_DETAILS_BUTTONS) > 0) {
            clickElementByIndex(VIEW_DETAILS_BUTTONS, 0);
        }
        return this;
    }

    // Геттеры элементов для проверок отображения
    public boolean isOrdersTableDisplayed() {
        return isElementDisplayed(ORDERS_TABLE);
    }

    public boolean isPaginationDisplayed() {
        return isElementDisplayed(PAGINATION);
    }

}