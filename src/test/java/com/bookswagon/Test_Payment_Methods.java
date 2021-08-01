/*
 *Purpose : Class is implemented for executing the test methods of payments page
 *                @Test annotation is identify the test method
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 31-07-2021
 */
package com.bookswagon;

import com.bookswagon.base.BaseClass;
import com.bookswagon.pages.Payments;
import com.bookswagon.utility.listeners.TestListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
@Epic("payment feature working to user")
public class Test_Payment_Methods extends BaseClass {
    Payments payments;

    @Test(priority = 10,dependsOnMethods = "com.bookswagon.Test_Cart_Page.checkAnd_GoTo_Payments")
    @Description("verify payments method working")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("payment")
    @Story("payment is working")
    public void payWith_PayPal() throws InterruptedException {
        payments = new Payments(driver);
        String actualTitle = payments.payWithPayPal();
        String expectedTitle = "Log in to your PayPal account";

        Assert.assertEquals(actualTitle, expectedTitle);
    }
}
