package testautomation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testautomation.pages.LoginPage;
import testautomation.pages.RegisterPage;
import testautomation.utils.Constants;
import testautomation.utils.DriverManager;

import java.util.Random;
import java.util.UUID;

public class RegisterTests {
    private WebDriver driver;
    private RegisterPage registerPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(Constants.BASE_URL + "/register.php");
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void testRegisterUser() {
        String randomString = UUID.randomUUID().toString();
        registerPage.registerUser(randomString, randomString + "@example.com",
                Constants.DEFAULT_PASSWORD, Constants.DEFAULT_PASSWORD);

        Assert.assertEquals(registerPage.getSuccessMessage(), "Cont creat cu succes! Te poți autentifica acum.", "Incorrect Message");
        LoginPage loginPage = registerPage.navigateToLoginPage();
        loginPage.login(randomString, Constants.DEFAULT_PASSWORD);
        Assert.assertEquals(driver.getCurrentUrl(), Constants.BASE_URL + "/dashboard.php", "Expected to land on "  +
                Constants.BASE_URL + "/dashboard.php" + " but landed on" + driver.getCurrentUrl());
    }

    @Test
    public void testRegisterWithEmptyFields() {
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getUsernameFieldValidationMessage(), "Please fill in this field.");
    }

    @Test
    public void testRegisterWithOneEmptyField() {
        registerPage.enterPassword("password");
        registerPage.enterEmail("email@email.com");
        registerPage.enterConfirmPassword("password");
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getUsernameFieldValidationMessage(), "Please fill in this field.");
        registerPage.clearAllFields();

        registerPage.enterUsername("username");
        registerPage.enterEmail("email@email.com");
        registerPage.enterConfirmPassword("password");
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getPasswordFieldValidationMessage(), "Please fill in this field.");
        registerPage.clearAllFields();

        registerPage.enterUsername("username");
        registerPage.enterPassword("password");
        registerPage.enterConfirmPassword("password");
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getEmailFieldValidationMessage(), "Please fill in this field.");
        registerPage.clearAllFields();

        registerPage.enterUsername("username");
        registerPage.enterPassword("password");
        registerPage.enterEmail("email@email.com");
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getConfirmPasswordFieldValidationMessage(), "Please fill in this field.");
    }

    @Test
    public void testEmailValidation() {
        registerPage.enterUsername("username");
        registerPage.enterPassword("password");
        registerPage.enterEmail("email");
        registerPage.enterConfirmPassword("password");
        Assert.assertEquals(registerPage.getEmailFieldValidationMessage(), "Please include an '@' in the email address. 'email' is missing an '@'.");
    }
//    public void

    @Test
    public void testPasswordNotMatching() {
        String randomString = UUID.randomUUID().toString();
        registerPage.enterUsername(randomString);
        registerPage.enterEmail(randomString + "@example.com");
        registerPage.enterPassword(Constants.DEFAULT_PASSWORD);
        registerPage.enterConfirmPassword(Constants.DEFAULT_PASSWORD + "123");
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getErrorMessage(), "Parolele nu coincid.");
    }

    @AfterTest
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
