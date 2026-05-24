package tests;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.DataProvider;
import pages.CartPage;
import tests.base.BaseTest;

@Log4j2
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
    @Description("Проверка добавления товара в корзину")
    @Epic("E2E")
    @Feature("Корзина товаров")
    @Story("Добавление товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("ID-3")
    @Issue("ID-4")
    @Owner("Khvadina Aleksandra")
    public void testCartFlow(String expectedName, String addToCartSelector, String expectedPrice) {
        log.info("Starting cart flow test for item: '{}'", expectedName);

        CartPage cartPage = loginStep
                .login(user, password)
                .isPageOpened()
                .addToCart(expectedName)
                .goToCart();

        SoftAssert softAssert = new SoftAssert();
        log.info("Verifying item '{}' with price '{}' in cart", expectedName, expectedPrice);
        boolean isInCart = cartPage.isItemInCart(expectedName, expectedPrice);

        softAssert.assertTrue(
                isInCart,
                "Товар '" + expectedName + "' с ценой " + expectedPrice + " не найден в корзине"
        );

        if (isInCart) {
            log.info("✓ Item '{}' verified in cart", expectedName);
        } else {
            log.error("✗ Item '{}' NOT found in cart", expectedName);
        }

        softAssert.assertAll();
        log.info("Cart flow test completed for item: '{}'", expectedName);
    }
}

