package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CheckoutShippingAddressPage extends BaseTest {
    TestUtils testUtils = new TestUtils();
    @AndroidFindBy(accessibility = "Full Name* input field")
    private WebElement checkoutFullName;

    @AndroidFindBy(accessibility = "Address Line 1* input field")
    private WebElement checkoutAddressLine1;

    @AndroidFindBy(accessibility = "Address Line 2 input field")
    private WebElement checkoutAddressLine2;

    @AndroidFindBy(accessibility = "City* input field")
    private WebElement checkoutCity;

    @AndroidFindBy(accessibility = "State/Region input field")
    private WebElement checkoutState_Region;

    @AndroidFindBy(accessibility = "Zip Code* input field")
    private WebElement checkoutZipCode;

    @AndroidFindBy(accessibility = "Country* input field")
    private WebElement checkoutCountry;

    @AndroidFindBy (accessibility = "To Payment button")
    private WebElement checkoutPaymentBtn;



    // Enter Full Name
    public void enterFullName(String fullName){
        testUtils.sendKeys(getDriver(),checkoutFullName,fullName);
    }

    // Enter Address line 1
    public void enterAddressLine1(String addressLine1){
        testUtils.sendKeys(getDriver(),checkoutAddressLine1,addressLine1);
    }

    // Enter Address line 2
    public void enterAddressLine2(String addressLine2){
        testUtils.sendKeys(getDriver(),checkoutAddressLine2,addressLine2);
    }

    // Enter city
    public void enterCity(String city){
        testUtils.sendKeys(getDriver(),checkoutCity,city);
    }

    // Enter state/region
    public void enterStateRegion(String state_region){
        testUtils.sendKeys(getDriver(),checkoutState_Region,state_region);
    }

    // Enter zip code
    public void enterZipCode(String zip_code){
        testUtils.sendKeys(getDriver(),checkoutZipCode,zip_code);
    }

    // Enter zip code
    public void enterCountry(String country){
        testUtils.sendKeys(getDriver(),checkoutCountry,country);
    }

    // click on Payment button

    public CheckoutCardDetailsPage tapOnPaymentBtn(){
        testUtils.tap(getDriver(),checkoutPaymentBtn);
        return new CheckoutCardDetailsPage();
    }



}
