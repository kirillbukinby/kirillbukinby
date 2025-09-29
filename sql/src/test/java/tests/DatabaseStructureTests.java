package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;
import java.sql.*;

import static config.DatabaseConstants.*;
import static utils.AssertionHelper.*;

public class DatabaseStructureTests extends BaseDriver {

    private DatabaseElements dbElements;

    @BeforeEach
    public void setUpTest() {
        dbElements = databasePage.getDatabaseElements();
    }

    @Test
    @DisplayName("Проверка соединения с базой данных")
    public void shouldEstablishDatabaseConnection() throws SQLException {
        // Assert
        assertDatabaseConnectionValid(dbElements.getConnection(), ERROR_DB_CONNECTION_FAILED);
    }

    @Test
    @DisplayName("Проверка существования всех таблиц")
    public void shouldAllTablesExist() {
        // Assert
        assertAll(
                () -> assertTableExists(() -> dbElements.isTableExists(TABLE_COURSES), ERROR_TABLE_NOT_EXISTS),
                () -> assertTableExists(() -> dbElements.isTableExists(TABLE_STUDENTS), ERROR_TABLE_NOT_EXISTS),
                () -> assertTableExists(() -> dbElements.isTableExists(TABLE_TEACHERS), ERROR_TABLE_NOT_EXISTS),
                () -> assertTableExists(() -> dbElements.isTableExists(TABLE_SUBSCRIPTIONS), ERROR_TABLE_NOT_EXISTS),
                () -> assertTableExists(() -> dbElements.isTableExists(TABLE_PURCHASE_LIST), ERROR_TABLE_NOT_EXISTS)
        );
    }

    @Test
    @DisplayName("Проверка основных столбцов таблицы Courses")
    public void shouldCoursesTableHaveRequiredColumns() {
        // Assert
        assertAll(
                () -> assertColumnExists(() -> dbElements.isColumnExists(TABLE_COURSES, COLUMN_ID), ERROR_COLUMN_NOT_EXISTS),
                () -> assertColumnExists(() -> dbElements.isColumnExists(TABLE_COURSES, COLUMN_NAME), ERROR_COLUMN_NOT_EXISTS),
                () -> assertColumnExists(() -> dbElements.isColumnExists(TABLE_COURSES, COLUMN_DURATION), ERROR_COLUMN_NOT_EXISTS),
                () -> assertColumnExists(() -> dbElements.isColumnExists(TABLE_COURSES, COLUMN_TYPE), ERROR_COLUMN_NOT_EXISTS),
                () -> assertColumnExists(() -> dbElements.isColumnExists(TABLE_COURSES, COLUMN_PRICE), ERROR_COLUMN_NOT_EXISTS)
        );
    }

    @Test
    @DisplayName("Проверка внешних ключей")
    public void shouldForeignKeysExist() {
        // Assert
        assertAll(
                () -> assertForeignKeyExists(() -> dbElements.isForeignKeyExists(TABLE_COURSES, FK_TEACHER), ERROR_FK_NOT_EXISTS),
                () -> assertForeignKeyExists(() -> dbElements.isForeignKeyExists(TABLE_SUBSCRIPTIONS, FK_COURSE), ERROR_FK_NOT_EXISTS),
                () -> assertForeignKeyExists(() -> dbElements.isForeignKeyExists(TABLE_SUBSCRIPTIONS, FK_STUDENT), ERROR_FK_NOT_EXISTS)
        );
    }
}