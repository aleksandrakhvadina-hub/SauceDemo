package utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("================================================ STARTING TEST {} ================================================",
                iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("================================================ FINISHED TEST {} Duration: {}s ================================================",
                iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.error("================================================ FAILED TEST {} Duration: {}s ================================================",
                iTestResult.getName());

        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        AllureUtils.takeScreenshot(driver);
        }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.warn("================================================ SKIPPING TEST {} ================================================",
                iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.warn("================================================ FAILED BUT WITHIN SUCCESS PERCENTAGE {} ================================================",
                iTestResult.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("================================================TEST SUITE STARTED: {} ================================================",
                context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("================================================TEST SUITE FINISHED: {} ================================================",
                context.getName());
    }
}