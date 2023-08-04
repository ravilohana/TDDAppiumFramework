package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuOptionsPage extends BaseTest {
    TestUtils testUtils = new TestUtils();
    @AndroidFindBy(accessibility = "menu item catalog")
    private WebElement catalogOptions;

    @AndroidFindBy(accessibility = "menu item webview")
    private WebElement webViewOptions;

    @AndroidFindBy(accessibility = "menu item qr code scanner")
    private WebElement qrScannerOptions;

    @AndroidFindBy(accessibility = "menu item geo location")
    private WebElement geoLocationOptions;

    @AndroidFindBy(accessibility = "menu item drawing")
    private WebElement drawingOptions;

    @AndroidFindBy(accessibility = "menu item about")
    private WebElement aboutOptions;

    @AndroidFindBy(accessibility = "menu item reset app")
    private WebElement resetAppOptions;
    @AndroidFindBy(accessibility = "menu item biometrics")
    private WebElement biometricsOptions;

    @AndroidFindBy(accessibility = "menu item log in")
    @FindBy(id = "menu item log in")
    private WebElement loginOptions;

    @AndroidFindBy(accessibility = "menu item log out")
    @FindBy(id = "menu item log out")
    private WebElement logoutOptions;

    @AndroidFindBy(id = "android:id/button1")
    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"Log Out\"]")
    private WebElement logOutBtn;

    @AndroidFindBy(accessibility = "menu item api calls")
    private WebElement apiCallsOptions;


    @AndroidFindBy(accessibility = "menu item sauce bot video")
    private WebElement sauceBotVideoOptions;

    @FindBy(id = "OK")
    private WebElement IOSOkBtn;


    // Tap on login
    public LoginPage tapOnLoginOption() {
        testUtils.tap(getDriver(), loginOptions);
        return new LoginPage();
    }

    // tap on logout
    public LoginPage tapOnLogoutOption() {
        testUtils.tap(getDriver(), logoutOptions);
        tapOnLogoutBtn();
        tapOnOKBtn();
        return new LoginPage();
    }

    public void tapOnLogoutBtn() {
        testUtils.tap(getDriver(), logOutBtn);

    }

    public void tapOnOKBtn() {
        if(getPlatform().equalsIgnoreCase("Android")){
            testUtils.tap(getDriver(), logOutBtn);
        }else {
            testUtils.tap(getDriver(),IOSOkBtn);
        }

    }


    public WebElement getCatalogOptions() {
        return catalogOptions;
    }

    public WebElement getWebViewOptions() {
        return webViewOptions;
    }

    public WebElement getQrScannerOptions() {
        return qrScannerOptions;
    }

    public WebElement getGeoLocationOptions() {
        return geoLocationOptions;
    }

    public WebElement getDrawingOptions() {
        return drawingOptions;
    }

    public WebElement getAboutOptions() {
        return aboutOptions;
    }

    public WebElement getResetAppOptions() {
        return resetAppOptions;
    }

    public WebElement getBiometricsOptions() {
        return biometricsOptions;
    }

    public WebElement getLoginOptions() {
        return loginOptions;
    }

    public WebElement getLogoutOptions() {
        return logoutOptions;
    }

    public WebElement getApiCallsOptions() {
        return apiCallsOptions;
    }

    public WebElement getSauceBotVideoOptions() {
        return sauceBotVideoOptions;
    }
}
