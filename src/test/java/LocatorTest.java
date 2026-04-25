import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

public class LocatorTest {

    @Test
    public void checkLocator() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // открыть страницу
        driver.get("https://www.saucedemo.com/");
        // 1. поиск по id
        WebElement usernameById = driver.findElement(By.id("user-name"));
        // 2. поиск по name
        WebElement usernameByName = driver.findElement(By.name("user-name"));
        // 3. поиск по className
        WebElement usernameByClass = driver.findElement(By.className("form_input"));
        // 4. поиск по tagName
        WebElement firstDiv = driver.findElement(By.tagName("div"));

        // xpath
        // 1. по атрибуту
        WebElement xpAttr = driver.findElement(By.xpath("//input[@id='password']"));
        // 2. по тексту
        WebElement xpText = driver.findElement(By.xpath("//input[@value='Login']"));
        // 3. по частичному совпадению атрибута
        WebElement xpContAttr = driver.findElement(By.xpath("//input[contains(@value,'Log')]"));
        // 4. по частичному совпадению текста
        WebElement xpContText = driver.findElement(By.xpath("//input[contains(@value,'Log')]"));
        // 5. AND условие
        WebElement xpAnd = driver.findElement(By.xpath("//input[@type='password' and @name='password']"));
        // 6. ancestor
        WebElement xpAnc = driver.findElement(By.xpath("//input[@id='password']//ancestor::form"));
        // 7. descendant
        WebElement xpDesc = driver.findElement(By.xpath("//form//descendant::input"));
        // 8. following
        WebElement xpFollow = driver.findElement(By.xpath("//input[@id='user-name']//following::input[1]"));
        // 9. parent
        WebElement xpParent = driver.findElement(By.xpath("//input[@id='user-name']/parent::div"));
        // 10. preceding
        WebElement xpPreceding = driver.findElement(By.xpath("//input[@value='Login']//preceding::input[1]"));

        // CSS
        // 1. .class (по одному классу)
        WebElement cssClass = driver.findElement(By.cssSelector(".form_input"));
        // 2. .class1.class2 (составной класс, без пробела)
        WebElement cssMultiClass = driver.findElement(By.cssSelector(".input_error.form_input"));
        // 3. #id
        WebElement cssId = driver.findElement(By.cssSelector("#user-name"));
        // 4. tagname.class
        WebElement cssTagClass = driver.findElement(By.cssSelector("input.form_input"));
        // 5. [attribute=value]  - точное совпадение
        WebElement cssAttr = driver.findElement(By.cssSelector("[data-test='username']"));
        // 6. [attribute~=value] - содержит слово разделённое пробелами
        WebElement cssTilde = driver.findElement(By.cssSelector("[class~='form_input']"));
        // 7. [attribute|=value] - значение + дефис
        WebElement cssPipe = driver.findElement(By.cssSelector("[id|='user']"));
        // 8. [attribute^=value] - начинается с
        WebElement cssStartsWith = driver.findElement(By.cssSelector("[data-test^='user']"));
        // 9. [attribute$=value] - заканчивается на
        WebElement cssEndsWith = driver.findElement(By.cssSelector("[data-test$='name']"));
        // 10. [attribute*=value] содержит
        WebElement cssContains = driver.findElement(By.cssSelector("[data-test*='user']"));

        // пришло время залогиниться)
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // 6. поиск по linkText и partialLinkText
        WebElement link = driver.findElement(By.linkText("Sauce Labs Backpack"));
        WebElement partialLink = driver.findElement(By.partialLinkText("Sauce"));

        driver.quit();
    }
}
