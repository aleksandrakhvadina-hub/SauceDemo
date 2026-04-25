package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.LoginPage;

public class RemoveItemFromCart extends BaseTest {

    @Test
    public void removeItemFromCart() {
        // 1. открыть страницу и залогиниться
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        // 2. добавить товар в корзину
        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")).click();
        // 3. перейти в корзину
        driver.findElement(By.className("shopping_cart_link")).click();
        // 4. удалить товар
        CartPage cartPage = new CartPage(driver);
        cartPage.removeItem();
        // 5. проверить что корзина пустая
        SoftAssert softAssert = new SoftAssert();
        // найти все товары в корзине - если список пустой, то size = 0
        int itemsCount = driver.findElements(By.className("cart_item")).size();

        softAssert.assertAll();
    }
}
