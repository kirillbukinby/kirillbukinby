package pages;

import elements.*;

import static config.CartPageConstants.*;
import static utils.BrowserHelper.*;

public class CartPage {

    public CartPage open() {
        openUrl(CART_URL);
        return this;
    }

    public CartPageElements getCartElements() {
        return new CartPageElements();
    }
}