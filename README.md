
# Appium Framework

This project is a testing framework designed for mobile applications using **Appium**, **Selenium**, and **TestNG**. It supports running tests on Android emulators and real devices. The project is managed using **Maven** to handle dependencies and uses **Allure** for test reporting.

## Project Structure

```
APPiumFramework/
│
├── src/
    ├───main
    │   ├───java
    │   │   ├───core                # Core framework components
    │   │   │   ├───annotation      # Custom annotations
    │   │   │   │       DataKey.java
    │   │   │   │
    │   │   │   ├───configuration   # Configuration management
    │   │   │   │       ConfigurationManager.java
    │   │   │   │
    │   │   │   ├───constant        # Project constants
    │   │   │   │       Constant.java
    │   │   │   │
    │   │   │   ├───driver          # Driver management for different platforms
    │   │   │   │   │   DriverFactory.java
    │   │   │   │   │   DriverHelper.java
    │   │   │   │   │   DriverManager.java
    │   │   │   │   └───platforms
    │   │   │   │           AndroidDriverFactory.java
    │   │   │   │           BrowserStackDriverFactory.java
    │   │   │   │           IDriverFactory.java
    │   │   │   │           IOSDriverFactory.java
    │   │   │   │
    │   │   │   ├───element         # Mobile element utilities
    │   │   │   │       MobileObject.java
    │   │   │   │
    │   │   │   ├───reporter        # Reporting utilities (Allure)
    │   │   │   │       AllureReport.java
    │   │   │   │
    │   │   │   ├───teststarter     # Base class for test setup and teardown
    │   │   │   │       MobileTestStarter.java
    │   │   │   │
    │   │   │   └───utils           # General utilities
    │   │   │           AnnotationUtils.java
    │   │   │           Assertion.java
    │   │   │           DataUtils.java
    │   │   │           JsonUtils.java
    │   │   │
    │   │   ├───dataprovider        # Data provider for test data
    │   │   │       DataProvider.java
    │   │   │
    │   │   ├───model               # Data Transfer Objects (DTOs)
    │   │   │       AccountDto.java
    │   │   │       CredentialsDto.java
    │   │   │
    │   │   └───pages               # Page Object Model (POM) classes for interacting with UI elements
    │   │           DragPage.java
    │   │           FormsPage.java
    │   │           LoginPage.java
    │   │           NavigationBar.java
    │   │           SwipePage.java
    │   │
    │   └───resources               # Non-code resources (configuration files, APKs, etc.)
    │       ├───app
    │       │       android.wdio.native.app.v1.0.8.apk   # APK file for testing
    │       └───config
    │               config.json      # Configuration JSON for the project
    │
    └───test                        # Test files and test resources
        ├───java
        │   └───tests
        │       ├───cucumber        # Cucumber (BDD) test cases
        │       └───testNG          # TestNG (unit testing) test cases
        │               BaseTest.java
        │               DragTest.java
        │               FormsTest.java
        │               LoginTest.java
        │               SwipeTest.java
        │
        └───resources               # Test-related resources like data files and configurations
            ├───test-data           # Test data in JSON format
            │       accounts.json    # Account-related test data
            │       credentials.json # Credentials-related test data
            │
            └───allure.properties    # Allure reporting configuration

├── target/                # Maven target directory
├── .gitignore             # Ignored files for Git
└── pom.xml                # Maven project file with dependencies
```

## Getting Started

### Prerequisites

Ensure that you have the following installed:
- **Java 8+**
- **Maven** (for dependency management and test execution)
- **Appium** (for mobile automation)
- **Android SDK** (for running Android emulators or devices)

### Setting Up an Emulator or Device

To get the device information and set up the test environment, follow these steps:

1. Run the emulator or connect a real device:
   ```
   adb devices
   ```
   Example output:
   ```
   List of devices attached
   emulator-5554   device
   1234567890ABCDEF device
   ```

2. Retrieve device details (e.g., Android version and current activity):
   ```
   adb -s [device_id] shell getprop ro.build.version.release
   adb shell dumpsys window | grep mCurrentFocus
   ```
   This will return the app package and activity needed for Appium setup.


## Running the Tests

### Run Commands

To execute the tests, you can use the following commands:

1. Start the Android emulator:
   ```
   emulator -avd android-34
   ```

2. Start the Appium server:
   ```
   appium
   ```

3. Run the tests with Maven:
   ```
   mvn clean test
   ```

4. Generate and serve the Allure report:
   ```
   mvn allure:serve
   ```

You can also run specific test classes or methods:
```
mvn -Dtest=LoginTest#LoginSuccessfullTest test
mvn -Dtest=LoginTest test
mvn test -Dgroups="valid, invalid"
```


