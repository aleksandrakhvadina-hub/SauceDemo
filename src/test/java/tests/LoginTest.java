package tests;

import org.testng.asserts.SoftAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class LoginTest extends BaseTest {

    @Test (priority = 1,
            description = "Проверка входа в систему с валидными кредами",
            testName = "Проверка входа в систему с валидными кредами",
            groups = {"smoke"}
    )
    public void checkLoginWithPositiveCred() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        softAssert.assertEquals(productsPage.getTitle(), "Products");
    }

    @Test (priority = 4,
            description = "Проверка входа в систему с пустым паролем",
            testName = "Проверка входа в систему с пустым паролем",
            groups = {"regression"}
    )
    public void checkLoginWithEmptyPassword() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "");
        softAssert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test (priority = 3,
            groups = {"regression"})
    public void checkLoginWithEmptyUser() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("", "secret_sauce");
        softAssert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test (priority = 2,
            groups = {"regression"})
    public void checkLoginWithNegativeCred() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("test", "test");
        softAssert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @DataProvider(name = "Тестовые данные для негативного логина")
    public Object[][] loginData() {
        return new Object[][] {
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"},
        };
    }

    @Test (dataProvider = "Тестовые данные для негативного логина")
    public void negativeLogin(String user, String password, String errorMessage) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);
        softAssert.assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}


