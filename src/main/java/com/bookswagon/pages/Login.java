/*
 *Purpose : Class is implemented to identify the web elements in login page and written steps to execute operations
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

public class Login extends BaseClass {

    @FindBy(xpath = "//a[contains(text(),'Login')]")
    WebElement loginButton;
    @FindBy(name = "ctl00$phBody$SignIn$txtEmail")
    WebElement emailBox;
    @FindBy(name = "ctl00$phBody$SignIn$txtPassword")
    WebElement passwordBox;
    @FindBy(name = "ctl00$phBody$SignIn$btnLogin")
    WebElement login;
    @FindBy(xpath = "//a[contains(text(),'Forgot Password ?')]")
    WebElement forgotPassword;
    @FindBy(xpath = "//label[contains(text(),'Please enter correct Email or Password.')]")
    WebElement errorMessage;

    //parameterized constructor
    public Login(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * loginToApplication is used to login to application
     * @param email email id
     * @param password password
     * @return current url
     * @throws InterruptedException intercepts execution for given period of time
     */
    public String loginToApplication(String email,String password) throws InterruptedException {
        Log.debug("click on login");
        loginButton.click();
        Thread.sleep(1000);
        Log.debug("enter email id: "+email);
        emailBox.sendKeys(email);
        Log.debug("enter password: "+password);
        passwordBox.sendKeys(password);
        Log.debug("click on login button");
        login.click();
        Thread.sleep(1000);
        return driver.getCurrentUrl();
    }

    /**
     * forgotPassword is used to navigate t forgot password page
     * @return title of the page
     */
    public String forgotPassword() {
        forgotPassword.click();

        return driver.getTitle();
    }

    /**
     * errorMessage method is used to check error message is displayed or not
     * @return boolean
     */
    public boolean errorMessage() {
        return errorMessage.isDisplayed();
    }
}
