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

}
