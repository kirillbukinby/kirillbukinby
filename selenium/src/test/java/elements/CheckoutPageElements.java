package elements;

import org.openqa.selenium.*;

import static config.CheckoutPageConstants.*;
import static utils.ElementHelper.*;
import static utils.JavaScriptHelper.*;
import static utils.WaitHelper.*;

public class CheckoutPageElements {
    // Локаторы формы оформления заказа
    private static final By FIRST_NAME_INPUT = By.xpath("//input[@id='billing_first_name']");
    private static final By LAST_NAME_INPUT = By.xpath("//input[@id='billing_last_name']");
    private static final By ADDRESS_INPUT = By.xpath("//input[@id='billing_address_1']");
    private static final By CITY_INPUT = By.xpath("//input[@id='billing_city']");
    private static final By STATE_INPUT = By.xpath("//input[@id='billing_state']");
    private static final By POSTCODE_INPUT = By.xpath("//input[@id='billing_postcode']");
    private static final By PHONE_INPUT = By.xpath("//input[@id='billing_phone']");
    private static final By EMAIL_INPUT = By.xpath("//input[@id='billing_email']");
    private static final By ORDER_COMMENTS = By.xpath("//textarea[@id='order_comments']");
    private static final By ORDER_DATE_INPUT = By.xpath("//input[@id='order_date']");
    private static final By PLACE_ORDER_BUTTON = By.xpath("//button[@id='place_order']");
    private static final By I_HAVE_READ_CHECKBOX = By.xpath("//input[@id='terms']");

    // Локаторы методов оплаты
    private static final By DIRECT_BANK_TRANSFER_BUTTON = By.xpath("//input[@id='payment_method_bacs']");
    private static final By CASH_ON_DELIVERY_BUTTON = By.xpath("//input[@id='payment_method_cod']");

    // Локаторы подтверждения заказа
    private static final By ORDER_RECEIVED = By.xpath("//h2[@class='post-title']");
    private static final By PAYMENT_METHOD_ELEMENT = By.xpath("//li[@class='woocommerce-order-overview__payment-method method']/strong");

    // Методы заполнения полей формы
    public CheckoutPageElements fillFormFields() {
        return fillFirstName()
                .fillLastName()
                .fillAddress()
                .fillCity()
                .fillState()
                .fillPostcode()
                .fillPhone()
                .verifyEmail()
                .fillOrderComments();
    }

    public CheckoutPageElements fillFirstName() {
        fillField(FIRST_NAME_INPUT, FIRST_NAME);
        return this;
    }

    public CheckoutPageElements fillLastName() {
        fillField(LAST_NAME_INPUT, LAST_NAME);
        return this;
    }

    public CheckoutPageElements fillAddress() {
        fillField(ADDRESS_INPUT, ADDRESS);
        return this;
    }

    public CheckoutPageElements fillCity() {
        fillField(CITY_INPUT, CITY);
        return this;
    }

    public CheckoutPageElements fillState() {
        fillField(STATE_INPUT, STATE);
        return this;
    }

    public CheckoutPageElements fillPostcode() {
        fillField(POSTCODE_INPUT, POSTCODE);
        return this;
    }

    public CheckoutPageElements fillPhone() {
        fillField(PHONE_INPUT, PHONE);
        return this;
    }

    public CheckoutPageElements verifyEmail() {
        String emailValue = waitForVisibility(EMAIL_INPUT).getAttribute("value");
        if (!EMAIL.equals(emailValue)) {
            clearAndSetElementValue(EMAIL_INPUT, EMAIL);
        }
        return this;
    }

    public CheckoutPageElements fillOrderComments() {
        fillField(ORDER_COMMENTS, ORDER_COMMENT);
        return this;
    }

    private void fillField(By locator, String value) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(value);
    }

    // Методы выбора опций
    public CheckoutPageElements selectCountryCheckout(String countryCode) {
        selectCountry(countryCode);
        return this;
    }

    public CheckoutPageElements setOrderDate() {
        setInputValue(waitForVisibility(ORDER_DATE_INPUT), ORDER_DATE);
        return this;
    }

    public CheckoutPageElements directBankTransfer() {
        scrollAndClick(DIRECT_BANK_TRANSFER_BUTTON);
        return this;
    }

    public CheckoutPageElements cashOnDelivery() {
        scrollAndClick(CASH_ON_DELIVERY_BUTTON);
        return this;
    }

    public CheckoutPageElements iHaveRead() {
        scrollAndClick(I_HAVE_READ_CHECKBOX);
        return this;
    }

    // Методы действий с заказом
    public CheckoutPageElements placeOrder() {
        scrollAndClick(PLACE_ORDER_BUTTON);
        return this;
    }

    public CheckoutPageElements waitForOrderConfirmation() {
        waitForUrlContains(ORDER_RECEIVED_URL_PART);
        return this;
    }

    // Методы получения информации
    public String getOrderReceivedText() {
        return waitForVisibility(ORDER_RECEIVED).getText();
    }

    public String getPaymentMethodText() {
        return waitForVisibility(PAYMENT_METHOD_ELEMENT).getText();
    }

    // Комбинированные методы
    public CheckoutPageElements completeOrderWithCashPayment() {
        return cashOnDelivery()
                .placeOrder()
                .waitForOrderConfirmation();
    }

    public CheckoutPageElements completeOrderWithBankTransfer() {
        return directBankTransfer()
                .placeOrder()
                .waitForOrderConfirmation();
    }
}