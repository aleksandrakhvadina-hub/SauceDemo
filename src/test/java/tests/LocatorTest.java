package tests;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import utils.DriverManager;

@Log4j2
public class LocatorTest extends BaseTest {

    @Test(
            description = "Проверка локаторов Selenium",
            testName = "Проверка локаторов Selenium",
            groups = {"regression"}
    )
    @Description("Проверка локаторов Selenium")
    @Epic("Technical")
    @Feature("Локаторы и элементы")
    @Story("Проверка локаторов Selenium")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("ID-7")
    @Issue("ID-8")
    @Owner("Khvadina Aleksandra")
    public void checkLocator() {
        log.info("Starting locator verification test");
        // открыть страницу
        WebDriver driver = DriverManager.getDriver();
        log.info("Opening SauceDemo login page");
        driver.get("https://www.saucedemo.com/");
        log.info("Testing basic locators");
        WebElement usernameById = driver.findElement(By.id("user-name"));
        log.info("✓ Found by id: {}", usernameById.isDisplayed());

        WebElement usernameByName = driver.findElement(By.name("user-name"));
        log.info("✓ Found by name: {}", usernameByName.isDisplayed());

        WebElement usernameByClass = driver.findElement(By.className("form_input"));
        log.info("✓ Found by className: {}", usernameByClass.isDisplayed());

        WebElement firstDiv = driver.findElement(By.tagName("div"));
        log.info("✓ Found by tagName: {}", firstDiv.isDisplayed());

        // xpath
        log.info("Testing XPath locators");

        // 1. по атрибуту
        WebElement xpAttr = driver.findElement(By.xpath("//input[@id='password']"));
        log.info("✓ XPath by attribute: {}", xpAttr.isDisplayed());

        // 2. по тексту (для input value)
        WebElement xpText = driver.findElement(By.xpath("//input[@value='Login']"));
        log.info("✓ XPath by value: {}", xpText.isDisplayed());

        // 3. по частичному совпадению атрибута
        WebElement xpContAttr = driver.findElement(By.xpath("//input[contains(@value,'Log')]"));
        log.info("✓ XPath contains attribute: {}", xpContAttr.isDisplayed());

        // 4. AND условие
        WebElement xpAnd = driver.findElement(By.xpath("//input[@type='password' and @name='password']"));
        log.info("✓ XPath AND condition: {}", xpAnd.isDisplayed());

        // 5. ancestor
        WebElement xpAnc = driver.findElement(By.xpath("//input[@id='password']//ancestor::form"));
        log.info("✓ XPath ancestor: {}", xpAnc.isDisplayed());

        // 6. following
        WebElement xpFollow = driver.findElement(By.xpath("//input[@id='user-name']//following::input[1]"));
        log.info("✓ XPath following: {}", xpFollow.isDisplayed());

        // 7. parent
        WebElement xpParent = driver.findElement(By.xpath("//input[@id='user-name']/parent::div"));
        log.info("✓ XPath parent: {}", xpParent.isDisplayed());

        // CSS
        log.info("Testing CSS selectors");

        // 1. .class
        WebElement cssClass = driver.findElement(By.cssSelector(".form_input"));
        log.info("✓ CSS by class: {}", cssClass.isDisplayed());

        // 2. #id
        WebElement cssId = driver.findElement(By.cssSelector("#user-name"));
        log.info("✓ CSS by id: {}", cssId.isDisplayed());

        // 3. [attribute=value]
        WebElement cssAttr = driver.findElement(By.cssSelector("[data-test='username']"));
        log.info("✓ CSS by attribute: {}", cssAttr.isDisplayed());

        // 4. [attribute^=value] starts with
        WebElement cssStartsWith = driver.findElement(By.cssSelector("[data-test^='user']"));
        log.info("✓ CSS starts with: {}", cssStartsWith.isDisplayed());

        // 5. [attribute*=value] contains
        WebElement cssContains = driver.findElement(By.cssSelector("[data-test*='user']"));
        log.info("✓ CSS contains: {}", cssContains.isDisplayed());

        // пришло время залогиниться)
        log.info("Logging in via PageObject");
        loginPage.login("standard_user", "secret_sauce");
        log.info("Login completed");

        // 6. поиск по linkText и partialLinkText
        log.info("Testing link locators");
        WebElement link = driver.findElement(By.linkText("Sauce Labs Backpack"));
        log.info("✓ Found by linkText: {}", link.isDisplayed());

        WebElement partialLink = driver.findElement(By.partialLinkText("Sauce"));
        log.info("✓ Found by partialLinkText: {}", partialLink.isDisplayed());

        log.info("Locator verification test completed successfully");
    }
}
