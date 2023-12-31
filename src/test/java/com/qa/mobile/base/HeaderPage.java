package com.qa.mobile.base;

import com.qa.mobile.pages.MenuOptionsPage;
import com.qa.mobile.pages.MyCartPage;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends BaseTest{

    TestUtils testUtils = new TestUtils();
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"open menu\"]//android.widget.ImageView")
    @FindBy(id = "tab bar option menu")
    private WebElement menuBtn;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"longpress reset app\"]//android.widget.ImageView")
    private WebElement myDemoAppLogo;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"sort button\"]/android.widget.ImageView")
    @FindBy(id = "sort button")
    private WebElement headerSortBtn;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.ImageView")
    @FindBy(id = "tab bar option cart")
    private WebElement cartBadge;

    public WebElement getHeaderSortBtn() {
        return headerSortBtn;
    }

    public MenuOptionsPage tapOnMenu(){

        testUtils.tap(getDriver(),menuBtn);
        return new MenuOptionsPage();
    }

    public void myDemoAppLogoDisplayed(){
        TestUtils.webElementIsDisplayed(getDriver(),myDemoAppLogo);
    }

    public void headerSortBtnDisplayed(){
        TestUtils.webElementIsDisplayed(getDriver(),headerSortBtn);
    }

    public void cartBadgeDisplayed(){
        TestUtils.webElementIsDisplayed(getDriver(),cartBadge);
    }


    public MyCartPage tapOnCartBadgeBtn(){
        testUtils.tap(getDriver(),cartBadge);
        return new MyCartPage();
    }


}
