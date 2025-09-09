package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.*;

public class ElementHelper {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void initialize(WebDriver webDriver, WebDriverWait webDriverWait) {
        driver = webDriver;
        wait = webDriverWait;
    }

    public static  WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static  List<WebElement> findElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static int getElementsCount(By locator) {
        return findElements(locator).size();
    }

    public static List<String> getElementsTexts(By locator) {
        return findElements(locator).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public static void clickElementByIndex(By locator, int index) {
        List<WebElement> elements = findElements(locator);
        elements.get(index).click();
    }

    public static void clearAndSetElementValue(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(value);
    }


    public static boolean isElementDisplayed(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
}
