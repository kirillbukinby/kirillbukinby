package common;

import io.github.bonigarcia.wdm.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import pages.*;
import utils.*;
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;

import static org.apache.commons.io.FileUtils.*;
import static org.openqa.selenium.OutputType.*;

public class BaseDriver {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected BonusProgramPage bonusProgramPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected DeliveryPaymentPage deliveryPaymentPage;
    protected MainPage mainPage;
    protected MyAccountPage myAccountPage;
    protected OrdersHistoryPage ordersHistoryPage;
    protected PizzaPage pizzaPage;

    @BeforeEach
    void setUp() {
        boolean isCI = System.getenv("CI") != null ||
                System.getenv("GITLAB_CI") != null ||
                Boolean.parseBoolean(System.getProperty("ci", "false"));

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        if (isCI) {
            options.addArguments("--headless=new");
        }

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory",
                System.getProperty("java.io.tmpdir") + "/selenium-downloads");
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(isCI ? 60 : 15));

        wait = new WebDriverWait(driver, Duration.ofSeconds(isCI ? 45 : 15));

        if (!isCI) {
            driver.manage().window().maximize();
        }

        ActionHelper.initialize(driver, wait);
        AlertHelper.initialize(driver, wait);
        AssertHelper.initialize(driver, wait);
        BrowserHelper.initialize(driver, wait);
        ElementHelper.initialize(driver, wait);
        JavaScriptHelper.initialize(driver, wait);
        ScrollHelper.initialize(driver, wait);
        WaitHelper.initialize(driver, wait);
        WindowHelper.initialize(driver, wait);

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
    public void tearDown() throws IOException {
        if (driver != null) {
            takeScreenshot();
            driver.manage().deleteAllCookies();
            driver.quit();
        }
    }

    private void takeScreenshot() throws IOException {
        Path screenshotsDir = Paths.get("target", "screenshots");
        Files.createDirectories(screenshotsDir);

        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(FILE);
        File destinationFile = new File(screenshotsDir.toFile(), "screenshot_" + System.currentTimeMillis() + ".png");
        copyFile(sourceFile, destinationFile);
    }
}