package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;

public class GoToEmptyCart extends BaseTest {

    @Test
    public void checkEmptyCart() {
        // 1. перейти в корзину (ничего не добавляя)
        driver.findElement(By.className("shopping_cart_link")).click();
        // 2. проверить что корзина пуста
        CartPage cartPage = new CartPage(driver);
        SoftAssert softAssert = new SoftAssert();
        int itemsCount = driver.findElements(By.className("cart_item")).size();
        softAssert.assertEquals(itemsCount, 0);

        softAssert.assertAll();
    }
}
