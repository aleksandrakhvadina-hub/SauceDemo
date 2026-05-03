package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test (priority = 1,
            description = "Проверка входа в систему с валидными кредами",
            testName = "Проверка входа в систему с валидными кредами",
            groups = {"smoke"}
    )
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }

    @Test (priority = 4,
            description = "Проверка входа в систему с пустым паролем",
            testName = "Проверка входа в систему с пустым паролем",
            groups = {"regression"}
    )
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test (priority = 3,
            groups = {"regression"})
    public void checkLoginWithEmptyUser() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test (priority = 2,
            groups = {"regression"})
    public void checkLoginWithNegativeCred() {
        loginPage.open();
        loginPage.login("test", "test");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @DataProvider(name = "Тестовые данные для негативного логина", indices = {0, 2})
    public Object[][] loginData() {
        return new Object[][] {
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"},
        };
    }

    @Test (dataProvider = "Тестовые данные для негативного логина")
    public void negativeLogin(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}