## Dependency Management

The `pom.xml` file includes essential dependencies such as **Selenium**, **Appium**, and **slf4j**. To avoid conflicts, several dependency exclusions and version alignments have been made:

1. **Selenium Version Consistency**: Managed through `<dependencyManagement>` to ensure consistent versioning (4.25.0).
2. **Slf4j Conflict Resolution**: Excluded older versions from Appium and ensured the project uses `slf4j-api` version 1.7.36.
3. **Appium Java Client**: Updated to version 9.2.2 with dependency exclusions to avoid conflicts with Selenium.


## Configuration



### Configuration Explanation

The project includes a `config.json` file located in `src/main/resources/config`, which holds configurations for running tests across different environments such as Android emulators, real devices, and BrowserStack. Below is an explanation of each configuration and how it is used.

---

#### 1. **Emulator Configuration** (`emulator`)

This configuration is used for running tests on a local Android emulator.

```json
"emulator": {
    "platformName": "Android",
    "deviceName": "emulator-5554",
    "platformVersion": "14",
    "app": "./src/main/resources/app/android.wdio.native.app.v1.0.8.apk",
    "appPackage": "com.wdiodemoapp",
    "appActivity": "com.wdiodemoapp.MainActivity",
    "automationName": "UiAutomator2",
    "noReset": false,
    "fullReset": false,
    "newCommandTimeout": 300,
    "autoGrantPermissions": true,
    "uiautomator2ServerLaunchTimeout": 60000,
    "adbExecTimeout": 30000,
    "appiumServerUrl": "http://127.0.0.1:4723"
}
```

- **platformName**: Specifies the platform to run on, which in this case is Android.
- **deviceName**: Defines the emulator to use, `emulator-5554`, a default name for Android emulators.
- **platformVersion**: Sets the version of Android on the emulator (e.g., Android 14).
- **app**: Path to the APK file for the app to be tested.
- **appPackage**: The app’s package name.
- **appActivity**: The app's main activity that gets launched.
- **automationName**: Specifies the Appium automation engine, `UiAutomator2`, for Android testing.
- **noReset**: If `false`, the app will reset between test sessions (i.e., no saved data).
- **fullReset**: If `false`, the app will not be uninstalled and reinstalled after each session.
- **newCommandTimeout**: Time (in seconds) before Appium terminates the session if no new commands are received.
- **autoGrantPermissions**: Automatically grants all required app permissions during installation.
- **uiautomator2ServerLaunchTimeout**: Time (in milliseconds) that Appium waits for UiAutomator2 to start.
- **adbExecTimeout**: Time (in milliseconds) Appium waits for ADB commands to complete.
- **appiumServerUrl**: URL for the local Appium server, typically running at `http://127.0.0.1:4723`.

This configuration is used when running tests on an Android emulator via Appium.

---

#### 2. **Real Device Configuration** (`real_device`)

This section configures the test environment for running on a **real physical Android device**.

```json
"real_device": {
    "platformName": "Android",
    "deviceName": "MyPhysicalDevice",
    "platformVersion": "11.0",
    "app": "./src/main/resources/app/android.wdio.native.app.v1.0.8.apk",
    "appPackage": "com.wdiodemoapp",
    "appActivity": "com.wdiodemoapp.MainActivity",
    "automationName": "UiAutomator2",
    "noReset": true,
    "fullReset": false
}
```

- **platformName**: Android platform.
- **deviceName**: Name of the real device (e.g., `"MyPhysicalDevice"`).
- **platformVersion**: The Android version installed on the real device (`11.0`).
- **app**: Path to the APK for the app.
- **appPackage**: The app’s package name.
- **appActivity**: The main activity to be launched during tests.
- **automationName**: Specifies UiAutomator2 as the automation engine.
- **noReset**: If `true`, the app’s data is preserved between test sessions.
- **fullReset**: If `false`, the app will not be uninstalled between tests.

This configuration is used for tests running on a real, physical Android device.

---

#### 3. **BrowserStack Configuration** (`browserstack`)

This section is used for running tests on **BrowserStack**, a cloud-based platform for running automated tests on real devices.

```json
"browserstack": {
    "framework": "testng",
    "platformName": "android",
    "deviceName": "Google Pixel 4",
    "platformVersion": "11.0",
    "noReset": false,
    "fullReset": true,
    "interactiveDebugging": true,
    "app": "bs://<app_id>",
    "browserstack.debug": true,
    "browserstack.networkLogs": true,
    "browserstack.appium_version": "1.22.0",
    "parallelsPerPlatform": "1",
    "browserstackLocal": false,
    "buildName": "browserstack-build-1",
    "accessKey": "<accessKey>",
    "userName": "<userName>",
    "appiumServerUrl": "https://<userName>:<accessKey>@hub.browserstack.com/wd/hub"
}
```

