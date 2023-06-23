package com.qa.mobile.pages;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseTest {
    @AndroidFindBy(accessibility = "open menu")
    private WebElement openMenu;

    @AndroidFindBy(accessibility = "menu item log in")
    private WebElement loginLink;

    @AndroidFindBy(accessibility = "Username input field")
    private WebElement usernameTxtField;

    @AndroidFindBy(accessibility = "Password input field")
    private WebElement passwordTxtField;


    @AndroidFindBy(accessibility = "Login button")
    private WebElement loginBtn;

    @AndroidFindBy(xpath = "//*[@content-desc=\"generic-error-message\"]/android.widget.TextView")
    private WebElement errorMsg;

    public void enterUsername(String username){
        TestUtils.sendKeys(driver,usernameTxtField,username);
    }

    public void enterPassword(String password){
        TestUtils.sendKeys(driver,passwordTxtField,password);
    }

    public void clickOpenMenu(){
        TestUtils.click(driver,openMenu);
    }

    public void clickOnLoginLink(){
        TestUtils.click(driver,loginLink);
    }
    public ProductsPage clickLoginBtn(){
        TestUtils.click(driver,loginBtn);
        return new ProductsPage();
    }

    public  String getErrorTxt(){
        return TestUtils.getAttribute(driver,errorMsg,"text");
    }

}
