package utils;

import com.codeborne.selenide.*;
import org.openqa.selenium.interactions.*;
import java.util.*;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.*;

public class ElementHelper {
    public static void clickElement(SelenideElement element) {
        element
                .shouldBe(exist, enabled)
                .hover()
                .click();
    }

    public static void clickWithMoveToElement(SelenideElement element) {
        element.shouldBe(exist, visible, enabled);
        new Actions(WebDriverRunner.getWebDriver())
                .moveToElement(element.toWebElement())
                .click(element.toWebElement())
                .perform();
    }

    public static String getElementText(SelenideElement element) {
        return element
                .shouldBe(visible)
                .getText();
    }

    public static String getElementTextByNumber(ElementsCollection elements, int index) {
        int actualIndex = index - 1;
        return elements
                .shouldBe(sizeGreaterThan(actualIndex))
                .get(actualIndex)
                .shouldBe(visible)
                .getText();
    }

    public static void setElementValue(SelenideElement element, String value) {
        element
                .shouldBe(visible, enabled)
                .setValue(value);
    }

    public static void moveToElement(SelenideElement element) {
        new Actions(WebDriverRunner.getWebDriver())
                .moveToElement(element
                                .shouldBe(exist, visible, enabled)
                                .toWebElement()
                ).perform();
    }

    public static void selectDropdownOption(SelenideElement dropdown, String optionValue) {
        dropdown
                .shouldBe(visible, enabled)
                .selectOptionByValue(optionValue);
    }

    public static String getSelectedOptionValue(SelenideElement dropdown) {
        return dropdown
                .shouldBe(visible)
                .getSelectedOption()
                .getValue();
    }

    public static void setElementValueWithClear(SelenideElement element, String value) {
        element.shouldBe(visible, enabled)
                .clear();
        element .shouldHave(empty)
                .setValue(value);
    }

    public static String getElementValue(SelenideElement element) {
        return element
                .shouldBe(visible)
                .getValue();
    }

    public static void waitForElementToDisappear(SelenideElement element) {
        element.should(disappear);
    }

    public static void waitForElementToAppear(SelenideElement element) {
        element.should(appear);
    }

    public static void clearAndSetElementValue(SelenideElement element, String value) {
        element
                .shouldBe(visible, enabled)
                .clear();
        element.sendKeys(value);
    }

    public static void setCheckbox(SelenideElement checkbox, boolean checked) {
        if (checked) {
            checkbox.shouldBe(visible, enabled).click();
        }
    }

    public static int getElementsCount(ElementsCollection elements) {
        return elements
                .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(0))
                .filter(visible)
                .size();
    }

    public static void switchToIframe(SelenideElement iframe) {
        iframe.shouldBe(visible);
        switchTo().frame(iframe);
    }

    public static void switchToDefaultContent() {
        switchTo().defaultContent();
    }

    public static <T> T executeInIframe(SelenideElement iframe, IframeAction<T> action) {
        switchToIframe(iframe);
        try {
            return action.execute();
        } finally {
            switchToDefaultContent();
        }
    }

    @FunctionalInterface
    public interface IframeAction<T> {
        T execute();
    }

    public static String getElementAttribute(SelenideElement element, String attribute) {
        return element
                .shouldBe(visible)
                .getAttribute(attribute);
    }

    public static List<String> getElementsTexts(ElementsCollection elements) {
        List<String> texts = new ArrayList<>();
        for (SelenideElement element : elements) {
            texts.add(getElementText(element));
        }
        return texts;
    }

    public static void clickElementByNumber(ElementsCollection elements, int index) {
        int actualIndex = index - 1;

        elements
                .shouldBe(sizeGreaterThan(actualIndex))
                .get(actualIndex)
                .shouldBe(visible, enabled)
                .click();
    }

}