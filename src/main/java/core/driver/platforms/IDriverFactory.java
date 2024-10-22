package core.driver.platforms;

import io.appium.java_client.AppiumDriver;

public interface IDriverFactory {
    AppiumDriver createDriver();
}
