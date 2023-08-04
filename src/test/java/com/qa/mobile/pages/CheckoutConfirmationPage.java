package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CheckoutConfirmationPage extends BaseTest {
    TestUtils testUtils = new TestUtils();
    @AndroidFindBy (xpath = "//android.widget.TextView[@text=\"Checkout Complete\"]")
    private WebElement checkoutCnfTitle;

    @AndroidFindBy (xpath = "//android.widget.TextView[@text=\"Thank you for your order\"]")
    private WebElement checkoutThanksMsg;

    @AndroidFindBy (xpath = "//android.widget.TextView[@text=\" Your order has been dispatched and will arrive as fast as the pony gallops!\"]")
    private  WebElement checkoutDispatchMsg;

    @AndroidFindBy (accessibility = "Continue Shopping button")
    private WebElement checkoutContinueShopBtn;

    // get checkout confirmation title
    public String getCheckoutCnfTitle() {
        if (getPlatform().equalsIgnoreCase("Android")) {
            return testUtils.getAttribute(getDriver(), checkoutCnfTitle, "text");
        } else if (getPlatform().equalsIgnoreCase("IOS")) {
            return testUtils.getAttribute(getDriver(), checkoutCnfTitle, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

    // get checkout thanks msg
    public String getCheckoutCnfThanksMsg() {
        if (getPlatform().equalsIgnoreCase("Android")) {
            return testUtils.getAttribute(getDriver(), checkoutThanksMsg, "text");
        } else if (getPlatform().equalsIgnoreCase("IOS")) {
            return testUtils.getAttribute(getDriver(), checkoutThanksMsg, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }


    // get checkout thanks msg
    public String getCheckoutCnfDispatchMsh() {
        if (getPlatform().equalsIgnoreCase("Android")) {
            return testUtils.getAttribute(getDriver(), checkoutDispatchMsg, "text");
        } else if (getPlatform().equalsIgnoreCase("IOS")) {
            return testUtils.getAttribute(getDriver(), checkoutDispatchMsg, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

    // click on Continue button
    public ProductsPage tapOnCheckoutContinueShopBtn(){
        testUtils.tap(getDriver(),checkoutContinueShopBtn);
        return new ProductsPage();
    }
}
