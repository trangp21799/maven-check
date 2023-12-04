package utilities.listener;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import common.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.log.Log;
import utilities.report.ExtentManager;
import utilities.report.ExtentTestMananger;

public class ExtentListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.warn(result.getTestName() + "test is passed");
        ExtentTestMananger.getTest().log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Passed", ExtentColor.GREEN));
        // extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Passed", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("here");
        ExtentTestMananger.getTest().log(Status.FAIL, "Test Failed");
        Object testClass = result.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        ExtentTestMananger.getTest().log(Status.FAIL, "Screenshot",ExtentTestMananger.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
        ExtentTestMananger.getTest().log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Failed \n" + result.getThrowable().getMessage(), ExtentColor.RED));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.warn(result.getTestName() + "test is skipped");
        ExtentTestMananger.getTest().log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Skipped", ExtentColor.ORANGE));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ExtentTestMananger.getTest().log(Status.FAIL, "Test Failed with Percentage ");
        ExtentTestMananger.getTest().log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Failed with Percentage", ExtentColor.RED));
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
    }
}
