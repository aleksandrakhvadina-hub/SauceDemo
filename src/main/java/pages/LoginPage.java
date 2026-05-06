package pages;

import org.openqa.selenium.By;
import utils.DriverManager;

public class LoginPage extends BasePage {
    /*
    1. Описываем в классе элементы, с которыми взаимодействуем
    2. Описываем методы взаимодействия с этими классами
     */
    private final By USERNAME_FIELD = By.id("user-name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");

    public void open() {
        DriverManager.getDriver().get(BASE_URL);  // ← замена
    }

    public void login(String user, String password) {
        DriverManager.getDriver().findElement(USERNAME_FIELD).sendKeys(user);
        DriverManager.getDriver().findElement(PASSWORD_FIELD).sendKeys(password);
        DriverManager.getDriver().findElement(LOGIN_BUTTON).click();
    }

    public String getErrorMessage() {
        return DriverManager.getDriver().findElement(ERROR_MESSAGE).getText();
    }
}

