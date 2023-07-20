package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MyCartPage extends BaseTest {

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
        if (platform.equalsIgnoreCase("Android")) {
            return TestUtils.getAttribute(driver, myCartPageTitle, "text");
        } else if (platform.equalsIgnoreCase("IOS")) {
            return TestUtils.getAttribute(driver, myCartPageTitle, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

    // get product label
    public ArrayList<String> getMyCartProductLabel() {
        ArrayList<String> productLabelList = new ArrayList<>();
        if (platform.equalsIgnoreCase("Android")) {
            for (WebElement element : myCartProductLabel) {
                String productLabel = TestUtils.getAttribute(driver, element, "text");
                productLabelList.add(productLabel);
            }
            return productLabelList;
        }else if (platform.equalsIgnoreCase("IOS")) {
            for (WebElement element : myCartProductLabel) {
                String productLabel = TestUtils.getAttribute(driver, element, "text");
                productLabelList.add(productLabel);
            }
            return productLabelList;
        }else {
            System.out.println("Invalid platform: " + platform);

        }
        return null;
    }


    // get product price
    public ArrayList<String> getMyCartProductPrice() {
        ArrayList<String> productLabelList = new ArrayList<>();
        if (platform.equalsIgnoreCase("Android")) {
            for (WebElement element : myCartProductPrice) {
                String productLabel = TestUtils.getAttribute(driver, element, "text");
                productLabelList.add(productLabel);
            }
            return productLabelList;
        }else if (platform.equalsIgnoreCase("IOS")) {
            for (WebElement element : myCartProductPrice) {
                String productLabel = TestUtils.getAttribute(driver, element, "text");
                productLabelList.add(productLabel);
            }
            return productLabelList;
        }else {
            System.out.println("Invalid platform: " + platform);

        }
        return null;
    }

    // click on Proceed to Checkout button

    public CheckoutShippingAddressPage tapOnProceedCheckoutBtn(){
        TestUtils.tap(driver,myCartProceedCheckoutBtn);
        return new CheckoutShippingAddressPage();
    }

    // get the total items numbers
    public String getMyCartTotalItemsNo() {
        if (platform.equalsIgnoreCase("Android")) {
            return TestUtils.getAttribute(driver, myCartTotalItemsNo, "text");
        } else if (platform.equalsIgnoreCase("IOS")) {
            return TestUtils.getAttribute(driver, myCartTotalItemsNo, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

    public String getMyCartTotalItemsPrice() {
        if (platform.equalsIgnoreCase("Android")) {
            return TestUtils.getAttribute(driver, myCartTotalItemsPrice, "text");
        } else if (platform.equalsIgnoreCase("IOS")) {
            return TestUtils.getAttribute(driver, myCartTotalItemsPrice, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

}
