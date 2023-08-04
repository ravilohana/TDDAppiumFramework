package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseTest {


    TestUtils testUtils = new TestUtils();

    @AndroidFindBy(accessibility = "open menu")
    @FindBy(id = "tab bar option menu")
    private WebElement openMenu;

    @AndroidFindBy(accessibility = "menu item log in")
    @FindBy(id = "menu item log in")
    private WebElement loginLink;

    @AndroidFindBy(accessibility = "Username input field")
    @FindBy(id = "Username input field")
    private WebElement usernameTxtField;

    @AndroidFindBy(accessibility = "Password input field")
    @FindBy(id = "Password input field")
    private WebElement passwordTxtField;


    @AndroidFindBy(accessibility = "Login button")
    @FindBy(id = "Login button")
    private WebElement loginBtn;

    @AndroidFindBy(xpath = "//*[@content-desc=\"generic-error-message\"]/android.widget.TextView")
    @FindBy(xpath = "//XCUIElementTypeOther[@name= \"generic-error-message\"]//XCUIElementTypeStaticText")
    private WebElement errorMsg;

    public void enterUsername(String username){
        testUtils.sendKeys(getDriver(),usernameTxtField,username);
    }

    public void enterPassword(String password){
        testUtils.sendKeys(getDriver(),passwordTxtField,password);
    }

    public void clickOpenMenu(){
        testUtils.tap(getDriver(),openMenu);
    }

    public void clickOnLoginLink(){
        testUtils.tap(getDriver(),loginLink);
    }
    public ProductsPage clickLoginBtn(){
        testUtils.tap(getDriver(),loginBtn);
        return new ProductsPage();
    }

    public  String getErrorTxt(){
        if(getPlatform().equalsIgnoreCase("Android")){
            return testUtils.getAttribute(getDriver(),errorMsg,"text");
        }else if (getPlatform().equalsIgnoreCase("IOS")){
            return testUtils.getAttribute(getDriver(),errorMsg,"label");
        }
        else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

    public ProductsPage doLogin(String uName,String pwd){
        clickOpenMenu();
        clickOnLoginLink();
        enterUsername(uName);
        enterPassword(pwd);
        clickLoginBtn();
        return new ProductsPage();
    }

}
