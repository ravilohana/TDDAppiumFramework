package com.qa.mobile.tests;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.base.HeaderPage;
import com.qa.mobile.pages.LoginPage;
import com.qa.mobile.pages.MenuOptionsPage;
import com.qa.mobile.pages.ProductDetailsPage;
import com.qa.mobile.pages.ProductsPage;
import com.qa.mobile.utils.TestUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class ProductTest extends BaseTest {

    LoginPage loginPage;
    JSONObject loginUserData;

    ProductsPage productsPage;
    HeaderPage headerPage;
    MenuOptionsPage menuOptionsPage;
    ProductDetailsPage productDetailsPage;
    InputStream loginUserDataIS;


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
        headerPage = new HeaderPage();
        menuOptionsPage = new MenuOptionsPage();
        productsPage = new ProductsPage();
        productDetailsPage = new ProductDetailsPage();
        System.out.println("\n" + "************* Starting Test: " + m.getName() + "********" + "\n");
    }
    @Test
    public void validateProductOnProductPage(){
        SoftAssert softAssert = new SoftAssert();
        productsPage = loginPage.doLogin(loginUserData.getJSONObject("validUsernamePassword").getString("username"),
                loginUserData.getJSONObject("validUsernamePassword").getString("password"));

        System.out.println("Username: " + loginUserData.getJSONObject("validUsernamePassword").getString("username"));
        System.out.println("Password: " + loginUserData.getJSONObject("validUsernamePassword").getString("password"));

        String actualProductName = productsPage.getProductName();
        System.out.println("actual Product Name: " + actualProductName);
        String expectedProductName = stringStringHashMap.get("products_page_product_SL_Backpack");
        System.out.println("Expected Product Name: " + expectedProductName);
        softAssert.assertEquals(actualProductName,expectedProductName);

        String actualProductPrice = productsPage.getProductPrice();
        System.out.println("actual Product Price: " + actualProductPrice);
        String expectedProductPrice = stringStringHashMap.get("products_page_product_SL_Backpack_price");
        System.out.println("Expected Product Price: " + expectedProductPrice);
        softAssert.assertEquals(actualProductPrice,expectedProductPrice);
        menuOptionsPage = headerPage.tapOnMenu();
        loginPage = menuOptionsPage.tapOnLogoutOption();
        softAssert.assertAll();




    }

    @Test
    public void validateProductOnProductDetailsPage() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        productsPage = loginPage.doLogin(loginUserData.getJSONObject("validUsernamePassword").getString("username"),
                loginUserData.getJSONObject("validUsernamePassword").getString("password"));

        System.out.println("Username: " + loginUserData.getJSONObject("validUsernamePassword").getString("username"));
        System.out.println("Password: " + loginUserData.getJSONObject("validUsernamePassword").getString("password"));


        productDetailsPage = productsPage.tapOnProduct();
//        System.out.println("before wait");
//        TestUtils.waitUntilIsClickable(driver,productDetailsPage.getSLB_productTitle());
//        System.out.println("after wait");


        Thread.sleep(10000);
        String actualProductName = productDetailsPage.getProductTitle();
        System.out.println("actual Product Name: " + actualProductName);
        String expectedProductName = stringStringHashMap.get("products_details_page_product_SL_Backpack");
        System.out.println("Expected Product Name: " + expectedProductName);
        softAssert.assertEquals(actualProductName,expectedProductName);

        String actualProductPrice = productDetailsPage.getProductPrice();
        System.out.println("actual Product Price: " + actualProductPrice);
        String expectedProductPrice = stringStringHashMap.get("products_details_page_product_SL_Backpack_price");
        System.out.println("Expected  Product Price: " + expectedProductPrice);
        softAssert.assertEquals(actualProductPrice,expectedProductPrice);

        String actualProductDescription = productDetailsPage.getProductDescription();
        System.out.println("actual Product Description: " + actualProductDescription);

        String expectedProductDescription = stringStringHashMap.get("products_details_page_product_SL_Backpack_description");
        System.out.println("Expected Product Description: " + expectedProductDescription);
        softAssert.assertEquals(actualProductDescription,expectedProductDescription);

        headerPage.tapOnMenu();
        loginPage = menuOptionsPage.tapOnLogoutOption();
        softAssert.assertAll();




    }
}
