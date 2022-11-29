package com.LibraryCT.pages;

import com.LibraryCT.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class BooksPage {

    public BooksPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//select[@id='book_categories']")
    public WebElement bookCatSelection;

    @FindBy(name = "tbl_books_length")
    public WebElement tableBooksLength;

    @FindBy(xpath = "//input[@type = 'search']")
    public WebElement searchFieldBooks;

    @FindBy(xpath = "//i[@class='fa fa-plus']")
    public WebElement addBook;

    @FindBy(xpath = "//input[@placeholder='Book Name']")
    public WebElement addBookName;

    @FindBy(xpath = "//input[@placeholder='ISBN']")
    public WebElement addBookISBN;

    @FindBy(xpath = "//input[@placeholder='Year']")
    public WebElement addBookYear;

    @FindBy(xpath = "//input[@placeholder='Author']")
    public WebElement addBookAuthor;

    @FindBy(id = "book_group_id")
    public WebElement addBookCatSelection;

    @FindBy(id = "description")
    public WebElement addBookDescription;

    @FindBy(xpath = "//button[.='Close']")
    public WebElement addBookCloseBtn;

    @FindBy(xpath = "//button[.='Save changes']")
    public WebElement addBookSaveChangesBtn;

    @FindBy(xpath = "//th[.='ISBN']")
    public WebElement sortASCbyISBN;

    @FindBy(xpath = "//th[.='Name']")
    public WebElement sortASCbyName;

    @FindBy(xpath = "//th[.='Author']")
    public WebElement sortASCbyAuthor;

    @FindBy(xpath = "//th[.='Category']")
    public WebElement sortASCbyCategory;

    @FindBy(xpath = "//th[.='Year']")
    public WebElement sortASCbyYear;

    @FindBy(xpath = "//th[.='Borrowed By']")
    public WebElement sortASCbyBorrowedBy;



    public WebElement editBookBtnByID(String bookID) {
        String xPath = "//a[@onclick='Books.edit_book(" + bookID +"']";
        return Driver.getDriver().findElement(By.xpath(xPath));
    }

    public void editBookBtnByOtherFilters(String filter) {
        String xPath = "//td[.='" + filter + "']/..//a[@role='button']";
        JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
        WebElement bookEdit = Driver.getDriver().findElement(By.xpath(xPath));
        js.executeScript("arguments[0].click()", bookEdit);
    }

    /**Select book category or users table length (5,10,15,50,100,200,500)*/
    public void selectionOneInBooks(String input, WebElement element) {
        String tableLength = "5,10,15,50,100,200,500";
        if(tableLength.contains(input))
            input = input + "";
        Select select = new Select(element);
        select.selectByVisibleText(input);
    }

    public String getCurrentSelectionText(WebElement element) {
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }
    public List<String> getSelectionAllInBooks(WebElement element) {
        Select select = new Select(element);
        List<String> res = new ArrayList<>();
        List<WebElement> allOptions = select.getOptions();
        for (WebElement option : allOptions) {
            res.add(option.getText());
        }
        return res;
    }
}
