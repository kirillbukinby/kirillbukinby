package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;

import static config.BonusProgramPageConstants.*;
import static utils.AssertHelper.*;

public class BonusProgramTests extends BaseDriver {
    private BonusProgramPageElements bonusElements;

    @BeforeEach
    public void setUpTest() {
        bonusProgramPage.open();
        bonusElements = bonusProgramPage.getBonusElements();
    }

    @Test
    @DisplayName("Проверка оформления бонусной карты")
    public void shouldApplyForBonusCard() {
        // Act
        bonusElements.fillName(FIRST_NAME)
                .fillPhone(PHONE)
                .submitForm();

        // Assert
        assertAll(
                () -> assertAlertPresent(bonusElements.checkAlertPresence(), ERROR_ALERT_NOT_PRESENT),
                () -> assertTextEquals(BONUS_CARD_SUCCESS_MESSAGE, bonusElements.retrieveAlertMessage(), ERROR_ALERT_TEXT_MISMATCH)
        );

        // Cleanup
        bonusElements.confirmAlert();
    }

    @Test
    @DisplayName("Проверка оформления бонусной карты с использованием комбинированного метода")
    public void shouldApplyForBonusCardWithCombinedMethod() {
        // Act & Assert
        bonusElements.fillName(FIRST_NAME)
                .fillPhone(PHONE)
                .submitForm()
                .verifyAndAcceptAlert(BONUS_CARD_SUCCESS_MESSAGE);

        // Additional assertions
        assertAlertPresent(true, ERROR_ALERT_NOT_PROCESSED);
    }

    @Test
    @DisplayName("Проверка заполнения формы бонусной карты с валидными данными")
    public void shouldFillBonusFormWithValidData() {
        // Act
        bonusElements.fillBonusForm(FIRST_NAME, PHONE);

        // Assert
        assertAlertPresent(true, ERROR_FORM_NOT_FILLED);
    }

    @Test
    @DisplayName("Проверка отправки формы бонусной карты")
    public void shouldSubmitBonusForm() {
        // Arrange
        bonusElements.fillBonusForm(FIRST_NAME, PHONE);

        // Act
        bonusElements.submitBonusForm();

        // Assert
        assertAlertPresent(bonusElements.checkAlertPresence(), ERROR_FORM_NOT_SUBMITTED);

        // Cleanup
        bonusElements.confirmAlert();
    }
}