package utils;

import java.sql.*;
import java.util.*;

import static config.DatabaseConstants.*;

public class SQLHelper {

    private static final String URL = JDBC_PREFIX + MYSQL_DRIVER + DATABASE_HOST + DATABASE_PORT + DATABASE_NAME + CONNECTION_PARAMS;
    private static final String USER = DATABASE_USER;
    private static final String PASSWORD = DATABASE_PASSWORD;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Ошибка при закрытии соединения: " + e.getMessage());
            }
        }
    }

    // Универсальные методы для проверки структуры БД
    public static boolean isTableExists(String tableName) throws SQLException {
        String sql =
                "SELECT COUNT(*) FROM information_schema.tables " +
                        "WHERE table_schema = DATABASE() AND table_name = ?";
        return executeCountQuery(sql, tableName) > 0;
    }

    public static boolean isColumnExists(String tableName, String columnName) throws SQLException {
        String sql =
                "SELECT COUNT(*) FROM information_schema.columns " +
                        "WHERE table_schema = DATABASE() AND table_name = ? AND column_name = ?";
        return executeCountQuery(sql, tableName, columnName) > 0;
    }

    public static boolean isForeignKeyExists(String tableName, String fkName) throws SQLException {
        String sql =
                "SELECT COUNT(*) FROM information_schema.table_constraints " +
                        "WHERE table_schema = DATABASE() AND table_name = ? " +
                        "AND constraint_name = ? AND constraint_type = 'FOREIGN KEY'";
        return executeCountQuery(sql, tableName, fkName) > 0;
    }

    // Универсальные методы для работы с данными
    public static int getTableRecordsCount(String tableName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tableName;
        return executeCountQuery(sql);
    }

    public static List<String> getDistinctColumnValues(String tableName, String columnName) throws SQLException {
        String sql = "SELECT DISTINCT " + columnName + " FROM " + tableName;
        return executeQueryForList(sql, columnName);
    }

    // Универсальные методы для выполнения запросов
    public static ResultSet executeCustomQuery(String sql) throws SQLException {
        return executeQuery(sql);
    }

    public static ResultSet getAllRecords(String tableName) throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        return executeQuery(sql);
    }

    public static ResultSet getRecordById(String tableName, String idColumn, int id) throws SQLException {
        String sql = "SELECT * FROM " + tableName + " WHERE " + idColumn + " = ?";
        return executeQueryWithParams(sql, id);
    }

    // Базовые методы выполнения запросов
    private static int executeCountQuery(String sql, Object... params) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    private static List<String> executeQueryForList(String sql, String columnName, Object... params) throws SQLException {
        List<String> results = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(rs.getString(columnName));
                }
            }
        }
        return results;
    }

    private static ResultSet executeQuery(String sql) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeQuery();
    }

    private static ResultSet executeQueryWithParams(String sql, Object... params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }

        return stmt.executeQuery();
    }
}