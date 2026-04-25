import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;

public class AddToCartTest {

    @Test
    public void testCartFlow() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 1. открыть страницу и залогиниться
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // 2. добавить товар в корзину (первый рюкзак)
        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")).click();

        // 3. перейти в корзину
        driver.findElement(By.className("shopping_cart_link")).click();

        // 4. взять имя и цену товара из корзины
        String actualName = driver.findElement(By.className("inventory_item_name")).getText();
        String actualPrice = driver.findElement(By.className("inventory_item_price")).getText();

        // 5. проверить совпадение (soft assert)
SoftAssert softAssert = new SoftAssert();
softAssert.assertEquals(actualName, "Sauce Labs Backpack");
softAssert.assertEquals(actualPrice, "$29.99");
softAssert.assertAll(); 

        driver.quit();
    }
}

