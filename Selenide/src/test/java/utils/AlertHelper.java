package utils;

import static com.codeborne.selenide.Selenide.*;

public class AlertHelper {
    public static String getAlertText() {
        try {
            return switchTo().alert().getText();
        } catch (Exception e) {
            throw new RuntimeException("Alert не найден", e);
        }
    }

    public static void acceptAlert() {
        try {
            switchTo().alert().accept();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось принять alert", e);
        }
    }

}