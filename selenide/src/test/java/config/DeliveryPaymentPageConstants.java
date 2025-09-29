package config;

public class DeliveryPaymentPageConstants {
    // URL
    public static final String DELIVERY_PAYMENT_URL = "https://pizzeria.skillbox.cc/delivery/";
    public static final String IFRAME_SRC = "/wp-content/delivery.html";

    // Тексты для проверок
    public static final String PAGE_TITLE = "Доставка и оплата";
    public static final String DELIVERY_CONDITIONS_TITLE = "Условия доставки";

    // Селекторы и атрибуты
    public static final String IFRAME_SRC_ATTRIBUTE = "src";
    public static final String IFRAME_WIDTH_ATTRIBUTE = "width";
    public static final String IFRAME_HEIGHT_ATTRIBUTE = "height";
    public static final String BODY_SELECTOR = "body";

    // Значения для проверок
    public static final String IFRAME_WIDTH_VALUE = "100%";
    public static final String IFRAME_HEIGHT_VALUE = "100%";

    // Сообщения об ошибках
    public static final String ERROR_CONTENT_TITLE_TEXT = "Заголовок контента не содержит ожидаемый текст";
    public static final String ERROR_PAGE_TITLE_TEXT = "Заголовок страницы не содержит ожидаемый текст";
    public static final String ERROR_CONTENT_TITLE_DISPLAYED = "Заголовок контента не отображается";
    public static final String ERROR_PAGE_TITLE_DISPLAYED = "Заголовок страницы не отображается";
    public static final String ERROR_IFRAME_DISPLAYED = "Iframe не отображается";
    public static final String ERROR_IFRAME_SRC = "Src iframe не содержит ожидаемый путь";
    public static final String ERROR_IFRAME_WIDTH = "Ширина iframe не соответствует ожидаемой";
    public static final String ERROR_IFRAME_HEIGHT = "Высота iframe не соответствует ожидаемой";
    public static final String ERROR_IFRAME_CONTENT = "Контент iframe не содержит текст: ";
    public static final String ERROR_URL = "URL страницы не соответствует ожидаемому";
    public static final String ERROR_PAGE_LOAD_TITLE = "Страница не загрузилась корректно - заголовок отсутствует";
    public static final String ERROR_PAGE_LOAD_IFRAME = "Страница не загрузилась корректно - iframe отсутствует";

}