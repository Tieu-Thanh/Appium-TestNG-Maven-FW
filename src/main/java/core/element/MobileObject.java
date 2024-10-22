package core.element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import core.driver.DriverHelper;
import core.driver.DriverManager;
import io.appium.java_client.AppiumBy;

import java.util.HashMap;
import java.util.Map;
import java.time.Duration;
import java.util.Arrays;

public class MobileObject {

    private static final Map<Class<? extends By>, String> STRATEGY_MAP = new HashMap<>();

    static {
        // Initialize the map with By and AppiumBy strategies
        STRATEGY_MAP.put(By.ById.class, "id");
        STRATEGY_MAP.put(By.ByXPath.class, "xpath");
        STRATEGY_MAP.put(By.ByClassName.class, "class name");
        STRATEGY_MAP.put(AppiumBy.ByAccessibilityId.class, "accessibility id");
        STRATEGY_MAP.put(AppiumBy.ByAndroidUIAutomator.class, "-android uiautomator");
        STRATEGY_MAP.put(AppiumBy.ByIosNsPredicate.class, "ios predicate string");
    }

    private final By locator;

    // Constructor doesn't locate the element yet
    public MobileObject(By locator) {
        this.locator = locator;
    }

    // Fetch the element dynamically (lazy initialization)
    private WebElement getElement() {
        return DriverHelper.waitForElementToBeVisible(locator); // Wait for the element to be visible before interacting
    }

    // Get the coordinates of the element
    public Point getCoordinates() {
        WebElement element = getElement(); // Get the element dynamically
        return element.getLocation(); // Return the location (x, y) as a Point object
    }

    // Get the coordinates and size of the element
    public Map<String, Integer> getElementRect() {
        WebElement element = getElement(); // Get the element dynamically
        Map<String, Integer> rect = new HashMap<>();

        // Get coordinates and size
        rect.put("x", element.getRect().getX());
        rect.put("y", element.getRect().getY());
        rect.put("width", element.getRect().getWidth());
        rect.put("height", element.getRect().getHeight());

        return rect;
    }

    // Check if the element is visible
    public boolean isVisible() {
        try {
            return DriverHelper.waitForElementToBeVisible(locator) != null;
        } catch (Exception e) {
            return false;
        }
    }

    // Check if the element is enabled (clickable)
    public boolean isEnabled() {
        try {
            WebElement element = DriverHelper.waitForElementToBeClickable(locator);
            return element != null && element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    // Check if the element is displayed
    public boolean isDisplayed() {
        try {
            WebElement element = getElement();
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Check if the element is selected (e.g., for checkboxes or radio buttons)
    public boolean isSelected() {
        try {
            WebElement element = getElement();
            return element != null && element.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    // Tap action using Appium's clickGesture
    public void tap() {
        DriverHelper.waitForElementToBeClickable(locator); // Wait for the element to be clickable using DriverHelper
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) getElement()).getId()); // Get element dynamically
        js.executeScript("mobile: clickGesture", params);
    }

    public void tapByW3C() {
        WebElement element = getElement(); // Locate the element

        // Create a new pointer input (for touch interaction)
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Create the tap action sequence
        Sequence tap = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0),
                        PointerInput.Origin.viewport(),
                        element.getLocation().getX(),
                        element.getLocation().getY())) // Move to the element's position
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // Finger down (tap)
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Finger up (release)

        // Perform the tap action
        DriverManager.getDriver().perform(Arrays.asList(tap));
    }

    // Long press action using Appium's longClickGesture
    public void longPress(int duration) {
        DriverHelper.waitForElementToBeClickable(locator); // Wait for the element to be clickable
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) getElement()).getId()); // Get element dynamically
        params.put("duration", duration);
        js.executeScript("mobile: longClickGesture", params);
    }

    public void longPress() {
        longPress(2000);
    }

    // Swipe action using Appium's swipeGesture
    public void swipe(String direction) {
        DriverHelper.waitForElementToBeVisible(locator); // Ensure the element is visible before swiping

        // Use a map to pass the swipe direction
        Map<String, Object> params = new HashMap<>();
        params.put("direction", direction.toLowerCase()); // Pass direction directly (left, right, up, down)
        params.put("percent", 0.75); // Adjust swipe distance (percent of screen to swipe)
        params.put("elementId", ((RemoteWebElement) DriverManager.getDriver().findElement(locator)).getId());

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("mobile: swipeGesture", params);
    }

    // Pinch action using Appium's pinchGesture
    public void pinch() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) getElement()).getId()); // Get element dynamically
        js.executeScript("mobile: pinchGesture", params);
    }

    // Zoom action using Appium's zoomGesture
    public void zoom() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) getElement()).getId()); // Get element dynamically
        js.executeScript("mobile: zoomGesture", params);
    }

    // Drag and drop action using Appium's dragGesture
    public void dragAndDrop(int startX, int startY, int endX, int endY) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) getElement()).getId()); // Get element dynamically
        params.put("endX", endX);
        params.put("endY", endY);
        js.executeScript("mobile: dragGesture", params);
    }

    // Drag and drop from this object to another MobileObject
    public void dragAndDrop(MobileObject targetObject) {
        // Get the coordinates of the current (source) object
        Map<String, Integer> sourceRect = getElementRect();
        int startX = sourceRect.get("x") + sourceRect.get("width") / 2; // Start from the middle of the element
        int startY = sourceRect.get("y") + sourceRect.get("height") / 2;

        // Get the coordinates of the target (destination) object
        Map<String, Integer> targetRect = targetObject.getElementRect();
        int endX = targetRect.get("x") + targetRect.get("width") / 2;
        int endY = targetRect.get("y") + targetRect.get("height") / 2;

        // Execute the drag and drop action using Appium's dragGesture
        this.dragAndDrop(startX, startY, endX, endY);
    }

    // Type text into an input field
    public void type(String text) {
        getElement().sendKeys(text); // Get element dynamically and type text into it
    }

    public String getText() {
        return getElement().getText();
    }

    // Scroll directly to the element using Appium's 'mobile: scroll' command
    public void scrollToObject() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        Map<String, Object> scrollParams = new HashMap<>();

        // Use the dynamic strategy from the map
        scrollParams.put("strategy", getLocatorStrategy());
        scrollParams.put("selector", getLocatorSelector());
        // scrollParams.put("maxSwipes", 100);
        js.executeScript("mobile: scroll", scrollParams);

        DriverHelper.waitForElementToBeVisible(locator);
    }

    // Determine the strategy from the By locator
    private String getLocatorStrategy() {
        String strategy = STRATEGY_MAP.get(locator.getClass());
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported locator strategy: " + locator);
        }
        return strategy;
    }

    // Extract the actual selector from the By locator
    private String getLocatorSelector() {
        return locator.toString().replaceFirst(".*: ", ""); // Remove 'By.xxx:' part of the locator
    }
}
