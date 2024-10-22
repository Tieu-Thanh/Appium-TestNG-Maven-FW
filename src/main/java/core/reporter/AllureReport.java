package core.reporter;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import core.driver.DriverManager;

public class AllureReport implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
        logStep("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
        logStep("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        logStep("Test Failed: " + result.getName());
        saveScreenshot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
        logStep("Test Skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Start of Test Execution: " + context.getName());
        logStep("Start of Test Execution: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("End of Test Execution: " + context.getName());
        logStep("End of Test Execution: " + context.getName());
    }

    @Step("{description}")
    public static void logStep(String description) {
        // System.out.println(description);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] saveScreenshot(String testName) {
        // Add code to capture screenshot using Appium or Selenium
        if (DriverManager.isDriverInitialized()) {
            return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }

    @Attachment(value = "{message}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }
}
