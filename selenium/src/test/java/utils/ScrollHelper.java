package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class ScrollHelper {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static JavascriptExecutor js;

    public static void initialize(WebDriver webDriver, WebDriverWait webDriverWait) {
        driver = webDriver;
        wait = webDriverWait;
        js = (JavascriptExecutor) driver;
    }

    public static void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);
    }

    public static void scrollToPageBottom() {
        executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    private static Object executeScript(String script, Object... args) {
        return js.executeScript(script, args);
    }
}