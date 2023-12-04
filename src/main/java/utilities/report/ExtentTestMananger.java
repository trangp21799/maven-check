package utilities.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestMananger {
    private static ExtentReports reporter = ExtentManager.getReporter();
    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    public synchronized static ExtentTest startTest(String methodName, String description) {
        System.out.println(reporter);
        ExtentTest extentTest = reporter.createTest(methodName, description);
        extentTestMap.put((int) (long) Thread.currentThread().getId(), extentTest);
        return extentTest;
    }

    public synchronized static ExtentTest getTest() {
        return extentTestMap.get((int) (long) Thread.currentThread().getId());
    }

}
