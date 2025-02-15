package testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'card-body')]/h4")
    private WebElement usernameText;

    @FindBy(xpath = "//div[contains(@class, 'card-body')]/p")
    private WebElement emailText;

    @FindBy(xpath = "//div[contains(@class, 'card-body')]/button[contains(@class, 'bg-info')]")
    private WebElement editProfileButton;

    @FindBy(xpath = "//div[contains(@class, 'card-body')]/button[contains(@class, 'btn-outline-info')]")
    private WebElement changePasswordButton;


    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getUsernameText() {
        return usernameText;
    }

    public WebElement getEmailText() {
        return emailText;
    }

    public WebElement getEditProfileButton() {
        return editProfileButton;
    }

    public WebElement getChangePasswordButton() {
        return changePasswordButton;
    }
}
