package com.LibraryCT.step_definitions;

import com.LibraryCT.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class US_1_Steps {

    String uniqueUserIDs = "";
    String allUserIDs = "";
    List<String> allColumnsNamesFromUsers;

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
            DB_Util.runQuery("select distinct count(id) from users;");
            uniqueUserIDs = DB_Util.getFirstRowFirstColumn();
            DB_Util.runQuery("select count(id) from users;");
            allUserIDs = DB_Util.getFirstRowFirstColumn();
    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        Assert.assertEquals(uniqueUserIDs, allUserIDs);
    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        DB_Util.runQuery("SELECT COLUMN_NAME\n" +
                "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE TABLE_NAME = 'users';");
        allColumnsNamesFromUsers = DB_Util.getColumnDataAsList("COLUMN_NAME");

    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> list) {
        int i = 0;
        for (String str : list) {
            Assert.assertEquals(str, allColumnsNamesFromUsers.get(i));
            i++;
        }
    }
}
