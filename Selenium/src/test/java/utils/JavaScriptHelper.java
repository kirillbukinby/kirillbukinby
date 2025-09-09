package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class JavaScriptHelper {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static JavascriptExecutor js;

    public static void initialize(WebDriver webDriver, WebDriverWait webDriverWait) {
        driver = webDriver;
        wait = webDriverWait;
        js = (JavascriptExecutor) driver;
    }

    public static Object executeScript(String script, Object... args) {
        try {
            return js.executeScript(script, args);
        } catch (JavascriptException e) {
            throw new RuntimeException("Ошибка выполнения JavaScript: " + script, e);
        }
    }

    public static void setInputValue(WebElement inputElement, String value) {
        executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'))",
                inputElement,
                value
        );
    }

    public static void setPriceAndApply(int minPrice, int maxPrice) {
        executeScript(
                "document.getElementById('min_price').value = arguments[0];" +
                        "document.getElementById('max_price').value = arguments[1];" +
                        "jQuery(document).trigger('price_slider_update', [arguments[0], arguments[1]]);" +
                        "jQuery(document).trigger('price_slider_change', [arguments[0], arguments[1]]);",
                minPrice, maxPrice
        );
    }

    public static void selectCountry(String countryCode) {
        executeScript(
                "document.getElementById('billing_country').title = '" + countryCode.toLowerCase() + "';" +
                        "jQuery('#billing_country').trigger('change');"
        );
    }

    public static void scrollAndClick(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось проскроллить и кликнуть на элемент: " + locator, e);
        }
    }
}