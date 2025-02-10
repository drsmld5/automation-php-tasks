package testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddTaskPage extends BasePage {

    @FindBy(id = "title")
    private WebElement titleField;

    @FindBy(id = "description")
    private WebElement descriptionField;

    @FindBy(id = "due_date")
    private WebElement dueDateField;

    @FindBy(id = "priority")
    private WebElement priorityField;

    @FindBy(id = "assigned_to")
    private WebElement assignedToField;

    @FindBy(css = "button[type='submit']")
    private WebElement addTaskButton;

    @FindBy(linkText = "Anulează")
    private WebElement cancelAddTaskButton;

    public AddTaskPage(WebDriver driver) {
        super(driver);
    }

    public void clickCancelAddTaskButton() {
        click(cancelAddTaskButton);
    }

    public void addTask(String title, String description, String dueDate, String priority, String assignedTo) {
        type(titleField, title);
        type(descriptionField, description);
        type(dueDateField, dueDate);
        type(priorityField, priority);
        type(assignedToField, assignedTo);
        click(addTaskButton);
    }



}
