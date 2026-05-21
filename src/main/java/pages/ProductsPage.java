package pages;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

@Log4j2
public class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test=title]");
    private static final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private static final By CART_LINK = By.className("shopping_cart_link");

    // нагуглила и добавила это, потому что падали тесты на добавление в корзину второго и третьего
    // товаров (фонарь и футболка)
    private static final Map<String, String> ADD_TO_CART_LOCATORS = Map.of(
            "Sauce Labs Backpack", "[data-test='add-to-cart-sauce-labs-backpack']",
            "Sauce Labs Bike Light", "[data-test='add-to-cart-sauce-labs-bike-light']",
            "Sauce Labs Bolt T-Shirt", "[data-test='add-to-cart-sauce-labs-bolt-t-shirt']"
    );

    public ProductsPage(WebDriver driver) {
        super(driver);
        log.info("ProductsPage initialized");
    }

    @Step("Открытие страницы товаров (Products)")
    @Override
    public ProductsPage open() {
        log.info("Opening Products page");
        driver.get(BASE_URL + "/inventory.html");
        log.info("Products page opened");
        return this;
    }
    @Step("Получение заголовка страницы товаров")
    public String getTitle() {
        log.info("Getting page title");
        String title = driver.findElement(TITLE).getText();
        log.info("Page title: '{}'", title);
        return title;
    }
    @Step("Проверка, что страница товаров загружена")
    @Override
    public ProductsPage isPageOpened() {
        log.info("Checking if Products page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        log.info("Products page is opened");
        return this;
    }
    @Step("Добавление товара '{productName}' в корзину")
    public ProductsPage addToCart(String productName) {
        log.info("Adding item '{}' to cart", productName);

        String locator = ADD_TO_CART_LOCATORS.get(productName);

        if (locator != null) {
            driver.findElement(By.cssSelector(locator)).click();
            log.info("Item '{}' added to cart using specific locator", productName);
        } else {
            // Фоллбэк: если товара нет в Map, пробуем универсальный паттерн
            String fallbackLocator = String.format(ADD_TO_CART_PATTERN, productName);
            driver.findElement(By.xpath(fallbackLocator)).click();
            log.info("Item '{}' added to cart using fallback locator", productName);
        }
        return this;
    }
    @Step("Переход в корзину")
    public CartPage goToCart() {
        log.info("Navigating to Cart");
        driver.findElement(CART_LINK).click();
        log.info("Cart page opened");
        return new CartPage(driver);
    }
}
