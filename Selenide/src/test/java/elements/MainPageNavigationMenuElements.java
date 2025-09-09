package elements;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;
import static config.MainPageNavigationMenuConstants.*;
import static utils.ElementHelper.*;

public class MainPageNavigationMenuElements {

    private SelenideElement menuCatalog() { return $x("//li[@id='menu-item-389']"); }
    private SelenideElement pizzaSubmenu() { return $x("//li[@id='menu-item-390']"); }
    private SelenideElement dessertsSubmenu() { return $x("//li[@id='menu-item-391']"); }
    private SelenideElement drinksSubmenu() { return $x("//li[@id='menu-item-393']"); }

    // Методы работы с основным меню
    public MainPageNavigationMenuElements hoverMenuCatalog() {
        moveToElement(menuCatalog());
        return this;
    }

    // Методы перехода по подменю
    public MainPageNavigationMenuElements clickPizzaSubmenu() {
        clickWithMoveToElement(pizzaSubmenu());
        return this;
    }

    public MainPageNavigationMenuElements clickDessertsSubmenu() {
        clickWithMoveToElement(dessertsSubmenu());
        return this;
    }

    public MainPageNavigationMenuElements clickDrinksSubmenu() {
        clickWithMoveToElement(drinksSubmenu());
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