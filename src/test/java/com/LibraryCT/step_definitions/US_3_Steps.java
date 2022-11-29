package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.BooksPage;
import com.LibraryCT.pages.DashboardPage;
import com.LibraryCT.pages.LoginPage;
import com.LibraryCT.utility.BrowserUtils;
import com.LibraryCT.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class US_3_Steps {
    LoginPage loginPage = new LoginPage();
    BooksPage booksPage = new BooksPage();
    DashboardPage dashboardPage = new DashboardPage();

    List<String> allBookCat = new ArrayList<>();
    List<String> bd_allBookCat = new ArrayList<>();

    @Given("I login as a {string}")
    public void i_login_as_a(String user) {
        loginPage.login(user);
    }
    @When("I navigate to Books page")
    public void i_navigate_to_books_page() {
        BrowserUtils.waitFor(5);
        dashboardPage.booksModule.click();
        BrowserUtils.waitFor(3);

    }
    @When("I take all book categories in UI")
    public void i_take_all_book_categories_in_ui() {
        BrowserUtils.waitFor(2);
        allBookCat = booksPage.getSelectionAllInBooks(booksPage.bookCatSelection);
        System.out.println(allBookCat);
    }
    @When("I execute query to get book categories")
    public void i_execute_query_to_get_book_categories() {
        DB_Util.runQuery("select name from book_categories;");
        bd_allBookCat = DB_Util.getColumnDataAsList("name");
        System.out.println(bd_allBookCat);
    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        allBookCat.remove(0);
        System.out.println(allBookCat);

        for (int i = 0; i < allBookCat.size(); i++) {
            Assert.assertEquals(bd_allBookCat.get(i), allBookCat.get(i));
        }
        loginPage.logout();
    }
}
