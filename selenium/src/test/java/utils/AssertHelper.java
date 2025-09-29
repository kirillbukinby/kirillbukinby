package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.*;
import java.util.regex.*;

public class AssertHelper {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void initialize(WebDriver webDriver, WebDriverWait webDriverWait) {
        driver = webDriver;
        wait = webDriverWait;
    }

    public static void assertAll(Runnable... assertions) {
        List<AssertionError> errors = new ArrayList<>();

        for (Runnable assertion : assertions) {
            try {
                assertion.run();
            } catch (AssertionError e) {
                errors.add(e);
            }
        }

        if (!errors.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("⛔ \n");
            for (int i = 0; i < errors.size(); i++) {
                errorMessage.append(i + 1).append(") ").append(errors.get(i).getMessage()).append("\n");
            }
            throw new AssertionError(errorMessage.toString());
        }

        System.out.println("✅ Все ассерты прошли успешно");
    }

    public static void assertElementDisplayed(boolean isDisplayed, String message) {
        if (!isDisplayed) {
            throw new AssertionError("⛔ " + message + " (элемент не отображается)");
        }
    }

    public static void assertUrlEquals(String expectedUrl, String message) {
        String actualUrl = driver.getCurrentUrl();
        if (!Objects.equals(expectedUrl, actualUrl)) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидаемый URL: '" + expectedUrl + "'" +
                    "\nФактический URL: '" + actualUrl + "'");
        }
    }

    public static void assertWindowCount(int expectedCount, WebDriver driver, String message) {
        int actualCount = driver.getWindowHandles().size();
        if (actualCount != expectedCount) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидаемое количество окон: " + expectedCount +
                    "\nФактическое количество окон: " + actualCount);
        }
    }

    public static void assertUrlContains(String expectedPart, String message) {
        if (driver == null) {
            throw new IllegalStateException("⛔ WebDriver не инициализирован. Вызовите AssertHelper.initialize() сначала");
        }

        String actualUrl = driver.getCurrentUrl();
        if (!actualUrl.contains(expectedPart)) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидалось содержание: '" + expectedPart + "'" +
                    "\nФактический URL: '" + actualUrl + "'");
        }
    }

    public static void assertTextEquals(String expected, String actual, String message) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидаемый текст: '" + expected + "'" +
                    "\nФактический текст: '" + actual + "'");
        }
    }

    public static void assertTextContains(String expected, String actual, String message) {
        if (actual == null || !actual.contains(expected)) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидалось содержание: '" + expected + "'" +
                    "\nФактический текст: '" + actual + "'");
        }
    }

    public static void assertTextContainsIgnoreCase(String expected, String actual, String message) {
        if (actual == null || !actual.toLowerCase().contains(expected.toLowerCase())) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидаемый текст (ignore case): '" + expected + "'" +
                    "\nФактический текст: '" + actual + "'");
        }
    }

    public static void assertAlertPresent(boolean isPresent, String message) {
        if (!isPresent) {
            throw new AssertionError("⛔ " + message + " (alert не присутствует)");
        }
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("❌ " + message);
        }
    }

    public static void assertAllTextsMatchPattern(String expectedRegex, List<String> actualTexts, String errorMessage) {
        if (actualTexts == null) {
            throw new AssertionError("⛔ " + errorMessage + " - список текстов равен null");
        }

        if (actualTexts.isEmpty()) {
            throw new AssertionError("⛔ " + errorMessage + " - список текстов пуст");
        }

        for (int i = 0; i < actualTexts.size(); i++) {
            String actualText = actualTexts.get(i);
            if (actualText == null || !Pattern.matches(expectedRegex, actualText)) {
                throw new AssertionError("⛔ " + errorMessage +
                        "\nОжидаемый шаблон: '" + expectedRegex + "'" +
                        "\nЭлемент #" + (i + 1) +
                        "\nФактический текст: '" + actualText + "'");
            }
        }
    }

    public static void assertListContainsIgnoreCase(String expected, List<String> actualList, String message) {
        if (actualList == null) {
            throw new AssertionError("⛔ " + message + " - список равен null");
        }

        if (actualList.stream().noneMatch(item -> item != null && item.equalsIgnoreCase(expected))) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидалось: '" + expected + "'" +
                    "\nВ списке: " + actualList);
        }
    }

    public static void assertResultsNotEmpty(int resultsCount, String message) {
        if (resultsCount <= 0) {
            throw new AssertionError("⛔ " + message + " (найдено результатов: " + resultsCount + ")");
        }
    }
}