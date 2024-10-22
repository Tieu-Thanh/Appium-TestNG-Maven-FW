package core.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.configuration.ConfigurationManager;
import core.constant.Constant;
import core.driver.platforms.IDriverFactory;
import core.driver.platforms.*;
import io.appium.java_client.AppiumDriver;

public class DriverFactory {

    private final ConfigurationManager configManager;
    private final String platform;
    private final String profile;

    public DriverFactory(String platform, String profile, ConfigurationManager configManager) {
        this.platform = platform;
        this.profile = profile;
        this.configManager = configManager;
    }

    public IDriverFactory getDriverFactory() {
        switch (platform.toLowerCase()) {
            case "android":
                return new AndroidDriverFactory(configManager, profile);
            case "ios":
                return new IOSDriverFactory(configManager, profile);
            case "browserstack":
                return new BrowserStackDriverFactory(configManager, profile);
            default:
                throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }

    public static AppiumDriver createDriver(String platform, String profile) {
        ConfigurationManager configManager = ConfigurationManager.getInstance(Constant.CONFIG_PROFILES_PATH);

        // Create the appropriate factory based on platform
        IDriverFactory driverFactory;
        switch (platform.toLowerCase()) {
            case "android":
                driverFactory = new AndroidDriverFactory(configManager, profile);
                break;
            case "ios":
                driverFactory = new IOSDriverFactory(configManager, profile);
                break;
            case "browserstack":
                driverFactory = new BrowserStackDriverFactory(configManager, profile);
                break;
            default:
                throw new IllegalArgumentException("Unsupported platform: " + platform);
        }

        // Use the platform-specific factory to create the driver
        return driverFactory.createDriver();
    }
}
