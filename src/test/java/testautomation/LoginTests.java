package testautomation;

import org.testng.Assert;
import testautomation.pages.ForgotPasswordPage;
import testautomation.pages.LoginPage;
import testautomation.utils.Constants;
import testautomation.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(Constants.BASE_URL + "/login.php");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.login(Constants.USERS.get(0), Constants.DEFAULT_PASSWORD);
        Assert.assertEquals(driver.getCurrentUrl(), Constants.BASE_URL + "/dashboard.php", "Expected to land on "  +
                Constants.BASE_URL + "/dashboard.php" + " but landed on" + driver.getCurrentUrl());
        System.out.println("Login test passed!");
    }

    @Test
    public void testLoginIncorrectCredentials() {
        loginPage.login(Constants.WRONG_USER, Constants.DEFAULT_PASSWORD);
        Assert.assertNotEquals(driver.getCurrentUrl(), Constants.BASE_URL + "/dashboard.php", "Expected to land on "  +
                Constants.BASE_URL + "/login.php" + " but landed on" + driver.getCurrentUrl());
        System.out.println(loginPage.getErrorMessage());
        Assert.assertTrue(loginPage.getErrorMessage().contains("Nume de utilizator sau parolă incorectă"), "Incorrect Error message");
    }

    @Test
    public void testLoginNoCredentials() {
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getUsernameFieldValidationMessage(), "Please fill in this field.");
    }

    @Test
    public void testLoginNoUsername() {
        loginPage.enterPassword(Constants.DEFAULT_PASSWORD);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getUsernameFieldValidationMessage(), "Please fill in this field.");
    }

    @Test
    public void testLoginNoPassword() {
        loginPage.enterUsername("Username");
        Assert.assertEquals(loginPage.getPasswordFieldValidationMessage(), "Please fill in this field.");
    }

    @Test
    public void testNavBarHeaderLoaded() {
        Assert.assertTrue(loginPage.checkNavBarHeaderExists());
    }

    @Test
    public void testForgotPasswordNavigation() {
        ForgotPasswordPage forgotPasswordPage = loginPage.navigateToResetPasswordPage();
        Assert.assertTrue(forgotPasswordPage.checkNavBarHeaderExists());
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
