package com.qa.mobile.tests;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.pages.LoginPage;
import com.qa.mobile.pages.MenuOptionsPage;
import com.qa.mobile.pages.ProductsPage;
import com.qa.mobile.utils.TestUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Objects;

public class LoginTests extends BaseTest {


    LoginPage loginPage;
    ProductsPage productsPage;
    InputStream loginUserDataIS;
    JSONObject loginUserData;
   // MenuOptionsPage menuOptionsPage;
    //JSONTokener jsonTokener;
    @BeforeClass
    public void getData_LoginUser() throws Exception {
        try {
            String loginUserDataFilePath = "test_data/login_user.json";
            System.out.println(loginUserDataFilePath);
            loginUserDataIS = getClass().getClassLoader().getResourceAsStream(loginUserDataFilePath);
            JSONTokener jsonTokener = new JSONTokener(loginUserDataIS);
            loginUserData = new JSONObject(jsonTokener);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (loginUserDataIS != null) {
                loginUserDataIS.close();
            }
        }



    }

//    @AfterClass
//    public void close_launch_app() throws Exception {
//
//        closeApp();
//        System.out.println("CLOSED APP");
//        launchApp();
//        System.out.println("LAUNCH APP");
//
//    }

    @BeforeMethod
    public void whichMethodExecuting(Method m) {
        loginPage = new LoginPage();
        System.out.println("\n" + "************* Starting Test: " + m.getName() + "********" + "\n");
    }

    @Test(priority = 1)
    public void invalidUsername() {
        loginPage.clickOpenMenu();
        loginPage.clickOnLoginLink();
        loginPage.enterUsername(loginUserData.getJSONObject("invalidUsername").getString("username"));
        loginPage.enterPassword(loginUserData.getJSONObject("invalidUsername").getString("password"));
        loginPage.clickLoginBtn();

        String actualErrorMsg = loginPage.getErrorTxt();
        String expectedErrorMes = stringStringHashMap.get("invalid_credentials");
        System.out.println("Actual Error Msg: " + actualErrorMsg + "\n" + "Expected Error Msg: " + expectedErrorMes);
        Assert.assertEquals(actualErrorMsg, expectedErrorMes);
    }

    @Test(priority = 2)
    public void invalidPassword() {
        loginPage.clickOpenMenu();
        loginPage.clickOnLoginLink();
        loginPage.enterUsername(loginUserData.getJSONObject("invalidPassword").getString("username"));
        loginPage.enterPassword(loginUserData.getJSONObject("invalidPassword").getString("password"));
        loginPage.clickLoginBtn();

        String actualErrorMsg = loginPage.getErrorTxt();
        String expectedErrorMes = stringStringHashMap.get("invalid_credentials");
        System.out.println("Actual Error Msg: " + actualErrorMsg + "\n" + "Expected Error Msg: " + expectedErrorMes);
        Assert.assertEquals(actualErrorMsg, expectedErrorMes);
    }

    @Test(priority = 3)
    public void invalidUsernamePassword() {
        loginPage.clickOpenMenu();
        loginPage.clickOnLoginLink();
        loginPage.enterUsername(loginUserData.getJSONObject("invalidUsernamePassword").getString("username"));
        loginPage.enterPassword(loginUserData.getJSONObject("invalidUsernamePassword").getString("password"));
        loginPage.clickLoginBtn();

        String actualErrorMsg = loginPage.getErrorTxt();
        String expectedErrorMes = stringStringHashMap.get("invalid_credentials");
        System.out.println("Actual Error Msg: " + actualErrorMsg + "\n" + "Expected Error Msg: " + expectedErrorMes);
        Assert.assertEquals(actualErrorMsg, expectedErrorMes);
    }

    @Test(priority = 4)
    public void lockedAccount() {
        loginPage.clickOpenMenu();
        loginPage.clickOnLoginLink();
        loginPage.enterUsername(loginUserData.getJSONObject("lockedAccount").getString("username"));
        loginPage.enterPassword(loginUserData.getJSONObject("lockedAccount").getString("password"));
        loginPage.clickLoginBtn();

        String actualErrorMsg = loginPage.getErrorTxt();
        String expectedErrorMes = stringStringHashMap.get("locked_account");
        System.out.println("Actual Error Msg: " + actualErrorMsg + "\n" + "Expected Error Msg: " + expectedErrorMes);
        Assert.assertEquals(actualErrorMsg, expectedErrorMes);
    }


    @Test(priority = 5)
    public void validUsernamePassword() throws InterruptedException {
        loginPage.clickOpenMenu();
        loginPage.clickOnLoginLink();
        loginPage.enterUsername(loginUserData.getJSONObject("validUsernamePassword").getString("username"));
        loginPage.enterPassword(loginUserData.getJSONObject("validUsernamePassword").getString("password"));
        productsPage = loginPage.clickLoginBtn();

       // TestUtils.waitUntilIsClickable(driver, productsPage.getProductsPageTitle());
        Thread.sleep(10000);
        String actualProductsTitle = productsPage.getTitleProductsPage();
        String expectedProductsTitle = stringStringHashMap.get("products_title");
        System.out.println("Actual Products Title: " + actualProductsTitle + "\n" + "Expected Products Title: " + expectedProductsTitle);

        Assert.assertEquals(actualProductsTitle, expectedProductsTitle);
        loginPage.clickOpenMenu();
        new MenuOptionsPage().tapOnLogoutOption();


    }

}
