package pages;


import core.element.MobileObject;
import io.appium.java_client.AppiumBy;

public class FormsPage {
    // Input field
    private MobileObject _txtInputField = new MobileObject(AppiumBy.accessibilityId("text-input"));
    private MobileObject _txtResultInput = new MobileObject(AppiumBy.accessibilityId("input-text-result"));

    // Switch
    private MobileObject _switchToggle = new MobileObject(AppiumBy.accessibilityId("switch"));
    private MobileObject _txtSwitchResult = new MobileObject(AppiumBy.accessibilityId("switch-text"));

    // Dropdown
    private MobileObject _dropdown = new MobileObject(AppiumBy.accessibilityId("Dropdown"));
    private MobileObject _itemDropdown(String label) {
        return new MobileObject(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + label + "\")"));
    }

    // Active Button
    private MobileObject _btnActive = new MobileObject(AppiumBy.accessibilityId("button-Active"));
    private MobileObject _btnPopup(String label) {
        return new MobileObject(AppiumBy.androidUIAutomator("new UiSelector().text(\""+ label+"\")"));
    }

    // Inactive Button
    private MobileObject _btnInactive = new MobileObject(AppiumBy.accessibilityId("button-Inactive"));

    // Methods to interact with the elements

    public void enterText(String text) {
        _txtInputField.type(text); // Send text to the input field
    }

    public String getResultText(){
        return _txtResultInput.getText();
    }

    public void toggleSwitch() {
        _switchToggle.tap(); // Tap the switch to toggle
    }

    public String getSwitchResultText(){
        return _txtSwitchResult.getText();
    }

    public boolean verifySwitchWorks() {
        String originString = this.getSwitchResultText();
        this.toggleSwitch();
        return !originString.equals(this.getSwitchResultText());
    }

    public void openDropdown() {
        _dropdown.tap(); // Tap to open the dropdown
    }
    public void chooseItem(String label) {
        _itemDropdown(label).tap(); // Tap to choose an item from the dropdown
    }

    public void clickActiveButton() {
        _btnActive.scrollToObject();
        _btnActive.tap(); // Tap the "Active" button
    }
    public void clickPopupButton(String label){
        clickActiveButton();
        _btnPopup(label).tap();
    }


    public void clickInactiveButton() {
        _btnInactive.tap(); // Tap the "Inactive" button
    }
}
