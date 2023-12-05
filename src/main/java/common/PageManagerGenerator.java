package common;

import org.openqa.selenium.WebDriver;
import pageObjects.HomePageObject;

public class PageManagerGenerator {
    public static HomePageObject getHomePage(WebDriver driver) {
        return new HomePageObject(driver);
    }
}
