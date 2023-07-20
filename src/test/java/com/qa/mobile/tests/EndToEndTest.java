package com.qa.mobile.tests;

import com.qa.mobile.base.BaseTest;
import com.qa.mobile.base.HeaderPage;
import com.qa.mobile.pages.*;
import com.qa.mobile.utils.UtilScrollSwipe;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class EndToEndTest extends BaseTest {

    LoginPage loginPage;
    JSONObject loginUserData;

    ProductsPage productsPage;
    HeaderPage headerPage;
    MenuOptionsPage menuOptionsPage;
    ProductDetailsPage productDetailsPage;
    InputStream loginUserDataIS;
    ArrayList<String> productsList;
    ArrayList<String> productsPrice;
    MyCartPage myCartPage;

    CheckoutShippingAddressPage chkShipAddressPage;

    CheckoutCardDetailsPage chkCardDetailsPage;

    CheckoutReviewPage chkOutReviewPage;

    CheckoutConfirmationPage chkOutCnfPage;
    SoftAssert softAssert;



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

    @BeforeMethod
    public void whichMethodExecuting(Method m) {
        loginPage = new LoginPage();
        headerPage = new HeaderPage();
        menuOptionsPage = new MenuOptionsPage();
        productsPage = new ProductsPage();
        productDetailsPage = new ProductDetailsPage();
        myCartPage = new MyCartPage();
        chkShipAddressPage = new CheckoutShippingAddressPage();
        chkCardDetailsPage = new CheckoutCardDetailsPage();
        chkOutReviewPage = new CheckoutReviewPage();
        chkOutCnfPage = new CheckoutConfirmationPage();
        productsList = new ArrayList<>();
        productsPrice = new ArrayList<>();
        softAssert = new SoftAssert();
        System.out.println("\n" + "************* Starting Test: " + m.getName() + "********" + "\n");
    }


    @Test
    public void loginToCheckoutTest(){

        productsPage = loginPage.doLogin(loginUserData.getJSONObject("validUsernamePassword").getString("username"),
                loginUserData.getJSONObject("validUsernamePassword").getString("password"));

        // First Product
        productsPage.tapOnProductSLBackpack();
        String actualDefaultQtyForProduct = productDetailsPage.getCounterTextQty();
        String expectedDefaultQtyForProduct = "1";
        softAssert.assertEquals(actualDefaultQtyForProduct,expectedDefaultQtyForProduct,"First product Qty Assert"+ productDetailsPage.getProductTitle() + "   ");
        productDetailsPage.tapOnCounterPlusBtn();
        productDetailsPage.tapOnCounterPlusBtn();
        productDetailsPage.tapOnCounterPlusBtn();
        productDetailsPage.tapOnCounterPlusBtn();
        productDetailsPage.tapOnCounterMinusBtn();
        productDetailsPage.tapOnCounterMinusBtn();
        String qtyForProductA = productDetailsPage.getCounterTextQty();
        System.out.println("Total Qty for first Product : " +  qtyForProductA);
        productDetailsPage.tapOnAddToCart();
        driver.navigate().back();

        // Second Product


        productsPage.tapOnProductSLFleeceJacket();
        actualDefaultQtyForProduct = productDetailsPage.getCounterTextQty();
        expectedDefaultQtyForProduct = "1";
        softAssert.assertEquals(actualDefaultQtyForProduct,expectedDefaultQtyForProduct,"Second product Qty Assert "+ productDetailsPage.getProductTitle() + "   ");
        productDetailsPage.tapOnCounterPlusBtn();
        productDetailsPage.tapOnCounterPlusBtn();
        productDetailsPage.tapOnCounterPlusBtn();
        productDetailsPage.tapOnCounterMinusBtn();
        qtyForProductA = productDetailsPage.getCounterTextQty();
        System.out.println("Total Qty for Second Product: " +  qtyForProductA);
        productDetailsPage.tapOnAddToCart();
        driver.navigate().back();

        // Third Product
        UtilScrollSwipe.scroll(driver, UtilScrollSwipe.ScrollDirection.DOWN,0.3);
        productsPage.tapOnProductSLTheThingsTShirt();
        actualDefaultQtyForProduct = productDetailsPage.getCounterTextQty();
        expectedDefaultQtyForProduct = "1";
        softAssert.assertEquals(actualDefaultQtyForProduct,expectedDefaultQtyForProduct,"Third product Qty Assert "+ productDetailsPage.getProductTitle() + "   ");
        productDetailsPage.tapOnCounterPlusBtn();
        productDetailsPage.tapOnCounterPlusBtn();
        productDetailsPage.tapOnCounterPlusBtn();
        productDetailsPage.tapOnCounterMinusBtn();
        qtyForProductA = productDetailsPage.getCounterTextQty();
        System.out.println("Total Qty for Second Product: " +  qtyForProductA);
        productDetailsPage.tapOnAddToCart();
        headerPage.tapOnCartBadgeBtn();
        String actualCartPageTitle = myCartPage.getMyCartPageTitle();
        String expectedCartPageTitle = "My Cart";

        // Selected Products Name
        ArrayList<String> selectedProductsNameList = myCartPage.getMyCartProductLabel();
        System.out.println("The Selected Products Names are: " + selectedProductsNameList);

        // Selected Products Price
        ArrayList<String> selectedProductsPriceList = myCartPage.getMyCartProductPrice();
        System.out.println("The Selected Products Price are: " + selectedProductsPriceList);

        // Total Items in Cart
        String totalItemsInCart =  myCartPage.getMyCartTotalItemsNo();
        System.out.println("Total Items in Cart:" + totalItemsInCart);

        // Total Items Price in Cart

        String totalPriceInCart = myCartPage.getMyCartTotalItemsPrice();
        System.out.println("Total Price in Cart:" + totalPriceInCart);

        myCartPage.tapOnProceedCheckoutBtn();

        chkShipAddressPage.enterFullName("The Great Ashoka");
        chkShipAddressPage.enterAddressLine1("Ashok Nagar");
        chkShipAddressPage.enterAddressLine2("Mauryan Empire");
        chkShipAddressPage.enterCity("Pataliputra");
        chkShipAddressPage.enterStateRegion("Bihar");
        chkShipAddressPage.enterZipCode("25157");
        chkShipAddressPage.enterCountry("INDIA");
        chkShipAddressPage.tapOnPaymentBtn();

        chkCardDetailsPage.enterCardFullName("Ashoka The Great");
        chkCardDetailsPage.enterCardNumber("7879 6565 4748 9856");
        chkCardDetailsPage.enterCardExpiryDate("04/27");
        chkCardDetailsPage.enterCardSecurityCode("369");
        chkCardDetailsPage.tapOnReviewOrderBtn();
        chkCardDetailsPage.tapOnReviewOrderBtn();


        // Checkout Review Page

        String actualReviewPageTitle = chkOutReviewPage.getCheckoutReviewTitle();
        String expectedReviewPageTitle = "Review your order";
        softAssert.assertEquals(actualReviewPageTitle,expectedReviewPageTitle);


        // Selected Products Name from Review Page
        selectedProductsNameList = myCartPage.getMyCartProductLabel();
        System.out.println("The Selected Products Names are: " + selectedProductsNameList);

        // Selected Products Price from Review Page
        selectedProductsPriceList = myCartPage.getMyCartProductPrice();
        System.out.println("The Selected Products Price are: " + selectedProductsPriceList);

        // Total Items in Cart from Review Page
        totalItemsInCart =  myCartPage.getMyCartTotalItemsNo();
        System.out.println("Total Items in Cart:" + totalItemsInCart);

        // Total Items Price in Cart from Review Page

        totalPriceInCart = myCartPage.getMyCartTotalItemsPrice();
        System.out.println("Total Price in Cart:" + totalPriceInCart);


        chkOutReviewPage.tapOnCheckoutPlaceOrderBtn();

        // checkout confirmation page

        String actualCnfPageTitle =  chkOutCnfPage.getCheckoutCnfTitle();
        String expectedCnfPageTitle = "Checkout Complete";
        softAssert.assertEquals(actualCnfPageTitle,expectedCnfPageTitle);

        String actualCnfPageThanksMsg =  chkOutCnfPage.getCheckoutCnfThanksMsg();
        String expectedCnfPageThanksMsg = "Thank you for your order";
        Assert.assertEquals(actualCnfPageThanksMsg,expectedCnfPageThanksMsg);

        String actualCnfPageDispatchMsg =  chkOutCnfPage.getCheckoutCnfDispatchMsh();
        String expectedCnfPageDispatchMsg = " Your order has been dispatched and will arrive as fast as the pony gallops!";
        softAssert.assertEquals(actualCnfPageDispatchMsg,expectedCnfPageDispatchMsg);


        productsPage = chkOutCnfPage.tapOnCheckoutContinueShopBtn();

        String actualProductsTitle = productsPage.getTitleProductsPage();
        String expectedProductsTitle = stringStringHashMap.get("products_title");
        System.out.println("Actual Products Title: " + actualProductsTitle + "\n" + "Expected Products Title: " + expectedProductsTitle);

        softAssert.assertEquals(actualProductsTitle, expectedProductsTitle);

        loginPage.clickOpenMenu();
        menuOptionsPage.tapOnLogoutOption();
        softAssert.assertAll();
















    }
}
