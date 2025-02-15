package testautomation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testautomation.pages.DashboardPage;
import testautomation.pages.LoginPage;
import testautomation.utils.Constants;
import testautomation.utils.DriverManager;
import testautomation.utils.NavBar;

public class NavBarTests {
    private WebDriver driver;


    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(Constants.BASE_URL + "/login.php");
    }

    @Test
    public void testPreLoginNavBar() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.checkNavBarHeaderExists());

        NavBar loginNavBar = loginPage.getNavBarHeader();
        Assert.assertEquals(loginNavBar.getNumberOfNavigationItems(), 2, "Incorrect number of navigation items");
    }

    @Test
    public void testPostLoginNavBar() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constants.USERS.get(0), Constants.DEFAULT_PASSWORD);
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.checkNavBarHeaderExists());

        NavBar dashboardNavBar = dashboardPage.getNavBarHeader();
        Assert.assertEquals(dashboardNavBar.getNumberOfNavigationItems(), 3, "Incorrect number of navigation items");
        Assert.assertEquals(dashboardNavBar.getUsernameButton().getText(), Constants.USERS.get(0), "Incorrect username text");
        Assert.assertTrue(dashboardPage.isElementVisible(dashboardNavBar.getLogoutButton()));
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

}
