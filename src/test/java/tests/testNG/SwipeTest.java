package tests.testNG;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import core.driver.DriverHelper;
import core.reporter.AllureReport;
import pages.SwipePage;

public class SwipeTest extends BaseTest{
    private SwipePage _swipePage;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        AllureReport.logStep("Navigating to Swipe view");
        _swipePage = new SwipePage();
        navigationBar.clickView("Swipe");
    }

    @Test
    public void testSwipeCarousel() {
        AllureReport.logStep("Swiping the carousel to the left");
        _swipePage.swipeCarousel("left");
    }

    @Test
    public void testScrollToLogo() {
        AllureReport.logStep("Scrolling down the page");
        DriverHelper.scrollDown();

        // AllureReport.logStep("Scrolling to the logo");
        // _swipePage.scrollToLogo();
    }

}
