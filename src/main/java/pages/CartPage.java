package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DriverManager;

import java.util.List;

public class CartPage extends BasePage {

    // описываем элементы, с которыми взаимодействуем
    private final By ITEM_NAME = By.className("inventory_item_name");
    private final By ITEM_PRICE = By.className("inventory_item_price");
    private final By REMOVE_BUTTON = By.className("btn_action");
    private final By CHECKOUT_BUTTON = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_contents_container")));
        return this;
    }

    @Override
    public CartPage open() {
        driver.get(BASE_URL + "/cart.html");
        return this;
    }

    @Step("Получение названия товара из корзины")
    public String getItemName() {
        return driver.findElement(ITEM_NAME).getText();}
    @Step("Получение цены товара из корзины")
    public String getItemPrice() {
        return driver.findElement(ITEM_PRICE).getText();}
    @Step("Удаление товара из корзины")
    public CartPage removeItem() {
        driver.findElement(REMOVE_BUTTON).click();
        return this;}
    @Step("Переход на страницу оформления заказа (Checkout)")
    public CheckoutPage goToCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }
    // добавила потому что при добавлении в корзину трёх разных товаров тесты падали
    // этот метод ищет точное совпадение
    @Step("Проверка, что товар '{itemName}' есть в корзине с ценой '{expectedPrice}'")
    public boolean isItemInCart(String itemName, String expectedPrice) {
        List<WebElement> items = driver.findElements(By.className("cart_item"));

        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            String price = item.findElement(By.className("inventory_item_price")).getText();

            if (name.equals(itemName) && price.equals(expectedPrice)) {
                return true;
            }
        }
        return false;
    }
}