- **framework**: Specifies that **TestNG** is the testing framework used.
- **platformName**: Platform for testing, here it is Android.
- **deviceName**: The specific device model to be used on BrowserStack (e.g., `Google Pixel 4`).
- **platformVersion**: Android version on the cloud device (`11.0`).
- **noReset**: If `false`, the app data will be reset between tests.
- **fullReset**: If `true`, the app will be uninstalled and reinstalled between tests.
- **interactiveDebugging**: Enables interactive debugging on BrowserStack.
- **app**: The path to the app, using BrowserStack's app ID (`bs://<app_id>`).
- **browserstack.debug**: Enables debugging features like screenshots and logs.
- **browserstack.networkLogs**: Captures network logs during test execution.
- **browserstack.appium_version**: Specifies the Appium version on BrowserStack (`1.22.0`).
- **parallelsPerPlatform**: Number of parallel tests that can be run (`1`).
- **browserstackLocal**: Set to `false`, meaning BrowserStack’s local testing feature is disabled.
- **buildName**: Specifies the build name for tracking tests (`browserstack-build-1`).
- **accessKey** and **userName**: Credentials for accessing BrowserStack (to be replaced with actual values).
- **appiumServerUrl**: The URL for the Appium server hosted by BrowserStack.

This configuration is used for running tests on real devices in the cloud via BrowserStack.

---

### How to Use the Configuration

- **Emulator**: When running tests on an emulator, use the `"emulator"` configuration. This typically happens on a local machine with Appium pointing to a local server.
- **Real Device**: For running tests on a physical Android device connected via USB, use the `"real_device"` configuration.
- **BrowserStack**: For cloud-based testing on BrowserStack, use the `"browserstack"` configuration. You'll need to upload your APK to BrowserStack and use the correct credentials (`userName`, `accessKey`).


``` Java
@BeforeMethod(alwaysRun = true)
public void setupMethod() throws Exception {
    DriverManager.initializeDriver(Constant.DEFAULT_PLATFORM, Constant.DEFAULT_PROFILE);
    // DriverManager.initializeDriver("browserstack", "browserstack");
}

```
---
## Designing a Test Class

In this project, we employ different approaches for data-driven testing in the `LoginTest` class. These methods show the flexibility of test data sourcing, including direct reading in the test body, custom annotations, and centralized data providers. Below is a detailed explanation and guide to designing a test class similar to `LoginTest`.

---

### 1. **Understanding the Test Class Structure**

#### `BaseTest` Class

The `BaseTest` class is the foundational class from which other test classes like `LoginTest` inherit. It provides common setup functionality, such as initializing drivers or creating page object instances like `NavigationBar`.

```java
public class BaseTest extends MobileTestStarter {
    protected NavigationBar navigationBar;

    @BeforeMethod
    public void setUp() {
        navigationBar = new NavigationBar();
    }
}
```

#### `LoginTest` Class

The `LoginTest` class focuses on testing login and signup functionalities. It utilizes three data-driven methods for different test cases:
- **Custom Annotation** (`@DataKey`)
- **Direct Data Access in Test Body**
- **Centralized Data Provider** (via TestNG’s `@DataProvider`).

### 2. **Custom Annotation for Data-Driven Testing**

This approach is useful when you want to map specific test cases to corresponding data in a preloaded map. The `@DataKey` custom annotation helps dynamically pull the required data inside the test case.

- **Custom Annotation**: You annotate the test method with `@DataKey`, passing a value to fetch corresponding test data.
- **AnnotationUtils**: This utility class retrieves the data linked to the `DataKey`.

```java
@Test(groups = { "loginTest", "invalid" })
@DataKey("account2")
void LoginMissingEmailTest(Method method) throws Exception {
    AllureReport.logStep("Read data from JSON file for missing email test");
    AccountDto account = annotationUtils.getData(method, _accounts);

    AllureReport.logStep("Filling login form with missing email");
    _loginPage.fillLoginForm(account.getEmail(), account.getPassword());

    Assertion.assertTrue(_loginPage.isMsgInvalidEmailDisplayed(), "Warning message is not displayed");
}
```

**Guidance**:
- Use custom annotations when you want more control over mapping test data to methods.
- The data is accessed dynamically during the test's runtime based on the annotation's value.

---

### 3. **Direct Data Access in Test Body**

In this approach, data is read directly in the test body. For example, the `LoginSuccessfullTest` fetches test data from a preloaded map and uses it to fill the login form.

