package tests.testNG;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import core.annotation.DataKey;
import core.reporter.AllureReport;
import core.utils.JsonUtils;
import dataprovider.DataProvider;
import core.utils.AnnotationUtils;
import core.utils.Assertion;
import model.AccountDto;
import model.CredentialsDto;
import pages.LoginPage;


public class LoginTest extends BaseTest {
    private LoginPage _loginPage;
    private AnnotationUtils annotationUtils;
    private Map<String, AccountDto> _accounts;
    private static final String ACCOUNTS_PATH = "test-data/accounts.json";

    public LoginTest() {
        _loginPage = new LoginPage();
        annotationUtils = new AnnotationUtils();
        try {
            _accounts = JsonUtils.parseJson(ACCOUNTS_PATH, AccountDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        AllureReport.logStep("Navigating to Login view");
        navigationBar.clickView("Login");
    }

    @Test(groups = { "loginTest", "valid" })
    public void LoginSuccessfullTest() throws Exception {
        // Log: Retrieve account data using the preloaded _accounts map
        AllureReport.logStep("Read data from JSON file");
        AccountDto account = _accounts.get("account1");

        // Log: Filling login form with valid credentials
        AllureReport.logStep("Filling login form with valid credentials");
        _loginPage.fillLoginForm(account);

        // Log: Verifying if login was successful
        Assertion.assertTrue(_loginPage.isLoginSuccessfully(), "Login is not successful");
    }

    @Test(groups = { "loginTest", "invalid" })
    @DataKey("account2")
    void LoginMissingEmailTest(Method method) throws Exception {
        AllureReport.logStep("Read data from JSON file for missing email test");
        AccountDto account = annotationUtils.getData(method, _accounts);

        AllureReport.logStep("Filling login form with missing email");
        _loginPage.fillLoginForm(account.getEmail(), account.getPassword());

        Assertion.assertTrue(_loginPage.isMsgInvalidEmailDisplayed(), "Warning message is not displayed");
    }

    @Test(dataProvider = "credentialsDataProvider", dataProviderClass = DataProvider.class, groups = { "valid" })
    public void SignupSuccessfullTest(String dataKey, CredentialsDto credentials) {
        // Log: Filling sign up form with test data
        AllureReport.logStep("Filling signup form with test data");
        _loginPage.fillSignUpForm(credentials);

        // Assertion
        Assertion.assertTrue(_loginPage.isSignupSuccessfully(), "Signup is not successful");
    }
}
