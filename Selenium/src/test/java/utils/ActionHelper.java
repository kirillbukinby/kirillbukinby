package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;

public class ActionHelper {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions actions;

    public static void initialize(WebDriver webDriver, WebDriverWait webDriverWait) {
        driver = webDriver;
        wait = webDriverWait;
        actions = new Actions(driver);
    }

    public static void moveToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        actions.moveToElement(element).perform();
    }

    public static void moveToAndClickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        actions.moveToElement(element).click().perform();
    }
}