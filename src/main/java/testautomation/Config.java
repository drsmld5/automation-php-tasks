package testautomation;
import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getChromeDriverPath() {
        return dotenv.get("PATH_TO_CHROMEDRIVER");
    }
}