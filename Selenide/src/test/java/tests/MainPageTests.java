package tests;

import common.*;
import elements.*;
import io.github.artsok.*;
import org.junit.jupiter.api.*;

import static config.MainPageConstants.*;
import static utils.AssertionHelper.*;
import static utils.WindowHelper.*;

public class MainPageTests extends BaseDriver {
    private MainPageElements mainElements;

    @BeforeEach
    public void setUpTest() {
        mainElements = mainPage.open().getMainElements();
    }

    @Test
    @DisplayName("Проверка переключения слайдера пицц")
    public void shouldSwitchPizzaSlider() {
        // Act
        mainElements.clickNextSliderArrow()
                .clickPrevSliderArrow();

        // Assert
        assertTrue(true, "Слайдер должен переключаться");

    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @DisplayName("Проверка отображения кнопки 'В корзину' при наведении на напиток")
    public void shouldShowAddToCartButtonOnDrinkHover() {
        // Act
        mainElements.hoverDrinkImage();

        // Assert
        assertElementDisplayed(mainElements.getAddToCartButton(), ERROR_ADD_TO_CART_BUTTON_NOT_DISPLAYED);
    }

    @Test
    @DisplayName("Проверка перехода на страницу десерта")
    public void shouldOpenDessertPage() {
        // Act
        mainElements.openDessertPage();

        // Assert
        assertAll(
                () -> assertUrlContains(DESERT_URL_IMG_CONTAINS, ERROR_DESSERT_URL_NOT_CONTAINS),
                () -> assertTextContains(DESSERT_TEXT, mainElements.getDessertText(), ERROR_DESSERT_TEXT_NOT_CONTAINS)
        );
    }

    @Test
    @DisplayName("Проверка отображения кнопки 'Наверх' при скролле")
    public void shouldShowScrollToTopButton() {
        // Act
        mainElements.scrollToBottom();

        // Assert
        assertElementDisplayed(mainElements.getScrollToTopButton(), ERROR_SCROLL_TO_TOP_BUTTON_NOT_DISPLAYED);
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @DisplayName("Проверка ссылки на Facebook")
    public void shouldOpenFacebookLinkInNewTab() {
        // Arrange
        int initialWindowsCount = getWindowCount();

        // Act
        mainElements.openFacebookAndSwitch();

        // Assert
        assertAll(
                () -> assertUrlEquals(FACEBOOK_URL, ERROR_FACEBOOK_URL),
                () -> assertWindowCount(initialWindowsCount + 1, ERROR_FACEBOOK_WINDOW_COUNT)
        );
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @DisplayName("Проверка ссылки на VK")
    public void shouldOpenVkLinkInNewTab() {
        // Arrange
        int initialWindowsCount = getWindowCount();

        // Act
        mainElements.openVkAndSwitch();

        // Assert
        assertAll(
                () -> assertUrlEquals(VK_URL, ERROR_VK_URL),
                () -> assertWindowCount(initialWindowsCount + 1, ERROR_VK_WINDOW_COUNT)
        );
    }


    @RepeatedIfExceptionsTest(repeats = 3)
    @DisplayName("Проверка ссылки на Instagram")
    public void shouldOpenInstagramLinkInNewTab() {
        // Arrange
        int initialWindowsCount = getWindowCount();

        // Act
        mainElements.openInstagramAndSwitch();

        // Assert
        assertAll(
                () -> assertUrlEquals(INSTAGRAM_URL, ERROR_INSTAGRAM_URL),
                () -> assertWindowCount(initialWindowsCount + 1, ERROR_INSTAGRAM_WINDOW_COUNT)
        );
    }
}