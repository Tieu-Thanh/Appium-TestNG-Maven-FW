package core.driver.platforms;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.configuration.ConfigurationManager;
import core.driver.DriverFactory;

import java.net.URI;
import java.net.URL;
import java.util.Map;

public class AndroidDriverFactory implements IDriverFactory {

    private final ConfigurationManager configManager;
    private final String profile;

    public AndroidDriverFactory(ConfigurationManager configManager, String profile) {
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

        // Initialize AndroidDriver with provided capabilities
        try {
            URI uri = new URI(config.get("appiumServerUrl").toString());
            URL appiumServerUrl = uri.toURL();
            return new AndroidDriver(appiumServerUrl, capabilities);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Android driver", e);
        }
    }

}
