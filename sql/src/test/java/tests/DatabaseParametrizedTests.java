package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.sql.*;
import java.util.*;

import static config.CourseConstants.*;
import static config.DatabaseConstants.*;
import static utils.AssertionHelper.*;

public class DatabaseParametrizedTests extends BaseDriver {

    private DatabaseElements dbElements;

    @BeforeEach
    public void setUpTest() {
        dbElements = databasePage.getDatabaseElements();
    }

    @ParameterizedTest
    @ValueSource(strings = {COURSE_TYPE_DESIGN, COURSE_TYPE_PROGRAMMING, COURSE_TYPE_MARKETING, COURSE_TYPE_MANAGEMENT})
    @DisplayName("Параметризированная проверка типов курсов")
    public void shouldCourseTypeBeValid(String courseType) throws SQLException {
        // Act
        List<String> actualTypes = dbElements.getCourseTypes();

        // Assert
        assertListContainsIgnoreCase(courseType, actualTypes,
                String.format(ERROR_COURSE_TYPE_MISSING, courseType));
    }

    @ParameterizedTest
    @CsvSource({
            TEST_COURSE_ID_1 + ", " + COURSE_NAME_WEB_DEVELOPER + ", " + COURSE_TYPE_PROGRAMMING + ", " + COURSE_DURATION_10 + ", " + COURSE_PRICE_189600,
            TEST_COURSE_ID_3 + ", " + COURSE_NAME_JAVA_DEVELOPER + ", " + COURSE_TYPE_PROGRAMMING + ", " + COURSE_DURATION_5 + ", " + COURSE_PRICE_78000,
            TEST_COURSE_ID_12 + ", " + COURSE_NAME_WEB_DESIGN + ", " + COURSE_TYPE_DESIGN + ", " + COURSE_DURATION_10 + ", " + COURSE_PRICE_40613
    })
    @DisplayName("Параметризированная проверка конкретных курсов")
    public void shouldSpecificCoursesExist(int courseId, String expectedName, String expectedType,
                                           int expectedDuration, int expectedPrice) throws SQLException {
        // Act
        try (ResultSet rs = dbElements.getCourseById(courseId)) {
            // Assert
            assertTrue(rs.next(),
                    String.format(ERROR_COURSE_NOT_FOUND + COURSE_ID_FORMAT, courseId));

            // Получаем фактические значения
            String actualName = rs.getString(COLUMN_NAME);
            String actualType = rs.getString(COLUMN_TYPE);
            int actualDuration = rs.getInt(COLUMN_DURATION);
            int actualPrice = rs.getInt(COLUMN_PRICE);

            // Assert
            assertEntityDataMatches(actualName, expectedName, actualType, expectedType,
                    actualDuration, expectedDuration, actualPrice, expectedPrice,
                    String.format(ERROR_COURSE_DATA_MISMATCH, courseId));
        }
    }

    @ParameterizedTest
    @CsvSource({
            SALARY_20000 + ", " + EXPECTED_TEACHERS_3,
            SALARY_25000 + ", " + EXPECTED_TEACHERS_2,
            SALARY_30000 + ", " + EXPECTED_TEACHERS_1
    })
    @DisplayName("Параметризированная проверка преподавателей с высокой зарплатой")
    public void shouldTeachersWithHighSalaryExist(int minSalary, int expectedMinCount) throws SQLException {
        // Act
        try (Connection conn = dbElements.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_COUNT_TEACHERS_BY_SALARY)) {
            stmt.setInt(1, minSalary);
            try (ResultSet rs = stmt.executeQuery()) {
                int actualCount = rs.next() ? rs.getInt(COLUMN_COUNT) : 0;

                // Assert
                assertRecordCountGreaterThan(expectedMinCount, actualCount, ERROR_RECORD_COUNT_TOO_LOW);
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {TEST_AGE_18, TEST_AGE_25, TEST_AGE_30, TEST_AGE_35, TEST_AGE_40})
    @DisplayName("Параметризированная проверка студентов по возрасту")
    public void shouldStudentsWithSpecificAgeExist(int age) throws SQLException {
        // Act
        try (Connection conn = dbElements.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_COUNT_STUDENTS_BY_AGE)) {
            stmt.setInt(1, age);
            try (ResultSet rs = stmt.executeQuery()) {
                int actualCount = rs.next() ? rs.getInt(COLUMN_COUNT) : 1;

                // Assert
                assertRecordCountGreaterThan(0, actualCount, ERROR_STUDENTS_AGE_NOT_FOUND);
            }
        }
    }
}