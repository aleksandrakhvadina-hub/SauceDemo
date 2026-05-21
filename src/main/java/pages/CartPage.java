package pages;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
public class CartPage extends BasePage {

    // описываем элементы, с которыми взаимодействуем
    private final By ITEM_NAME = By.className("inventory_item_name");
    private final By ITEM_PRICE = By.className("inventory_item_price");
    private final By REMOVE_BUTTON = By.className("btn_action");
    private final By CHECKOUT_BUTTON = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
        log.info("CartPage initialized");
    }

    @Override
    @Step("Проверка открытия страницы корзины")
    public CartPage isPageOpened() {
        log.info("Checking if Cart page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_contents_container")));
        log.info("Cart page is opened");
        return this;
    }

    @Override
    public CartPage open() {
        log.info("Opening Cart page");
        driver.get(BASE_URL + "/cart.html");
        return this;
    }

    @Step("Получение названия товара из корзины")
    public String getItemName() {
        log.info("Getting item name from cart");
        String itemName = driver.findElement(ITEM_NAME).getText();
        log.info("Item name: '{}'", itemName);
        return itemName;
    }

    @Step("Получение цены товара из корзины")
    public String getItemPrice() {
        log.info("Getting item price from cart");
        String price = driver.findElement(ITEM_PRICE).getText();
        log.info("Item price: '{}'", price);
        return price;
    }

    @Step("Удаление товара из корзины")
    public CartPage removeItem() {
        log.info("Removing item from cart");
        driver.findElement(REMOVE_BUTTON).click();
        log.info("Item removed from cart");
        return this;
    }

    @Step("Переход на страницу оформления заказа (Checkout)")
    public CheckoutPage goToCheckout() {
        log.info("Navigating to Checkout page");
        driver.findElement(CHECKOUT_BUTTON).click();
        log.info("Checkout page opened");
        return new CheckoutPage(driver);
    }
    // добавила потому что при добавлении в корзину трёх разных товаров тесты падали
    // этот метод ищет точное совпадение
    @Step("Проверка, что товар '{itemName}' есть в корзине с ценой '{expectedPrice}'")
    public boolean isItemInCart(String itemName, String expectedPrice) {
        log.info("Checking if item '{}' with price '{}' is in cart", itemName, expectedPrice);

        List<WebElement> items = driver.findElements(By.className("cart_item"));
        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            String price = item.findElement(By.className("inventory_item_price")).getText();
            if (name.equals(itemName) && price.equals(expectedPrice)) {
                log.info("Item '{}' found in cart", itemName);
                return true;
            }
        }
        log.info("Item '{}' NOT found in cart", itemName);
        return false;
    }
    @Step("Получение количества товаров в корзине")
    public int getItemsCount() {
        log.info("Counting items in cart");
        int count = driver.findElements(By.className("cart_item")).size();
        log.info("Found {} items in cart", count);
        return count;
    }
}
