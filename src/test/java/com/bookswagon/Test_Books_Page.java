/*
 *Purpose : Class is implemented for executing the test methods of books page
 *                @Test annotation is identify the test method
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 31-07-2021
 */
package com.bookswagon;

import com.bookswagon.base.BaseClass;
import com.bookswagon.pages.Books;
import com.bookswagon.utility.DataProvider;
import com.bookswagon.utility.listeners.TestListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
@Epic("Books page feature like selecting book and add to cart working to user")
public class Test_Books_Page extends BaseClass {

    @Test(dataProvider = "BooksWagonData",dataProviderClass = DataProvider.class,
            priority = 2)
    @Description("verify the book is selected")
    @Severity(SeverityLevel.NORMAL)
    @Feature("selecting book")
    @Story("select book from list")
    public void selectBook(String bookName) {
        Books books = new Books(driver);
        boolean displayed = books.selectBook(bookName);

        Assert.assertTrue(displayed);
    }

    @Test(dataProvider = "BooksWagonData",dataProviderClass = DataProvider.class,
            priority = 3,dependsOnMethods = "selectBook")
    @Description("verify book is added to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("adding to cart")
    @Story("Add book to cart and check added or not")
    public void addBook_To_Cart(String expectedName) throws InterruptedException {
        Books books = new Books(driver);
        String actualItemName = books.addBookToCart();

        Assert.assertEquals(actualItemName, expectedName);
    }
}
