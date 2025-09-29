package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;

import static config.MyAccountPageConstants.*;
import static utils.AssertHelper.*;

public class MyAccountPageTests extends BaseDriver {

    private MyAccountPageElements accountElements;

    @BeforeEach
    public void setUpTest() {
        accountElements = myAccountPage.open().getAccountElements();
    }

    @Test
    @DisplayName("Проверка авторизации в аккаунт")
    public void shouldLoginToAccount() {
        // Act
        accountElements.login();

        // Assert
        assertTextEquals(ACCOUNT_TITLE, accountElements.getAccountTitle(), ERROR_ACCOUNT_TITLE_MISMATCH);
    }

    @Test
    @DisplayName("Проверка авторизации с неверными данными")
    public void shouldNotLoginWithInvalidCredentials() {
        // Act
        accountElements.login(INVALID_USERNAME, INVALID_PASSWORD);

        // Assert
        assertAll(
                () -> assertUrlContains(MY_ACCOUNT_URL_PART, ERROR_SHOULD_STAY_ON_LOGIN_PAGE),
                () -> assertElementDisplayed(accountElements.isLoginPageDisplayed(), ERROR_LOGIN_FORM_NOT_DISPLAYED)
        );
    }

    @Test
    @DisplayName("Проверка отображения формы входа")
    public void shouldDisplayLoginForm() {
        // Assert
        assertAll(
                () -> assertElementDisplayed(accountElements.isUsernameInputDisplayed(), ERROR_USERNAME_INPUT_NOT_DISPLAYED),
                () -> assertElementDisplayed(accountElements.isPasswordInputDisplayed(), ERROR_PASSWORD_INPUT_NOT_DISPLAYED),
                () -> assertElementDisplayed(accountElements.isLoginButtonDisplayed(), ERROR_LOGIN_BUTTON_NOT_DISPLAYED)
        );
    }
}