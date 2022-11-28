package com.LibraryCT.pages;

import com.LibraryCT.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    public DashboardPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[.='Dashboard']")
    public WebElement dashboardModule;

    @FindBy(xpath = "//span[.='Users']")
    public WebElement usersModule;

    @FindBy(xpath = "//span[.='Books']")
    public WebElement booksModule;

    @FindBy(xpath = "//h2[@id='user_count']")
    public WebElement userCount;

    @FindBy(xpath = "//h2[@id='book_count']")
    public WebElement booksCount;

    @FindBy(xpath = "//h2[@id='borrowed_books']")
    public WebElement borrowedBooksCount;

    @FindBy(id = "user_avatar")
    public WebElement userIcon;

    @FindBy(xpath = "//a[.='Log Out']")
    public WebElement logOut;

    public String getStats(WebElement element) {
        return element.getText();
    }

}
