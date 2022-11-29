package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.BooksPage;
import com.LibraryCT.pages.DashboardPage;
import com.LibraryCT.pages.LoginPage;
import com.LibraryCT.utility.BrowserUtils;
import com.LibraryCT.utility.DB_Util;
import com.LibraryCT.utility.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class US_4_Steps {

    LoginPage loginPage = new LoginPage();
    BooksPage booksPage = new BooksPage();
    DashboardPage dashboardPage = new DashboardPage();
    Map<String,String> db_BookInfo = new HashMap<>();
    List<String> actual_BookInfo = new ArrayList<>();

    @Given("I login as {string}")
    public void i_login_as(String user) {
        loginPage.login(user);
        BrowserUtils.waitFor(3);
    }
    @When("I navigate to {string} page")
    public void i_navigate_to_page(String moduleName) {
        BrowserUtils.waitFor(3);
        dashboardPage.navigateModule(moduleName);
        BrowserUtils.waitForPageToLoad(30);
    }
    @When("I open book {string}")
    public void i_open_book(String bookName) {
        BrowserUtils.waitFor(3);
        booksPage.searchFieldBooks.sendKeys(bookName);
        BrowserUtils.waitFor(5);
        booksPage.editBookBtnByOtherFilters(bookName);
        BrowserUtils.waitFor(3);
    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

        DB_Util.runQuery("select \n" +
                "    name, isbn, year, \n" +
                "    author, book_category_id, \n" +
                "    description from books\n" +
                "where name = 'Chordeiles minor';");
        db_BookInfo = DB_Util.getAllRowAsListOfMap().get(0);

        actual_BookInfo.add(booksPage.addBookName.getAttribute("value"));
        actual_BookInfo.add(booksPage.addBookISBN.getAttribute("value"));
        actual_BookInfo.add(booksPage.addBookYear.getAttribute("value"));
        actual_BookInfo.add(booksPage.addBookAuthor.getAttribute("value"));
        actual_BookInfo.add(booksPage.addBookCatSelection.getAttribute("value"));
        actual_BookInfo.add(booksPage.addBookDescription.getAttribute("value"));

        int i = 0;
        for (Map.Entry<String, String> entry : db_BookInfo.entrySet()) {
            Assert.assertEquals(entry.getValue(), actual_BookInfo.get(i));
            i++;
        }
        loginPage.logout();
    }
}
