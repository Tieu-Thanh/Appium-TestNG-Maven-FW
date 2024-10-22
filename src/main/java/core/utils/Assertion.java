package core.utils;

import org.testng.Assert;

import com.beust.ah.A;

import core.reporter.AllureReport;

public class Assertion {

    public static void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
        } catch (AssertionError e) {
            // Log the failure message along with the exception details
            AllureReport.logStep("Assertion failed: " + message + ". Error: " + e.getMessage());
            AllureReport.saveScreenshot(message);
            throw e; // Re-throw the exception to fail the test
        }
    }

    public static void assertFalse(boolean condition, String message) {
        try {
            Assert.assertFalse(condition, message);
        } catch (AssertionError e) {
            // Log the failure message along with the exception details
            AllureReport.logStep("Assertion failed: " + message + ". Error: " + e.getMessage());
            AllureReport.saveScreenshot(message);
            throw e; // Re-throw the exception to fail the test
        }
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            // Log the failure message along with the exception details
            AllureReport.logStep("Assertion failed: " + message + ". Error: " + e.getMessage());
            AllureReport.saveScreenshot(message);
            throw e; // Re-throw the exception to fail the test
        }
    }

    public static void assertNotEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertNotEquals(actual, expected, message);
        } catch (AssertionError e) {
            // Log the failure message along with the exception details
            AllureReport.logStep("Assertion failed: " + message + ". Error: " + e.getMessage());
            AllureReport.saveScreenshot(message);
            throw e; // Re-throw the exception to fail the test
        }
    }

}
