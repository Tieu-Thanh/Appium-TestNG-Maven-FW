package core.driver.platforms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import core.configuration.ConfigurationManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;
import java.net.URI;
import java.util.Map;

public class BrowserStackDriverFactory implements IDriverFactory {
    private final ConfigurationManager configManager;
    private final String profile;

    public BrowserStackDriverFactory(ConfigurationManager configManager, String profile) {
        this.configManager = configManager;
        this.profile = profile;
    }

    @Override
    public AppiumDriver createDriver() {
        Map<String, Object> config = configManager.getConfig(profile);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        config.forEach(capabilities::setCapability);

        try {
            URI uri = new URI(config.get("appiumServerUrl").toString());
            URL appiumServerUrl = uri.toURL();

            switch (config.get("platformName").toString().toLowerCase()) {
                case "android":
                    return new AndroidDriver(appiumServerUrl, capabilities);
                case "ios":
                    return new IOSDriver(appiumServerUrl, capabilities);
                default:
                    throw new IllegalArgumentException("Unsupported platform: " + config.get("platformName"));
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to create BrowserStack driver", e);
        }
    }
}
