package fb;

import com.aventstack.extentreports.Status;
import common.BaseTest;
import common.PageManagerGenerator;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePageObject;
import utilities.environment.Environment;
import utilities.report.ExtentTestMananger;

import java.lang.reflect.Method;
import java.util.Set;

public class RegisterTests extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;

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

        ExtentTestMananger.getTest().log(Status.INFO,"Register Step 2 - Open Sign Up pop up");
        homePage.clickToRegisterLink();

        ExtentTestMananger.getTest().log(Status.INFO,"Register Step 3 - switch to Sign Up pop up");
        String currentWindow = driver.getWindowHandle();
        Set<String> winowHandles = driver.getWindowHandles();
        for(String windowHandle : winowHandles) {
            if(!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }
        ExtentTestMananger.getTest().log(Status.INFO,"Register Step 3 - Click Register button in pop up");
        homePage.clickToRegisterButtonInPopup();

        ExtentTestMananger.getTest().log(Status.INFO,"Register Step 5 - Verify firstname input textbox border");
        Assert.assertEquals(homePage.getInputFirstNameBorderColor(), "rgb(203, 208, 213)");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
