package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.qa.mobile.utils.TestUtils.waitUntilElementStaleness;

public class ProductsPage extends BaseTest {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"container header\"]//android.widget.TextView")
    @FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Products\"]")
    private WebElement productsPageTitle;


    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"store item\"])[1]")
    @FindBy(xpath = "(//XCUIElementTypeOther[@name=\"store item\"])[1]")
    private WebElement productSLBackpack;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sauce Labs Backpack\"]")
    @FindBy(xpath = "//XCUIElementTypeStaticText[@label=\"Sauce Labs Backpack\"]")
    private WebElement productNameSLBackpack;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"$29.99\"]")
    @FindBy(xpath = "//XCUIElementTypeStaticText[@label=\"$29.99\"]")
    private WebElement productPriceSLBackpack;




    public WebElement getProductsPageTitle() {
        return productsPageTitle;
    }





    public String getTitleProductsPage() {
        if (platform.equalsIgnoreCase("Android")) {
            return TestUtils.getAttribute(driver, productsPageTitle, "text");
        } else if (platform.equalsIgnoreCase("IOS")) {
            return TestUtils.getAttribute(driver, productsPageTitle, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

    public String getProductName() {
        if (platform.equalsIgnoreCase("Android")) {
            return TestUtils.getAttribute(driver, productNameSLBackpack, "text");
        } else if (platform.equalsIgnoreCase("IOS")) {
            return TestUtils.getAttribute(driver, productNameSLBackpack, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;


    }


    public String getProductPrice() {
        if (platform.equalsIgnoreCase("Android")) {
            return TestUtils.getAttribute(driver, productPriceSLBackpack, "text");
        } else if (platform.equalsIgnoreCase("IOS")) {
            return TestUtils.getAttribute(driver, productPriceSLBackpack, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;


    }

    public ProductDetailsPage tapOnProduct(){
        TestUtils.tap(driver,productSLBackpack);
        return new ProductDetailsPage();
    }

}
