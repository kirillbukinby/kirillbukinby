package utils;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;
import java.util.*;
import java.util.regex.*;

import static utils.WindowHelper.*;

public class AssertionHelper {
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

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("❌ " + message);
        }
    }

    public static void assertTextEquals(String expected, String actual, String message) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError("⛔  " + message +
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

    public static void assertTextContainsIgnoreCase(String expected, String actual, String message) {
        if (actual == null || !actual.toLowerCase().contains(expected.toLowerCase())) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидалось содержание: '" + expected + "'" +
                    "\nФактический текст: '" + actual + "'");
        }
    }

    public static void assertListContainsIgnoreCase(String expected, List<String> actualList, String message) {
        if (actualList == null) {
            throw new AssertionError("⛔ " + message + " - список равен null");
        }

        if (actualList.stream().noneMatch(item -> item.equalsIgnoreCase(expected))) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидалось: '" + expected + "'" +
                    "\nВ списке: " + actualList);
        }
    }

    public static void assertUrlEquals(String expectedUrl, String message) {
        String actualUrl = getCurrentUrlSafely();
        if (!actualUrl.equals(expectedUrl)) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидаемый URL: '" + expectedUrl + "'" +
                    "\nФактический URL: '" + actualUrl + "'");
        }
    }


    public static void assertUrlContains(String expectedPart, String message) {
        String actualUrl = WebDriverRunner.url();
        if (!actualUrl.contains(expectedPart)) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидалось содержание: '" + expectedPart + "'" +
                    "\nФактический URL: '" + actualUrl + "'");
        }
    }

    public static void assertElementDisplayed(SelenideElement element, String message) {
        if (!element.isDisplayed()) {
            throw new AssertionError("⛔ " + message);
        }
    }

    public static void assertElementEnabled(SelenideElement element, String message) {
        if (!element.isEnabled()) {
            throw new AssertionError("⛔ " + message);
        }
    }

    public static void assertAlertTextEquals(String expectedText, String message) {
        try {
            Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
            String actualText = alert.getText();
            if (!actualText.equals(expectedText)) {
                throw new AssertionError("⛔ " + message +
                        "\nОжидаемый текст alert: '" + expectedText + "'" +
                        "\nФактический текст alert: '" + actualText + "'");
            }
        } catch (Exception e) {
            throw new AssertionError("⛔ " + message + " (alert не появился)");
        }
    }

    public static void assertWindowCount(int expectedCount, String message) {
        int actualCount = getWindowCount(); // Используем безопасный метод
        if (actualCount != expectedCount) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидаемое количество окон: " + expectedCount +
                    "\nФактическое количество окон: " + actualCount);
        }
    }

    public static void assertAlertPresent(String message) {
        try {
            Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
            if (alert == null) {
                throw new AssertionError("⛔ " + message + " (alert не присутствует)");
            }
        } catch (Exception e) {
            throw new AssertionError("⛔ " + message + " (alert не появился: " + e.getMessage() + ")");
        }
    }

    public static void assertAlertNotPresent(String message) {
        try {
            Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
            if (alert != null) {
                throw new AssertionError("⛔ " + message + " (alert присутствует, когда не должен)");
            }
        } catch (Exception e) {
        }
    }

    public static void assertFieldEmpty(SelenideElement element, String message) {
        String actualValue = element.getValue();
        if (actualValue != null && !actualValue.isEmpty()) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидалось: пустое поле" +
                    "\nФактически: '" + actualValue + "'");
        }
    }

    public static void assertResultsNotEmpty(int resultsCount, String message) {
        if (resultsCount <= 0) {
            throw new AssertionError("⛔ " + message + " (найдено результатов: " + resultsCount + ")");
        }
    }

}