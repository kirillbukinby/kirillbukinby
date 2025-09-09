package elements;

import com.codeborne.selenide.*;
import java.time.*;

import static com.codeborne.selenide.Selenide.*;
import static config.MainPageConstants.*;
import static utils.BrowserHelper.*;
import static utils.ElementHelper.*;
import static utils.JavaScriptHelper.*;
import static utils.WindowHelper.*;

public class MainPageElements {

    // Локаторы слайдера пицц
    private SelenideElement sliderPrevArrow() { return $x("//a[@class='slick-prev']"); }
    private SelenideElement sliderNextArrow() { return $x("//a[@class='slick-next']"); }

    // Локаторы напитков
    private SelenideElement drinkImage() { return $x("(//img[@class='attachment-shop_catalog size-shop_catalog wp-post-image'])[15]"); }
    private SelenideElement addToCartButton() { return $x("(//a[@class='button product_type_simple add_to_cart_button ajax_add_to_cart'])[15]"); }

    // Локаторы десертов
    private SelenideElement dessertImage() { return $x("//img[contains(@src, 'pexels-geraud')]"); }

    // Локаторы футера
    private SelenideElement scrollToTopButton() { return $x("//div[@id='ak-top']"); }
    private SelenideElement facebookLink() { return $x("(//a[@rel='noopener noreferrer'])[1]"); }
    private SelenideElement vkLink() { return $x("(//a[@rel='noopener noreferrer'])[2]"); }
    private SelenideElement instagramLink() { return $x("(//a[@rel='noopener noreferrer'])[3]"); }
    private SelenideElement title() { return $x("//h1[@class='product_title entry-title']"); }

    // Методы работы со слайдером
    public MainPageElements clickPrevSliderArrow() {
        clickElement(sliderPrevArrow());
        return this;
    }

    public MainPageElements clickNextSliderArrow() {
        clickElement(sliderNextArrow());
        return this;
    }

    // Методы работы с напитками
    public MainPageElements hoverDrinkImage() {
        moveToElement(drinkImage());
        return this;
    }

    // Методы работы с десертами
    public MainPageElements clickDessertImage() {
        clickElement(dessertImage());
        return this;
    }

    public MainPageElements waitForDessertUrl() {
        waitForUrlToContain(DESERT_URL_IMG_CONTAINS);
        return this;
    }

    public String getDessertText() {
        return getElementText(title());
    }

    // Методы прокрутки страницы
    public MainPageElements scrollToBottom() {
        scrollToPageBottom();
        return this;
    }

    // Методы работы с социальными сетями
    public MainPageElements clickFacebookLink() {
        clickElement(facebookLink());
        return this;
    }

    public MainPageElements clickVkLink() {
        clickElement(vkLink());
        return this;
    }

    public MainPageElements clickInstagramLink() {
        clickElement(instagramLink());
        return this;
    }

    // Комбинированные методы социальных сетей
    public MainPageElements openFacebookAndSwitch() {
        int initialWindows = getWindowCount();
        clickFacebookLink();
        switchToNewWindow(initialWindows, Duration.ofSeconds(30));
        return this;
    }

    public MainPageElements openVkAndSwitch() {
        int initialWindows = getWindowCount();
        clickVkLink();
        switchToNewWindow(initialWindows, Duration.ofSeconds(30));
        return this;
    }

    public MainPageElements openInstagramAndSwitch() {
        int initialWindows = getWindowCount();
        clickInstagramLink();
        switchToNewWindow(initialWindows, Duration.ofSeconds(30));
        return this;
    }

    // Комбинированные методы десертов
    public MainPageElements openDessertPage() {
        return clickDessertImage()
                .waitForDessertUrl();
    }

    // Геттеры для элементов
    public SelenideElement getAddToCartButton() {
        return addToCartButton();
    }

    public SelenideElement getScrollToTopButton() {
        return scrollToTopButton();
    }
}