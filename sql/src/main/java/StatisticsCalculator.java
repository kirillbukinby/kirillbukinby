import java.sql.*;
import java.util.*;

public class StatisticsCalculator {

    public List<CourseStatistics> calculateAveragePurchasesPerMonth() throws SQLException {
        List<CourseStatistics> statistics = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnector.getConnection();

            String sql = "SELECT " +
                    "course_name, " +
                    "COUNT(*) AS total_purchases, " +
                    "MIN(MONTH(subscription_date)) AS first_month, " +
                    "MAX(MONTH(subscription_date)) AS last_month " +
                    "FROM PurchaseList " +
                    "WHERE YEAR(subscription_date) = 2018 " +
                    "GROUP BY course_name";

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                int totalPurchases = resultSet.getInt("total_purchases");
                int firstMonth = resultSet.getInt("first_month");
                int lastMonth = resultSet.getInt("last_month");

                int monthsCount = lastMonth - firstMonth + 1;

                double averagePurchasesPerMonth = (double) totalPurchases / monthsCount;

                statistics.add(new CourseStatistics(courseName, averagePurchasesPerMonth));
            }

            return statistics;

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }
}