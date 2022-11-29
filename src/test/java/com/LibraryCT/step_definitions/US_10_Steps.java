package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.DashboardPage;
import com.LibraryCT.pages.LoginPage;
import com.LibraryCT.pages.UsersPage;
import com.LibraryCT.utility.BrowserUtils;
import com.LibraryCT.utility.DB_Util;
import com.LibraryCT.utility.Driver;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;


public class US_10_Steps {

    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    UsersPage usersPage = new UsersPage();

    String email = "";

    String actualMessage = "";

    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String user) {
        loginPage.login(user);
        BrowserUtils.waitForPageToLoad(30);
    }
    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String module) {
        BrowserUtils.waitFor(3);
        dashboardPage.navigateModule(module);
        BrowserUtils.waitFor(4);
    }
    @When("the user clicks Edit User button")
    public void the_user_clicks_edit_user_button() {
        usersPage.selectionOneInUsers(usersPage.tableUsersLength, "500");
        BrowserUtils.waitFor(5);
        usersPage.editUserBtnByOtherFilters("jeffrey.hilpert@gmail.com");
        BrowserUtils.waitFor(4);
    }
    @When("the user changes user status {string} to {string}")
    public void the_user_changes_user_status_to(String active, String inactive) {
        BrowserUtils.selectByVisibleText(usersPage.addUserStatusSelect, inactive);
        BrowserUtils.waitFor(4);
        email = usersPage.addUserEmail.getAttribute("value");
        BrowserUtils.waitFor(3);
    }
    @When("the user clicks save changes button")
    public void the_user_clicks_save_changes_button() {
        usersPage.addUserSaveChangesBtn.click();
        System.out.println("User " + email + " is deactivated");
    }
    @Then("{string} message should appear")
    public void message_should_appear(String expected) {
        actualMessage = usersPage.toastMessage.getText();
        Assert.assertEquals(expected, actualMessage);
    }
    @Then("the users should see same status for related user in database")
    public void the_users_should_see_same_status_for_related_user_in_database() {
        DB_Util.runQuery("select status from users\n" +
                "where email='" + email + "';");
        String db_userStatus = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(db_userStatus, usersPage.
                getCurrentSelectionInUsers(usersPage.addUserStatusSelect));
    }
    @Then("the user changes current user status {string} to {string}")
    public void the_user_changes_current_user_status_to(String inactive, String active) {
        BrowserUtils.selectByVisibleText(usersPage.userStatusSelect, inactive);
        BrowserUtils.waitFor(5);
        usersPage.selectionOneInUsers(usersPage.tableUsersLength, "500");
        BrowserUtils.waitFor(4);
        usersPage.editUserBtnByOtherFilters(email);
        BrowserUtils.waitFor(4);
        BrowserUtils.selectByVisibleText(usersPage.addUserStatusSelect, active);
        BrowserUtils.waitFor(3);
        usersPage.addUserSaveChangesBtn.click();
        BrowserUtils.waitFor(5);
        loginPage.logout();
    }
}
