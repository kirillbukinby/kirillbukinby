package elements;

import com.codeborne.selenide.*;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;
import static utils.ElementHelper.*;

public class OrdersHistoryPageElements {

    private SelenideElement ordersTable() {
        return $x("//table[@class='woocommerce-orders-table woocommerce-MyAccount-orders shop_table shop_table_responsive my_account_orders account-orders-table']");
    }

    private ElementsCollection orderRows() {
        return $$x("//tr[@class='woocommerce-orders-table__row woocommerce-orders-table__row--status-processing order']");
    }

    private ElementsCollection orderAmounts() {
        return $$x("//span[@class='woocommerce-Price-amount amount']");
    }

    private ElementsCollection orderStatuses() {
        return $$x("//td[@class='woocommerce-orders-table__cell woocommerce-orders-table__cell-order-status']");
    }

    private ElementsCollection orderDates() {
        return $$x("//td[@class='woocommerce-orders-table__cell woocommerce-orders-table__cell-order-date']");
    }

    private ElementsCollection viewDetailsButtons() {
        return $$x("//a[@class='woocommerce-button button view']");
    }

    private SelenideElement pagination() {
        return $x("//div[@class='woocommerce-pagination woocommerce-pagination--without-numbers woocommerce-Pagination']");
    }

    private ElementsCollection tableHeaders() {
        return $$x("//table[contains(@class, 'woocommerce-orders-table')]//thead//th");
    }

    private ElementsCollection orderNumbers() {
        return $$x("//td[@class='woocommerce-orders-table__cell woocommerce-orders-table__cell-order-number']");
    }

    // Методы получения количества заказов
    public int getOrdersCount() {
        return getElementsCount(orderRows());
    }

    public int getStatusCount() {
        return getElementsCount(orderStatuses());
    }

    public int getAllOrderNumbers() {
        return getElementsCount(orderNumbers());
    }

    // Методы получения текстовой информации
    public String getLastOrderAmount() {
        return getElementTextByNumber(orderAmounts(), 1);
    }

    public List<String> getAllOrderAmounts() {
        return getElementsTexts(orderAmounts());
    }

    public List<String> getAllOrderStatuses() {
        return getElementsTexts(orderStatuses());
    }

    public List<String> getAllOrderDates() {
        return getElementsTexts(orderDates());
    }

    public List<String> getAllOrderNumbersTexts() {
        return getElementsTexts(orderNumbers());
    }

    public List<String> getTableHeaderTexts() {
        return getElementsTexts(tableHeaders());
    }

    // Методы взаимодействия с заказами
    public OrdersHistoryPageElements viewFirstOrderDetails() {
        clickElementByNumber(viewDetailsButtons(),1);
        return this;
    }

    // Геттеры элементов
    public SelenideElement getOrdersTable() {
        return ordersTable();
    }

    public SelenideElement getPagination() {
        return pagination();
    }
}