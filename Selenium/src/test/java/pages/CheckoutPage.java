package pages;

import elements.*;

import static config.CheckoutPageConstants.*;
import static utils.BrowserHelper.*;
import static utils.WaitHelper.*;

public class CheckoutPage {

    public CheckoutPage open() {
        openUrl(CHECKOUT_URL);
        waitForUrl(CHECKOUT_URL);
        return this;
    }

    public CheckoutPageElements getCheckoutElements() {
        return new CheckoutPageElements();
    }
}