package pages;

import static org.testng.Assert.assertEquals;

import core.element.MobileObject;
import io.appium.java_client.AppiumBy;

public class DragPage {
    // dropped boxes
    private MobileObject _boxDrop(String order) {
        return new MobileObject(AppiumBy.accessibilityId("drop-" + order));
    }

    // draggable boxes
    private MobileObject _boxDrag(String order) {
        return new MobileObject(AppiumBy.accessibilityId("drag-" + order));
    }

    // button Retry
    private MobileObject _btnRetry = new MobileObject(AppiumBy.accessibilityId("button-Retry"));

    // congratulations text
    private MobileObject _txtCongratulations = new MobileObject(AppiumBy.androidUIAutomator("new UiSelector().text(\"Congratulations\")"));



    public void dragToBox(String dragOrder, String boxOrder) {
        _boxDrag(dragOrder).dragAndDrop(_boxDrop(boxOrder));
    }

    public void solvePuzzle() {
        String[] columns = { "l", "c", "r" }; // Columns: l (left), c (center), r (right)

        for (String column : columns) {
            for (int row = 1; row <= 3; row++) {
                // Construct source and target identifiers dynamically
                String source = column + row; // For example, l1, c2, r3
                String target = column + row; // For example, l1, c2, r3

                // Drag the source element to the target box
                this.dragToBox(source, target);
            }
        }
    }

    public void verifyFinishPuzzle(){
        assertEquals(_txtCongratulations.getText(), "Congratulations", null);
    }

    public void tapRetry(){
        _btnRetry.tap();
    }
}
