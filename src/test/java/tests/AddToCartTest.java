package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddToCartTest extends BaseTest {

    @Test
    public void testCartFlow() {
        // 1. добавить товар в корзину (первый рюкзак)
        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")).click();
        // 2. перейти в корзину
        driver.findElement(By.className("shopping_cart_link")).click();
        // 3. взять имя и цену товара из корзины
        String actualName = cartPage.getItemName();
        String actualPrice = cartPage.getItemPrice();
        // 4. проверить совпадение (soft assert)
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualName, "Sauce Labs Backpack");
        softAssert.assertEquals(actualPrice, "$29.99");
        softAssert.assertAll();

        driver.quit();
    }
}

