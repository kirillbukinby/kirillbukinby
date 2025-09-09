package elements;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;
import static config.BonusProgramPageConstants.*;
import static utils.AlertHelper.*;
import static utils.ElementHelper.*;

public class BonusProgramPageElements {
    private SelenideElement nameInput() { return $x("//input[@id='bonus_username']"); }
    private SelenideElement phoneInput() { return $x("//input[@id='bonus_phone']"); }
    private SelenideElement submitButton() { return $x("//button[@name='bonus']"); }

    // Методы заполнения полей
    public BonusProgramPageElements fillName() {
        setElementValue(nameInput(), FIRST_NAME);
        return this;
    }

    public BonusProgramPageElements fillPhone() {
        setElementValue(phoneInput(), PHONE);
        return this;
    }

    // Методы работы с формой
    public BonusProgramPageElements submitForm() {
        clickElement(submitButton());
        return this;
    }

    // Методы работы с алертами
    public BonusProgramPageElements confirmAlert() {
        acceptAlert();
        return this;
    }

    public String getAlertMessageText() {
        return getAlertText();
    }

    // Комплексные методы
    public BonusProgramPageElements fillBonusForm() {
        return fillName().fillPhone();
    }

    // Методы получения значений
    public String getNameValue() {
        return nameInput().getValue();
    }

    public String getPhoneValue() {
        return phoneInput().getValue();
    }

    // Методы проверки состояния
    public boolean isSubmitButtonEnabled() {
        return submitButton().isEnabled();
    }
}