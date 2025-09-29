package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class AlertHelper {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void initialize(WebDriver webDriver, WebDriverWait webDriverWait) {
        driver = webDriver;
        wait = webDriverWait;
    }

    public static boolean isAlertPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public static String getAlertText() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }
}