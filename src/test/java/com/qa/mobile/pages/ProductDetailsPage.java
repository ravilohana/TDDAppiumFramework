package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BaseTest {


    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"container header\"]/android.widget.TextView")
    @FindBy(id = "Sauce Labs Backpack")
    private  WebElement SLB_productTitle;
    @AndroidFindBy (accessibility = "product price")
    @FindBy(id = "product price")
    private WebElement SLB_productPrice;

    @AndroidFindBy (accessibility = "product description")
    private WebElement SLB_productDescription;

    @AndroidFindBy (accessibility = "counter minus button")
    private WebElement SL_CounterMinusBtn;

    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"counter amount\"]/android.widget.TextView")
    private WebElement SL_CounterAmountText;

    @AndroidFindBy (accessibility = "counter plus button")
    private WebElement SL_CounterPlusBtn;

    @AndroidFindBy (accessibility = "Add To Cart button")
    private WebElement SL_AddToCartBtn;


    public WebElement getSLB_productTitle() {
        return SLB_productTitle;
    }

    public String getProductTitle() {
        if (platform.equalsIgnoreCase("Android")) {
            return TestUtils.getAttribute(driver, SLB_productTitle, "text");
        } else if (platform.equalsIgnoreCase("IOS")) {
            return TestUtils.getAttribute(driver, SLB_productTitle, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }
    public String getProductPrice() {
        if (platform.equalsIgnoreCase("Android")) {
            return TestUtils.getAttribute(driver, SLB_productPrice, "text");
        } else if (platform.equalsIgnoreCase("IOS")) {
            return TestUtils.getAttribute(driver, SLB_productPrice, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

    public String getProductDescription() {
        if (platform.equalsIgnoreCase("Android")) {
            return TestUtils.getAttribute(driver, SLB_productDescription, "text");
        } else if (platform.equalsIgnoreCase("IOS")) {
            return TestUtils.getAttribute(driver, SLB_productDescription, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

    // Get the text for Qty for product
    public String getCounterTextQty() {
        if (platform.equalsIgnoreCase("Android")) {
            return TestUtils.getAttribute(driver, SL_CounterAmountText, "text");
        } else if (platform.equalsIgnoreCase("IOS")) {
            return TestUtils.getAttribute(driver, SL_CounterAmountText, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }


    // tap on SL_CounterMinusBtn
    public void tapOnCounterMinusBtn(){
        TestUtils.tap(driver,SL_CounterMinusBtn);
    }

    // tap on SL_CounterPlusBtn
    public void tapOnCounterPlusBtn(){
        TestUtils.tap(driver,SL_CounterPlusBtn);
    }

    // tap on SL_CounterMinusBtn
    public void tapOnAddToCart(){
        TestUtils.tap(driver,SL_AddToCartBtn);
    }








}
