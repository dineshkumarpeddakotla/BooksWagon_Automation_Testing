/*
 *Purpose : Class is implemented for executing the test methods of home page
 *                @Test annotation is identify the test method
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 31-07-2021
 */

package com.bookswagon;

import com.bookswagon.base.BaseClass;
import com.bookswagon.pages.HomePage;
import com.bookswagon.utility.DataProvider;
import com.bookswagon.utility.listeners.TestListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
@Epic("home page feature working to user")
public class Test_Home_Page extends BaseClass {

    HomePage homePage;

    @Test(priority = 1)
    @Description("verify text book button working")
    @Story("Verify click text book")
    @Severity(SeverityLevel.MINOR)
    public void clickTextBook() throws InterruptedException {
        homePage = new HomePage(driver);
        String actualTitle = homePage.clickTextBooks();
        String expectedTitle = "Buy Textbooks Online | Free Text Books Online | World History Textbook";

        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(dataProvider = "BooksWagonData",dataProviderClass = DataProvider.class)
    @Description("verify search books working")
    @Story("Verify search books correctly")
    @Severity(SeverityLevel.NORMAL)
    public void searchText_Book(String bookName) throws InterruptedException {
        homePage = new HomePage(driver);
        String actualTitle = homePage.search(bookName);
        String expectedTitle = bookName + " - Books - 24x7 online bookstore Bookswagon.com";
        System.out.println("actual: "+actualTitle);
        System.out.println("expected: "+expectedTitle);
        boolean value = actualTitle.equalsIgnoreCase(expectedTitle);

        Assert.assertTrue(value);
    }
}
