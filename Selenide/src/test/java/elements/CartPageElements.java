package elements;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;
import static utils.ElementHelper.*;

public class CartPageElements {
    private SelenideElement quantityInput() { return $x("//input[@type='number']"); }
    private SelenideElement updateCartButton() { return $x("//button[@name='update_cart']"); }
    private SelenideElement productSubtotal() { return $x("(//span[@class='woocommerce-Price-amount amount'])[2]"); }
    private SelenideElement cartSubtotal() { return $x("(//span[@class='woocommerce-Price-amount amount'])[3]"); }
    private SelenideElement checkoutButton() { return $x("//a[@class='checkout-button button alt wc-forward']"); }
    private SelenideElement emptyCartMessage() { return $x("//p[@class='cart-empty woocommerce-info']"); }
    private SelenideElement loadingOverlay() { return $x("//div[@class='blockUI blockOverlay']"); }

    // Методы получения информации о корзине
    public String getQuantityValue() {
        return getElementValue(quantityInput());
    }

    public String getProductSubtotal() {
        return getElementText(productSubtotal());
    }

    public String getCartSubtotal() {
        return getElementText(cartSubtotal());
    }

    public String getEmptyCartMessage() {
        return getElementText(emptyCartMessage());
    }

    // Методы управления количеством товаров
    public CartPageElements setQuantity(String quantity) {
        setElementValueWithClear(quantityInput(), quantity);
        return this;
    }

    public CartPageElements updateCart() {
        clickElement(updateCartButton());
        waitForElementToAppear(loadingOverlay());
        waitForElementToDisappear(loadingOverlay());
        return this;
    }

    // Методы навигации
    public CartPageElements proceedToCheckout() {
        clickElement(checkoutButton());
        return this;
    }

    // Комбинированные методы операций
    public CartPageElements updateProductQuantity(String quantity) {
        return setQuantity(quantity).updateCart();
    }
}