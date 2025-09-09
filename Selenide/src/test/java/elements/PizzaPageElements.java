package elements;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;
import static utils.ElementHelper.*;
import static utils.TextHelper.*;
import static utils.JavaScriptHelper.*;

public class PizzaPageElements {

    private SelenideElement sortDropdown() { return $x("//select[@class='orderby']"); }
    private SelenideElement priceFilterButton() { return $x("//button[@class='button']"); }
    private SelenideElement priceRangeLabel() { return $x("//div[@class='price_label']"); }
    private SelenideElement firstPizzaAddToCart() { return $x("(//a[contains(@class, 'add_to_cart_button')])[1]"); }
    private SelenideElement firstPizzaName() { return $x("(//div[contains(@class, 'collection_desc')]//h3)[1]"); }
    private SelenideElement firstPizzaPrice() { return $x("(//span[contains(@class, 'woocommerce-Price-amount')]//bdi)[1]"); }
    private SelenideElement cartItemName() { return $x("//td[@class='product-name']"); }
    private SelenideElement cartItemPrice() { return $x("//td[@class='product-price']"); }
    private SelenideElement cartMenuItem() { return $x("//li[@id='menu-item-29']"); }

    // Методы сортировки
    public PizzaPageElements selectSorting(String optionValue) {
        selectDropdownOption(sortDropdown(), optionValue);
        return this;
    }

    public String getSelectedSortOptionValue() {
        return getSelectedOptionValue(sortDropdown());
    }

    // Методы фильтрации по цене
    public PizzaPageElements filterByPrice(int minPrice, int maxPrice) {
        setPriceFilterValues(minPrice, maxPrice);
        clickElement(priceFilterButton());
        return this;
    }

    public PizzaPageElements filterByDefaultPriceRange() {
        return filterByPrice(450, 490);
    }

    public String getPriceRangeText() {
        return getElementText(priceRangeLabel());
    }

    // Методы работы с товарами
    public PizzaPageElements addFirstPizzaToCart() {
        clickElement(firstPizzaAddToCart());
        return this;
    }

    public String getFirstPizzaName() {
        String text = getElementText(firstPizzaName());
        return normalizeQuotes(text);
    }

    public String getFirstPizzaPrice() {
        return getElementText(firstPizzaPrice());
    }

    // Методы работы с корзиной
    public PizzaPageElements goToCart() {
        clickElement(cartMenuItem());
        return this;
    }

    public String getCartItemName() {
        return getElementText(cartItemName());
    }

    public String getCartItemPrice() {
        return getElementText(cartItemPrice());
    }
}