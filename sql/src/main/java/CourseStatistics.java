public class CourseStatistics {
    private String courseName;
    private double averagePurchasesPerMonth;

    public CourseStatistics(String courseName, double averagePurchasesPerMonth) {
        this.courseName = courseName;
        this.averagePurchasesPerMonth = averagePurchasesPerMonth;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getAveragePurchasesPerMonth() {
        return averagePurchasesPerMonth;
    }
}