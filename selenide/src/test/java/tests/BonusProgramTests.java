package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;

import static config.BonusProgramPageConstants.*;
import static utils.AssertionHelper.*;

public class BonusProgramTests extends BaseDriver {
    private BonusProgramPageElements bonusElements;

    @BeforeEach
    public void setUpTest() {
        // Arrange
        bonusProgramPage.open();
        bonusElements = bonusProgramPage.getBonusElements();
    }

    @Test
    @DisplayName("Проверка оформления бонусной карты")
    public void shouldApplyForBonusCard() {
        // Act
        bonusElements.fillName()
                .fillPhone()
                .submitForm();

        // Assert
        assertAll(
                () -> assertAlertPresent(ERROR_ALERT_NOT_PRESENT),
                () -> assertAlertTextEquals(BONUS_CARD_SUCCESS_MESSAGE, ERROR_ALERT_TEXT_MISMATCH)
        );

        // Cleanup
        bonusElements.confirmAlert();
    }

    @Test
    @DisplayName("Проверка отправки формы бонусной карты")
    public void shouldSubmitBonusForm() {
        // Arrange
        bonusElements.fillBonusForm();

        // Act
        bonusElements.submitForm();

        // Assert
        assertAlertPresent(ERROR_FORM_NOT_SUBMITTED);
        assertAlertTextEquals(BONUS_CARD_SUCCESS_MESSAGE, ERROR_ALERT_TEXT_MISMATCH);

        // Cleanup
        bonusElements.confirmAlert();
    }

    @Test
    @DisplayName("Проверка заполнения формы")
    public void shouldFillFormCorrectly() {
        // Act
        bonusElements.fillBonusForm();

        // Assert
        assertAll(
                () -> assertTextEquals(FIRST_NAME, bonusElements.getNameValue(), ERROR_NAME_FIELD_INCORRECT),
                () -> assertTextEquals(PHONE, bonusElements.getPhoneValue(), ERROR_PHONE_FIELD_INCORRECT)
        );
    }

    @Test
    @DisplayName("Проверка корректного заполнения имени")
    public void shouldFillNameCorrectly() {
        // Act
        bonusElements.fillName();

        // Assert
        assertTextEquals(FIRST_NAME, bonusElements.getNameValue(), ERROR_NAME_FIELD_INCORRECT);
    }

    @Test
    @DisplayName("Проверка корректного заполнения телефона")
    public void shouldFillPhoneCorrectly() {
        // Act
        bonusElements.fillPhone();

        // Assert
        assertTextEquals(PHONE, bonusElements.getPhoneValue(), ERROR_PHONE_FIELD_INCORRECT);
    }

    @Test
    @DisplayName("Проверка что кнопка отправки активна после заполнения формы")
    public void shouldEnableSubmitButtonAfterFormFilling() {
        // Act
        bonusElements.fillBonusForm();

        // Assert
        assertTrue(bonusElements.isSubmitButtonEnabled(), ERROR_SUBMIT_BUTTON_DISABLED);
    }

    @Test
    @DisplayName("Проверка текста alert")
    public void shouldWaitForAlert() {
        // Act
        bonusElements.fillBonusForm().submitForm();

        // Assert
        assertAlertTextEquals(BONUS_CARD_SUCCESS_MESSAGE, ERROR_ALERT_TEXT_MISMATCH);

        // Cleanup
        bonusElements.confirmAlert();
    }

    @Test
    @DisplayName("Проверка что alert отсутствует до отправки формы")
    public void shouldNotHaveAlertBeforeSubmission() {
        // Act
        bonusElements.fillBonusForm();

        // Assert
        assertAlertNotPresent(ERROR_ALERT_PRESENT_BEFORE_SUBMISSION);
    }

    @Test
    @DisplayName("Проверка содержания текста в alert")
    public void shouldContainSuccessMessageInAlert() {
        // Act
        bonusElements.fillBonusForm().submitForm();

        // Assert
        assertAlertPresent(ERROR_ALERT_NOT_PRESENT);
        assertTextContains(CARD_REGISTRATION_TEXT, bonusElements.getAlertMessageText(), ERROR_ALERT_TEXT_DOES_NOT_CONTAIN);

        // Cleanup
        bonusElements.confirmAlert();
    }

}