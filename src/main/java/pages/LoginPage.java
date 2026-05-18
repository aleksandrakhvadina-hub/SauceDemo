package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DriverManager;

public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.id("user-name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Login")
    @Override
    public LoginPage open() {
        DriverManager.getDriver().get(BASE_URL);
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    @Step("Вход в систему с именем пользователя: '{user}' и паролем '{password}'")
    public ProductsPage login(String user, String password) {
        DriverManager.getDriver().findElement(USERNAME_FIELD).sendKeys(user);
        DriverManager.getDriver().findElement(PASSWORD_FIELD).sendKeys(password);
        DriverManager.getDriver().findElement(LOGIN_BUTTON).click();
        return new ProductsPage(DriverManager.getDriver());
    }

    public String getErrorMessage() {
        return DriverManager.getDriver().findElement(ERROR_MESSAGE).getText();
    }
}

