package tests.base;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Listeners;
import utils.AllureUtils;
import utils.DriverManager;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;

import java.time.Duration;
import java.util.HashMap;

@Listeners(utils.TestListener.class)
public class BaseTest {

    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, description = "Настройка браузера")
    @Description("Настройка браузера")
    public void setUp(@Optional("chrome") String browser) {
        WebDriver driver;

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } else {
            throw new IllegalArgumentException("Unknown browser: " + browser);
        }

        DriverManager.setDriver(driver);

        loginPage = new LoginPage(DriverManager.getDriver());
        productsPage = new ProductsPage(DriverManager.getDriver());
        cartPage = new CartPage(DriverManager.getDriver());

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
    }
    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    @Description("Закрытие браузера")
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            AllureUtils.takeScreenshot(DriverManager.getDriver());
        }
        DriverManager.quitDriver();
        System.out.println("🔚 Browser closed");
    }
}
