package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebElement;

public class CheckoutReviewPage extends BaseTest {
    TestUtils testUtils = new TestUtils();
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"checkout review order screen\"]/android.view.ViewGroup/android.widget.TextView")
    private WebElement checkoutReviewTitle;

    @AndroidFindBy (accessibility = "Place Order button")
    private WebElement checkoutPlaceOrderBtn;

    // get My cart title
    public String getCheckoutReviewTitle() {
        if (getPlatform().equalsIgnoreCase("Android")) {
            return testUtils.getAttribute(getDriver(), checkoutReviewTitle, "text");
        } else if (getPlatform().equalsIgnoreCase("IOS")) {
            return testUtils.getAttribute(getDriver(), checkoutReviewTitle, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

    public CheckoutConfirmationPage tapOnCheckoutPlaceOrderBtn(){
        testUtils.tap(getDriver(),checkoutPlaceOrderBtn);
        return new CheckoutConfirmationPage();
    }
}
