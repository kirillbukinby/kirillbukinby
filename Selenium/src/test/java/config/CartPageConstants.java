package config;

public class CartPageConstants {
    // URL
    public static final String CART_URL = "https://pizzeria.skillbox.cc/cart/";
    public static final String CHECKOUT_URL = "https://pizzeria.skillbox.cc/checkout/";

    // Тексты для проверок
    public static final String CART_EMPTY = "Корзина пуста";

    // Количество товаров
    public static final String CART_ITEM_QUANTITY_1 = "1";
    public static final String CART_ITEM_QUANTITY_5 = "5";

    // Цены
    public static final String SUBTOTAL_PRICE_1 = "435,00₽";
    public static final String SUBTOTAL_PRICE_5 = "2175,00₽";

    // Сообщения об ошибках
    public static final String ERROR_QUANTITY_NOT_CHANGED = "Количество товара не изменилось";
    public static final String ERROR_SUBTOTAL_NOT_MATCH = "Общая стоимость не соответствует количеству товаров";
    public static final String ERROR_CART_SUBTOTAL_NOT_MATCH = "Итоговая сумма не соответствует количеству товаров";
    public static final String ERROR_NOT_REDIRECTED_TO_CHECKOUT = "Не перешли на страницу оформления заказа";
    public static final String ERROR_EMPTY_CART_NOT_DISPLAYED = "Сообщение о пустой корзине не отображается";
}