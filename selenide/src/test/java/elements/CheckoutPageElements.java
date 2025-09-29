package elements;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;
import static config.CheckoutPageConstants.*;
import static utils.BrowserHelper.*;
import static utils.ElementHelper.*;
import static utils.JavaScriptHelper.*;

public class CheckoutPageElements  {
    // Локаторы формы оформления заказа
    private SelenideElement firstNameInput() { return $x("//input[@id='billing_first_name']"); }
    private SelenideElement lastNameInput() { return $x("//input[@id='billing_last_name']"); }
    private SelenideElement addressInput() { return $x("//input[@id='billing_address_1']"); }
    private SelenideElement cityInput() { return $x("//input[@id='billing_city']"); }
    private SelenideElement stateInput() { return $x("//input[@id='billing_state']"); }
    private SelenideElement postcodeInput() { return $x("//input[@id='billing_postcode']"); }
    private SelenideElement phoneInput() { return $x("//input[@id='billing_phone']"); }
    private SelenideElement emailInput() { return $x("//input[@id='billing_email']"); }
    private SelenideElement orderComments() { return $x("//textarea[@id='order_comments']"); }
    private SelenideElement orderDateInput() { return $x("//input[@id='order_date']"); }
    private SelenideElement placeOrderButton() { return $x("//button[@id='place_order']"); }
    private SelenideElement iHaveReadCheckbox() { return $x("//input[@id='terms']"); }

    // Локаторы методов оплаты
    private SelenideElement directBankTransferButton() { return $x("//input[@id='payment_method_bacs']"); }
    private SelenideElement cashOnDeliveryButton() { return $x("//input[@id='payment_method_cod']"); }

    // Локаторы подтверждения заказа
    private SelenideElement orderReceived() { return $x("//h2[contains(@class, 'post-title')]"); }
    private SelenideElement paymentMethodElement() { return $x("(//strong)[5]"); }

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
        clearAndSetElementValue(firstNameInput(), FIRST_NAME);
        return this;
    }

    public CheckoutPageElements fillLastName() {
        clearAndSetElementValue(lastNameInput(), LAST_NAME);
        return this;
    }

    public CheckoutPageElements fillAddress() {
        clearAndSetElementValue(addressInput(), ADDRESS);
        return this;
    }

    public CheckoutPageElements fillCity() {
        clearAndSetElementValue(cityInput(), CITY);
        return this;
    }

    public CheckoutPageElements fillState() {
        clearAndSetElementValue(stateInput(), STATE);
        return this;
    }

    public CheckoutPageElements fillPostcode() {
        clearAndSetElementValue(postcodeInput(), POSTCODE);
        return this;
    }

    public CheckoutPageElements fillPhone() {
        clearAndSetElementValue(phoneInput(), PHONE);
        return this;
    }

    public CheckoutPageElements verifyEmail() {
        String emailValue = emailInput().getValue();
        if (!EMAIL.equals(emailValue)) {
            clearAndSetElementValue(emailInput(), EMAIL);
        }
        return this;
    }

    public CheckoutPageElements fillOrderComments() {
        clearAndSetElementValue(orderComments(), ORDER_COMMENT);
        return this;
    }

    // Методы выбора опций
    public CheckoutPageElements selectCountry(String countryCode) {
        selectByValue(countryCode);
        return this;
    }

    public CheckoutPageElements setOrderDate() {
        setInputValue(orderDateInput(), ORDER_DATE);
        return this;
    }

    public CheckoutPageElements directBankTransfer() {
        clickElement(directBankTransferButton());
        return this;
    }

    public CheckoutPageElements cashOnDelivery() {
        clickElement(cashOnDeliveryButton());
        return this;
    }

    public CheckoutPageElements iHaveRead() {
        setCheckbox(iHaveReadCheckbox(), true);
        return this;
    }

    // Методы действий с заказом
    public CheckoutPageElements placeOrder() {
        clickElement(placeOrderButton());
        return this;
    }

    public CheckoutPageElements waitForOrderConfirmation() {
        waitForUrlToContain(ORDER_RECEIVED_URL_PART);
        return this;
    }

    // Методы получения информации
    public String getOrderReceivedText() {
        return getElementText(orderReceived());
    }

    public String getPaymentMethodText() {
        return getElementText(paymentMethodElement());
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

    // Методы получения значений полей
    public String getFirstNameValue() {
        return firstNameInput().getValue();
    }

    public String getLastNameValue() {
        return lastNameInput().getValue();
    }

    public String getPhoneValue() {
        return phoneInput().getValue();
    }

    public String getAddressValue() {
        return addressInput().getValue();
    }

    public String getCityValue() {
        return cityInput().getValue();
    }

    public String getStateValue() {
        return stateInput().getValue();
    }

    public String getPostcodeValue() {
        return postcodeInput().getValue();
    }

    public String getEmailValue() {
        return emailInput().getValue();
    }
}