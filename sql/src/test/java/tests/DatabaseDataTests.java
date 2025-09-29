package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;
import java.sql.*;

import static config.CourseConstants.*;
import static config.DatabaseConstants.*;
import static utils.AssertionHelper.*;

public class DatabaseDataTests extends BaseDriver {

    private DatabaseElements dbElements;

    @BeforeEach
    public void setUpTest() {
        dbElements = databasePage.getDatabaseElements();
    }

    @Test
    @DisplayName("Проверка количества записей в таблицах")
    public void shouldHaveCorrectRecordCounts() throws SQLException {
        // Act
        int coursesCount = dbElements.getRecordsCount(TABLE_COURSES);
        int studentsCount = dbElements.getRecordsCount(TABLE_STUDENTS);
        int teachersCount = dbElements.getRecordsCount(TABLE_TEACHERS);

        // Assert
        assertAll(
                () -> assertRecordCountEquals(EXPECTED_COURSES_COUNT, coursesCount, TABLE_COURSES, ERROR_RECORD_COUNT_MISMATCH),
                () -> assertRecordCountEquals(EXPECTED_STUDENTS_COUNT, studentsCount, TABLE_STUDENTS, ERROR_RECORD_COUNT_MISMATCH),
                () -> assertRecordCountEquals(EXPECTED_TEACHERS_COUNT, teachersCount, TABLE_TEACHERS, ERROR_RECORD_COUNT_MISMATCH)

        );
    }

    @Test
    @DisplayName("Проверка валидности данных курсов")
    public void shouldCoursesHaveValidData() throws SQLException {
        // Act
        try (ResultSet rs = dbElements.getAllCourses()) {
            while (rs.next()) {
                String courseName = rs.getString(COLUMN_NAME);
                int duration = rs.getInt(COLUMN_DURATION);
                int price = rs.getInt(COLUMN_PRICE);
                int studentsCount = rs.getInt(COLUMN_STUDENTS_COUNT);
                String type = rs.getString(COLUMN_TYPE);

                // Assert
                assertAll(
                        () -> assertDurationValid(duration, courseName, ERROR_INVALID_DURATION),
                        () -> assertPriceValueValid(price, courseName, ERROR_INVALID_PRICE),
                        () -> assertCountValid(studentsCount, courseName, ERROR_INVALID_STUDENTS_COUNT),
                        () -> assertEnumValueValid(type, COURSE_TYPES, ERROR_INVALID_ENUM_VALUE),
                        () -> assertStringLengthValid(courseName, MAX_COURSE_NAME_LENGTH, ERROR_STRING_LENGTH_EXCEEDED)
                );
            }
        }
    }

    @Test
    @DisplayName("Проверка валидности данных студентов")
    public void shouldStudentsHaveValidData() throws SQLException {
        // Act
        try (ResultSet rs = dbElements.getAllStudents()) {
            while (rs.next()) {
                String studentName = rs.getString(COLUMN_NAME);
                int age = rs.getInt(COLUMN_AGE);
                Date regDate = rs.getDate(COLUMN_REGISTRATION_DATE);

                // Assert
                assertAll(
                        () -> assertAgeValid(age, studentName, ERROR_INVALID_AGE),
                        () -> assertDateValid(regDate, ERROR_DATE_INVALID),
                        () -> assertStringLengthValid(studentName, MAX_PERSON_NAME_LENGTH, ERROR_STRING_LENGTH_EXCEEDED)
                );
            }
        }
    }
}