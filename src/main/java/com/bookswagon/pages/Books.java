/*
 *Purpose : Class is implemented to identify the web elements in books page and written steps to execute operations
 *                 @FindBy annotation is used to identify the web elements
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 29-07-2021
 */
package com.bookswagon.pages;

import com.bookswagon.base.BaseClass;
import com.bookswagon.utility.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Books extends BaseClass {

    @FindBy(xpath = "//div[@class='title']/a")
    List<WebElement> products;
    @FindBy(xpath = "//div[@class='btn-grp']")
    WebElement buyNow;
    @FindBy(xpath = "//div[contains(text(),'close')]")
    WebElement close;
    @FindBy(css = "#ctl00_phBody_ProductDetail_lblTitle")
    WebElement bookName;
    @FindBy(xpath = "//div[@class = 'title']/label")
    WebElement cartItem;

    //parameterized constructor
    public Books(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * selectBook is used to select book from list of books in books page
     * @param name book name
     * @return boolean of book name is displayed correct or not
     */
    public boolean selectBook(String name) {
        for (WebElement book : products) {
            System.out.println(book.getText());
            if (book.getText().equalsIgnoreCase(name)) {
                System.out.println("book is found");
                Log.debug("click on book");
                book.click();
                break;
            }
        }

        return bookName.isDisplayed();
    }

    /**
     * addBookToCart method is used to add book to cart
     * @return book name in cart
     * @throws InterruptedException intercepts execution for a given period of time
     */
    public String addBookToCart() throws InterruptedException {
        Thread.sleep(2000);
        Log.debug("click on buy now button");
        buyNow.click();
        Thread.sleep(2000);
        driver.switchTo().frame(1);

        return cartItem.getText();
    }
}
