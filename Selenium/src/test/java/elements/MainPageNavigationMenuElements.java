package elements;

import org.openqa.selenium.*;

import static config.MainPageNavigationMenuConstants.*;
import static utils.ActionHelper.*;

public class MainPageNavigationMenuElements {

    private static final By MENU_CATALOG = By.xpath("//li[@id='menu-item-389']/a");
    private static final By PIZZA_SUBMENU = By.xpath("//li[@id='menu-item-390']/a");
    private static final By DESSERTS_SUBMENU = By.xpath("//li[@id='menu-item-391']/a");
    private static final By DRINKS_SUBMENU = By.xpath("//li[@id='menu-item-393']/a");

    // Методы работы с основным меню
    public MainPageNavigationMenuElements hoverMenuCatalog() {
        moveToElement(MENU_CATALOG);
        return this;
    }

    // Методы перехода по подменю
    public MainPageNavigationMenuElements clickPizzaSubmenu() {
        moveToAndClickElement(PIZZA_SUBMENU);
        return this;
    }

    public MainPageNavigationMenuElements clickDessertsSubmenu() {
        moveToAndClickElement(DESSERTS_SUBMENU);
        return this;
    }

    public MainPageNavigationMenuElements clickDrinksSubmenu() {
        moveToAndClickElement(DRINKS_SUBMENU);
        return this;
    }

    // Методы навигации по секциям
    public MainPageNavigationMenuElements navigateToSection(String sectionKey) {
        hoverMenuCatalog();

        switch (sectionKey) {
            case PIZZA_SECTION_KEY:
                clickPizzaSubmenu();
                break;
            case DESSERTS_SECTION_KEY:
                clickDessertsSubmenu();
                break;
            case DRINKS_SECTION_KEY:
                clickDrinksSubmenu();
                break;
            default:
                throw new IllegalArgumentException("Неизвестный раздел меню: " + sectionKey);
        }
        return this;
    }
}