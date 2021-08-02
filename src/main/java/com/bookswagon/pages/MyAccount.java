/*
 *Purpose : Class is implemented to identify the web elements in login page and written steps to execute operations
 *                 @FindBy annotation is used to identify the web elements
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 02-08-2021
 */

package com.bookswagon.pages;

import com.bookswagon.base.BaseClass;
import com.bookswagon.utility.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccount extends BaseClass {
    @FindBy(id = "ctl00_lnkbtnLogout")
    WebElement logOut;
    @FindBy(id = "ctl00_imglogo")
    WebElement homeLogo;
    @FindBy(id = "ctl00_lblUser")
    WebElement user;

    public MyAccount(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * logout method is used to logout from application
     * @return current url of the page
     * @throws InterruptedException intercepts the execution for given period of time
     */
    public String logout() throws InterruptedException {
        Log.debug("navigate back");
        driver.navigate().back();
        Thread.sleep(3000);
        Log.debug("click on home logo");
        homeLogo.click();
        Thread.sleep(1000);
        Log.debug("move to user");
        mouseHOver(user);
        Log.debug("move to logout");
        mouseHOver(logOut);
        Log.debug("click on logout");
        logOut.click();
        Thread.sleep(1500);

        return driver.getCurrentUrl();
    }

    //it performs mouse over actions
    private void mouseHOver(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
