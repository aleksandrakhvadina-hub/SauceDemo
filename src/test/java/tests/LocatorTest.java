package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import tests.base.BaseTest;
import utils.DriverManager;

public class LocatorTest extends BaseTest {

    @Test(
            description = "Проверка локаторов Selenium",
            testName = "Проверка локаторов Selenium",
            groups = {"regression"}
    )
    public void checkLocator() {
        // открыть страницу
        DriverManager.getDriver().get("https://www.saucedemo.com/");
        // 1. поиск по id
        WebElement usernameById = DriverManager.getDriver().findElement(By.id("user-name"));
        // 2. поиск по name
        WebElement usernameByName = DriverManager.getDriver().findElement(By.name("user-name"));
        // 3. поиск по className
        WebElement usernameByClass = DriverManager.getDriver().findElement(By.className("form_input"));
        // 4. поиск по tagName
        WebElement firstDiv = DriverManager.getDriver().findElement(By.tagName("div"));

        // xpath
        // 1. по атрибуту
        WebElement xpAttr = DriverManager.getDriver().findElement(By.xpath("//input[@id='password']"));
        // 2. по тексту
        WebElement xpText = DriverManager.getDriver().findElement(By.xpath("//input[@value='Login']"));
        // 3. по частичному совпадению атрибута
        WebElement xpContAttr = DriverManager.getDriver().findElement(By.xpath("//input[contains(@value,'Log')]"));
        // 4. по частичному совпадению текста
        WebElement xpContText = DriverManager.getDriver().findElement(By.xpath("//input[contains(@value,'Log')]"));
        // 5. AND условие
        WebElement xpAnd = DriverManager.getDriver().findElement(By.xpath("//input[@type='password' and @name='password']"));
        // 6. ancestor
        WebElement xpAnc = DriverManager.getDriver().findElement(By.xpath("//input[@id='password']//ancestor::form"));
        // 7. descendant
        WebElement xpDesc = DriverManager.getDriver().findElement(By.xpath("//form//descendant::input"));
        // 8. following
        WebElement xpFollow = DriverManager.getDriver().findElement(By.xpath("//input[@id='user-name']//following::input[1]"));
        // 9. parent
        WebElement xpParent = DriverManager.getDriver().findElement(By.xpath("//input[@id='user-name']/parent::div"));
        // 10. preceding
        WebElement xpPreceding = DriverManager.getDriver().findElement(By.xpath("//input[@value='Login']//preceding::input[1]"));

        // CSS
        // 1. .class (по одному классу)
        WebElement cssClass = DriverManager.getDriver().findElement(By.cssSelector(".form_input"));
        // 2. .class1.class2 (составной класс, без пробела)
        WebElement cssMultiClass = DriverManager.getDriver().findElement(By.cssSelector(".input_error.form_input"));
        // 3. #id
        WebElement cssId = DriverManager.getDriver().findElement(By.cssSelector("#user-name"));
        // 4. tagname.class
        WebElement cssTagClass = DriverManager.getDriver().findElement(By.cssSelector("input.form_input"));
        // 5. [attribute=value]  - точное совпадение
        WebElement cssAttr = DriverManager.getDriver().findElement(By.cssSelector("[data-test='username']"));
        // 6. [attribute~=value] - содержит слово разделённое пробелами
        WebElement cssTilde = DriverManager.getDriver().findElement(By.cssSelector("[class~='form_input']"));
        // 7. [attribute|=value] - значение + дефис
        WebElement cssPipe = DriverManager.getDriver().findElement(By.cssSelector("[id|='user']"));
        // 8. [attribute^=value] - начинается с
        WebElement cssStartsWith = DriverManager.getDriver().findElement(By.cssSelector("[data-test^='user']"));
        // 9. [attribute$=value] - заканчивается на
        WebElement cssEndsWith = DriverManager.getDriver().findElement(By.cssSelector("[data-test$='name']"));
        // 10. [attribute*=value] содержит
        WebElement cssContains = DriverManager.getDriver().findElement(By.cssSelector("[data-test*='user']"));

        // пришло время залогиниться)
        DriverManager.getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        DriverManager.getDriver().findElement(By.id("password")).sendKeys("secret_sauce");
        DriverManager.getDriver().findElement(By.id("login-button")).click();

        // 6. поиск по linkText и partialLinkText
        WebElement link = DriverManager.getDriver().findElement(By.linkText("Sauce Labs Backpack"));
        WebElement partialLink = DriverManager.getDriver().findElement(By.partialLinkText("Sauce"));
    }
}
