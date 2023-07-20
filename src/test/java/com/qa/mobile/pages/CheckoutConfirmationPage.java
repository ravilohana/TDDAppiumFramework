package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CheckoutConfirmationPage extends BaseTest {

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
        if (platform.equalsIgnoreCase("Android")) {
            return TestUtils.getAttribute(driver, checkoutCnfTitle, "text");
        } else if (platform.equalsIgnoreCase("IOS")) {
            return TestUtils.getAttribute(driver, checkoutCnfTitle, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

    // get checkout thanks msg
    public String getCheckoutCnfThanksMsg() {
        if (platform.equalsIgnoreCase("Android")) {
            return TestUtils.getAttribute(driver, checkoutThanksMsg, "text");
        } else if (platform.equalsIgnoreCase("IOS")) {
            return TestUtils.getAttribute(driver, checkoutThanksMsg, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }


    // get checkout thanks msg
    public String getCheckoutCnfDispatchMsh() {
        if (platform.equalsIgnoreCase("Android")) {
            return TestUtils.getAttribute(driver, checkoutDispatchMsg, "text");
        } else if (platform.equalsIgnoreCase("IOS")) {
            return TestUtils.getAttribute(driver, checkoutDispatchMsg, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

    // click on Continue button
    public ProductsPage tapOnCheckoutContinueShopBtn(){
        TestUtils.tap(driver,checkoutContinueShopBtn);
        return new ProductsPage();
    }
}
