package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;

import static config.CartPageConstants.*;
import static utils.AssertionHelper.*;

public class CartPageTests extends BaseDriver {
    private CartPageElements cartElements;

    @BeforeEach
    public void setUpTest() {
        cartElements = cartPage.getCartElements();
    }

    private void addProductToCart() {
        PizzaPageElements pizzaElements = pizzaPage.open().getPizzaElements();
        pizzaElements.addFirstPizzaToCart().goToCart();
        cartElements = cartPage.getCartElements();
    }

    @Test
    @DisplayName("Проверка изменения количества товара в корзине")
    public void shouldChangeProductQuantity() {
        // Arrange
        addProductToCart();

        // Act & Assert
        cartElements.updateProductQuantity(CART_ITEM_QUANTITY_5);

        assertAll(
                () -> assertTextEquals(CART_ITEM_QUANTITY_5, cartElements.getQuantityValue(), ERROR_QUANTITY_NOT_CHANGED),
                () -> assertTextEquals(SUBTOTAL_PRICE_5, cartElements.getProductSubtotal(), ERROR_SUBTOTAL_NOT_MATCH),
                () -> assertTextEquals(SUBTOTAL_PRICE_5, cartElements.getCartSubtotal(), ERROR_CART_SUBTOTAL_NOT_MATCH)
        );

        // Act & Assert
        cartElements.updateProductQuantity(CART_ITEM_QUANTITY_1);

        assertAll(
                () -> assertTextEquals(CART_ITEM_QUANTITY_1, cartElements.getQuantityValue(), ERROR_QUANTITY_NOT_CHANGED),
                () -> assertTextEquals(SUBTOTAL_PRICE_1, cartElements.getProductSubtotal(), ERROR_SUBTOTAL_NOT_MATCH),
                () -> assertTextEquals(SUBTOTAL_PRICE_1, cartElements.getCartSubtotal(), ERROR_CART_SUBTOTAL_NOT_MATCH)
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
        assertUrlContains(CHECKOUT_URL, ERROR_NOT_REDIRECTED_TO_CHECKOUT);
    }

    @Test
    @DisplayName("Проверка отображения пустой корзины")
    public void shouldShowEmptyCart() {
        // Act
        cartPage.open();

        // Assert
        assertTextContains(CART_EMPTY, cartElements.getEmptyCartMessage(),
                ERROR_EMPTY_CART_NOT_DISPLAYED);
    }

    @Test
    @DisplayName("Проверка начального состояния корзины с товаром")
    public void shouldHaveCorrectInitialQuantity() {
        // Arrange
        addProductToCart();

        // Assert
        assertAll(
                () -> assertTextEquals(CART_ITEM_QUANTITY_1, cartElements.getQuantityValue(), ERROR_INITIAL_QUANTITY_INCORRECT),
                () -> assertTextEquals(SUBTOTAL_PRICE_1, cartElements.getProductSubtotal(), ERROR_INITIAL_SUBTOTAL_INCORRECT),
                () -> assertTextEquals(SUBTOTAL_PRICE_1, cartElements.getCartSubtotal(), ERROR_INITIAL_CART_SUBTOTAL_INCORRECT)
        );
    }
}