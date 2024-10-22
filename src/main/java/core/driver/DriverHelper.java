package core.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import core.constant.Constant;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DriverHelper {

    private static Duration timeout = Duration.ofSeconds(Constant.DEFAULT_EXPLICIT_WAIT);

    // Wait for an element to be visible
    public static WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for an element to be clickable
    public static WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Implicit wait to handle mobile actions (set implicit wait globally)
    public static void setImplicitWait(long timeInSeconds) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
    }

    // Close the app by executing a shell command to stop the app process
    public static void closeApp() {
        String appPackage = DriverManager.getDriver().getCapabilities().getCapability("appPackage").toString();
        DriverManager.getDriver().executeScript("mobile: shell",
                ImmutableMap.of("command", "am force-stop " + appPackage));
    }

    // Launch the app by starting the main activity
    public static void launchApp() {
        String appPackage = DriverManager.getDriver().getCapabilities().getCapability("appPackage").toString();
        String appActivity = ".HomeActivity"; // Replace with your main activity name
        DriverManager.getDriver().executeScript("mobile: startActivity", ImmutableMap.of(
                "appPackage", appPackage,
                "appActivity", appActivity));
    }

    // Restart the app
    public static void restartApp() {
        closeApp();
        launchApp();
    }

    // Pause the test execution (useful for debugging or deliberate delays)
    public static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeout);
            wait.until(ExpectedConditions.alertIsPresent()); // Wait until the alert is present
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Handle alert popups (if any)
    public static void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeout);
        wait.until(ExpectedConditions.alertIsPresent());
        DriverManager.getDriver().switchTo().alert().accept();
    }

    // Dismiss alert
    public static void dismissAlert() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeout);
        wait.until(ExpectedConditions.alertIsPresent());
        DriverManager.getDriver().switchTo().alert().dismiss();
    }

    // Get text from alert (if needed)
    public static String getAlertText() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeout);
        wait.until(ExpectedConditions.alertIsPresent());
        return DriverManager.getDriver().switchTo().alert().getText();
    }

    // Navigate to a specific URL (if testing a webview or hybrid app)
    public static void navigateTo(String url) {
        DriverManager.getDriver().get(url);
    }

    // Check if page source contains a specific text (useful for verification)
    public static boolean isPageSourceContains(String text) {
        return DriverManager.getDriver().getPageSource().contains(text);
    }

    // Scroll down by screen size (using Actions API)
    public static void scrollDown() {

        Map<String, Object> scrollParams = new HashMap<>();

        scrollParams.put("left", 100);
        scrollParams.put("top", 100);
        scrollParams.put("width", 200); // No horizontal scrolling
        scrollParams.put("height", 200); // Vertical scrolling
        scrollParams.put("direction", "down"); // Scroll direction (down, up, left, right)
        scrollParams.put("percent", 1.0); // Scroll 100% of the screen height

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("mobile: scrollGesture", scrollParams);
    }
}
