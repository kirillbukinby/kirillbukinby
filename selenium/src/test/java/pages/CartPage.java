package pages;

import elements.*;

import static config.CartPageConstants.*;
import static utils.BrowserHelper.*;
import static utils.WaitHelper.*;

public class CartPage {

    public CartPage open() {
        openUrl(CART_URL);
        waitForUrl(CART_URL);
        return this;
    }

    public CartPageElements getCartElements() {
        return new CartPageElements();
    }
}