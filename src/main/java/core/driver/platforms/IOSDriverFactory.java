package core.driver.platforms;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import core.configuration.ConfigurationManager;

import java.net.URI;
import java.net.URL;
import java.util.Map;

public class IOSDriverFactory implements IDriverFactory {

    private final ConfigurationManager configManager;
    private final String profile;

    public IOSDriverFactory(ConfigurationManager configManager, String profile) {
        this.configManager = configManager;
        this.profile = profile;
    }

    @Override
    public AppiumDriver createDriver() {
        // Load config for the provided profile
        Map<String, Object> config = configManager.getConfig(profile);

        // Set up DesiredCapabilities using config
        DesiredCapabilities capabilities = new DesiredCapabilities();
        config.forEach(capabilities::setCapability);

        // Initialize IOSDriver with provided capabilities
        try {
            URI uri = new URI(config.get("appiumServerUrl").toString());
            URL appiumServerUrl = uri.toURL();
            return new IOSDriver(appiumServerUrl, capabilities);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create iOS driver", e);
        }
    }
}
