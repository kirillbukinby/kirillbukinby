package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static config.DeliveryPaymentPageConstants.*;
import static utils.AssertionHelper.*;

public class DeliveryPaymentPageTests extends BaseDriver {
    private DeliveryPaymentPageElements deliveryPaymentElements;

    @BeforeEach
    public void setUpTest() {
        deliveryPaymentElements = deliveryPaymentPage.open().getDeliveryPaymentElements();
    }

    @Test
    @DisplayName("Проверка заголовка контента")
    public void shouldDisplayContentTitle() {
        // Assert
        assertAll(
                () -> assertTextContainsIgnoreCase(DELIVERY_CONDITIONS_TITLE, deliveryPaymentElements.getContentTitle(), ERROR_CONTENT_TITLE_TEXT),
                () -> assertTextContainsIgnoreCase(PAGE_TITLE, deliveryPaymentElements.getPageTitle(), ERROR_PAGE_TITLE_TEXT),
                () -> assertElementDisplayed(deliveryPaymentElements.getContentTitleElement(), ERROR_CONTENT_TITLE_DISPLAYED),
                () -> assertElementDisplayed(deliveryPaymentElements.getPageTitleElement(), ERROR_PAGE_TITLE_DISPLAYED)
        );
    }

    @Test
    @DisplayName("Проверка отображения iframe")
    public void shouldDisplayIframe() {
        // Assert
        assertAll(
                () -> assertElementDisplayed(deliveryPaymentElements.getIframeElement(), ERROR_IFRAME_DISPLAYED),
                () -> assertTextContains(IFRAME_SRC, deliveryPaymentElements.getIframeSrc(), ERROR_IFRAME_SRC)
        );
    }

    @Test
    @DisplayName("Проверка атрибутов iframe")
    public void shouldHaveCorrectIframeAttributes() {
        // Assert
        assertAll(
                () -> assertTextEquals(IFRAME_WIDTH_VALUE, deliveryPaymentElements.getIframeWidth(), ERROR_IFRAME_WIDTH),
                () -> assertTextEquals(IFRAME_HEIGHT_VALUE, deliveryPaymentElements.getIframeHeight(), ERROR_IFRAME_HEIGHT)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Сделать заказ",
            "Минимальная сумма",
            "Доставка заказа",
            "Заказ можно",
            "Точное время ",
            "Оплата заказа"

    })
    @DisplayName("Параметризированная проверка содержания iframe")
    public void shouldContainTextInIframe(String expectedText) {
        // Assert
        assertTextContains(expectedText, deliveryPaymentElements.getIframeContentText(), ERROR_IFRAME_CONTENT + expectedText);
    }

    @Test
    @DisplayName("Проверка URL страницы")
    public void shouldHaveCorrectUrl() {
        // Assert
        assertUrlEquals(DELIVERY_PAYMENT_URL, ERROR_URL);
    }

    @Test
    @DisplayName("Проверка навигационного меню")
    public void shouldHaveNavigationMenu() {
        // Assert
        assertAll(
                () -> assertElementDisplayed(deliveryPaymentElements.getPageTitleElement(), ERROR_PAGE_LOAD_TITLE),
                () -> assertElementDisplayed(deliveryPaymentElements.getIframeElement(), ERROR_PAGE_LOAD_IFRAME)
        );
    }
}