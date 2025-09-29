package elements;

import org.openqa.selenium.*;

import static utils.ActionHelper.*;
import static utils.ScrollHelper.*;
import static utils.WaitHelper.*;

public class CartPageElements {
    private static final By QUANTITY_INPUT = By.xpath("//input[@type='number']");
    private static final By UPDATE_CART_BUTTON = By.xpath("//button[@name='update_cart']");
    private static final By PRODUCT_SUBTOTAL = By.xpath("//td[@class='product-subtotal']//span");
    private static final By CART_SUBTOTAL = By.xpath("//tr[@class='cart-subtotal']//span");
    private static final By CHECKOUT_BUTTON = By.xpath("//a[@class='checkout-button button alt wc-forward']");
    private static final By EMPTY_CART_MESSAGE = By.xpath("//p[@class='cart-empty woocommerce-info']");
    private static final By LOADING_OVERLAY = By.xpath("//div[@class='blockUI blockOverlay']");

    // Методы получения информации о товарах
    public String getQuantityValue() {
        return waitForVisibility(QUANTITY_INPUT).getAttribute("value");
    }

    public String getProductSubtotal() {
        return waitForVisibility(PRODUCT_SUBTOTAL).getText();
    }

    public String getCartSubtotal() {
        return waitForVisibility(CART_SUBTOTAL).getText();
    }

    public String getEmptyCartMessage() {
        return waitForVisibility(EMPTY_CART_MESSAGE).getText();
    }

    // Методы взаимодействия с корзиной
    public CartPageElements setQuantity(String quantity) {
        WebElement quantityInput = waitForVisibility(QUANTITY_INPUT);
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
        return this;
    }

    public CartPageElements updateCart() {
        moveToAndClickElement(UPDATE_CART_BUTTON);
        waitForVisibility(LOADING_OVERLAY);
        waitForInvisibility(LOADING_OVERLAY);
        return this;
    }

    public CartPageElements proceedToCheckout() {
        scrollToElement(CHECKOUT_BUTTON);
        waitForClickable(CHECKOUT_BUTTON).click();
        return this;
    }

    // Комбинированные методы
    public CartPageElements updateProductQuantity(String quantity) {
        return setQuantity(quantity)
                .updateCart();
    }
}