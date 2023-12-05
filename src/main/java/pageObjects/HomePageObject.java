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

    public void clickToRegisterLink() {
        clickToElement(driver, HomePageUI.REGISTER_LINK);
//        return PageManagerGenerator.getRegisterPage(driver);
    }

    public void clickToRegisterButtonInPopup() {
        clickToElement(driver, HomePageUI.REGSTER_BUTTON_IN_POP_UP);
    }

    public String getInputFirstNameBorderColor() {
        return getCssValue(driver, HomePageUI.FIRST_NAME_TEXT_BOX, "border-color");
    }




}
