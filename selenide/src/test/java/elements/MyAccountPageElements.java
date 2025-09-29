package elements;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;
import static config.MyAccountPageConstants.*;
import static utils.BrowserHelper.*;
import static utils.ElementHelper.*;

public class MyAccountPageElements {

    private SelenideElement usernameInput() { return $x("//input[@id='username']"); }
    private SelenideElement passwordInput() { return $x("//input[@id='password']"); }
    private SelenideElement loginButton() { return $x("//button[@name='login']"); }
    private SelenideElement accountTitle() { return $x("//h2[@class='post-title']"); }
    private SelenideElement errorMessage() { return $x("//ul[@class='woocommerce-error']//li"); }

    // Методы заполнения полей входа
    public MyAccountPageElements enterUsername(String username) {
        clearAndSetElementValue(usernameInput(), username);
        return this;
    }

    public MyAccountPageElements enterPassword(String password) {
        clearAndSetElementValue(passwordInput(), password);
        return this;
    }

    // Методы действий с формой
    public MyAccountPageElements clickLoginButton() {
        clickElement(loginButton());
        return this;
    }

    // Методы авторизации
    public MyAccountPageElements login() {
        return enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickLoginButton();
    }

    public MyAccountPageElements loginInvalid() {
        return enterUsername(INVALID_USERNAME)
                .enterPassword(INVALID_PASSWORD)
                .clickLoginButton();
    }

    // Методы получения информации
    public String getAccountTitle() {
        return getElementText(accountTitle());
    }

    public String getErrorText() {
        return getElementText(errorMessage());
    }

    // Геттеры для элементов
    public SelenideElement getUsernameInput() {
        return usernameInput();
    }

    public SelenideElement getPasswordInput() {
        return passwordInput();
    }

    public SelenideElement getLoginButton() {
        return loginButton();
    }

    public SelenideElement getAccountTitleElement() {
        return accountTitle();
    }

    // Методы навигации
    public MyAccountPageElements navigateToMainPage() {
        openUrl(MAIN_PAGE_URL);
        return this;
    }
}