package com.LibraryCT.pages;

import com.LibraryCT.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class UsersPage {

    public UsersPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "user_groups")
    public WebElement userGroupSelect;

    @FindBy(id = "user_status")
    public WebElement userStatusSelect;

    @FindBy(xpath = "//i[@class='fa fa-plus']")
    public WebElement addUser;

    @FindBy(xpath = "//input[@placeholder='Full Name']")
    public WebElement addUserFullName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    public WebElement addUserPassword;

    @FindBy(xpath = "//input[@placeholder='Email']")
    public WebElement addUserEmail;

    @FindBy(id = "user_group_id")
    public WebElement addUserGroupSelect;

    @FindBy(id = "status")
    public WebElement addUserStatusSelect;

    @FindBy(name = "start_date")
    public WebElement addUserStartDate;

    @FindBy(name = "end_date")
    public WebElement addUserEndDate;

    @FindBy(id = "address")
    public WebElement addUserAddress;

    @FindBy(xpath = "//button[.='Close']")
    public WebElement addUserCloseBtn;

    @FindBy(xpath = "//button[.='Save changes']")
    public WebElement addUserSaveChangesBtn;

    @FindBy(name = "tbl_users_length")
    public WebElement tableUsersLength;

    @FindBy(xpath = "//input[@type = 'search']")
    public WebElement searchFieldUsers;

    @FindBy(xpath = "//th[.='User ID']")
    public WebElement sortASCbyUserID;

    @FindBy(xpath = "//th[.='Full Name']")
    public WebElement sortASCbyFullName;

    @FindBy(xpath = "//th[.='Email']")
    public WebElement sortASCbyEmail;

    @FindBy(xpath = "//th[.='Group']")
    public WebElement sortASCbyGroup;

    @FindBy(xpath = "//th[.='Status']")
    public WebElement sortASCbyStatus;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement toastMessage;


    public WebElement editUserBtn(String userID) {
        String xPath = "//a[@onclick = 'Users.edit_user(" + userID + ")']";
        return Driver.getDriver().findElement(By.xpath(xPath));
    }

    public void editUserBtnByOtherFilters(String filter) {
        String xPath = "//td[.='" + filter + "']/..//a[@role='button']";
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        WebElement userEdit = Driver.getDriver().findElement(By.xpath(xPath));
        js.executeScript("arguments[0].click()", userEdit);
    }

    /**
     * Select user type (librarian or student), user status (active or inactive)
     * users table length (5,10,15,50,100,200,500)
     */
    public void selectionOneInUsers(WebElement element, String input) {
        String tableLength = "5,10,15,50,100,200,500";
        if (tableLength.contains(input))
            input = input + "";
        else {
            switch (input) {
                case "student" -> input = "Students";
                case "librarian" -> input = "Librarian";
                case "active" -> input = "ACTIVE";
                case "inactive" -> input = "INACTIVE";
                default -> System.out.println("WRONG INPUT OF USER TYPE IN ADDING USER MODULE");
            }
        }
        Select select = new Select(element);
        select.selectByVisibleText(input);
    }

    public List<WebElement> getSelectionAllInUsers(WebElement element) {
        Select select = new Select(element);
        return select.getOptions();
    }
    public String getCurrentSelectionInUsers(WebElement element) {
        Select select = new Select(element);
        return select.getFirstSelectedOption().getAttribute("value");
    }
}
