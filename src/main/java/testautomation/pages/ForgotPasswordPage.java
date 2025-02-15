package testautomation.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BasePage {

    @FindBy(id = "current_password")
    private WebElement currentPasswordField;

    @FindBy(id = "new_password")
    private WebElement newPasswordField;

    @FindBy(id = "confirm_password")
    private WebElement confirmPasswordField;

    @FindBy(css = "button[type='submit']")
    private WebElement changePasswordButton;

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getCurrentPasswordField() {
        return currentPasswordField;
    }

    public WebElement getNewPasswordField() {
        return newPasswordField;
    }

    public WebElement getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public WebElement getChangePasswordButton() {
        return changePasswordButton;
    }

    public void completeChangePasswordFlow(String currentPassword, String newPassword) {
        type(currentPasswordField, currentPassword);
        type(newPasswordField, newPassword);
        type(confirmPasswordField, newPassword);
        click(changePasswordButton);
    }
}
