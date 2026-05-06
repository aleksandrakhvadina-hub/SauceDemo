package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import utils.DriverManager;

public class GoToEmptyCart extends BaseTest {

    @Test(
            description = "Проверка отображения пустой корзины",
            testName = "Проверка отображения пустой корзины",
            groups = {"smoke"}
    )
    public void checkEmptyCart() {
        // 1. перейти в корзину (ничего не добавляя)
        DriverManager.getDriver().findElement(By.className("shopping_cart_link")).click();
        // 2. проверить что корзина пуста
        SoftAssert softAssert = new SoftAssert();
        int itemsCount = DriverManager.getDriver().findElements(By.className("cart_item")).size();
        softAssert.assertEquals(itemsCount, 0);

        softAssert.assertAll();
    }
}
