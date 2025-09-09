package tests;

import common.*;
import elements.*;
import io.github.artsok.*;
import org.junit.jupiter.api.*;

import static config.MyAccountPageConstants.*;
import static utils.AssertionHelper.*;

public class MyAccountPageTests extends BaseDriver {
    private MyAccountPageElements accountElements;

    @BeforeEach
    public void setUpTest() {
        // Arrange
        accountElements = myAccountPage.open().getAccountElements();
    }

    @Test
    @DisplayName("Проверка успешной авторизации")
    public void shouldLoginSuccessfully() {
        // Act
        accountElements.login();

        // Assert
        assertAll(
                () -> assertUrlContains(MY_ACCOUNT_URL_PART, ERROR_URL_SHOULD_CONTAIN_ACCOUNT_PART),
                () -> assertTextEquals(ACCOUNT_TITLE, accountElements.getAccountTitle(), ERROR_ACCOUNT_TITLE_MISMATCH),
                () -> assertElementDisplayed(accountElements.getAccountTitleElement(), ERROR_ACCOUNT_TITLE_NOT_DISPLAYED)
        );
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @DisplayName("Проверка неудачной авторизации")
    public void shouldStayOnLoginPageAfterInvalidLogin() {
        // Act
        accountElements.loginInvalid();

        // Assert
        assertAll(
                () -> assertElementDisplayed(accountElements.getUsernameInput(), ERROR_USERNAME_INPUT_NOT_DISPLAYED),
                () -> assertElementDisplayed(accountElements.getPasswordInput(), ERROR_PASSWORD_INPUT_NOT_DISPLAYED),
                () -> assertElementDisplayed(accountElements.getLoginButton(), ERROR_LOGIN_BUTTON_NOT_DISPLAYED),
                () -> assertUrlContains(MY_ACCOUNT_URL_PART, ERROR_SHOULD_STAY_ON_LOGIN_PAGE),
                () -> assertTextContains(UNKNOWN_USERNAME_ERROR, accountElements.getErrorText(), ERROR_LOGIN_ERROR_NOT_DISPLAYED),
                () -> assertUrlContains(MY_ACCOUNT_URL_PART, ERROR_SHOULD_STAY_ON_LOGIN_PAGE)
        );
    }

    @Test
    @DisplayName("Проверка отображения формы логина при открытии страницы")
    public void shouldDisplayLoginFormOnPageOpen() {
        // Assert
        assertAll(
                () -> assertElementDisplayed(accountElements.getUsernameInput(), ERROR_USERNAME_INPUT_NOT_DISPLAYED),
                () -> assertElementDisplayed(accountElements.getPasswordInput(), ERROR_PASSWORD_INPUT_NOT_DISPLAYED),
                () -> assertElementDisplayed(accountElements.getLoginButton(), ERROR_LOGIN_BUTTON_NOT_DISPLAYED),
                () -> assertUrlContains(MY_ACCOUNT_URL_PART, ERROR_URL_SHOULD_CONTAIN_ACCOUNT_PART),
                () -> assertUrlEquals(MY_ACCOUNT_URL, ERROR_URL_SHOULD_EQUAL_ACCOUNT_PAGE)
        );
    }

    @Test
    @DisplayName("Проверка перехода на главную страницу")
    public void shouldNavigateToMainPage() {
        // Act
        accountElements.navigateToMainPage();

        // Assert
        assertAll(
                () -> assertUrlContains(MAIN_PAGE_URL_PART, ERROR_URL_SHOULD_CONTAIN_MAIN_PART),
                () -> assertUrlEquals(MAIN_PAGE_URL, ERROR_URL_SHOULD_EQUAL_MAIN_PAGE)
        );
    }

    @Test
    @DisplayName("Проверка элементов формы логина")
    public void shouldHaveAllLoginFormElements() {
        // Assert
        assertAll(
                () -> assertElementEnabled(accountElements.getUsernameInput(), ERROR_USERNAME_INPUT_NOT_ENABLED),
                () -> assertElementEnabled(accountElements.getPasswordInput(), ERROR_PASSWORD_INPUT_NOT_ENABLED),
                () -> assertElementEnabled(accountElements.getLoginButton(), ERROR_LOGIN_BUTTON_NOT_ENABLED),
                () -> assertFieldEmpty(accountElements.getUsernameInput(), ERROR_USERNAME_FIELD_NOT_EMPTY),
                () -> assertFieldEmpty(accountElements.getPasswordInput(), ERROR_PASSWORD_FIELD_NOT_EMPTY)
        );
    }
}