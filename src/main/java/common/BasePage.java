package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    protected void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public By getByLocator(String locatorType) {
        By by = null;
        if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")){
            by = By.xpath(locatorType.substring(6));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("classname=") || locatorType.startsWith("Classname=") || locatorType.startsWith("CLASSNAME=")) {
            by = By.className(locatorType.substring(10));
        } else {
            throw new RuntimeException("Locator type is not supported");
        }
        return by;
    }

    public WebElement getWebElement(WebDriver driver, String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    public List<WebElement> getWebElements(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    public void sendKeyToElement(WebDriver driver, String locatorType, String text) {
        WebElement element = getWebElement(driver, locatorType);
        element.clear();
        element.sendKeys(text);
    }

    public String getTextElement(WebDriver driver, String locatorType) {
        waitForElementVisible(driver, locatorType);
        return getWebElement(driver, locatorType).getText();
    }

    public void clickToElement(WebDriver driver, String locatorType) {
        getWebElement(driver, locatorType).click();
    }

    public void waitForElementClickable(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIME_OUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }
    public void waitForElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIME_OUT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    public String getCssValue(WebDriver driver, String locatorType, String attribute) {
        return getWebElement(driver, locatorType).getCssValue("border-color");
    }


}
