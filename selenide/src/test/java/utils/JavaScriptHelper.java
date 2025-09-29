package utils;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;

public class JavaScriptHelper {

    public static void setPriceFilterValues(int minPrice, int maxPrice) {
        executeJavaScript(
                "document.getElementById('min_price').value = arguments[0];" +
                        "document.getElementById('max_price').value = arguments[1];" +
                        "jQuery(document).trigger('price_slider_update', [arguments[0], arguments[1]]);" +
                        "jQuery(document).trigger('price_slider_change', [arguments[0], arguments[1]]);",
                minPrice, maxPrice
        );
    }

    public static void scrollToPageBottom() {
        executeJavaScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void selectByValue(String countryName) {
        executeJavaScript(
                "var select = document.querySelector('select#billing_country');" +
                        "var options = select.options;" +
                        "for (var i = 0; i < options.length; i++) {" +
                        "  if (options[i].text === arguments[0]) {" +
                        "    select.value = options[i].value;" +
                        "    break;" +
                        "  }" +
                        "}" +
                        "var event = new Event('change', { bubbles: true });" +
                        "select.dispatchEvent(event);",
                countryName
        );
    }

    public static void setInputValue(SelenideElement element, String value) {
        executeJavaScript("arguments[0].value = arguments[1];", element, value);
    }

}