package elements;

import utils.*;
import java.sql.*;
import java.util.*;

import static config.DatabaseConstants.*;

public class DatabaseElements {

    private Connection connection;

    public DatabaseElements() throws SQLException {
        this.connection = SQLHelper.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    // Методы для проверки структуры базы данных
    public boolean isTableExists(String tableName) throws SQLException {
        return SQLHelper.isTableExists(tableName);
    }

    public boolean isColumnExists(String tableName, String columnName) throws SQLException {
        return SQLHelper.isColumnExists(tableName, columnName);
    }

    public boolean isForeignKeyExists(String tableName, String fkName) throws SQLException {
        return SQLHelper.isForeignKeyExists(tableName, fkName);
    }

    // Методы для получения данных
    public int getRecordsCount(String tableName) throws SQLException {
        return SQLHelper.getTableRecordsCount(tableName);
    }

    public List<String> getCourseTypes() throws SQLException {
        return SQLHelper.getDistinctColumnValues(TABLE_COURSES, COLUMN_TYPE);
    }

    public ResultSet getCoursesWithInvalidPricePerHour() throws SQLException {
        return SQLHelper.executeCustomQuery(SQL_INVALID_PRICE_PER_HOUR);
    }

    public ResultSet getCoursesWithoutTeacher() throws SQLException {
        return SQLHelper.executeCustomQuery(SQL_COURSES_WITHOUT_TEACHER);
    }

    public ResultSet getSubscriptionsWithoutStudentOrCourse() throws SQLException {
        return SQLHelper.executeCustomQuery(SQL_SUBSCRIPTIONS_INTEGRITY);
    }

    public ResultSet getPopularCourses() throws SQLException {
        return SQLHelper.executeCustomQuery(SQL_POPULAR_COURSES);
    }

    public ResultSet getCourseById(int courseId) throws SQLException {
        return SQLHelper.getRecordById(TABLE_COURSES, COLUMN_ID, courseId);
    }

    public ResultSet getAllCourses() throws SQLException {
        return SQLHelper.getAllRecords(TABLE_COURSES);
    }

    public ResultSet getAllStudents() throws SQLException {
        return SQLHelper.getAllRecords(TABLE_STUDENTS);
    }

    public void close() {
        SQLHelper.closeConnection(connection);
    }
}