package elements;

import org.openqa.selenium.*;
import java.util.function.*;

import static utils.WaitHelper.*;
import static utils.WindowHelper.*;

public class DeliveryPaymentPageElements {

    // Локаторы iframe
    private static final By IFRAME_LOCATOR = By.xpath("//iframe[contains(@src,'delivery.html')]");

    // Локаторы контента внутри iframe
    private static final By PAGE_TITLE_LOCATOR = By.xpath("//h2[@class='post-title']");
    private static final By CONTENT_TITLE_LOCATOR = By.xpath("//h5[contains(@class, 'has-text-align-center')]");

    // Методы работы с iframe
    private static void switchToIframe() {
        switchToFrame(IFRAME_LOCATOR);
    }

    private static void switchToDefaultContentDelivery() {
        switchToDefaultContent();
    }

    private static <T> T executeInIframe(Supplier<T> action) {
        switchToIframe();
        try {
            return action.get();
        } finally {
            switchToDefaultContentDelivery();
        }
    }

    // Методы получения текста элементов
    public String getPageTitle() {
        return waitForVisibility(PAGE_TITLE_LOCATOR).getText();
    }

    public String getContentTitle() {
        return waitForVisibility(CONTENT_TITLE_LOCATOR).getText();
    }

    // Методы проверки видимости элементов
    public boolean isPageTitleDisplayed() {
        return isElementDisplayed(PAGE_TITLE_LOCATOR);
    }

    public boolean isContentTitleDisplayed() {
        return isElementDisplayed(CONTENT_TITLE_LOCATOR);
    }

    public boolean isIframeDisplayed() {
        return isElementDisplayed(IFRAME_LOCATOR);
    }

    // Методы получения атрибутов iframe
    public String getIframeSrc() {
        WebElement iframe = waitForVisibility(IFRAME_LOCATOR);
        return iframe.getAttribute("src");
    }

    public String getIframeWidth() {
        WebElement iframe = waitForVisibility(IFRAME_LOCATOR);
        return iframe.getAttribute("width");
    }

    public String getIframeHeight() {
        WebElement iframe = waitForVisibility(IFRAME_LOCATOR);
        return iframe.getAttribute("height");
    }

    // Методы работы с контентом iframe
    public String getIframeContentText() {
        return executeInIframe(() -> {
            WebElement body = waitForVisibility(By.tagName("body"));
            return body.getText();
        });
    }
}