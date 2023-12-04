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
        String[] locatorParts = locatorType.split("=");
        String type = locatorParts[0].toLowerCase();
        String locator = locatorParts[1];
        By by = null;
        switch (type){
            case "xpath":
                by = By.xpath(locator);
                break;
            case "id":
                by = By.id(locator);
                break;
            case "css":
                by = By.cssSelector(locator);
                break;
            case "class":
                by = By.className(locator);
                break;
            case "name":
                by = By.name(locator);
                break;
            default:
                throw new RuntimeException("Locator type is not valid");
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
//        waitForElementClickable(driver, locatorType);
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

}
