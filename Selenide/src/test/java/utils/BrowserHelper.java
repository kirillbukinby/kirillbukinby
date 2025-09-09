package utils;

import com.codeborne.selenide.*;

public class BrowserHelper {
    public static void openUrl(String url) {
        Selenide.open(url);
    }

    public static void waitForUrlToContain(String expectedPart) {
        Selenide.Wait().until(driver -> driver.getCurrentUrl().contains(expectedPart));
    }

}