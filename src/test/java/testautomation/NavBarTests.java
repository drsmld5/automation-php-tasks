package testautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import testautomation.pages.LoginPage;
import testautomation.utils.Constants;
import testautomation.utils.DriverManager;

public class NavBarTests {
    private WebDriver driver;


    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(Constants.BASE_URL + "/login.php");
    }

    @AfterTest
    public void tearDown() {
        DriverManager.quitDriver();
    }

}
