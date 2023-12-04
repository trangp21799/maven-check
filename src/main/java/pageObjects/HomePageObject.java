package pageObjects;

import common.BasePage;
import common.PageManagerGenerator;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject( WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPageObject clickToRegisterLink() {
        clickToElement(driver, HomePageUI.REGISTER_LINK);
        return PageManagerGenerator.getRegisterPage(driver);
    }
}
