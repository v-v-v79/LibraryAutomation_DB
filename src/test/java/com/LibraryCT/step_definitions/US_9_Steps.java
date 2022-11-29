package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.LoginPage;
import com.LibraryCT.utility.BrowserUtils;
import com.LibraryCT.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;


public class US_9_Steps {

    LoginPage loginPage = new LoginPage();
    String email = "";
    String actualUserName = "";

    @Given("the user logged in  {string} and {string}")
    public void the_user_logged_in_and(String username, String password) {
        loginPage.login(username, password);
        BrowserUtils.waitFor(5);
        this.email = username;

    }
    @When("user gets username  from user fields")
    public void user_gets_username_from_user_fields() {
        actualUserName = loginPage.accountHolderName.getText();
    }
    @Then("the username should be same with database")
    public void the_username_should_be_same_with_database() {
        DB_Util.runQuery("select full_name from users\n" +
                "where email like '" + email + "';");
        String expectedHolderName = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedHolderName, actualUserName);
    }

}
