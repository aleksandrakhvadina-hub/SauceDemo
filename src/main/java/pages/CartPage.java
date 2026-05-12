package pages;

import org.openqa.selenium.By;
import utils.DriverManager;

public class CartPage extends BasePage {

    // описываем элементы, с которыми взаимодействуем
    private final By ITEM_NAME = By.className("inventory_item_name");
    private final By ITEM_PRICE = By.className("inventory_item_price");
    private final By REMOVE_BUTTON = By.className("btn_action");
    private final By CHECKOUT_BUTTON = By.id("checkout");

    // методы взаимодействия с элементами
    // получение названия товара
    public String getItemName() {
        return DriverManager.getDriver().findElement(ITEM_NAME).getText();
    }
    // получение цены товара
    public String getItemPrice() {
        return DriverManager.getDriver().findElement(ITEM_PRICE).getText();
    }
    // удаление товара из корзины
    public void removeItem() {
        DriverManager.getDriver().findElement(REMOVE_BUTTON).click();
    }
    // переход на страницу оформления заказа
    public void goToCheckout() {
        DriverManager.getDriver().findElement(CHECKOUT_BUTTON).click();
    }
}