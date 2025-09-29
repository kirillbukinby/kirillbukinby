package utils;

import java.util.*;

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

    public static void assertStatusCode(int expectedStatusCode, int actualStatusCode, String message) {
        if (actualStatusCode != expectedStatusCode) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидаемый статус код: " + expectedStatusCode +
                    "\nФактический статус код: " + actualStatusCode);
        }
    }

    public static void assertResponseTimeLessThan(long actualTime, long maxTime, String message) {
        if (actualTime > maxTime) {
            throw new AssertionError("⛔ " + message +
                    "\nМаксимальное время: " + maxTime + "ms" +
                    "\nФактическое время: " + actualTime + "ms");
        }
    }

    public static void assertJsonFieldExists(String fieldValue, String message) {
        if (fieldValue == null) {
            throw new AssertionError("⛔ " + message + " (поле отсутствует в ответе)");
        }
    }

    public static void assertJsonFieldEquals(String expectedValue, String actualValue, String message) {
        if (!Objects.equals(expectedValue, actualValue)) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидаемое значение: '" + expectedValue + "'" +
                    "\nФактическое значение: '" + actualValue + "'");
        }
    }

    public static void assertJsonFieldEquals(int expectedValue, String actualValue, String message) {
        String expectedString = String.valueOf(expectedValue);
        if (!Objects.equals(expectedString, actualValue)) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидаемое значение: '" + expectedValue + "'" +
                    "\nФактическое значение: '" + actualValue + "'");
        }
    }

    public static void assertResponseNotEmpty(String actualResponse, String message) {
        if (actualResponse == null || actualResponse.isEmpty()) {
            throw new AssertionError("⛔ " + message + " (ответ пустой)");
        }
    }

    public static void assertListSize(int expectedSize, int actualSize, String message) {
        if (actualSize != expectedSize) {
            throw new AssertionError("⛔ " + message +
                    "\nОжидаемый размер: " + expectedSize +
                    "\nФактический размер: " + actualSize);
        }
    }
}