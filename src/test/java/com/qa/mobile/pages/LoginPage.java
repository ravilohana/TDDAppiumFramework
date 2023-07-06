package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseTest {




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
        TestUtils.sendKeys(driver,usernameTxtField,username);
    }

    public void enterPassword(String password){
        TestUtils.sendKeys(driver,passwordTxtField,password);
    }

    public void clickOpenMenu(){
        TestUtils.tap(driver,openMenu);
    }

    public void clickOnLoginLink(){
        TestUtils.tap(driver,loginLink);
    }
    public ProductsPage clickLoginBtn(){
        TestUtils.tap(driver,loginBtn);
        return new ProductsPage();
    }

    public  String getErrorTxt(){
        if(platform.equalsIgnoreCase("Android")){
            return TestUtils.getAttribute(driver,errorMsg,"text");
        }else if (platform.equalsIgnoreCase("IOS")){
            return TestUtils.getAttribute(driver,errorMsg,"label");
        }
        else {
            System.out.println("Invalid platform: " + platform);
        }
        return null;
    }

}
