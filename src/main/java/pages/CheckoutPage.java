package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CheckoutPage extends BasePage {

        public CheckoutPage(WebDriver driver) {
            super(driver);
        }

        @Override
        public BasePage isPageOpened() {
            return this;
        }

        @Override
        public pages.CheckoutPage open() {
            driver.get(BASE_URL + "/checkout-step-one.html");
            return this;
        }
    }