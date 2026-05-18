package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";

    // нагуглила и добавила это, потому что падали тесты на добавление в корзину второго и третьего
    // товаров (фонарь и футболка)
    private static final Map<String, String> ADD_TO_CART_LOCATORS = Map.of(
            "Sauce Labs Backpack", "[data-test='add-to-cart-sauce-labs-backpack']",
            "Sauce Labs Bike Light", "[data-test='add-to-cart-sauce-labs-bike-light']",
            "Sauce Labs Bolt T-Shirt", "[data-test='add-to-cart-sauce-labs-bolt-t-shirt']"
    );

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы товаров (Products)")
    @Override
    public ProductsPage open() {
        driver.get(BASE_URL + "/inventory.html");
        return this;
    }
    @Step("Получение заголовка страницы товаров")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
    @Step("Проверка, что страница товаров загружена")
    @Override
    public ProductsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }
    @Step("Добавление товара '{productName}' в корзину")
    public ProductsPage addToCart(String productName) {
        String locator = ADD_TO_CART_LOCATORS.get(productName);
        driver.findElement(By.cssSelector(locator)).click();
        return this;
    }
    @Step("Переход в корзину")
    public CartPage goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
        return new CartPage(driver);
    }
}
