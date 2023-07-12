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
    @FindBy(id = "product description")
    private WebElement SLB_productDescription;

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


}
