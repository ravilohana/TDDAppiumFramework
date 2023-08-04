package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;



public class ProductsPage extends BaseTest {
    TestUtils testUtils = new TestUtils();
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


    //Sauce Labs Fleece Jacket
    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"store item\"])[4]")
    private WebElement productSLFleeceJacket;


    //Test.allTheThings() T-Shirt

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"store item\"])[6]")
    private WebElement productSLTheThingsTShirt;

    @AndroidFindBy(accessibility = "nameAsc")
    private WebElement productNameSortByAscending;

    @AndroidFindBy(accessibility = "nameDesc")
    private WebElement productNameSortByDescending;

    @AndroidFindBy(accessibility = "priceAsc")
    private WebElement productPriceSortByAscending;

    @AndroidFindBy(accessibility = "priceDesc")
    private WebElement productPriceSortByDescending;
    
//    private List<WebElement> productsNameList;
//
//    public List<WebElement> getProductsNameList() {
//        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"store item text\"]"));
//        return productsNameList;
//    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"store item text\"]")
    public List<WebElement> productsName;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"store item price\"]")
    public List<WebElement> productsPrice;


    public WebElement getProductsPageTitle() {
        return productsPageTitle;
    }

    public WebElement getProductNameSortByAscending() {
        return productNameSortByAscending;
    }

    public WebElement getProductNameSortByDescending() {
        return productNameSortByDescending;
    }

    public WebElement getProductPriceSortByAscending() {
        return productPriceSortByAscending;
    }

    public WebElement getProductPriceSortByDescending() {
        return productPriceSortByDescending;
    }

    public String getTitleProductsPage() {
        if (getPlatform().equalsIgnoreCase("Android")) {
            return testUtils.getAttribute(getDriver(), productsPageTitle, "text");
        } else if (getPlatform().equalsIgnoreCase("IOS")) {
            return testUtils.getAttribute(getDriver(), productsPageTitle, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

    public String getProductName() {
        if (getPlatform().equalsIgnoreCase("Android")) {
            return testUtils.getAttribute(getDriver(), productNameSLBackpack, "text");
        } else if (getPlatform().equalsIgnoreCase("IOS")) {
            return testUtils.getAttribute(getDriver(), productNameSLBackpack, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;


    }


    public String getProductPrice() {
        if (getPlatform().equalsIgnoreCase("Android")) {
            return testUtils.getAttribute(getDriver(), productPriceSLBackpack, "text");
        } else if (getPlatform().equalsIgnoreCase("IOS")) {
            return testUtils.getAttribute(getDriver(), productPriceSLBackpack, "label");
        } else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;


    }

    public ProductDetailsPage tapOnProduct(){
        testUtils.tap(getDriver(),productSLBackpack);
        return new ProductDetailsPage();
    }

    public ProductDetailsPage tapOnProductSLBackpack(){
        testUtils.tap(getDriver(),productSLBackpack);
        return new ProductDetailsPage();
    }

    public ProductDetailsPage tapOnProductSLFleeceJacket(){
        testUtils.tap(getDriver(),productSLFleeceJacket);
        return new ProductDetailsPage();
    }

    public ProductDetailsPage tapOnProductSLTheThingsTShirt(){
        testUtils.tap(getDriver(),productSLTheThingsTShirt);
        return new ProductDetailsPage();
    }







}
