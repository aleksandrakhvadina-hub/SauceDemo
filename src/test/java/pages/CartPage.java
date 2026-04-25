package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    // описываем элементы, с которыми взаимодействуем
    private final By ITEM_NAME = By.className("inventory_item_name");
    private final By ITEM_PRICE = By.className("inventory_item_price");
    private final By REMOVE_BUTTON = By.className("btn_action");
    private final By CHECKOUT_BUTTON = By.id("checkout");

    // конструктор
    public CartPage(WebDriver driver) {
        super(driver);
    }

    // методы взаимодействия с элементами
    // получение названия товара
    public String getItemName() {
        return driver.findElement(ITEM_NAME).getText();
    }
    // получение цены товара
    public String getItemPrice() {
        return driver.findElement(ITEM_PRICE).getText();
    }
    // удаление товара из корзины
    public void removeItem() {
        driver.findElement(REMOVE_BUTTON).click();
    }
    // переход на страницу оформления заказа
    public void goToCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }
}