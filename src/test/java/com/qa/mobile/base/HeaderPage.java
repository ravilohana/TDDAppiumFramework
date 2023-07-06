package com.qa.mobile.base;

import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HeaderPage extends BaseTest{

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"open menu\"]//android.widget.ImageView")
    private WebElement hamburgerMenu;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"longpress reset app\"]//android.widget.ImageView")
    private WebElement myDemoAppLogo;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"sort button\"]/android.widget.ImageView")
    private WebElement headerSortBtn;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.ImageView")
    private WebElement cartBadge;

    public void tapOnHamburgerMenu(){
        TestUtils.tap(driver,hamburgerMenu);
    }

    public void myDemoAppLogoDisplayed(){
        TestUtils.webElementIsDisplayed(driver,myDemoAppLogo);
    }

    public void headerSortBtnDisplayed(){
        TestUtils.webElementIsDisplayed(driver,headerSortBtn);
    }

    public void cartBadgeDisplayed(){
        TestUtils.webElementIsDisplayed(driver,cartBadge);
    }


}
