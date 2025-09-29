package elements;

import org.openqa.selenium.*;

import static utils.ActionHelper.*;
import static utils.AlertHelper.*;
import static utils.WaitHelper.*;

public class BonusProgramPageElements {

    private static final By NAME_INPUT = By.xpath("//input[@id='bonus_username']");
    private static final By PHONE_INPUT = By.xpath("//input[@id='bonus_phone']");
    private static final By SUBMIT_BUTTON = By.xpath("//button[@name='bonus']");

    // Методы заполнения полей формы
    public BonusProgramPageElements fillName(String name) {
        waitForVisibility(NAME_INPUT).sendKeys(name);
        return this;
    }

    public BonusProgramPageElements fillPhone(String phone) {
        waitForVisibility(PHONE_INPUT).sendKeys(phone);
        return this;
    }

    // Методы действий с формой
    public BonusProgramPageElements submitForm() {
        moveToAndClickElement(SUBMIT_BUTTON);
        return this;
    }

    // Методы для работы с alert
    public boolean checkAlertPresence() {
        return isAlertPresent();
    }

    public String retrieveAlertMessage() {
        return getAlertText();
    }

    public BonusProgramPageElements confirmAlert() {
        acceptAlert();
        return this;
    }

    // Комбинированный метод для проверки и принятия alert
    public BonusProgramPageElements verifyAndAcceptAlert(String expectedMessage) {
        if (!checkAlertPresence()) {
            throw new RuntimeException("Alert окно не появилось после отправки формы");
        }

        String actualMessage = retrieveAlertMessage();
        if (!expectedMessage.equals(actualMessage)) {
            throw new AssertionError("Текст в alert не соответствует ожидаемому. " +
                    "Ожидалось: '" + expectedMessage + "', Получено: '" + actualMessage + "'");
        }

        confirmAlert();
        return this;
    }

    // Комплексные методы
    public BonusProgramPageElements fillBonusForm(String name, String phone) {
        return fillName(name)
                .fillPhone(phone);
    }

    public BonusProgramPageElements submitBonusForm() {
        return submitForm();
    }
}