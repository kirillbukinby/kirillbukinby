package config;

public class BonusProgramPageConstants {
    // URL
    public static final String BONUS_PROGRAM_URL = "https://pizzeria.skillbox.cc/bonus/";

    // Тексты для проверок
    public static final String BONUS_CARD_SUCCESS_MESSAGE = "Заявка отправлена, дождитесь, пожалуйста, оформления карты!";
    public static final String CARD_REGISTRATION_TEXT = "оформления карты";

    // Данные для заполнения формы
    public static final String FIRST_NAME = "Иван";
    public static final String PHONE = "+71234567890";

    // Сообщения об ошибках
    public static final String ERROR_ALERT_NOT_PRESENT = "Alert окно не появилось после отправки формы";
    public static final String ERROR_ALERT_TEXT_MISMATCH = "Текст в alert окне не соответствует ожидаемому";
    public static final String ERROR_FORM_NOT_SUBMITTED = "Alert окно появилось после отправки формы";
    public static final String ERROR_NAME_FIELD_INCORRECT = "Поле 'Имя' заполнено некорректно";
    public static final String ERROR_PHONE_FIELD_INCORRECT = "Поле 'Телефон' заполнено некорректно";
    public static final String ERROR_SUBMIT_BUTTON_DISABLED = "Кнопка отправки должна быть активна после заполнения формы";
    public static final String ERROR_ALERT_PRESENT_BEFORE_SUBMISSION = "Alert не должен появляться до отправки формы";
    public static final String ERROR_ALERT_TEXT_DOES_NOT_CONTAIN = "Сообщение об успехе должно содержать информацию об оформлении карты";
}
