package utils;

import java.sql.*;
import java.util.*;
import java.util.Date;

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
    }

    @FunctionalInterface
    public interface SQLSupplier<T> {
        T get() throws SQLException;
    }

    public static void assertTableExists(SQLSupplier<Boolean> tableExistsSupplier, String message) {
        try {
            boolean tableExists = tableExistsSupplier.get();
            if (!tableExists) {
                throw new AssertionError("⛔ " + message);
            }
        } catch (SQLException e) {
            throw new AssertionError("⛔ Ошибка при проверке существования таблицы");
        }
    }

    public static void assertColumnExists(SQLSupplier<Boolean> columnExistsSupplier, String message) {
        try {
            boolean columnExists = columnExistsSupplier.get();
            if (!columnExists) {
                throw new AssertionError("⛔ " + message);
            }
        } catch (SQLException e) {
            throw new AssertionError("⛔ Ошибка при проверке существования столбца в таблице ");
        }
    }

    public static void assertForeignKeyExists(SQLSupplier<Boolean> fkExistsSupplier, String message) {
        try {
            boolean fkExists = fkExistsSupplier.get();
            if (!fkExists) {
                throw new AssertionError("⛔ " + message);
            }
        } catch (SQLException e) {
            throw new AssertionError("⛔ Ошибка при проверке внешнего ключа");
        }
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("❌ " + message);
        }
    }

    public static void assertDatabaseConnectionValid(Connection connection, String message) throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                throw new AssertionError("⛔ " + message);
            }
            try (ResultSet rs = connection.createStatement().executeQuery("SELECT 1")) {
                assertTrue(rs.next(), "Проверка соединения с БД не удалась");
            }
        } catch (SQLException e) {
            throw new AssertionError("⛔ " + message + " - " + e.getMessage());
        }
    }

    public static void assertNoOrphanedRecords(int orphanedCount, String message) {
        if (orphanedCount > 0) {
            throw new AssertionError("⛔ " + message + " (найдено: " + orphanedCount + ")");
        }
    }

    public static void assertRecordCountEquals(int expectedCount, int actualCount, String entityName, String message) {
        if (actualCount != expectedCount) {
            throw new AssertionError("⛔ " + message + " (ожидалось: " + expectedCount + ", фактически: " + actualCount + ")");
        }
    }

    public static void assertRecordCountGreaterThan(int minCount, int actualCount, String message) {
        if (minCount > actualCount) {
            throw new AssertionError("⛔ " + message);
        }
    }

    public static void assertEntityDataMatches(String actualName, String expectedName,
                                               String actualType, String expectedType,
                                               int actualDuration, int expectedDuration,
                                               int actualPrice, int expectedPrice,
                                               String message) {
        List<String> errors = new ArrayList<>();

        if (!Objects.equals(actualName, expectedName)) {
            errors.add("Название: ожидалось '" + expectedName + "', фактически '" + actualName + "'");
        }
        if (!Objects.equals(actualType, expectedType)) {
            errors.add("Тип: ожидалось '" + expectedType + "', фактически '" + actualType + "'");
        }
        if (actualDuration != expectedDuration) {
            errors.add("Длительность: ожидалось " + expectedDuration + ", фактически " + actualDuration);
        }
        if (actualPrice != expectedPrice) {
            errors.add("Цена: ожидалось " + expectedPrice + ", фактически " + actualPrice);
        }

        if (!errors.isEmpty()) {
            throw new AssertionError("⛔ " + message + "\n" + String.join("\n", errors));
        }
    }

    public static void assertDurationValid(int duration, String entityName, String message) {
        if (duration <= 0) {
            throw new AssertionError("⛔ " + message + " (длительность: " + duration + ")");
        }
    }

    public static void assertPriceValueValid(int price, String entityName, String message) {
        if (price < 0) {
            throw new AssertionError("⛔ " + message + " (цена: " + price + ")");
        }
    }

    public static void assertCountValid(int count, String entityName, String message) {
        if (count < 0) {
            throw new AssertionError("⛔ " + message + " (количество: " + count + ")");
        }
    }

    public static void assertAgeValid(int age, String personName, String message) {
        if (age <= 0 || age > 150) {
            throw new AssertionError("⛔ " + message + " (возраст: " + age + ")");
        }
    }

    public static void assertDateValid(Date date, String message) {
        if (date == null) {
            throw new AssertionError("⛔ " + message);
        }
        if (date.after(new Date())) {
            throw new AssertionError("⛔ " + message + " (дата в будущем: " + date + ")");
        }
    }

    public static void assertEnumValueValid(String enumValue, String[] validValues, String message) {
        boolean valid = false;
        for (String validValue : validValues) {
            if (validValue.equals(enumValue)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new AssertionError("⛔ " + message +
                    "\nНедопустимое значение: " + enumValue +
                    "\nДопустимые значения: " + String.join(", ", validValues));
        }
    }

    public static void assertStringLengthValid(String value, int maxLength, String message) {
        if (value != null && value.length() > maxLength) {
            throw new AssertionError("⛔ " + message +
                    " (максимум: " + maxLength + ", фактически: " + value.length() + ")");
        }
    }

    public static void assertEntityHasMinimumCount(String entityName, int actualCount, int minCount, String message) {
        if (actualCount < minCount) {
            throw new AssertionError("⛔ " + message +
                    "\nОбъект: " + entityName +
                    "\nОжидалось минимум: " + minCount +
                    "\nФактически: " + actualCount);
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
}