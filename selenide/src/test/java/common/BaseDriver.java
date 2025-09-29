package common;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.*;
import io.qameta.allure.selenide.*;
import org.junit.jupiter.api.*;
import pages.*;
import java.time.*;
import java.util.*;

public class BaseDriver {
    protected BonusProgramPage bonusProgramPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected DeliveryPaymentPage deliveryPaymentPage;
    protected MainPage mainPage;
    protected MyAccountPage myAccountPage;
    protected OrdersHistoryPage ordersHistoryPage;
    protected PizzaPage pizzaPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @BeforeEach
    void setUp() {
        boolean isCI = System.getenv("CI") != null ||
                System.getenv("GITLAB_CI") != null ||
                System.getProperty("selenide.headless") != null;

        Configuration.browser = "chrome";
        Configuration.headless = isCI;
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = Duration.ofSeconds(isCI ? 45 : 15).toMillis();
        Configuration.pageLoadTimeout  = Duration.ofSeconds(isCI ? 60 : 15).toMillis();
        Configuration.browserSize = "1920x1080";
        Configuration.screenshots = true;
        Configuration.savePageSource = false;
        Configuration.reportsFolder = System.getProperty("allure.results.directory", "target/allure-results");

        if (isCI) {
            Configuration.browserCapabilities.setCapability(
                    "goog:chromeOptions", Map.of(
                    "args", Arrays.asList(
                            "--no-sandbox",
                            "--disable-dev-shm-usage",
                            "--headless=new",
                            "--disable-gpu"
                    )
            ));
        }

        initPages();
    }

    private void initPages() {
        bonusProgramPage = new BonusProgramPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        deliveryPaymentPage = new DeliveryPaymentPage();
        mainPage = new MainPage();
        myAccountPage = new MyAccountPage();
        ordersHistoryPage = new OrdersHistoryPage();
        pizzaPage = new PizzaPage();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
