package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class BrowserHelper {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void initialize(WebDriver webDriver, WebDriverWait webDriverWait) {
        driver = webDriver;
        wait = webDriverWait;
    }


    public static void openUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL-адрес не может быть пустым или null");
        }
        driver.get(url);
    }

}