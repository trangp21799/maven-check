package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    public WebDriver getBrowserDriver (String browserName, String url) {

        browserName = browserName.toLowerCase();
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "h_chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions headlessChrome0ptions = new ChromeOptions();
                headlessChrome0ptions.addArguments("--headless");
                driver = new ChromeDriver(headlessChrome0ptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "h_firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions headlessFirefox0ptions = new FirefoxOptions();
                headlessFirefox0ptions.addArguments("--headless");
                driver = new FirefoxDriver(headlessFirefox0ptions);
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIME_OUT, TimeUnit.SECONDS);
        driver.get(url);
        return driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
