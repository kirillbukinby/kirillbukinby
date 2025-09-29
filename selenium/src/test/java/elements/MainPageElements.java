package elements;

import org.openqa.selenium.*;

import java.time.*;

import static config.MainPageConstants.*;
import static utils.ActionHelper.*;
import static utils.ScrollHelper.*;
import static utils.WaitHelper.*;
import static utils.WindowHelper.*;

public class MainPageElements {
    // Локаторы слайдера пицц
    private static final By SLIDER_PREV_ARROW = By.xpath("//a[@class='slick-prev']");
    private static final By SLIDER_NEXT_ARROW = By.xpath("//a[@class='slick-next']");

    // Локаторы напитков
    private static final By DRINK_IMAGE = By.xpath("(//img[@class='attachment-shop_catalog size-shop_catalog wp-post-image'])[15]");
    private static final By ADD_TO_CART_BUTTON = By.xpath("(//a[@class='button product_type_simple add_to_cart_button ajax_add_to_cart'])[15]");

    // Локаторы десертов
    private static final By DESSERT_IMAGE = By.xpath("//img[contains(@src, 'pexels-geraud')]");

    // Локаторы футера
    private static final By SCROLL_TO_TOP_BUTTON = By.xpath("//div[@id='ak-top']");
    private static final By FACEBOOK_LINK = By.xpath("(//a[@rel='noopener noreferrer'])[1]");
    private static final By VK_LINK = By.xpath("(//a[@rel='noopener noreferrer'])[2]");
    private static final By INSTAGRAM_LINK = By.xpath("(//a[@rel='noopener noreferrer'])[3]");
    private static final By TITLE = By.xpath("//h1[@class='product_title entry-title']");
    private static final By FOOTER_SECTION = By.xpath("//footer[@class='site-footer']");

    // Методы работы со слайдером
    public MainPageElements clickPrevSliderArrow() {
        moveToAndClickElement(SLIDER_PREV_ARROW);
        return this;
    }

    public MainPageElements clickNextSliderArrow() {
        moveToAndClickElement(SLIDER_NEXT_ARROW);
        return this;
    }

    // Методы работы с напитками
    public MainPageElements hoverDrinkImage() {
        moveToElement(DRINK_IMAGE);
        return this;
    }

    public boolean isAddToCartButtonDisplayed() {
        return isElementDisplayed(ADD_TO_CART_BUTTON);
    }

    // Методы работы с десертами
    public MainPageElements clickDessertImage() {
        moveToAndClickElement(DESSERT_IMAGE);
        return this;
    }

    public MainPageElements waitForDessertUrl() {
        waitForUrlContains(DESERT_URL_IMG_CONTAINS);
        return this;
    }

    public String getDessertText() {
        return waitForVisibility(TITLE).getText();
    }

    // Методы работы с футером
    public MainPageElements scrollToFooter() {
        waitForVisibility(FOOTER_SECTION);
        scrollToElement(FOOTER_SECTION);
        return this;
    }

    public MainPageElements scrollToBottom() {
        scrollToPageBottom();
        return this;
    }

    public boolean isScrollToTopButtonDisplayed() {
        return isElementDisplayed(SCROLL_TO_TOP_BUTTON);
    }

    // Методы работы с социальными сетями
    public MainPageElements clickFacebookLink() {
        scrollToFooter();
        moveToAndClickElement(FACEBOOK_LINK);
        return this;
    }

    public MainPageElements clickVkLink() {
        scrollToFooter();
        moveToAndClickElement(VK_LINK);
        return this;
    }

    public MainPageElements clickInstagramLink() {
        scrollToFooter();
        moveToAndClickElement(INSTAGRAM_LINK);
        return this;
    }

    // Комбинированные методы социальных сетей
    public MainPageElements openFacebookAndSwitch() {
        int initialWindows = getWindowCountMain();
        clickFacebookLink();
        switchToNewWindow(initialWindows, Duration.ofSeconds(30));
        return this;
    }

    public MainPageElements openVkAndSwitch() {
        int initialWindows = getWindowCountMain();
        clickVkLink();
        switchToNewWindow(initialWindows, Duration.ofSeconds(30));
        return this;
    }

    public MainPageElements openInstagramAndSwitch() {
        int initialWindows = getWindowCountMain();
        clickInstagramLink();
        switchToNewWindow(initialWindows, Duration.ofSeconds(30));
        return this;
    }

    // Комбинированные методы десертов
    public MainPageElements openDessertPage() {
        return clickDessertImage()
                .waitForDessertUrl();
    }

    // Вспомогательные методы для тестов
    public int getWindowCountMain() {
        return getWindowCount();
    }
}