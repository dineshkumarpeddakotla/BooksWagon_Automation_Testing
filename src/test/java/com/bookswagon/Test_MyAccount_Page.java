/*
 *Purpose : Class is implemented for executing the test methods of my account page
 *                @Test annotation is identify the test method
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 31-07-2021
 */
package com.bookswagon;

import com.bookswagon.base.BaseClass;
import com.bookswagon.pages.MyAccount;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_MyAccount_Page extends BaseClass {

    @Test(priority = 11,dependsOnMethods = "com.bookswagon.Test_Login_Page.loginTOApplication_With_ValidCredentials")
    @Description("verify user is able to logout successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("logout")
    @Story("user is able to logout")
    public void logOutFrom_Application() throws InterruptedException {
        MyAccount myAccount = new MyAccount(driver);

        String actualUrl = myAccount.logout();
        String expectedUrl = "https://www.bookswagon.com/login";

        Assert.assertEquals(actualUrl, expectedUrl);
    }
}
