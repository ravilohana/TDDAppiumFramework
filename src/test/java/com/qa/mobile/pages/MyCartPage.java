package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MyCartPage extends BaseTest {
    TestUtils testUtils = new TestUtils();
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"container header\"]/android.widget.TextView")
    private WebElement myCartPageTitle;

    @AndroidFindBy (xpath = "//android.widget.TextView[@content-desc=\"product label\"]")
    private List<WebElement> myCartProductLabel;

    @AndroidFindBy (xpath = "//android.widget.TextView[@content-desc=\"product price\"]")
    private List<WebElement> myCartProductPrice;


    @AndroidFindBy (accessibility = "total number")
    private WebElement myCartTotalItemsNo;

    @AndroidFindBy (accessibility = "total price")
    private WebElement myCartTotalItemsPrice;



    @AndroidFindBy(accessibility = "Proceed To Checkout button")
    private WebElement myCartProceedCheckoutBtn;




    // get My cart title
    public String getMyCartPageTitle() {
        if (getPlatform().equalsIgnoreCase("Android")) {
            return testUtils.getAttribute(getDriver(), myCartPageTitle, "text");
        } else if (getPlatform().equalsIgnoreCase("IOS")) {
            return testUtils.getAttribute(getDriver(), myCartPageTitle, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

    // get product label
    public ArrayList<String> getMyCartProductLabel() {
        ArrayList<String> productLabelList = new ArrayList<>();
        if (getPlatform().equalsIgnoreCase("Android")) {
            for (WebElement element : myCartProductLabel) {
                String productLabel = testUtils.getAttribute(getDriver(), element, "text");
                productLabelList.add(productLabel);
            }
            return productLabelList;
        }else if (getPlatform().equalsIgnoreCase("IOS")) {
            for (WebElement element : myCartProductLabel) {
                String productLabel = testUtils.getAttribute(getDriver(), element, "text");
                productLabelList.add(productLabel);
            }
            return productLabelList;
        }else {
            System.out.println("Invalid platform: " + getPlatform());

        }
        return null;
    }


    // get product price
    public ArrayList<String> getMyCartProductPrice() {
        ArrayList<String> productLabelList = new ArrayList<>();
        if (getPlatform().equalsIgnoreCase("Android")) {
            for (WebElement element : myCartProductPrice) {
                String productLabel = testUtils.getAttribute(getDriver(), element, "text");
                productLabelList.add(productLabel);
            }
            return productLabelList;
        }else if (getPlatform().equalsIgnoreCase("IOS")) {
            for (WebElement element : myCartProductPrice) {
                String productLabel = testUtils.getAttribute(getDriver(), element, "text");
                productLabelList.add(productLabel);
            }
            return productLabelList;
        }else {
            System.out.println("Invalid platform: " + getPlatform());

        }
        return null;
    }

    // click on Proceed to Checkout button

    public CheckoutShippingAddressPage tapOnProceedCheckoutBtn(){
        testUtils.tap(getDriver(),myCartProceedCheckoutBtn);
        return new CheckoutShippingAddressPage();
    }

    // get the total items numbers
    public String getMyCartTotalItemsNo() {
        if (getPlatform().equalsIgnoreCase("Android")) {
            return testUtils.getAttribute(getDriver(), myCartTotalItemsNo, "text");
        } else if (getPlatform().equalsIgnoreCase("IOS")) {
            return testUtils.getAttribute(getDriver(), myCartTotalItemsNo, "label");
        } else {
            System.out.println("Invalid platform: " + getPlatform());
        }
        return null;
    }

    public String getMyCartTotalItemsPrice() {
        if (getPlatform().equalsIgnoreCase("Android")) {
            return testUtils.getAttribute(getDriver(), myCartTotalItemsPrice, "text");
        } else if (getPlatform().equalsIgnoreCase("IOS")) {
            return testUtils.getAttribute(getDriver(), myCartTotalItemsPrice, "label");
        } else {
            System.out.println("Invalid platform: " + getPlatform());
        }
        return null;
    }

}
