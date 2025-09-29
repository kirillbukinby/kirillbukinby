package pages;

import elements.*;

import static config.PizzaPageConstants.*;
import static utils.BrowserHelper.*;

public class PizzaPage {

    public PizzaPage open() {
        openUrl(PIZZA_CATEGORY_URL);
        return this;
    }

    public PizzaPageElements getPizzaElements() {
        return new PizzaPageElements();
    }
}