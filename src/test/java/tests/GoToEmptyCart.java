package tests;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import pages.CartPage;

@Log4j2
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
        log.info("Starting empty cart verification test");
        // 1. перейти в корзину (ничего не добавляя)
        CartPage cartPage = productsPage.goToCart();
        log.info("Navigated to Cart page");
        // 2. проверить что корзина пуста
        SoftAssert softAssert = new SoftAssert();

        log.info("Checking cart items count");
        int itemsCount = cartPage.getItemsCount(); // ← вынесли логику в страницу

        softAssert.assertEquals(itemsCount, 0, "Expected empty cart, but found " + itemsCount + " items");

        if (itemsCount == 0) {
            log.info("✓ Cart is empty as expected");
        } else {
            log.error("✗ Cart contains {} items, expected 0", itemsCount);
        }

        softAssert.assertAll();
        log.info("Empty cart verification test completed");
    }
}
