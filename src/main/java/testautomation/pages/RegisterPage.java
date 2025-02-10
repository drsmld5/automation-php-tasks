package testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "confirm_password")
    private WebElement confirmPasswordField;

    @FindBy(css = "button[type='submit']")
    private WebElement registerButton;

    @FindBy(css = "div.alert.alert-danger")
    private WebElement errorMessage;

    @FindBy(css = "div.alert.alert-success")
    private WebElement successMessage;

    @FindBy(xpath = "//a[@class='nav-link' and @href='login.php']")
    private WebElement loginNavigationButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void registerUser(String username, String email, String password, String confirmPassword) {
        type(usernameField, username);
        type(emailField, email);
        type(passwordField, password);
        type(confirmPasswordField, confirmPassword);
        click(registerButton);
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        type(confirmPasswordField, confirmPassword);
    }

    public void clickRegisterButton() {
        click(registerButton);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public LoginPage navigateToLoginPage() {
        click(loginNavigationButton);
        return new LoginPage(driver);
    }

    public String getUsernameFieldValidationMessage() {
        return getValidationMessage(usernameField);
    }

    public String getEmailFieldValidationMessage() {
        return getValidationMessage(emailField);
    }

    public String getPasswordFieldValidationMessage() {
        return getValidationMessage(passwordField);
    }

    public String getConfirmPasswordFieldValidationMessage() {
        return getValidationMessage(confirmPasswordField);
    }

    public void clearAllFields() {
        clearField(usernameField);
        clearField(emailField);
        clearField(passwordField);
        clearField(confirmPasswordField);
    }
}
