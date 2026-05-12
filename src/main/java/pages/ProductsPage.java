package pages;

import org.openqa.selenium.By;
import utils.DriverManager;

public class ProductsPage extends BasePage {

    private final By TITLE = By.className("title");

    public void open() {
       DriverManager.getDriver().get(BASE_URL + "/inventory.html");
}

    public String getTitle() {
       return DriverManager.getDriver().findElement(TITLE).getText();
}
}
