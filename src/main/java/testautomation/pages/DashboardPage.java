package testautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {
    @FindBy(xpath = "//div/div[@class='row'][2]")
    private WebElement columnsContainer;

    @FindBy(xpath = "//div[@class='card']/div[contains(@class, 'bg-warning')]/parent::div")
    private WebElement waitingColumn;

    @FindBy(xpath = "//div[@class='card']/div[contains(@class, 'bg-info')]/parent::div")
    private WebElement inProgressColumn;

    @FindBy(xpath = "//div[@class='card']/div[contains(@class, 'bg-success')]/parent::div")
    private WebElement doneColumn;

    @FindBy(xpath = "//div/a[@href='add_task.php']")
    private WebElement addTaskButton;

    @FindBy(name = "toggle_language")
    private WebElement toggleLanguageChangeButton;


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getColumnsContainer() {
        return columnsContainer;
    }

    public WebElement getWaitingColumn() {
        return waitingColumn;
    }

    public WebElement getInProgressColumn() {
        return inProgressColumn;
    }

    public WebElement getDoneColumn() {
        return doneColumn;
    }

    public WebElement getAddTaskButton() {
        return addTaskButton;
    }

    public WebElement getToggleLanguageChangeButton() {
        return toggleLanguageChangeButton;
    }

    public String getWaitingColumnTitle() {
        return waitingColumn.findElement(By.className("card-header")).getText();
    }


    public String getProgressColumnTitle() {
        return inProgressColumn.findElement(By.className("card-header")).getText();
    }

    public String getDoneColumnTitle() {
        return doneColumn.findElement(By.className("card-header")).getText();
    }

    public String getWaitingColumnContent() {
        return waitingColumn.findElement(By.className("card-body")).getText();
    }
    public String getProgressColumnContent() {
        return inProgressColumn.findElement(By.className("card-body")).getText();
    }
    public String getDoneColumnContent() {
        return doneColumn.findElement(By.className("card-body")).getText();
    }

    public void clickAddTaskButton() {
        click(addTaskButton);
    }

    public String getFirstStartTaskTitle() {
        return waitingColumn.findElement(By.className("card-body")).findElement(By.tagName("h6")).getText();
    }

    public void clickToggleLanguageChangeButton() {
        click(toggleLanguageChangeButton);
    }
}
