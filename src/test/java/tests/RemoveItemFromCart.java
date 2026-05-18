package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import tests.base.BaseTest;
import utils.DriverManager;

public class RemoveItemFromCart extends BaseTest {

    @Test(
            description = "Проверка удаления товара из корзины",
            testName = "Проверка удаления товара из корзины",
            groups = {"smoke"}
    )
    @Description("Проверка удаления товара из корзины")
    @Epic("E2E")
    @Feature("Корзина товаров")
    @Story("Удаление товара из корзины")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("ID-9")
    @Issue("ID-10")
    @Owner("Khvadina Aleksandra")
    public void removeItemFromCart() {
        CartPage cartPage = loginPage
                .open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .goToCart();

        cartPage.removeItem();
        SoftAssert softAssert = new SoftAssert();
        // найти все товары в корзине - если список пустой, то size = 0
        int itemsCount = DriverManager.getDriver().findElements(By.className("cart_item")).size();

        softAssert.assertEquals(itemsCount, 0); // ← проверяем, что корзина пуста
        softAssert.assertAll();
    }
}
