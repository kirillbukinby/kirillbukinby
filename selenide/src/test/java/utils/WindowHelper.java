package utils;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.*;
import java.util.*;

public class WindowHelper {

    public static int getWindowCount() {
        try {
            return WebDriverRunner.getWebDriver().getWindowHandles().size();
        } catch (WebDriverException e) {
            System.out.println("Ошибка при получении количества окон: " + e.getMessage());
            return 0;
        }
    }

    public static String getCurrentUrlSafely() {
        try {
            return WebDriverRunner.getWebDriver().getCurrentUrl();
        } catch (WebDriverException e) {
            System.out.println("Ошибка при получении текущего URL: " + e.getMessage());
            return "неизвестный_url";
        }
    }

    public static void waitForNewWindow(int initialCount, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), timeout);
            wait.until(d -> {
                try {
                    return d.getWindowHandles().size() > initialCount;
                } catch (WebDriverException e) {
                    System.out.println("Ошибка при ожидании нового окна: " + e.getMessage());
                    return false;
                }
            });
        } catch (TimeoutException e) {
            System.out.println("Таймаут ожидания нового окна: " + e.getMessage());
        }
    }

    public static void switchToWindow(int index) {
        try {
            Set<String> windowHandles = WebDriverRunner.getWebDriver().getWindowHandles();
            if (index < windowHandles.size()) {
                String handle = (String) windowHandles.toArray()[index];
                WebDriverRunner.getWebDriver().switchTo().window(handle);
                waitForBasicStability(Duration.ofSeconds(5));
            }
        } catch (WebDriverException e) {
            System.out.println("Ошибка при переключении на окно: " + e.getMessage());
        }
    }

    public static void switchToNewWindow(int initialWindowsCount, Duration timeout) {
        waitForNewWindow(initialWindowsCount, timeout);
        switchToWindow(1);
    }

    private static void waitForBasicStability(Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), timeout);
            wait.until(d -> {
                try {
                    return ((JavascriptExecutor) d)
                            .executeScript("return document.body != null");
                } catch (WebDriverException e) {
                    System.out.println("Ошибка проверки стабильности страницы: " + e.getMessage());
                    return false;
                }
            });
        } catch (TimeoutException e) {
            System.out.println("Таймаут проверки стабильности страницы, продолжаем...");
        }
    }

}