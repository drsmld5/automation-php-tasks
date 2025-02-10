package testautomation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testautomation.pages.AddTaskPage;
import testautomation.pages.DashboardPage;
import testautomation.pages.LoginPage;
import testautomation.utils.AutomationUtils;
import testautomation.utils.Constants;
import testautomation.utils.DriverManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DashboardTests {
    private WebDriver driver;


    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(Constants.BASE_URL + "/login.php");
    }

    @Test
    public void testEmptyColumnsOnNewAccount() {
        AutomationUtils.registerNewUserAndLogin(driver);

        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertEquals(dashboardPage.getWaitingColumnContent(), "", "Column not empty");
        Assert.assertEquals(dashboardPage.getProgressColumnContent(), "", "Column not empty");
        Assert.assertEquals(dashboardPage.getDoneColumnContent(), "", "Column not empty");
    }

    @Test
    public void testColumnsCorrectHeaders() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constants.USERS.get(0), Constants.DEFAULT_PASSWORD);
        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertEquals(dashboardPage.getWaitingColumnTitle(), "În Așteptare", "Incorrect column title");
        Assert.assertEquals(dashboardPage.getProgressColumnTitle(), "În Progres", "Incorrect column title");
        Assert.assertEquals(dashboardPage.getDoneColumnTitle(),"Finalizate", "Incorrect column title");
    }

    @Test
    public void testGoToAddTask() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constants.USERS.get(0), Constants.DEFAULT_PASSWORD);
        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.clickAddTaskButton();
        Assert.assertTrue(driver.getCurrentUrl().contains(Constants.BASE_URL + "/add_task.php"), "Add Task URL not present");
    }

    @Test
    public void testAddTask() {
        Map<String, String> userDetailsMap = AutomationUtils.registerNewUserAndLogin(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickAddTaskButton();
        AddTaskPage addTaskPage = new AddTaskPage(driver);

        addTaskPage.addTask("Test", "Description", "01/01/2026", "Medie", userDetailsMap.get("username"));
        Assert.assertEquals(dashboardPage.getFirstStartTaskTitle(), "Test", "Incorrect task title");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
