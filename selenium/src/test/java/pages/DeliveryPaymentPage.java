package pages;

import elements.*;

import static config.DeliveryPaymentPageConstants.*;
import static utils.BrowserHelper.*;
import static utils.WaitHelper.*;

public class DeliveryPaymentPage {

    public DeliveryPaymentPage open() {
        openUrl(DELIVERY_PAYMENT_URL);
        waitForUrl(DELIVERY_PAYMENT_URL);
        return this;
    }

    public DeliveryPaymentPageElements getDeliveryPaymentElements() {
        return new DeliveryPaymentPageElements();
    }
}