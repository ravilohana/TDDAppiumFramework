package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;


public class CheckoutCardDetailsPage extends BaseTest {

    @AndroidFindBy(accessibility = "Full Name* input field")
    private WebElement cardDetailsFullName;

    @AndroidFindBy(accessibility = "Card Number* input field")
    private WebElement cardDetailsCardNumber;

    @AndroidFindBy(accessibility = "Expiration Date* input field")
    private WebElement cardDetailsExpirationDate;

    @AndroidFindBy (accessibility = "Security Code* input field")
    private WebElement cardDetailsSecurityCode;

    @AndroidFindBy(accessibility = "Review Order button")
    private WebElement cardDetailsReviewOrderBtn;

    // Enter Full Name
    public void enterCardFullName(String fullName){
        TestUtils.sendKeys(driver,cardDetailsFullName,fullName);
    }

    // Enter Full Name
    public void enterCardNumber(String cardNumber){
        TestUtils.sendKeys(driver,cardDetailsCardNumber,cardNumber);
    }

    // Enter Full Name
    public void enterCardExpiryDate(String expiryDate){
        TestUtils.sendKeys(driver,cardDetailsExpirationDate,expiryDate);
    }

    // Enter Full Name
    public void enterCardSecurityCode(String securityCode){
        TestUtils.sendKeys(driver,cardDetailsSecurityCode,securityCode);
    }

    public CheckoutReviewPage tapOnReviewOrderBtn(){
        TestUtils.tap(driver,cardDetailsReviewOrderBtn);
        return new CheckoutReviewPage();
    }
}
