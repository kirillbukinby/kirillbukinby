package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;

import static config.CartPageConstants.*;
import static utils.AssertHelper.*;

public class CartPageTests extends BaseDriver {
    private PizzaPageElements pizzaElements;
    private CartPageElements cartElements;

    @BeforeEach
    public void setUpTest() {
        cartElements = cartPage.getCartElements();
    }

    private void addProductToCart() {
        pizzaElements = pizzaPage.open().getPizzaElements();
        pizzaElements.addFirstPizzaToCart().goToCart();
        cartElements = cartPage.getCartElements();
    }

    @Test
    @DisplayName("Проверка изменения количества товара в корзине")
    public void shouldChangeProductQuantity() {
        // Arrange
        addProductToCart();

        // Act
        cartElements.updateProductQuantity(CART_ITEM_QUANTITY_5);

        // Assert
        assertAll(
                () -> assertTextEquals(CART_ITEM_QUANTITY_5, cartElements.getQuantityValue(), ERROR_QUANTITY_NOT_CHANGED + " на 5"),
                () -> assertTextEquals(SUBTOTAL_PRICE_5, cartElements.getProductSubtotal(), ERROR_SUBTOTAL_NOT_MATCH + " 5 товарам"),
                () -> assertTextEquals(SUBTOTAL_PRICE_5, cartElements.getCartSubtotal(), ERROR_CART_SUBTOTAL_NOT_MATCH + " 5 товарам")
        );

        // Act
        cartElements.updateProductQuantity(CART_ITEM_QUANTITY_1);

        // Assert
        assertAll(
                () -> assertTextEquals(CART_ITEM_QUANTITY_1, cartElements.getQuantityValue(), ERROR_QUANTITY_NOT_CHANGED + " на 1"),
                () -> assertTextEquals(SUBTOTAL_PRICE_1, cartElements.getProductSubtotal(), ERROR_SUBTOTAL_NOT_MATCH + " 1 товару"),
                () -> assertTextEquals(SUBTOTAL_PRICE_1, cartElements.getCartSubtotal(), ERROR_CART_SUBTOTAL_NOT_MATCH + " 1 товару")
        );
    }

    @Test
    @DisplayName("Проверка перехода к оформлению заказа")
    public void shouldProceedToCheckout() {
        // Arrange
        addProductToCart();

        // Act
        cartElements.proceedToCheckout();

        // Assert
        assertUrlEquals(CHECKOUT_URL, ERROR_NOT_REDIRECTED_TO_CHECKOUT);
    }

    @Test
    @DisplayName("Проверка отображения пустой корзины")
    public void shouldShowEmptyCart() {
        // Act
        cartPage.open();

        // Assert
        assertTextContains(CART_EMPTY, cartElements.getEmptyCartMessage(), ERROR_EMPTY_CART_NOT_DISPLAYED);
    }
}