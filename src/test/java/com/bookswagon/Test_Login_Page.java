/*
 *Purpose : Class is implemented for executing the test methods of login page
 *                @Test annotation is identify the test method
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 31-07-2021
 */
package com.bookswagon;

import com.bookswagon.base.BaseClass;
import com.bookswagon.pages.Login;
import com.bookswagon.utility.DataProvider;
import com.bookswagon.utility.listeners.TestListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
@Epic("login features working to user")
public class Test_Login_Page extends BaseClass {

    Login login;

    @Test(dataProvider = "BooksWagonData",dataProviderClass = DataProvider.class)
    @Description("verify user is able login with valid credentials")
    @Feature("login")
    @Severity(SeverityLevel.BLOCKER)
    @Story("user is able to login")
    public void loginTOApplication_With_ValidCredentials(String email, String password) throws InterruptedException {
        login = new Login(driver);
        String actualTitle = login.loginToApplication(email,password);
        String expectedTitle = "https://www.bookswagon.com/myaccount.aspx";

        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(dataProvider = "BooksWagonData",dataProviderClass = DataProvider.class)
    @Description("verify user is not able to login with invalid credentials")
    @Feature("login")
    @Severity(SeverityLevel.BLOCKER)
    @Story("user is not able to login")
    public void loginToApplication_With_InvalidCredentials(String email,String password) throws InterruptedException {
        login = new Login(driver);
        login.loginToApplication(email, password);
        boolean displayed = login.errorMessage();

        Assert.assertTrue(displayed);
    }
}
