package tests;

import io.qameta.allure.*;
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
    @Description("Проверка отображения пустой корзины")
    @Epic("E2E")
    @Feature("Корзина товаров")
    @Story("Просмотр пустой корзины")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("ID-5")
    @Issue("ID-6")
    @Owner("Khvadina Aleksandra")
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
