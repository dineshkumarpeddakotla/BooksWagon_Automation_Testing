/*
 *Purpose : Class is implemented to identify the web elements in cart page and written steps to execute operations
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
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;

public class Cart extends BaseClass {

    @FindBy(xpath = "//div[@class='shopping-content']")
    List<WebElement> shoppingContent;
    @FindBy(name = "ctl00$cpBody$txtEmail")
    WebElement email;
    @FindBy(name = "ctl00$cpBody$txtPassword")
    WebElement password;
    @FindBy(name = "ctl00$cpBody$imgLogin")
    WebElement login;
    @FindBy(xpath = "//div[@class='shopping-content'][1]/div[3]/input[1]")
    WebElement quantity;
    @FindBy(xpath = "//div[@class='sale-price']/label")
    WebElement itemPrice;
    @FindBy(xpath = "//div[@class='total']/label")
    WebElement totalPrice;
    @FindBy(name = "BookCart$lvCart$ctrl0$imgUpdate")
    WebElement refresh;
    @FindBy(xpath = "//a[contains(text(),'Move to Wishlist')]")
    WebElement moveToWishlist;
    @FindBy(xpath = "//a[contains(text(),'Remove')]")
    WebElement remove;
    @FindBy(name = "BookCart$lvCart$imgPayment")
    WebElement placeOrder;
    @FindBy(xpath = "//div[@class='loggedin']/p/a/strong/span")
    WebElement checkMailId;
    @FindBy(xpath = "//a[contains(text(),'Continue')]")
    WebElement continueButton;
    @FindBy(name = "ctl00$cpBody$txtNewRecipientName")
    WebElement recipientName;
    @FindBy(name = "ctl00$cpBody$txtNewAddress")
    WebElement streetAddress;
    @FindBy(name = "ctl00$cpBody$txtNewLandmark")
    WebElement lMark;
    @FindBy(name = "ctl00$cpBody$ddlNewCountry")
    WebElement country;
    @FindBy(name = "ctl00$cpBody$ddlNewState")
    WebElement state;
    @FindBy(name = "ctl00$cpBody$ddlNewCities")
    WebElement city;
    @FindBy(name = "ctl00$cpBody$txtNewPincode")
    WebElement zipCode;
    @FindBy(name = "ctl00$cpBody$txtNewMobile")
    WebElement mobileNumber;
    @FindBy(name = "ctl00_cpBody_chkUseAddress")
    WebElement useSameAddress;
    @FindBy(name = "ctl00$cpBody$imgSaveNew")
    WebElement addressSave;
    @FindBy(xpath = "//div[@class='itm-description os-left']/label[1]")
    List<WebElement> allOrderItems;
    @FindBy(xpath = "//div[@class='os-itm-body']/div[3]")
    List<WebElement> reviewQuantity;
    @FindBy(name = "ctl00$cpBody$ShoppingCart$lvCart$savecontinue")
    WebElement saveAndContinueOrder;

    //parameterized constructor
    public Cart(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * changeBookQuantity method is used to change the quantity in product page
     * @param noOfItem quantity of a book
     * @return boolean value for after checking the products price
     * @throws InterruptedException intercept execution for given time period
     */
    public boolean changeBookQuantity(String noOfItem) throws InterruptedException {
        Log.debug("clear quantity in quantity box");
        quantity.clear();
        Log.debug("send quantity " +noOfItem);
        quantity.sendKeys(noOfItem);
        Log.debug("click refresh button");
        refresh.click();
        Thread.sleep(1000);
        Log.debug("price for each book: " +itemPrice.getText());
        int price = price(itemPrice.getText());
        Log.debug("total price of book: "+totalPrice.getText());
        int total = price(totalPrice.getText());

        Log.debug("returns boolean value");
        return (price * Integer.parseInt(noOfItem)) == total;
    }

    /**
     * price method is used to convert string to integer
     * @param string value
     * @return integer
     */
    private int price(String string) {
        Log.debug("matching string with number only");
        String price = string.replaceAll("[^(0-9)]+", "");
        Log.debug("returns integer value");
        return Integer.parseInt(price);
    }

    /**
     * placeOrder method is used to place order
     * @return current url of page
     * @throws InterruptedException intercepts execution for given period of time
     */
    public String placeOrder() throws InterruptedException {
        Log.debug("click on place order");
        placeOrder.click();
        Thread.sleep(2000);

        return driver.getCurrentUrl();
    }

    /**
     * checkLogin_And_Continue method is used to check user mail is correct or not
     * @return mail id
     */
    public String checkLogin_And_Continue() {
        Log.debug("user mail id: "+checkMailId.getText());
        String mail = checkMailId.getText();
        Log.debug("click on continue button");
        continueButton.click();

        return mail;
    }

    /**
     * enterShippingAddress method is used to add the address
     * @param name recipient name
     * @param address address
     * @param landMark land mark of address
     * @param countryName country name
     * @param stateName state name
     * @param cityName city name
     * @param pinCode pin code
     * @param number mobile number
     * @return current url of page
     * @throws InterruptedException intercepts execution for given period of time
     */
    public String enterShippingAddress(String name, String address, String landMark, String countryName,
                                     String stateName,String cityName,String pinCode,String number) throws InterruptedException {
        Log.debug("enter recipient name: "+name);
        recipientName.sendKeys(name);
        Log.debug("enter street address: "+address);
        streetAddress.sendKeys(address);
        Log.debug("enter land mark: "+landMark);
        lMark.sendKeys(landMark);
        Log.debug("click on country drop down");
        country.click();
        Log.debug("select country name: "+countryName);
        select(country, countryName);
        Log.debug("click on state drop down list");
        state.click();
        Log.debug("select state name: "+stateName);
        select(state,stateName);
        Log.debug("click on city drop down ");
        city.click();
        Log.debug("select city name: "+cityName);
        select(city, cityName);
        Log.debug("enter pin code: "+pinCode);
        zipCode.sendKeys(pinCode);
        Log.debug("enter mobile number: "+mobileNumber);
        mobileNumber.sendKeys(number);
//        useSameAddress.click();
        Log.debug("click on save button");
        addressSave.click();
        Thread.sleep(2000);

        return driver.getCurrentUrl();
    }

    /**
     * select method is used to select elements from drop down list
     * @param element drop down list name
     * @param selectName name in drop down list
     */
    private void select(WebElement element, String selectName) {
        Select select = new Select(element);
        select.selectByVisibleText(selectName);
    }

    /**
     * reviewAllOrders method is used to review orders are displayed correct or not
     * @param orderName order name
     * @param quantity quantity of order
     * @return boolean value after checking things
     */
    public boolean reviewAllOrders(String orderName, String quantity) {
        HashMap<String,String> ordersList = new HashMap<>();
        System.out.println(allOrderItems.size());
        System.out.println(allOrderItems.get(0).getText());
        for(int i = 0; i < allOrderItems.size(); i++) {
            ordersList.put(allOrderItems.get(i).getText(), reviewQuantity.get(i).getText());
        }

        Log.debug("checking quantity in order list");
        return ordersList.get(orderName).equals(quantity);
    }

    /**
     * continueToPay method is used navigate to continue payments page
     * @return current url
     * @throws InterruptedException intercepts execution for given period of time
     */
    public String continueToPay() throws InterruptedException {
        Log.debug("click on save and continue button");
        saveAndContinueOrder.click();
        Thread.sleep(2000);
        return driver.getCurrentUrl();
    }

    /**
     * loginToApplication is used to login while placing order
     * @param emailId email id
     * @param pass password
     * @return current url
     * @throws InterruptedException intercepts execution for given period of time
     */
    public String loginToApplication(String emailId, String pass) throws InterruptedException {
        Log.debug("enter email: "+emailId);
        email.sendKeys(emailId);
        Thread.sleep(600);
        Log.debug("enter password: "+pass);
        password.sendKeys(pass);
        Thread.sleep(600);
        Log.debug("click on login button");
        login.click();
        Thread.sleep(2000);

        return driver.getCurrentUrl();
    }

}
