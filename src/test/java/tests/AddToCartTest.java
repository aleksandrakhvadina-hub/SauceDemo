package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.DataProvider;
import pages.CartPage;
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
    @Description("Проверка добавления товара в корзину")
    @Epic("E2E")
    @Feature("Корзина товаров")
    @Story("Добавление товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("ID-3")
    @Issue("ID-4")
    @Owner("Khvadina Aleksandra")
    public void testCartFlow(String expectedName, String addToCartSelector, String expectedPrice) {
        CartPage cartPage = loginPage
                .open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addToCart(expectedName)
                .goToCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                cartPage.isItemInCart(expectedName, expectedPrice),
                "Товар '" + expectedName + "' с ценой " + expectedPrice + " не найден в корзине"
        );
        softAssert.assertAll();
    }
}

