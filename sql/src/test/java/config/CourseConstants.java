package config;

public class CourseConstants {
    // Типы курсов
    public static final String COURSE_TYPE_DESIGN = "DESIGN";
    public static final String COURSE_TYPE_PROGRAMMING = "PROGRAMMING";
    public static final String COURSE_TYPE_MARKETING = "MARKETING";
    public static final String COURSE_TYPE_MANAGEMENT = "MANAGEMENT";

    // Названия курсов
    public static final String COURSE_NAME_WEB_DEVELOPER = "'Веб-разработчик c 0 до PRO'";
    public static final String COURSE_NAME_JAVA_DEVELOPER = "'Java-разработчик'";
    public static final String COURSE_NAME_WEB_DESIGN = "'Веб-дизайн с нуля до PRO'";

    // Возраста для тестирования
    public static final int TEST_AGE_18 = 18;
    public static final int TEST_AGE_25 = 25;
    public static final int TEST_AGE_30 = 30;
    public static final int TEST_AGE_35 = 35;
    public static final int TEST_AGE_40 = 40;

    // ID курсов для тестов
    public static final int TEST_COURSE_ID_1 = 1;
    public static final int TEST_COURSE_ID_3 = 3;
    public static final int TEST_COURSE_ID_12 = 12;

    // Длительности курсов
    public static final int COURSE_DURATION_10 = 10;
    public static final int COURSE_DURATION_5 = 5;

    // Цены курсов
    public static final int COURSE_PRICE_189600 = 189600;
    public static final int COURSE_PRICE_78000 = 78000;
    public static final int COURSE_PRICE_40613 = 40613;

    // Зарплаты для тестов
    public static final int SALARY_20000 = 20000;
    public static final int SALARY_25000 = 25000;
    public static final int SALARY_30000 = 30000;

    // Ожидаемые количества преподавателей для зарплат
    public static final int EXPECTED_TEACHERS_3 = 3;
    public static final int EXPECTED_TEACHERS_2 = 2;
    public static final int EXPECTED_TEACHERS_1 = 1;

    // Ожидаемые количества записей
    public static final int EXPECTED_COURSES_COUNT = 46;
    public static final int EXPECTED_STUDENTS_COUNT = 100;
    public static final int EXPECTED_TEACHERS_COUNT = 50;

    // Максимальные длины строк
    public static final int MAX_COURSE_NAME_LENGTH = 500;
    public static final int MAX_PERSON_NAME_LENGTH = 500;

    // Допустимые отклонения
    public static final double PRICE_CALCULATION_TOLERANCE = 0.05;
    public static final int MIN_STUDENTS_FOR_POPULAR_COURSE = 100;
}