```java
@Test(groups = { "loginTest", "valid" })
public void LoginSuccessfullTest() throws Exception {
    // Retrieve account data using the preloaded _accounts map
    AllureReport.logStep("Read data from JSON file");
    AccountDto account = _accounts.get("account1");

    // Fill login form with valid credentials
    AllureReport.logStep("Filling login form with valid credentials");
    _loginPage.fillLoginForm(account);

    // Verify if login was successful
    Assertion.assertTrue(_loginPage.isLoginSuccessfully(), "Login is not successful");
}
```

**Guidance**:
- Use this approach when you have predefined test data and want direct access within the test method.
- It offers flexibility for simpler cases but can become cumbersome for larger datasets.

---

### 4. **Centralized Data Provider (TestNG `@DataProvider`)**

A centralized `DataProvider` class is used to supply test data to specific test methods. The `SignupSuccessfullTest` method pulls credentials via the data provider.

```java
@Test(dataProvider = "credentialsDataProvider", dataProviderClass = DataProvider.class, groups = { "valid" })
public void SignupSuccessfullTest(String dataKey, CredentialsDto credentials) {
    // Filling sign-up form with test data
    AllureReport.logStep("Filling signup form with test data");
    _loginPage.fillSignUpForm(credentials);

    // Assertion
    Assertion.assertTrue(_loginPage.isSignupSuccessfully(), "Signup is not successful");
}
```

The `DataProvider` class will look something like this:

```java
public class DataProvider {

    private static final String ACCOUNTS_PATH = "test-data/accounts.json";
    private static final String CREDENTIALS_PATH = "test-data/credentials.json";


    /**
     * DataProvider for AccountDto (login tests)
     */
    @org.testng.annotations.DataProvider(name = "accountDataProvider")
    public Object[][] accountDataProvider() throws Exception {
        return DataUtils.parseJsonData(ACCOUNTS_PATH, AccountDto.class);
    }

    /**
     * DataProvider for CredentialsDto (signup tests)
     */
    @org.testng.annotations.DataProvider(name = "credentialsDataProvider")
    public Object[][] credentialsDataProvider() throws Exception {
        return DataUtils.parseJsonData(CREDENTIALS_PATH, CredentialsDto.class);
    }
}
```

**Guidance**:
- Use this approach when you need to run tests multiple times with different datasets (parameterized tests).
- Ideal for scalability and reuse across various test methods.

---

### 5. **Using Assertions with Allure Reporting**

To integrate **assertions** into Allure reporting, we utilize a custom `Assertion` class. This logs failed assertions with a descriptive message to enhance visibility in the Allure report.

```java
public class Assertion {
    public static void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
        } catch (AssertionError e) {
            AllureReport.logStep("Assertion failed: " + message);
            throw e;  // Re-throw to fail the test
        }
    }
}
```

By wrapping assertions in this custom method, every assertion error is logged to Allure.

---

### 6. **How to Design a Test Class**

When designing a new test class similar to `LoginTest`, follow these steps:

#### Step 1: **Inherit from `BaseTest`**
- Extend your test class from `BaseTest` to inherit common setup functionality, such as driver initialization or navigation setup.

#### Step 2: **Use Page Objects**
- Create instances of the necessary **Page Object Model** (POM) classes like `LoginPage`, `SignupPage`, or `NavigationBar`.

#### Step 3: **Determine Data Source for Each Test Case**
- **Direct Data**: Use direct access in the test body for simple or one-off data.
- **Custom Annotation**: Use `@DataKey` when you want to map data to specific test methods in a flexible way.
- **Data Provider**: Use `@DataProvider` when you want to run a test method with multiple sets of data. It scales well for running parameterized tests.

#### Step 4: **Incorporate Allure Reporting and Assertions**
- Log steps using `AllureReport.logStep` to document each action in your test flow.
- Use the `Assertion.assertTrue` method to log assertion errors and enhance visibility in the Allure reports.

---

### Example Class Structure

```java
public class SampleTest extends BaseTest {

    private SamplePage _samplePage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        _samplePage = new SamplePage();
        AllureReport.logStep("Navigating to Sample view");
        navigationBar.clickView("Sample");
    }

    @Test(groups = { "sampleTest", "valid" })
    public void sampleValidTest() {
        AllureReport.logStep("Running sample test");
        _samplePage.performAction();
        Assertion.assertTrue(_samplePage.isActionSuccessful(), "Sample action failed");
    }

    @Test(dataProvider = "sampleDataProvider", dataProviderClass = DataProvider.class)
    public void sampleParameterizedTest(String dataKey, SampleDto data) {
        AllureReport.logStep("Running test with data: " + dataKey);
        _samplePage.performActionWithData(data);
        Assertion.assertTrue(_samplePage.isActionSuccessful(), "Sample action failed with data");
    }
}
```
