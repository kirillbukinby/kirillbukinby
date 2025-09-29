package config;

public class DatabaseConstants {
    // Настройки подключения к базе данных
    public static final String DATABASE_USER = "root";
    public static final String DATABASE_PASSWORD = "Root1234!@";
    public static final String JDBC_PREFIX = "jdbc:";
    public static final String MYSQL_DRIVER = "mysql://";
    public static final String DATABASE_HOST = "localhost:";
    public static final int DATABASE_PORT = 3306;
    public static final String DATABASE_NAME = "/database";
    public static final String CONNECTION_PARAMS = "?useSSL=false&serverTimezone=UTC";

    // Названия таблиц
    public static final String TABLE_COURSES = "Courses";
    public static final String TABLE_STUDENTS = "Students";
    public static final String TABLE_TEACHERS = "Teachers";
    public static final String TABLE_SUBSCRIPTIONS = "Subscriptions";
    public static final String TABLE_PURCHASE_LIST = "PurchaseList";

    // Названия столбцов
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_PRICE_PER_HOUR = "price_per_hour";
    public static final String COLUMN_STUDENTS_COUNT = "students_count";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_REGISTRATION_DATE = "registration_date";
    public static final String COLUMN_COUNT = "count";
    public static final String COLUMN_CALCULATED_PRICE = "calculated_price";

    // Форматы для сообщений
    public static final String COURSE_ID_FORMAT = " (ID: %d)";

    // Внешние ключи
    public static final String FK_TEACHER = "teacher";
    public static final String FK_COURSE = "course";
    public static final String FK_STUDENT = "student";

    // Enum значения
    public static final String[] COURSE_TYPES = {"DESIGN", "PROGRAMMING", "MARKETING", "MANAGEMENT", "BUSINESS"};

    // SQL запросы
    public static final String SQL_INVALID_PRICE_PER_HOUR =
                   "SELECT name, price, duration, price_per_hour, " +
                   "ROUND(price / duration, 2) as calculated_price " +
                   "FROM Courses " +
                   "WHERE ABS(price_per_hour - (price / duration)) > 0.01";

    public static final String SQL_COURSES_WITHOUT_TEACHER =
                   "SELECT * FROM Courses WHERE teacher_id IS NULL";

    public static final String SQL_SUBSCRIPTIONS_INTEGRITY =
                   "SELECT Subscriptions.* " +
                   "FROM Subscriptions " +
                   "LEFT JOIN Students ON Subscriptions.student_id = Students.id " +
                   "LEFT JOIN Courses ON Subscriptions.course_id = Courses.id " +
                   "WHERE Students.id IS NULL OR Courses.id IS NULL";

    public static final String SQL_POPULAR_COURSES =
                   "SELECT name, students_count FROM Courses " +
                   "WHERE students_count > 100 ORDER BY students_count DESC";

    public static final String SQL_COUNT_TEACHERS_BY_SALARY =
                   "SELECT COUNT(*) as count FROM Teachers WHERE salary >= ?";

    public static final String SQL_COUNT_STUDENTS_BY_AGE =
                   "SELECT COUNT(*) as count FROM Students WHERE age = ?";

    public static final String SQL_STUDENTS_REGISTRATION_DATES =
                   "SELECT name, registration_date FROM Students";

    public static final String SQL_COURSES_DUPLICATE_NAMES =
                   "SELECT name, COUNT(*) as count " +
                   "FROM Courses " +
                   "GROUP BY name " +
                   "HAVING COUNT(*) > 1";

    // Сообщения об ошибках
    public static final String ERROR_DATE_INVALID = "Некорректная дата";
    public static final String ERROR_COURSE_NOT_FOUND = "Курс не найден в базе данных";
    public static final String ERROR_COURSE_NAMES_SHOULD_BE_UNIQUE = "Названия курсов должны быть уникальными";
    public static final String ERROR_TABLE_NOT_EXISTS = "Таблица должна существовать в базе данных";
    public static final String ERROR_COLUMN_NOT_EXISTS = "Столбец должен существовать в указанной таблице";
    public static final String ERROR_FK_NOT_EXISTS = "Внешний ключ должен существовать в указанной таблице";
    public static final String ERROR_RECORD_COUNT_MISMATCH = "Количество записей в таблице не соответствует ожидаемому";
    public static final String ERROR_RECORD_COUNT_TOO_LOW = "Количество записей должно быть больше минимального требуемого значения";
    public static final String ERROR_INVALID_PRICE = "Цена курса должна быть положительной";
    public static final String ERROR_INVALID_DURATION = "Длительность курса должна быть положительной";
    public static final String ERROR_INVALID_STUDENTS_COUNT = "Количество студентов курса должно быть неотрицательным";
    public static final String ERROR_INVALID_AGE = "Возраст должен быть в разумных пределах от 1 до 150 лет";
    public static final String ERROR_INVALID_ENUM_VALUE = "Тип курса должен быть одним из допустимых значений";
    public static final String ERROR_STRING_LENGTH_EXCEEDED = "Длина текстового поля превышает максимально допустимую";
    public static final String ERROR_INVALID_PRICE_CALCULATION = "Расчет цены за час для всех курсов должен быть корректным";
    public static final String ERROR_RELATIONSHIP_INTEGRITY_VIOLATION = "Целостность связей между таблицами должна сохраняться";
    public static final String ERROR_COURSE_WITHOUT_TEACHER = "Все курсы должны иметь назначенных преподавателей";
    public static final String ERROR_POPULAR_COURSES_CRITERIA = "Популярные курсы должны иметь количество студентов больше установленного минимума";
    public static final String ERROR_COURSE_DATA_MISMATCH = "Данные курса не соответствуют ожидаемым значениям";
    public static final String ERROR_DB_CONNECTION_FAILED = "Соединение с базой данных не было установлено";
    public static final String ERROR_COURSE_TYPE_MISSING = "Тип курса должен присутствовать в базе данных";
    public static final String ERROR_STUDENTS_AGE_NOT_FOUND = "В базе данных должны существовать студенты указанного возраста";

}
