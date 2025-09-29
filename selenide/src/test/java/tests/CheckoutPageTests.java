package tests;

import common.*;
import elements.*;
import io.github.artsok.*;
import org.junit.jupiter.api.*;

import static config.CheckoutPageConstants.*;
import static utils.AssertionHelper.*;

public class CheckoutPageTests extends BaseDriver {
    private CheckoutPageElements checkoutElements;

    @BeforeEach
    public void setUpTest() {
        // Авторизуемся и добавляем товар в корзину
        myAccountPage.open()
                .getAccountElements()
                .login()
                .navigateToMainPage();

        pizzaPage.open()
                .getPizzaElements()
                .addFirstPizzaToCart()
                .goToCart();

        // Переходим к оформлению заказа
        cartPage.getCartElements()
                .proceedToCheckout();

        checkoutElements = checkoutPage.getCheckoutElements();

        // Заполняем общую часть формы
        checkoutElements.selectCountry("Belarus")
                .fillFormFields()
                .iHaveRead()
                .setOrderDate();
    }

    @Test
    @DisplayName("Проверка оформления заказа Оплата при доставке")
    public void shouldPlaceOrderWithCashPayment() {
        // Act
        checkoutElements.completeOrderWithCashPayment();

        // Assert
        assertAll(
                () -> assertUrlContains(ORDER_RECEIVED_URL_PART, ERROR_WRONG_URL_AFTER_ORDER),
                () -> assertTextContains(ORDER_RECEIVED_TEXT, checkoutElements.getOrderReceivedText(), ERROR_ORDER_CONFIRMATION_TEXT),
                () -> assertTextContains(PAYMENT_METHOD_TEXT, checkoutElements.getPaymentMethodText(), ERROR_PAYMENT_METHOD_TEXT)
        );
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @DisplayName("Проверка оформления заказа Прямой банковский перевод")
    public void shouldPlaceOrderWithCard() {
        // Act
        checkoutElements.completeOrderWithBankTransfer();

        // Assert
        assertAll(
                () -> assertUrlContains(ORDER_RECEIVED_URL_PART, ERROR_WRONG_URL_AFTER_ORDER),
                () -> assertTextContains(ORDER_RECEIVED_TEXT, checkoutElements.getOrderReceivedText(), ERROR_ORDER_CONFIRMATION_TEXT),
                () -> assertTextContains(DIRECT_BANK_TRANSFER_TEXT, checkoutElements.getPaymentMethodText(), ERROR_PAYMENT_METHOD_TEXT)
        );
    }

    @Test
    @DisplayName("Проверка заполнения формы оформления заказа")
    public void shouldFillCheckoutFormCorrectly() {
        // Act
        checkoutElements.fillFormFields();

        // Assert
        assertAll(
                () -> assertTextEquals(FIRST_NAME, checkoutElements.getFirstNameValue(), ERROR_INCORRECT_FIRST_NAME),
                () -> assertTextEquals(LAST_NAME, checkoutElements.getLastNameValue(), ERROR_INCORRECT_LAST_NAME),
                () -> assertTextEquals(PHONE, checkoutElements.getPhoneValue(), ERROR_INCORRECT_PHONE),
                () -> assertTextEquals(ADDRESS, checkoutElements.getAddressValue(), ERROR_INCORRECT_ADDRESS),
                () -> assertTextEquals(CITY, checkoutElements.getCityValue(), ERROR_INCORRECT_CITY),
                () -> assertTextEquals(STATE, checkoutElements.getStateValue(), ERROR_INCORRECT_STATE),
                () -> assertTextEquals(POSTCODE, checkoutElements.getPostcodeValue(), ERROR_INCORRECT_POSTCODE),
                () -> assertTextEquals(EMAIL, checkoutElements.getEmailValue(), ERROR_INCORRECT_EMAIL)
        );
    }
}