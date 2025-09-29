package config;

public class PizzaPageConstants {
    // URL
    public static final String PIZZA_CATEGORY_URL = "https://pizzeria.skillbox.cc/product-category/menu/pizza/";
    public static final String CART_URL = "https://pizzeria.skillbox.cc/cart/";

    // Тексты для проверок
    public static final String INITIAL_PRICE_RANGE = "Цена: 430₽ — 520₽";
    public static final String FILTERED_PRICE_RANGE = "Цена: 450₽ — 490₽";

    // Значения сортировки
    public static final String MENU_ORDER_VALUE = "menu_order";
    public static final String POPULARITY_VALUE = "popularity";
    public static final String DATE_VALUE = "date";
    public static final String PRICE_VALUE = "price";
    public static final String PRICE_DESC_VALUE = "price-desc";

    // Названия сортировки
    public static final String DEFAULT_SORTING = "Обычная сортировка";
    public static final String POPULARITY_SORTING = "По популярности";
    public static final String NEWEST_SORTING = "Последние";
    public static final String PRICE_ASC_SORTING = "По возрастанию цены";
    public static final String PRICE_DESC_SORTING = "По убыванию цены";

    // Сообщения об ошибках
    public static final String ERROR_INITIAL_PRICE_RANGE_MISMATCH = "Начальный диапазон цен не соответствует ожидаемому";
    public static final String ERROR_FILTERED_PRICE_RANGE_MISMATCH = "Отфильтрованный диапазон цен не соответствует ожидаемому";
    public static final String ERROR_NOT_REDIRECTED_TO_CART = "Не перешли на страницу корзины";
    public static final String ERROR_PIZZA_NAME_MISMATCH = "Название пиццы в корзине не соответствует добавленной";
    public static final String ERROR_PIZZA_PRICE_MISMATCH = "Цена пиццы в корзине не соответствует добавленной";
    public static final String ERROR_SORTING_NOT_SELECTED = "Не выбрана сортировка";
    public static final String ERROR_DEFAULT_SORTING_NOT_SELECTED = "По умолчанию должна быть выбрана обычная сортировка";
}