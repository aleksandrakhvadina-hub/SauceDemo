package tests;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ProductsPage;
import tests.base.BaseTest;

@Log4j2
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
        log.info("Starting positive login test");
        SoftAssert softAssert = new SoftAssert();
        ProductsPage productsPage = loginPage
                .open()
                .isPageOpened()
                .login("standard_user", "secret_sauce");

        productsPage.isPageOpened();
        softAssert.assertAll();

        log.info("Positive login test completed successfully");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] loginData() {
        log.info("Loading invalid login test data");
        return new Object[][] {
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(priority = 2,
            dataProvider = "invalidLoginData",
            description = "Проверка входа в систему с невалидными кредами",
            groups = {"regression"})
    @Description("Проверка входа в систему с невалидными кредами")
    @Epic("E2E") @Feature("Login to SauceDemo") @Story("Negative login - invalid credentials")
    @Severity(SeverityLevel.NORMAL) @TmsLink("ID-15") @Issue("ID-16") @Owner("Khvadina Aleksandra")
    public void negativeLogin(String user, String password, String expectedError) {
        String userLabel = user.isEmpty() ? "[empty]" : "'" + user + "'";
        log.info("Starting negative login test with user: {}", userLabel);

        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);

        String actualError = loginPage.getErrorMessage();
        softAssert.assertEquals(actualError, expectedError, "Error message mismatch");

        log.info("Expected error: '{}'", expectedError);
        log.info("Actual error:   '{}'", actualError);

        softAssert.assertAll();
        log.info("Negative login test completed");
    }
}


