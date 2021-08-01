/*
 *Purpose : Class is implemented for executing the test methods of cart page
 *                @Test annotation is identify the test method
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 31-07-2021
 */

package com.bookswagon;

import com.bookswagon.base.BaseClass;
import com.bookswagon.pages.Cart;
import com.bookswagon.utility.DataProvider;
import com.bookswagon.utility.listeners.TestListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
@Epic("Cart Page feature are working properly to user")
public class Test_Cart_Page extends BaseClass {

    Cart cart;

    @Test(dataProvider = "BooksWagonData",dataProviderClass = DataProvider.class,
            priority = 4,dependsOnMethods = "com.bookswagon.Test_Books_Page.addBook_To_Cart")
    @Description("verify product quantity changed correctly")
    @Severity(SeverityLevel.NORMAL)
    @Feature("change order quantity")
    @Story("change product quantity in cart")
    public void changeProductQuantity(String quantity) throws InterruptedException {
        cart = new Cart(driver);
        boolean value = cart.changeBookQuantity(quantity);
        Assert.assertTrue(value);
    }

    @Test(priority = 5, dependsOnMethods = "com.bookswagon.Test_Books_Page.addBook_To_Cart" )
    @Description("verify order is placed correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("place order from cart")
    @Story("place order correctly ")
    public void placeOrder() throws InterruptedException {
        cart = new Cart(driver);
        String actualUrl = cart.placeOrder();
        String expectedUrl = "https://www.bookswagon.com/checkout-login";

        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test(dataProvider = "BooksWagonData",dataProviderClass = DataProvider.class,
            priority = 6,dependsOnMethods ="placeOrder")
    @Description("verify email id displayed in correct")
    @Severity(SeverityLevel.MINOR)
    @Story("check email id is displayed")
    public void check_Email_Id(String email) {
        cart = new Cart(driver);
        String actualMail = cart.checkLogin_And_Continue();
        Assert.assertEquals(actualMail, email);
    }

    @Test(dataProvider = "BooksWagonData",priority = 6,dataProviderClass = DataProvider.class,
            dependsOnMethods = "placeOrder")
    @Description("verify user login to application when placing order")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login to application")
    @Story("user is able to login")
    public void login(String email, String password) throws InterruptedException {
        cart = new Cart(driver);
        String actualUrl = cart.loginToApplication(email,password);
        String expectedUrl = "https://www.bookswagon.com/shippingoption.aspx";

        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test(dataProvider = "BooksWagonData",dataProviderClass = DataProvider.class,
            priority = 7 )
    @Description("enter shipping address")
    @Severity(SeverityLevel.NORMAL)
    @Feature("adding address details")
    @Story("add address details")
    public void enter_Shipping_Address(String name, String address, String landMark, String countryName,
                                       String stateName, String cityName, String pinCode, String mobileNumber) throws InterruptedException {
        cart = new Cart(driver);
        String actualUrl = cart.enterShippingAddress(name, address, landMark, countryName, stateName, cityName,
                pinCode, mobileNumber);
        String expectedUrl = "https://www.bookswagon.com/viewshoppingcart.aspx";

        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test(dataProvider = "BooksWagonData",dataProviderClass = DataProvider.class,
            priority = 8,dependsOnMethods = "enter_Shipping_Address")
    @Description("Review order details in cart")
    @Severity(SeverityLevel.MINOR)
    @Story("check order details")
    public void review_Orders(String orderName, String quantity) {
        cart = new Cart(driver);
        boolean reviewOrders = cart.reviewAllOrders(orderName, quantity);
        Assert.assertTrue(reviewOrders);
    }

    @Test(priority = 9,dependsOnMethods = "review_Orders")
    @Description("navigate to payments page")
    @Severity(SeverityLevel.NORMAL)
    @Feature("navigate to payments page")
    @Story("use is able navigate to payments page")
    public void checkAnd_GoTo_Payments() throws InterruptedException {
        cart = new Cart(driver);
        String actualUrl = cart.continueToPay();
        String expectedUrl = "https://www.bookswagon.com/checkout.aspx";
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}
