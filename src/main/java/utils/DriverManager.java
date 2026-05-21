package utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            log.warn("Driver not initialized for thread: {}", Thread.currentThread().getName());
        }
        return driver;
    }
    public static void setDriver(WebDriver driver) {
        log.info("Setting driver for thread '{}': {}", Thread.currentThread().getName(), driver.getClass().getSimpleName());
        driverThreadLocal.set(driver);
    }
    public static void quitDriver() {
        log.info("Quitting driver for thread: {}", Thread.currentThread().getName());

        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            log.info("Driver quit successfully");
        } else {
            log.warn("Driver was null, nothing to quit");
        }
        driverThreadLocal.remove();
        log.info("Driver removed from ThreadLocal");
    }
}
