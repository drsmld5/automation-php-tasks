package testautomation.utils;

import org.openqa.selenium.WebDriver;
import testautomation.pages.LoginPage;
import testautomation.pages.RegisterPage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AutomationUtils {
    public static Map<String, String> registerNewUserAndLogin(WebDriver driver) {
        driver.get(Constants.BASE_URL + "/register.php");
        Map<String, String> map = new HashMap<>();
        String username = UUID.randomUUID().toString();
        String password = Constants.DEFAULT_PASSWORD;
        String email = username + "@example.com";

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerUser(username, email, password, password);
        registerPage.navigateToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        map.put("username", username);
        map.put("password", password);
        map.put("email", email);

        return map;
    }
}
