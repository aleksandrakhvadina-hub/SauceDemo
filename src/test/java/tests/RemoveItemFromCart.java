package tests;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import tests.base.BaseTest;

@Log4j2
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
        log.info("Starting remove item from cart test");

        // ↓↓↓ Используем PageObject-цепочку (читается как сценарий)
        CartPage cartPage = loginPage
                .open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .goToCart();

        log.info("Navigated to Cart page");

        log.info("Removing item from cart");
        cartPage.removeItem();

        SoftAssert softAssert = new SoftAssert();

        int itemsCount = cartPage.getItemsCount();  // ← метод в CartPage (см. ниже)

        log.info("Cart items count after removal: {}", itemsCount);
        softAssert.assertEquals(itemsCount, 0, "Expected empty cart, but found " + itemsCount + " items");

        if (itemsCount == 0) {
            log.info("✓ Cart is empty after removal");
        } else {
            log.error("✗ Cart still contains {} items", itemsCount);
        }

        softAssert.assertAll();
        log.info("Remove item from cart test completed");
    }
}
