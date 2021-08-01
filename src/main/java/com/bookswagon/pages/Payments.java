/*
 *Purpose : Class is implemented to identify the web elements in payments page and written steps to execute operations
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

public class Payments extends BaseClass {

    @FindBy(xpath = "//a[contains(text(),'Paypal')]")
    WebElement payWithPaypal;
    @FindBy(name = "ctl00$cpBody$btnPyaPal")
    WebElement payNow;

    //parameterized constructor
    public Payments(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * payWithPayPal method is used to select payments method
     * @return title of the page
     * @throws InterruptedException intercepts execution for given period of time
     */
    public String payWithPayPal() throws InterruptedException {
        Log.debug("click on paypal method");
        payWithPaypal.click();
        Log.debug("click on pay now");
        payNow.click();
        Thread.sleep(5000);

        return driver.getTitle();
    }
}
