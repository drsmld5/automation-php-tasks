package testautomation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testautomation.utils.NavBar;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private NavBar navBarHeader;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        this.navBarHeader = new NavBar(driver);
        PageFactory.initElements(driver, this);
    }

    public NavBar getNavBarHeader() {
        return navBarHeader;
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        element.sendKeys(text);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public void clearField(WebElement element) {
        element.clear();
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public String getValidationMessage(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", element);
    }

    public boolean checkNavBarHeaderExists() {
        try {
            waitForElementToBeVisible(navBarHeader.getNavBarContainer());
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
        if (navBarHeader.getNavBarContainer() == null) {
            return false;
        }
        return navBarHeader.getNavBarContainer().isDisplayed();
    }

    public boolean isElementVisible(WebElement element) {
        try {
            waitForElementToBeVisible(element);
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
        return element.isDisplayed();
    }

}
