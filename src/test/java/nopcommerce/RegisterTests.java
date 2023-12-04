package nopcommerce;

import com.aventstack.extentreports.Status;
import common.BaseTest;
import common.PageManagerGenerator;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;
import utilities.environment.Environment;
import utilities.listener.ExtentListener;
import utilities.log.Log;
import utilities.report.ExtentTestMananger;

import java.lang.reflect.Method;

@Listeners(ExtentListener.class)
public class RegisterTests extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    RegisterPageObject registerPage;

    @Parameters({"browserName", "serverName"})
    @BeforeClass
    public void beforeClass(String browserName, String serverName) {
        System.out.println(browserName);
        System.out.println(serverName);
        ConfigFactory.setProperty("env", serverName);
        Environment env = ConfigFactory.create(Environment.class);
        System.out.println(env);
        driver = getBrowserDriver(browserName, env.url());
    }

    @Test
    public void Register_01_Empty_Data(Method method) {
        ExtentTestMananger.startTest(method.getName(), "Register - Register with empty data");
        ExtentTestMananger.getTest().log(Status.INFO,"Register Step 1 - Open home page");
        homePage = PageManagerGenerator.getHomePage(driver);

        ExtentTestMananger.getTest().log(Status.INFO,"Register Step 2 - Open Register Page");
        registerPage = homePage.clickToRegisterLink();

        ExtentTestMananger.getTest().log(Status.INFO,"Register Step 3 - Click Register button");
        registerPage.clickToRegisterButton();

        ExtentTestMananger.getTest().log(Status.INFO,"Register Step 4 - Verify First Name message error");
        Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");

        ExtentTestMananger.getTest().log(Status.INFO,"Register Step 5 - Verify Last Name message error");
        Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");

        ExtentTestMananger.getTest().log(Status.INFO,"Register Step 5 - Verify Email message error");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");

        ExtentTestMananger.getTest().log(Status.INFO,"Register Step 5 - Verify Password message error");
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");

        ExtentTestMananger.getTest().log(Status.INFO,"Register Step 5 - Verify Confirm Password message error");
        Assert.assertEquals(registerPage.getConfirmPassordErrorMessage(), "Password is required.");
    }

//    @Test
//    public void Register_01_Wrong_Email() {
//
//    }
//
//    @Test
//    public void Register_01_Sucessfully() {
//
//    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
