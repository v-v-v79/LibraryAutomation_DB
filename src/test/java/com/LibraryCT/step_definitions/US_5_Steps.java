package com.LibraryCT.step_definitions;

import com.LibraryCT.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US_5_Steps {

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
    }
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        DB_Util.runQuery(" select bc.name, count(bb.id)\n" +
                "from books\n" +
                "left outer join book_borrow bb\n" +
                "    on books.id = bb.book_id\n" +
                "left outer join book_categories bc on books.book_category_id = bc.id\n" +
                "group by book_category_id\n" +
                "order by count(*) desc;");
    }
    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String genre) {
        String expected  = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expected, genre);
    }
}
