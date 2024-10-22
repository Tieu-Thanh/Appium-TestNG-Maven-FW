package tests.testNG;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import core.driver.DriverManager;
import core.reporter.AllureReport;
import pages.DragPage;

public class DragTest extends BaseTest {
    private DragPage _dragPage;

    // @Override
    // @BeforeTest
    // public void setupTest() throws Exception {
    //     // super.setupTest();
    //     DriverManager.initializeDriver("browserstack", "browserstack");
    // }


    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        AllureReport.logStep("Navigating to Drag view");
        _dragPage = new DragPage();
        navigationBar.clickView("Drag");
    }

    @Test
    public void dragAndDropTest() {
        AllureReport.logStep("Starting drag and drop test");

        AllureReport.logStep("Solve puzzle");
        _dragPage.solvePuzzle();  // Dragging and dropping actions

        AllureReport.logStep("Verify puzzle completion");
        _dragPage.verifyFinishPuzzle();

        AllureReport.saveScreenshot("Quiz_Screenshot");
    }
}
