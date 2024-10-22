package core.teststarter;

import core.constant.Constant;
import core.driver.DriverManager;
import core.reporter.AllureReport;

import org.testng.annotations.*;

@Listeners(AllureReport.class)
public class MobileTestStarter {

    @BeforeSuite
    public void setupSuite() throws Exception {
        // Initialize the report directory or other global setup if necessary
    }

    @BeforeTest(alwaysRun = true)
    public void setupTest() throws Exception {
    }

    @BeforeClass(alwaysRun = true)
    public void setupClass() throws Exception {
        // Class-level setup if necessary
    }

    @BeforeMethod(alwaysRun = true)
    public void setupMethod() throws Exception {
        DriverManager.initializeDriver(Constant.DEFAULT_PLATFORM, Constant.DEFAULT_PROFILE);
        // DriverManager.initializeDriver("browserstack", "browserstack");
    }

    @AfterMethod(alwaysRun = true)
    public void teardownMethod() throws Exception {

    }

    @AfterClass(alwaysRun = true)
    public void teardownClass() throws Exception {
        // Quit the driver after all methods in this class are executed
        DriverManager.quitDriver();
    }

    @AfterTest(alwaysRun = true)
    public void teardownTest() throws Exception {
        // Teardown after the test if necessary
    }

    @AfterSuite(alwaysRun = true)
    public void teardownSuite() throws Exception {
        // Global teardown if necessary
    }
}
