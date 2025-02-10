package testautomation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = "div.alert.alert-danger")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[@href='reset_password.php']")
    private WebElement resetPasswordLink;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public String getUsernameFieldValidationMessage() {
        String validationMessage;
        wait.until(driver -> {
                    String message = getValidationMessage(usernameField);
                    return message == null ? "" : message;
                });
        return getValidationMessage(usernameField);
    }

    public String getPasswordFieldValidationMessage() {
        wait.until(driver -> {
            String message = getValidationMessage(passwordField);
            return message == null ? "" : message;
        });
        return getValidationMessage(passwordField);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public ForgotPasswordPage navigateToResetPasswordPage() {
        click(resetPasswordLink);
        return new ForgotPasswordPage(driver);
    }

}
