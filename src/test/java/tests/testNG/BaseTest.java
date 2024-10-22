package tests.testNG;

import org.testng.annotations.BeforeMethod;

import core.driver.DriverManager;
import core.teststarter.MobileTestStarter;
import pages.NavigationBar;

public class BaseTest extends MobileTestStarter {
    protected NavigationBar navigationBar;

    @BeforeMethod
    public void setUp() {
        // DriverManager.initializeDriver("browserstack", "browserstack");
        navigationBar = new NavigationBar();
    }
}
