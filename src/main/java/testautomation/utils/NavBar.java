package testautomation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testautomation.pages.BasePage;

import java.util.List;

public class NavBar {
    private WebDriver driver;

    @FindBy(css = "nav.navbar")
    private WebElement navBarContainer;

    @FindBy(className = "navbar-brand")
    private WebElement title;

    @FindBy(css = "ul.navbar-nav.me-auto")
    private WebElement navigationList;

    @FindBy(xpath = "//li/a[contains(@href, 'profile.php')]")
    private WebElement usernameButton;

    @FindBy(xpath = "//li/a[contains(@href, 'logout.php')]")
    private WebElement logoutButton;

    public NavBar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getNavBarContainer() {
        return navBarContainer;
    }

    public WebElement getTitle() {
        return title;
    }

    public WebElement getNavigationList() {
        return navigationList;
    }

    public WebElement getUsernameButton() {
        return usernameButton;
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public int getNumberOfNavigationItems() {
        return navigationList.findElements(By.tagName("li")).size();
    }

    public List<WebElement> getAllNavigationItems() {
        return navigationList.findElements(By.tagName("li"));
    }
}
