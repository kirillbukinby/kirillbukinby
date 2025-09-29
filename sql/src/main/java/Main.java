import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            StatisticsCalculator calculator = new StatisticsCalculator();

            List<CourseStatistics> statistics = calculator.calculateAveragePurchasesPerMonth();

            System.out.println("Среднее количество покупок в месяц для каждого курса за 2018 год:");
            System.out.println("-------------------------------------------------------------------------");
            System.out.printf("%-40s | %s\n", "Название курса", "Среднее кол-во покупок в месяц");
            System.out.println("-------------------------------------------------------------------------");

            for (CourseStatistics stat : statistics) {
                System.out.printf("%-40s | %.2f\n", stat.getCourseName(), stat.getAveragePurchasesPerMonth());
            }

        } catch (Exception e) {
            System.err.println("Ошибка при выполнении программы: " + e.getMessage());
            e.printStackTrace();
        }
    }
}