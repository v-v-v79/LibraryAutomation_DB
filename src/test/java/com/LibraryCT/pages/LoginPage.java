package com.LibraryCT.pages;

import com.LibraryCT.utility.BrowserUtils;
import com.LibraryCT.utility.Config;
import com.LibraryCT.utility.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(css = "[id='inputEmail']")
    private WebElement username;

    @FindBy(css = "[id='inputPassword']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement signIn;

    @FindBy(id = "user_avatar")
    public WebElement userIcon;

    @FindBy(xpath = "//a[@id='navbarDropdown']//span")
    public WebElement accountHolderName;

    @FindBy(xpath = "//a[.='Log Out']")
    public WebElement logOutBtn;

    public void login(String user) {
        Driver.getDriver().get(Config.getProperty("url"));
        BrowserUtils.waitForPageToLoad(30);
        username.sendKeys(Config.getProperty(user));
        password.sendKeys(Config.getProperty(user + "_password"));
        signIn.click();
        BrowserUtils.waitForPageToLoad(30);
    }

    public void login(String username1, String password1) {
        Driver.getDriver().get(Config.getProperty("url"));
        BrowserUtils.waitForPageToLoad(30);
        username.sendKeys(username1);
        password.sendKeys(password1);
        signIn.click();
        BrowserUtils.waitForPageToLoad(30);
    }

    public void logout(){
        JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
        js.executeScript("arguments[0].click()", userIcon);
        BrowserUtils.waitFor(3);
        js.executeScript("arguments[0].click()", logOutBtn);
    }
}
