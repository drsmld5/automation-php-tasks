package testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditProfilePage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(css = "button[type='submit']")
    private WebElement saveChangesButton;

    public EditProfilePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getSaveChangesButton() {
        return saveChangesButton;
    }

    public void completeChangeProfileDetailsFlow(String newUsername, String newEmail) {
        clearField(usernameField);
        clearField(emailField);

        usernameField.sendKeys(newUsername);
        emailField.sendKeys(newEmail);
        saveChangesButton.click();
    }
}
