package com.qa.mobile.tests;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.pages.LoginPage;
import com.qa.mobile.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {


    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeMethod
    public void whichMethodExecuting(Method m){
        loginPage = new LoginPage();
        System.out.println("\n" + "************* Starting Test: " + m.getName() + "********" + "\n");
    }

    @Test
    public void invalidUsername() {
        loginPage.clickOpenMenu();
        loginPage.clickOnLoginLink();
        loginPage.enterUsername("bob@example1.com");
        loginPage.enterPassword("10203040");
        loginPage.clickLoginBtn();

        String actualErrorMsg = loginPage.getErrorTxt();
        String expectedErrorMes = "Provided credentials do not match any user in this service.";
        System.out.println("Actual Error Msg: " + actualErrorMsg + "\n" + "Expected Error Msg: " + expectedErrorMes);
        Assert.assertEquals(actualErrorMsg, expectedErrorMes);
    }

    @Test
    public void invalidPassword() {
        loginPage.clickOpenMenu();
        loginPage.clickOnLoginLink();
        loginPage.enterUsername("bob@example.com");
        loginPage.enterPassword("102030404654654");
        loginPage.clickLoginBtn();

        String actualErrorMsg = loginPage.getErrorTxt();
        String expectedErrorMes = "Provided credentials do not match any user in this service.";
        System.out.println("Actual Error Msg: " + actualErrorMsg + "\n" + "Expected Error Msg: " + expectedErrorMes);
        Assert.assertEquals(actualErrorMsg, expectedErrorMes);
    }

    @Test
    public void invalidUsernamePassword() {
        loginPage.clickOpenMenu();
        loginPage.clickOnLoginLink();
        loginPage.enterUsername("bob@example1.com");
        loginPage.enterPassword("10203040546465");
        loginPage.clickLoginBtn();

        String actualErrorMsg = loginPage.getErrorTxt();
        String expectedErrorMes = "Provided credentials do not match any user in this service.";
        System.out.println("Actual Error Msg: " + actualErrorMsg + "\n" + "Expected Error Msg: " + expectedErrorMes);
        Assert.assertEquals(actualErrorMsg,expectedErrorMes);
    }

    @Test
    public void lockedAccount() {
        loginPage.clickOpenMenu();
        loginPage.clickOnLoginLink();
        loginPage.enterUsername("alice@example.com");
        loginPage.enterPassword("10203040");
        loginPage.clickLoginBtn();

        String actualErrorMsg = loginPage.getErrorTxt();
        String expectedErrorMes = "Sorry, this user has been locked out.";
        System.out.println("Actual Error Msg: " + actualErrorMsg + "\n" + "Expected Error Msg: " + expectedErrorMes);
        Assert.assertEquals(actualErrorMsg,expectedErrorMes);
    }


    @Test
    public void validUsernamePassword() throws InterruptedException {
        loginPage.clickOpenMenu();
        loginPage.clickOnLoginLink();
        loginPage.enterUsername("bob@example.com");
        loginPage.enterPassword("10203040");
        productsPage = loginPage.clickLoginBtn();

        Thread.sleep(10000);
        String actualProductsTitle = productsPage.getTitleProductsPage();
        String expectedProductsTitle = "Products";
        System.out.println("Actual Products Title: " + actualProductsTitle + "\n" + "Expected Products Title: " + expectedProductsTitle);

        Assert.assertEquals(actualProductsTitle, expectedProductsTitle);

    }

}
