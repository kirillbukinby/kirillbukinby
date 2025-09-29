package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;

import static config.CheckoutPageConstants.*;
import static utils.AssertHelper.*;

public class CheckoutPageTests extends BaseDriver {
    private CheckoutPageElements checkoutElements;

    @BeforeEach
    public void setUpTest() {
        // Arrange: Авторизуемся и добавляем товар в корзину
        myAccountPage.open()
                .getAccountElements()
                .login()
                .navigateToMainPage();

        pizzaPage.open()
                .getPizzaElements()
                .addFirstPizzaToCart()
                .goToCart();

        // Act: Переходим к оформлению заказа
        cartPage.getCartElements()
                .proceedToCheckout();

        checkoutElements = checkoutPage.getCheckoutElements();

        // Заполняем общую часть формы
        checkoutElements.fillFormFields()
                .iHaveRead()
                .selectCountryCheckout("Belarus")
                .setOrderDate();
    }

    @Test
    @DisplayName("Проверка оформления заказа Оплата при доставке")
    public void shouldPlaceOrderWithCashPayment() {
        // Act
        checkoutElements.completeOrderWithCashPayment();

        // Assert
        assertAll(
                () -> assertUrlContains(ORDER_RECEIVED_URL, ERROR_WRONG_URL_AFTER_ORDER),
                () -> assertTextContains(ORDER_RECEIVED_TEXT, checkoutElements.getOrderReceivedText(), ERROR_ORDER_CONFIRMATION_TEXT),
                () -> assertTextContains(PAYMENT_METHOD_TEXT, checkoutElements.getPaymentMethodText(), ERROR_PAYMENT_METHOD_TEXT)
        );
    }

    @Test
    @DisplayName("Проверка оформления заказа Прямой банковский перевод")
    public void shouldPlaceOrderWithCard() {
        // Act
        checkoutElements.completeOrderWithBankTransfer();

        // Assert
        assertAll(
                () -> assertUrlContains(ORDER_RECEIVED_URL, ERROR_WRONG_URL_AFTER_ORDER),
                () -> assertTextContains(ORDER_RECEIVED_TEXT, checkoutElements.getOrderReceivedText(), ERROR_ORDER_CONFIRMATION_TEXT),
                () -> assertTextContains(DIRECT_BANK_TRANSFER_TEXT, checkoutElements.getPaymentMethodText(), ERROR_PAYMENT_METHOD_TEXT)
        );
    }
}