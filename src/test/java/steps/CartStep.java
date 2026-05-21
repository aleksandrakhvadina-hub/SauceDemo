package steps;

import lombok.extern.log4j.Log4j2;
import pages.ProductsPage;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CartStep {

    private final ProductsPage productsPage;

    public CartStep(WebDriver driver) {
        this.productsPage = new ProductsPage(driver);
        log.info("CartStep initialized");
    }

    public void addItemToCart(String itemName) {
        log.info("Adding item to cart: '{}'", itemName);
        productsPage.addToCart(itemName);  // ← ИСПРАВЛЕНО: правильное имя метода
        log.info("Item '{}' added to cart", itemName);
    }

    public void goToCart() {
        log.info("Navigating to cart");
        productsPage.goToCart();
    }
}
