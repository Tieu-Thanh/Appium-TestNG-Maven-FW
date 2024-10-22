package tests.testNG;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import core.reporter.AllureReport;
import pages.FormsPage;

import static org.testng.Assert.assertEquals;

public class FormsTest extends BaseTest {
    private FormsPage formsPage;

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        AllureReport.logStep("Navigating to Forms view");
        formsPage = new FormsPage();
        navigationBar.clickView("Forms");
    }

    @Test
    public void testButtonClick() {
        AllureReport.logStep("Clicking on Popup Button");
        formsPage.clickPopupButton("OK");

        // AllureReport.logStep("Clicking Inactive Button");
        // formsPage.clickInactiveButton();
    }

    @Test
    public void testInputField() {
        AllureReport.logStep("Entering text in the input field");
        formsPage.enterText("Sample Text");

        AllureReport.logStep("Verifying the result text");
        assertEquals(formsPage.getResultText(), "Sample Text", "The result text should match the entered text.");
    }
}
