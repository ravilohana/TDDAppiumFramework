package com.qa.mobile.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected static AppiumDriver driver;
    protected static Properties props;
    InputStream inputStream;

    String androidAppPath = System.getProperty("user.dir") + File.separator + "src" +File.separator +
            "test" + File.separator + "resources" + File.separator + "app"
            + File.separator + "Android-MyDemoAppRN.1.3.0.build-244.apk";

    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @Parameters({"platformName","deviceName","udid","mobileOSVersion"})
    @BeforeTest
    public void initializeDriver(String platformName,String deviceName,String udid,String mobileOSVersion) {

        try {
            props = new Properties();
            String propsFileName = "config.properties";
//            String androidAppLocation = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));

           // System.out.println(androidAppLocation);
            inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);
            props.load(inputStream);
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,mobileOSVersion);
          //  caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
            URL url = new URL(props.getProperty("appiumURL"));
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
            caps.setCapability(MobileCapabilityType.UDID, udid);
            caps.setCapability(MobileCapabilityType.APP, androidAppPath);
         //   caps.setCapability(MobileCapabilityType.NO_RESET, true);
         //   caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
          //  caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
            driver = new AndroidDriver(url, caps);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        } catch (Exception e) {
            e.printStackTrace();
         //   throw new RuntimeException("Driver not initialize!!");
        }
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}