package pages;

import elements.PizzaPageElements;

import static config.PizzaPageConstants.*;
import static utils.BrowserHelper.*;
import static utils.WaitHelper.*;

public class PizzaPage {

    public PizzaPage open() {
        openUrl(PIZZA_CATEGORY_URL);
        waitForUrl(PIZZA_CATEGORY_URL);
        return this;
    }

    public PizzaPageElements getPizzaElements() {
        return new PizzaPageElements();
    }
}