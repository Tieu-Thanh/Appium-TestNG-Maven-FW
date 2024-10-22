package pages;

import org.openqa.selenium.By;

import core.element.MobileObject;
import io.appium.java_client.AppiumBy;

public class SwipePage {
    private MobileObject _carousel = new MobileObject(AppiumBy.accessibilityId("Carousel"));
    private MobileObject _card0 = new MobileObject(AppiumBy.androidUIAutomator("new UiSelector().description(\"card\").instance(0)"));
    private MobileObject _logoWebIo = new MobileObject(AppiumBy.androidUIAutomator("new UiSelector().description(\"WebdriverIO logo\")"));

    public void swipeCarousel(String direction) {
        // _card0.swipe(direction);
        _carousel.swipe(direction);
    }

    public void scrollToLogo() {
        _logoWebIo.scrollToObject();
    }
}
