package utils;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;

@Log4j2
public class AllureUtils {

    public static void takeScreenshot(WebDriver driver) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(
                "screenshot",
                "image/png",
                new ByteArrayInputStream(screenshot),
                "png"
        );
    }
}
