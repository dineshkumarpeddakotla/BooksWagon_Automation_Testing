/*
 *Purpose : Class is implemented to identify the web elements in homepage page and written steps to execute operations
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

public class HomePage extends BaseClass {

    @FindBy(className = "cat-a01")
    WebElement home;
    @FindBy(xpath = "//div[@class='search-input']/input")
    WebElement searchBox;
    @FindBy(name = "ctl00$TopSearch1$Button1")
    WebElement searchButton;
    @FindBy(xpath = "//a[contains(text(),'Bestsellers')]")
    WebElement bestSellers;
    @FindBy(xpath = "//li[@id='ctl00_liTextBook']/a")
    WebElement textBooks;
    @FindBy(xpath = "//a[contains(text(),'New Arrivals')]")
    WebElement newArrivals;
    @FindBy(xpath = "//a[contains(text(),'Pre-order')]")
    WebElement preOrder;
    @FindBy(xpath = "//a[contains(text(),'Award Winners')]")
    WebElement awardWinners;

    //parameterized constructor
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * clickTextBooks method is used to open text books
     * @return title of the page
     * @throws InterruptedException intercepts execution foe given period of time
     */
    public String clickTextBooks() throws InterruptedException {
        Log.debug("click on text books");
        textBooks.click();
        Thread.sleep(1000);

        return driver.getTitle();
    }

    /**
     * search method is used to search
     * @param searchName name to enter in search box
     * @return title of the page
     * @throws InterruptedException intercepts execution for certain period of time
     */
    public String search(String searchName) throws InterruptedException {
        searchBox.sendKeys(searchName);
        Thread.sleep(500);
        searchButton.click();
        Thread.sleep(1000);

        return driver.getTitle();
    }
}
