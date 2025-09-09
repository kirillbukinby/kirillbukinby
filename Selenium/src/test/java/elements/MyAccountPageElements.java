package elements;

import org.openqa.selenium.*;

import static config.MainPageConstants.*;
import static config.MyAccountPageConstants.*;
import static utils.ActionHelper.*;
import static utils.BrowserHelper.*;
import static utils.WaitHelper.*;

public class MyAccountPageElements {

    private static final By USERNAME_INPUT = By.xpath("//input[@id='username']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@id='password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@name='login']");
    private static final By ACCOUNT_TITLE = By.xpath("//h2[@class='post-title']");

    // Методы работы с формой входа
    public MyAccountPageElements enterUsername(String username) {
        WebElement usernameField = waitForVisibility(USERNAME_INPUT);
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    public MyAccountPageElements enterPassword(String password) {
        WebElement passwordField = waitForVisibility(PASSWORD_INPUT);
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public MyAccountPageElements clickLoginButton() {
        moveToAndClickElement(LOGIN_BUTTON);
        return this;
    }

    // Методы авторизации
    public MyAccountPageElements login() {
        return enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickLoginButton();
    }

    public MyAccountPageElements login(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
    }

    // Методы получения информации
    public String getAccountTitle() {
        return waitForVisibility(ACCOUNT_TITLE).getText();
    }

    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(USERNAME_INPUT) &&
                isElementDisplayed(PASSWORD_INPUT);
    }

    // Вспомогательные методы проверки отображения элементов
    public boolean isUsernameInputDisplayed() {
        return isElementDisplayed(USERNAME_INPUT);
    }

    public boolean isPasswordInputDisplayed() {
        return isElementDisplayed(PASSWORD_INPUT);
    }

    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(LOGIN_BUTTON);
    }

    public MyAccountPageElements navigateToMainPage() {
        openUrl(MAIN_PAGE_URL);
        return this;
    }
}