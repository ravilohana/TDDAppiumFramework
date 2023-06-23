package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import static com.qa.mobile.utils.TestUtils.waitUntilElementStaleness;

public class ProductsPage extends BaseTest {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"container header\"]//android.widget.TextView")
    private WebElement productsPageTitle;

    public String getTitleProductsPage(){
        return  TestUtils.getAttribute(driver,productsPageTitle,"text");

    }
}
