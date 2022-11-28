package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.DashboardPage;
import com.LibraryCT.pages.LoginPage;
import com.LibraryCT.utility.BrowserUtils;
import com.LibraryCT.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US_2_Steps {
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    String borrowedBooksUI = "";

    @Given("user login as a {string}")
    public void userLoginAsA(String user) {
        loginPage.login(user);
    }
    @When("user take borrowed books number")
    public void user_take_borrowed_books_number() {
        BrowserUtils.waitFor(5);
        borrowedBooksUI = dashboardPage.getStats(dashboardPage.borrowedBooksCount);
    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        DB_Util.runQuery("select count(*) from book_borrow\n" +
                "where is_returned = 0;");
        String db_borrowedBooksCount = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(db_borrowedBooksCount, borrowedBooksUI);
        dashboardPage.userIcon.click();
        BrowserUtils.waitFor(3);
        dashboardPage.logOut.click();
    }
}
