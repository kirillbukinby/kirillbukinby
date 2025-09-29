package pages;

import elements.OrdersHistoryPageElements;

import static config.OrdersHistoryPageConstants.*;
import static utils.BrowserHelper.*;
import static utils.WaitHelper.*;

public class OrdersHistoryPage {

    public OrdersHistoryPage open() {
        openUrl(ORDERS_HISTORY_URL);
        waitForUrl(ORDERS_HISTORY_URL);
        return this;
    }

    public OrdersHistoryPageElements getOrdersHistoryElements() {
        return new OrdersHistoryPageElements();
    }
}