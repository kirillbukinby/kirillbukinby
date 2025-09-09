package config;

public class MyAccountPageConstants {
    // URL
    public static final String MY_ACCOUNT_URL = "https://pizzeria.skillbox.cc/my-account/";

    // Учетные данные
    public static final String USERNAME = "Skilbox";
    public static final String PASSWORD = "Skilbox";

    // Тексты для проверок
    public static final String ACCOUNT_TITLE = "МОЙ АККАУНТ";

    // URL части для проверок
    public static final String MY_ACCOUNT_URL_PART = "my-account";

    // Неверные учетные данные для тестирования
    public static final String INVALID_USERNAME = "invalidUser";
    public static final String INVALID_PASSWORD = "invalidPassword";

    // Сообщения об ошибках
    public static final String ERROR_ACCOUNT_TITLE_MISMATCH = "Заголовок аккаунта не соответствует ожидаемому";
    public static final String ERROR_SHOULD_STAY_ON_LOGIN_PAGE = "Должны остаться на странице логина при неверных данных";
    public static final String ERROR_LOGIN_FORM_NOT_DISPLAYED = "Форма логина должна отображаться после неудачной попытки входа";
    public static final String ERROR_USERNAME_INPUT_NOT_DISPLAYED = "Поле username должно отображаться";
    public static final String ERROR_PASSWORD_INPUT_NOT_DISPLAYED = "Поле password должно отображаться";
    public static final String ERROR_LOGIN_BUTTON_NOT_DISPLAYED = "Кнопка login должна отображаться";
}