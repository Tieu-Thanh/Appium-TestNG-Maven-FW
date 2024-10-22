package core.driver;

import io.appium.java_client.AppiumDriver;

public class DriverManager {

    // private static AppiumDriver driver;
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static void initializeDriver(String platform, String profile) {
        if (driver.get() != null) {
            return; // Driver already initialized
        }

        AppiumDriver newDriver = DriverFactory.createDriver(platform, profile);
        driver.set(newDriver);
    }

    public static boolean isDriverInitialized() {
        return driver.get() != null;
    }

    public static AppiumDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver not initialized. Call initializeDriver() first.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
