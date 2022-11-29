package com.LibraryCT.step_definitions;


import com.LibraryCT.utility.Config;
import com.LibraryCT.utility.DB_Util;
import com.LibraryCT.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hooks {

    @Before("@ui")
    public void setUp() {
        Driver.getDriver();
    }

    @After("@ui")
    public void teardownScenario(Scenario scenario) {
        // We will implement taking screenshot in this method
        //System.out.println("It will be closing browser using cucumber @After each scenario");
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Driver.closeDriver();
    }

    @Before("@db") // custom tag
    public void db_connection_setUp() {
        DB_Util.createConnection(Config.getProperty("db_url"),
                Config.getProperty("db_username"), Config.getProperty("db_password"));
    }

    @After("@bd")
    public void db_connection_tearDown() {
        DB_Util.destroy();
    }
}

