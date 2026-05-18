package tests;

import io.qameta.allure.*;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductsPage;
import tests.base.BaseTest;
import utils.AllureUtils;
import utils.DriverManager;

public class LoginTest extends BaseTest {

    @Test (priority = 1,
            description = "Проверка входа в систему с валидными кредами",
            groups = {"smoke"}
    )
    @Description("Проверка входа в систему с валидными кредами")
    @Epic("E2E")
    @Feature("Login in ti SauceDemo")
    @Story("Positive login")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("ID-1")
    @Issue("ID-2")
    @Owner("Khvadina Aleksandra")

    public void checkLoginWithPositiveCred() {
        SoftAssert softAssert = new SoftAssert();
        ProductsPage productsPage = loginPage
                .open()
                .isPageOpened()
                .login("standard_user", "secret_sauce");

        softAssert.assertAll();
    }

    @Test (priority = 4,
            description = "Проверка входа в систему с пустым паролем",
            groups = {"regression"}
    )
    @Description("Проверка входа в систему с пустым паролем")
    @Epic("E2E")
    @Feature("Login in ti SauceDemo")
    @Story("Negative login - empty password")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("ID-11")
    @Issue("ID-12")
    @Owner("Khvadina Aleksandra")

    public void checkLoginWithEmptyPassword() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "");
        softAssert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");

        softAssert.assertAll();
    }

    @Test (priority = 3,
            description = "Проверка входа в систему с пустым именем пользователя",
            groups = {"regression"})
    @Description("Проверка входа в систему с пустым именем пользователя")
    @Epic("E2E")
    @Feature("Login in ti SauceDemo")
    @Story("Negative login - empty username")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("ID-13")
    @Issue("ID-14")
    @Owner("Khvadina Aleksandra")

    public void checkLoginWithEmptyUser() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("", "secret_sauce");
        softAssert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");

        softAssert.assertAll();
    }

    @Test (priority = 2,
            description = "Проверка входа в систему с невалидными кредами",
            groups = {"regression"})
    @Description("Проверка входа в систему с невалидными кредами")
    @Epic("E2E")
    @Feature("Login in ti SauceDemo")
    @Story("Negative login - invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("ID-15")
    @Issue("ID-16")
    @Owner("Khvadina Aleksandra")

    public void checkLoginWithNegativeCred() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("test", "test");
        softAssert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");

        softAssert.assertAll();
    }

    @DataProvider(name = "Тестовые данные для негативного логина")
    public Object[][] loginData() {
        return new Object[][] {
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"},
        };
    }

    @Test(priority = 2,
            dataProvider = "Тестовые данные для негативного логина",
            description = "Проверка входа в систему с невалидными кредами",
            groups = {"regression"})
    @Description("Проверка входа в систему с невалидными кредами")
    @Epic("E2E")
    @Feature("Login in ti SauceDemo")
    @Story("Negative login - invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("ID-15")
    @Issue("ID-16")
    @Owner("Khvadina Aleksandra")
    public void negativeLogin(String user, String password, String errorMessage) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);
        softAssert.assertEquals(loginPage.getErrorMessage(), errorMessage);

        softAssert.assertAll();
    }
}


