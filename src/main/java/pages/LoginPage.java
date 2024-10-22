package pages;

import core.driver.DriverHelper;
import core.element.MobileObject;
import io.appium.java_client.AppiumBy;
import model.AccountDto;
import model.CredentialsDto;

public class LoginPage {
    // Login
    private MobileObject _btnLoginTab = new MobileObject(AppiumBy.accessibilityId("button-login-container"));
    private MobileObject _txtLoginEmail = new MobileObject(AppiumBy.accessibilityId("input-email"));
    private MobileObject _txtLoginPassword = new MobileObject(AppiumBy.accessibilityId("input-password"));
    private MobileObject _btnLogin = new MobileObject(AppiumBy.accessibilityId("button-LOGIN"));
    private MobileObject _msgInvalidEmail = new MobileObject(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Please enter a valid email address\")"));
    private MobileObject _msgInvalidPassword = new MobileObject(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Please enter at least 8 characters\")"));

    // Sign up
    private MobileObject _btnSignUpTab = new MobileObject(AppiumBy.accessibilityId("button-sign-up-container"));
    private MobileObject _txtSignupEmail = new MobileObject(AppiumBy.accessibilityId("input-email"));
    private MobileObject _txtSignupPassword = new MobileObject(AppiumBy.accessibilityId("input-password"));
    private MobileObject _txtSignupConfirmPassword = new MobileObject(
            AppiumBy.accessibilityId("input-repeat-password"));
    private MobileObject _btnSignup = new MobileObject(AppiumBy.accessibilityId("button-SIGN UP"));

    public void fillLoginForm(String email, String password) {
        _btnLoginTab.tapByW3C();
        _txtLoginEmail.type(email);
        _txtLoginPassword.type(password);
        _btnLogin.tapByW3C();
    }

    public void fillLoginForm(AccountDto account) {
        fillLoginForm(account.getEmail(), account.getPassword());
    }

    public void fillSignUpForm(String email, String password, String confirmPassword) {
        _btnSignUpTab.tapByW3C();
        _txtSignupEmail.type(email);
        _txtSignupPassword.type(password);
        _txtSignupConfirmPassword.type(confirmPassword);
        _btnSignup.tapByW3C();
    }

    public void fillSignUpForm(CredentialsDto credentials) {
        fillSignUpForm(credentials.getEmail(), credentials.getPassword(), credentials.getConfirmPassword());
    }

    public boolean isSignupSuccessfully() {
        if (DriverHelper.isAlertPresent()) {
            DriverHelper.acceptAlert();
            return true;
        } else {
            return false;
        }
    }

    public boolean isLoginSuccessfully() {
        if (DriverHelper.isAlertPresent()) {
            DriverHelper.acceptAlert();
            return true;
        } else {
            return false;
        }
    }

    public boolean isMsgInvalidEmailDisplayed() {
        return _msgInvalidEmail.isDisplayed();
    }

    public boolean isMsgInvalidPasswordDisplayed() {
        return _msgInvalidPassword.isDisplayed();
    }
}
