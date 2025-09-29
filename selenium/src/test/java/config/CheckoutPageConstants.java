package config;

public class CheckoutPageConstants {
    // URL
    public static final String CHECKOUT_URL = "https://pizzeria.skillbox.cc/checkout/";
    public static final String ORDER_RECEIVED_URL = "order-received";
    public static final String ORDER_RECEIVED_URL_PART = "/order-received/";

    // Данные для заполнения формы
    public static final String FIRST_NAME = "Иван";
    public static final String LAST_NAME = "Иванов";
    public static final String ADDRESS = "ул. Примерная, д. 1";
    public static final String CITY = "Минск";
    public static final String STATE = "Минская обл.";
    public static final String POSTCODE = "220000";
    public static final String PHONE = "+71234567890";
    public static final String EMAIL = "Skilbox@gmail.com";
    public static final String ORDER_COMMENT = "Тестовый заказ";
    public static final String ORDER_DATE = "2025-01-01";

    // Тексты для проверок
    public static final String ORDER_RECEIVED_TEXT = "ЗАКАЗ ПОЛУЧЕН";
    public static final String DIRECT_BANK_TRANSFER_TEXT = "Прямой банковский перевод";
    public static final String PAYMENT_METHOD_TEXT = "Оплата при доставке";

    // Сообщения об ошибках
    public static final String ERROR_WRONG_URL_AFTER_ORDER = "Неверный URL после оформления заказа";
    public static final String ERROR_ORDER_CONFIRMATION_TEXT = "Текст подтверждения заказа не соответствует ожидаемому";
    public static final String ERROR_PAYMENT_METHOD_TEXT = "Метод оплаты не соответствует ожидаемому";
}