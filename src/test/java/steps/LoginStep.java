package steps;

import lombok.extern.log4j.Log4j2;
import pages.LoginPage;
import org.openqa.selenium.WebDriver;
import pages.ProductsPage;

@Log4j2
public class LoginStep {

    private final LoginPage loginPage;

    public LoginStep(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
        log.info("LoginStep initialized");
    }

    public ProductsPage login(String username, String password) {
        log.info("Authenticating user: '{}'", username);
        loginPage.open();
        loginPage.isPageOpened();
        ProductsPage productsPage = loginPage.login(username, password);
        log.info("Authentication completed for user: '{}'", username);
        return productsPage;  // ← возвращаем страницу!
    }
}
