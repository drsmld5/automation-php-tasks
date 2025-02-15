package testautomation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testautomation.pages.*;
import testautomation.utils.AutomationUtils;
import testautomation.utils.Constants;
import testautomation.utils.DriverManager;

import java.util.Map;

public class ProfileTests {
    private WebDriver driver;
    private RegisterPage registerPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(Constants.BASE_URL + "/register.php");
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void testNavigateToProfilePage() {
        Map<String, String> userDetailsMap = AutomationUtils.registerNewUserAndLogin(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.getNavBarHeader().getUsernameButton().click();
        ProfilePage profilePage = new ProfilePage(driver);

        Assert.assertEquals(profilePage.getUsernameText().getText(), userDetailsMap.get("username"));
        Assert.assertEquals(profilePage.getEmailText().getText(), userDetailsMap.get("email"));
    }

    @Test
    public void testChangePassword() {
        Map<String, String> userDetailsMap = AutomationUtils.registerNewUserAndLogin(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.getNavBarHeader().getUsernameButton().click();
        ProfilePage profilePage = new ProfilePage(driver);

        profilePage.getChangePasswordButton().click();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.completeChangePasswordFlow(userDetailsMap.get("password"), userDetailsMap.get("password") + "1");

        profilePage.getNavBarHeader().getLogoutButton().click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userDetailsMap.get("username"), userDetailsMap.get("password") + "1");
        dashboardPage = new DashboardPage(driver);
        Assert.assertEquals(dashboardPage.getNavBarHeader().getUsernameButton().getText(), userDetailsMap.get("username"));
    }

    @Test
    public void testChangeUsername() {
        Map<String, String> userDetailsMap = AutomationUtils.registerNewUserAndLogin(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.getNavBarHeader().getUsernameButton().click();
        ProfilePage profilePage = new ProfilePage(driver);

        profilePage.getEditProfileButton().click();
        EditProfilePage editProfilePage = new EditProfilePage(driver);
        editProfilePage.completeChangeProfileDetailsFlow(userDetailsMap.get("username") + "1", userDetailsMap.get("email") + "1");

        editProfilePage.getNavBarHeader().getLogoutButton().click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userDetailsMap.get("username") + "1", userDetailsMap.get("password"));
        dashboardPage = new DashboardPage(driver);
        Assert.assertEquals(dashboardPage.getNavBarHeader().getUsernameButton().getText(), userDetailsMap.get("username") + "1");

        dashboardPage.getNavBarHeader().getUsernameButton().click();
        profilePage = new ProfilePage(driver);

        Assert.assertEquals(profilePage.getUsernameText().getText(), userDetailsMap.get("username") + "1");
        Assert.assertEquals(profilePage.getEmailText().getText(), userDetailsMap.get("email") + "1");
    }

    @AfterTest
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
