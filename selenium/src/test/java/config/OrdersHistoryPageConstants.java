package config;

public class OrdersHistoryPageConstants {
    // URL
    public static final String ORDERS_HISTORY_URL = "https://pizzeria.skillbox.cc/my-account/orders/";
    public static final String VIEW_ORDER_URL_PART = "/view-order/";

    // Тексты для проверок
    public static final String LAST_ORDER_AMOUNT = "435,00";
    public static final String PROCESSING_STATUS = "Обработка";
    public static final String ON_HOLD_STATUS = "На удержании";

    // Названия колонок таблицы
    public static final String ORDER_COLUMN = "Заказ";
    public static final String DATE_COLUMN = "Дата";
    public static final String STATUS_COLUMN = "Статус";
    public static final String AMOUNT_COLUMN = "Сумма";
    public static final String ACTIONS_COLUMN = "Действия";

    // Регулярные выражения
    public static final String DATE_PATTERN = "\\d{2}\\.\\d{2}\\.\\d{4}"; //Пример: "25.12.2023"
    public static final String AMOUNT_PATTERN = "\\d+,\\d{2}₽"; // Пример: "450,00₽"
    public static final String ORDER_NUMBER_PATTERN = "\\d+"; // Пример: "67890"

    // Сообщения об ошибках
    public static final String ERROR_ORDER_AMOUNT_MISMATCH = "Сумма последнего заказа не соответствует ожидаемой";
    public static final String ERROR_ORDERS_TABLE_NOT_DISPLAYED = "Таблица заказов не отображается";
    public static final String ERROR_NO_ORDERS_IN_HISTORY = "В истории заказов нет записей";
    public static final String ERROR_PAGINATION_NOT_DISPLAYED = "Пагинация не отображается";
    public static final String ERROR_ORDER_DETAILS_NOT_NAVIGATED = "Не перешли на страницу деталей заказа";
    public static final String ERROR_ORDER_COLUMN_NOT_FOUND = "Колонка 'Заказ' не найдена";
    public static final String ERROR_DATE_COLUMN_NOT_FOUND = "Колонка 'Дата' не найдена";
    public static final String ERROR_STATUS_COLUMN_NOT_FOUND = "Колонка 'Статус' не найдена";
    public static final String ERROR_AMOUNT_COLUMN_NOT_FOUND = "Колонка 'Сумма' не найдена";
    public static final String ERROR_ACTIONS_COLUMN_NOT_FOUND = "Колонка 'Действия' не найдена";
    public static final String ERROR_MULTIPLE_STATUSES_NOT_FOUND = "Должно быть несколько заказов с разными статусами";
    public static final String ERROR_PROCESSING_STATUS_NOT_FOUND = "Должен быть заказ со статусом 'Обработка'";
    public static final String ERROR_ON_HOLD_STATUS_NOT_FOUND = "Должен быть заказ со статусом 'На удержании'";
    public static final String ERROR_INVALID_DATE_FORMAT = "Неверный формат даты";
    public static final String ERROR_INVALID_AMOUNT_FORMAT = "Неверный формат суммы";
    public static final String ERROR_EMPTY_ORDER_NUMBERS_LIST = "Список номеров заказов не должен быть пустым";
    public static final String ERROR_INVALID_ORDER_NUMBER_FORMAT = "Неверный формат номера заказа";
    public static final String ERROR_ORDER_STATUS_NOT_FOUND = "Должен быть заказ со статусом";
}