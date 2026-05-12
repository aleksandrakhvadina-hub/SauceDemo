package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.DataProvider;
import tests.base.BaseTest;
import utils.DriverManager;

public class AddToCartTest extends BaseTest {

    @DataProvider(name = "products")
    public Object[][] cartItems() {
        return new Object[][] {
                {"Sauce Labs Backpack", "[data-test='add-to-cart-sauce-labs-backpack']", "$29.99"},
                {"Sauce Labs Bike Light", "[data-test='add-to-cart-sauce-labs-bike-light']", "$9.99"},
                {"Sauce Labs Bolt T-Shirt", "[data-test='add-to-cart-sauce-labs-bolt-t-shirt']", "$15.99"}
        };
    }

    @Test(
            dataProvider = "products",
            description = "Проверка добавления товара в корзину",
            testName = "Проверка добавления товара в корзину",
            groups = {"smoke"}
    )
    public void testCartFlow(String expectedName, String addToCartSelector, String expectedPrice) {
        // 1. добавить товар в корзину (первый рюкзак)
        DriverManager.getDriver().findElement(By.cssSelector(addToCartSelector)).click();
        // 2. перейти в корзину
        DriverManager.getDriver().findElement(By.className("shopping_cart_link")).click();
        // 3. взять имя и цену товара из корзины
        String actualName = cartPage.getItemName();
        String actualPrice = cartPage.getItemPrice();
        // 4. проверить совпадение (soft assert)
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualName, expectedName);
        softAssert.assertEquals(actualPrice, expectedPrice);
        softAssert.assertAll();
    }
}

