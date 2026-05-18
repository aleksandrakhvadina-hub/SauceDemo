package pages;

import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    @Override
    public BasePage isPageOpened() {
        return this;
    }
    @Override
    public CheckoutPage open() {
        driver.get(BASE_URL + "/checkout-step-one.html");
        return this;
    }
}
