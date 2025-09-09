package pages;

import elements.*;

import static config.OrdersHistoryPageConstants.*;
import static utils.BrowserHelper.*;

public class OrdersHistoryPage {

    public OrdersHistoryPage open() {
        openUrl(ORDERS_HISTORY_URL);
        return this;
    }

    public OrdersHistoryPageElements getOrdersHistoryElements() {
        return new OrdersHistoryPageElements();
    }

}