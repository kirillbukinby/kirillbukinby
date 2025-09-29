package config;

public class ApiConstants {
    // Базовые URL для тестирования
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    // Endpoints
    public static final String POSTS_ENDPOINT = "/posts";

    // Path
    public static final String POSTS_PATH = "/posts/";
    public static final String USERS_PATH = "/users/";

    // Тестовые данные - текстовые значения
    public static final String TEST_TITLE = "Test Title";
    public static final String TEST_BODY = "Test Body";
    public static final String UPDATED_TITLE = "Updated Title";
    public static final String UPDATED_BODY = "Updated Body";

    // Тестовые данные - числовые значения
    public static final int TEST_USER_ID = 2;
    public static final int TEST_POST_ID = 1;
    public static final int EXPECTED_POSTS_COUNT = 100;
    public static final int CREATED_STATUS_CODE = 201;
    public static final int SUCCESS_STATUS_CODE = 200;

    // Отдельные константы для параметризированных тестов (посты)
    public static final int POST_ID_1 = 1;
    public static final int POST_ID_2 = 2;
    public static final int POST_ID_3 = 3;
    public static final int POST_ID_50 = 50;
    public static final int POST_ID_100 = 100;

    // Отдельные константы для параметризированных тестов (пользователи)
    public static final int USER_ID_1 = 1;
    public static final int USER_ID_2 = 2;
    public static final int USER_ID_3 = 3;

    public static final String USER_NAME_1 = "Leanne Graham";
    public static final String USER_NAME_2 = "Ervin Howell";
    public static final String USER_NAME_3 = "Clementine Bauch";

    // Время ожидания
    public static final long MAX_RESPONSE_TIME = 5000L; // 5 секунд

    // Названия JSON полей
    public static final String FIELD_ID = "id";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_BODY = "body";
    public static final String FIELD_USER_ID = "userId";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_EMAIL = "email";

    // JSON Path выражения
    public static final String JSON_PATH_ROOT_ARRAY = "";

    // Сообщения об ошибках
    public static final String ERROR_STATUS_CODE = "Неверный статус код";
    public static final String ERROR_RESPONSE_TIME = "Время ответа превышает лимит";
    public static final String ERROR_RESPONSE_EMPTY = "Ответ пустой";
    public static final String ERROR_FIELD_MISSING = "Обязательное поле отсутствует";
    public static final String ERROR_FIELD_VALUE = "Неверное значение поля";
    public static final String ERROR_LIST_SIZE = "Неверный размер списка";
    public static final String ERROR_PARAMETRIZED_TEST_DISPLAY_NAME = "Проверка пользователя с ID {0} - {1}";
    public static final String ERROR_PARAMETRIZED_POST_TEST_DISPLAY_NAME = "Проверка поста с ID {0}";
    public static final String ERROR_ASSERT_POST_ID_MATCH = "ID поста должен соответствовать запрошенному";
    public static final String ERROR_ASSERT_USER_NAME_MATCH = "Имя пользователя должно соответствовать";
}