package com.LibraryCT.step_definitions;


import com.LibraryCT.utility.Config;
import com.LibraryCT.utility.DB_Util;
import com.LibraryCT.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hooks {

    @Before
    public void setUp() {
        DB_Util.createConnection(Config.getProperty("db_url"),
                Config.getProperty("db_username"), Config.getProperty("db_password"));
        Driver.getDriver();
    }

    @After
    public void teardownScenario(Scenario scenario) {
        // We will implement taking screenshot in this method
        //System.out.println("It will be closing browser using cucumber @After each scenario");
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Driver.closeDriver();
        DB_Util.destroy();
    }
}

