package pages;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.id("user-name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
        log.info("LoginPage initialized");
    }

    @Step("Открытие страницы Login")
    @Override
    public LoginPage open() {
        log.info("Opening Login page");
        driver.get(BASE_URL);
        log.info("Login page opened");
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        log.info("Checking if Login page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    @Step("Вход в систему с именем пользователя: '{user}' и паролем '{password}'")
    public ProductsPage login(String user, String password) {
        log.info("Logging in with username: '{}'", user);

        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();

        log.info("Login attempt completed for user: '{}'", user);
        return new ProductsPage(driver);
    }
    @Step("Получение текста ошибки авторизации")
    public String getErrorMessage() {
        log.info("Getting error message");
        String error = driver.findElement(ERROR_MESSAGE).getText();
        log.info("Error message: '{}'", error);
        return error;
    }
}


