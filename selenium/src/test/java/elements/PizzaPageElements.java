package elements;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import static utils.ActionHelper.*;
import static utils.JavaScriptHelper.*;
import static utils.WaitHelper.*;

public class PizzaPageElements {

    // Локаторы сортировки
    private static final By SORT_DROPDOWN = By.xpath("//select[@class='orderby']");

    // Локаторы фильтра по цене
    private static final By PRICE_FILTER_BUTTON = By.xpath("//button[@class='button']");
    private static final By PRICE_RANGE_LABEL = By.xpath("//div[@class='price_label']");

    // Локаторы пицц
    private static final By FIRST_PIZZA_ADD_TO_CART = By.xpath("(//a[@class='button product_type_simple add_to_cart_button ajax_add_to_cart'])[1]");
    private static final By FIRST_PIZZA_NAME = By.xpath("(//div[@class='collection_desc clearfix']//h3)[1]");
    private static final By FIRST_PIZZA_PRICE = By.xpath("(//span[@class='woocommerce-Price-amount amount']//bdi)[1]");

    // Локаторы корзины
    private static final By CART_ITEM_NAME = By.xpath("//*[@class='product-name']/a");
    private static final By CART_ITEM_PRICE = By.xpath("//*[@class='product-price']//bdi");

    // Локаторы меню
    private static final By CART_MENU_ITEM = By.xpath("//li[@id='menu-item-29']/a");

    // Методы для сортировки
    public PizzaPageElements selectSorting(String optionValue) {
        WebElement dropdown = waitForVisibility(SORT_DROPDOWN);
        Select select = new Select(dropdown);
        select.selectByValue(optionValue);
        return this;
    }

    public String getSelectedSortOptionValue() {
        WebElement dropdown = waitForVisibility(SORT_DROPDOWN);
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getAttribute("value");
    }

    // Методы для фильтрации по цене
    public PizzaPageElements filterByPrice(int minPrice, int maxPrice) {
        setPriceAndApply(minPrice, maxPrice);
        moveToAndClickElement(PRICE_FILTER_BUTTON);
        return this;
    }

    public PizzaPageElements filterByDefaultPriceRange() {
        return filterByPrice(450, 490);
    }

    public String getPriceRangeText() {
        return waitForVisibility(PRICE_RANGE_LABEL).getText();
    }

    // Методы для работы с пиццами
    public PizzaPageElements addFirstPizzaToCart() {
        moveToAndClickElement(FIRST_PIZZA_ADD_TO_CART);
        return this;
    }

    public PizzaPageElements goToCart() {
        moveToAndClickElement(CART_MENU_ITEM);
        return this;
    }

    public String getFirstPizzaName() {
        String text = waitForVisibility(FIRST_PIZZA_NAME).getText();
        return normalizeQuotes(text);
    }

    public String getFirstPizzaPrice() {
        return waitForVisibility(FIRST_PIZZA_PRICE).getText();
    }

    public String getCartItemName() {
        return waitForVisibility(CART_ITEM_NAME).getText();
    }

    public String getCartItemPrice() {
        return waitForVisibility(CART_ITEM_PRICE).getText();
    }

    private String normalizeQuotes(String text) {
        return text.replace('«', '"').replace('»', '"');
    }

}