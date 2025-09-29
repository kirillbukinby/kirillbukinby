package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;
import java.sql.*;

import static config.CourseConstants.*;
import static config.DatabaseConstants.*;
import static utils.AssertionHelper.*;

public class DatabaseBusinessLogicTests extends BaseDriver {

    private DatabaseElements dbElements;

    @BeforeEach
    public void setUpTest() {
        dbElements = databasePage.getDatabaseElements();
    }

    @Test
    @DisplayName("Проверка корректности расчета цены за час")
    public void shouldPricePerHourBeCalculatedCorrectly() throws SQLException {
        // Arrange
        double tolerance = PRICE_CALCULATION_TOLERANCE;

        // Act
        try (ResultSet rs = dbElements.getCoursesWithInvalidPricePerHour()) {
            int invalidCount = 0;

            while (rs.next()) {
                double actualPrice = rs.getDouble(COLUMN_PRICE_PER_HOUR);
                double calculatedPrice = rs.getDouble(COLUMN_CALCULATED_PRICE);

                if (Math.abs(calculatedPrice - actualPrice) > tolerance) {
                    invalidCount++;
                }
            }

            // Assert
            assertNoOrphanedRecords(invalidCount, ERROR_INVALID_PRICE_CALCULATION);
        }
    }


    @Test
    @DisplayName("Проверка целостности связей подписок")
    public void shouldSubscriptionsHaveValidRelationships() throws SQLException {
        // Act
        try (ResultSet rs = dbElements.getSubscriptionsWithoutStudentOrCourse()) {
            int orphanedCount = 0;

            while (rs.next()) {
                orphanedCount++;
            }

            // Assert
            assertNoOrphanedRecords(orphanedCount, ERROR_RELATIONSHIP_INTEGRITY_VIOLATION);
        }
    }

    @Test
    @DisplayName("Проверка что все курсы имеют преподавателей")
    public void shouldAllCoursesHaveTeachers() throws SQLException {
        // Act
        try (ResultSet rs = dbElements.getCoursesWithoutTeacher()) {
            int coursesWithoutTeacher = 0;

            while (rs.next()) {
                coursesWithoutTeacher++;
            }

            // Assert
            assertNoOrphanedRecords(coursesWithoutTeacher, ERROR_COURSE_WITHOUT_TEACHER);
        }
    }


    @Test
    @DisplayName("Проверка популярных курсов")
    public void shouldPopularCoursesHaveManyStudents() throws SQLException {
        // Arrange
        int minStudentsForPopular = MIN_STUDENTS_FOR_POPULAR_COURSE;

        // Act
        try (ResultSet rs = dbElements.getPopularCourses()) {
            int popularCoursesCount = 0;

            while (rs.next()) {
                popularCoursesCount++;
                String courseName = rs.getString(COLUMN_NAME);
                int studentsCount = rs.getInt(COLUMN_STUDENTS_COUNT);

                // Assert
                assertAll(
                        () -> assertCountValid(studentsCount, courseName, ERROR_INVALID_STUDENTS_COUNT),
                        () -> assertEntityHasMinimumCount(courseName, studentsCount, minStudentsForPopular, ERROR_POPULAR_COURSES_CRITERIA)
                );
            }

            // Assert
            assertRecordCountGreaterThan(0, popularCoursesCount, ERROR_RECORD_COUNT_TOO_LOW);
        }
    }

    @Test
    @DisplayName("Проверка корректности дат регистрации")
    public void shouldRegistrationDatesBeValid() throws SQLException {
        // Act
        try (Connection conn = dbElements.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_STUDENTS_REGISTRATION_DATES)) {

            while (rs.next()) {
                Date regDate = rs.getDate(COLUMN_REGISTRATION_DATE);

                // Assert
                assertDateValid(regDate, ERROR_DATE_INVALID);
            }
        }
    }

    @Test
    @DisplayName("Проверка уникальности имен курсов")
    public void shouldCourseNamesBeUnique() throws SQLException {
        // Act
        try (Connection conn = dbElements.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_COURSES_DUPLICATE_NAMES)) {

            int duplicateCount = 0;
            while (rs.next()) {
                duplicateCount++;
            }

            // Assert
            assertNoOrphanedRecords(duplicateCount, ERROR_COURSE_NAMES_SHOULD_BE_UNIQUE);
        }
    }
}