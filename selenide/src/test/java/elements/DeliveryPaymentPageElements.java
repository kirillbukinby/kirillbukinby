package elements;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;
import static config.DeliveryPaymentPageConstants.*;
import static utils.ElementHelper.*;

public class DeliveryPaymentPageElements {

    // Локаторы iframe
    private SelenideElement iframe() {
        return $x("//iframe[contains(@src,'delivery.html')]");
    }

    // Локаторы контента внутри iframe
    private SelenideElement pageTitle() {
        return $x("//h2[@class='post-title']");
    }

    private SelenideElement contentTitle() {
        return $x("//h5[@class='has-text-align-center']");
    }

    // Методы получения текста
    public String getPageTitle() {
        return getElementText(pageTitle());
    }

    public String getContentTitle() {
        return getElementText(contentTitle());
    }

    // Методы получения элементов для проверки
    public SelenideElement getPageTitleElement() {
        return executeInIframe(iframe(), () -> pageTitle());
    }

    public SelenideElement getContentTitleElement() {
        return executeInIframe(iframe(), () -> contentTitle());
    }

    public SelenideElement getIframeElement() {
        return iframe();
    }

    // Методы получения атрибутов iframe
    public String getIframeSrc() {
        return getElementAttribute(iframe(), IFRAME_SRC_ATTRIBUTE);
    }

    public String getIframeWidth() {
        return getElementAttribute(iframe(), IFRAME_WIDTH_ATTRIBUTE);
    }

    public String getIframeHeight() {
        return getElementAttribute(iframe(), IFRAME_HEIGHT_ATTRIBUTE);
    }

    // Методы работы с контентом
    public String getIframeContentText() {
        return executeInIframe(iframe(), () ->
                $(BODY_SELECTOR).getText()
        );
    }
}