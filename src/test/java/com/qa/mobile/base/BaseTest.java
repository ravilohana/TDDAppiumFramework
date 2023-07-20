package com.qa.mobile.base;

import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    protected static AppiumDriver driver;
    protected static Properties props;
    protected static HashMap<String, String> stringStringHashMap = new HashMap<>();
    InputStream inputStream;
    InputStream stringInputStream;
    protected static String platform;

    protected static String dateTime;

    String androidAppPath = System.getProperty("user.dir") + File.separator + "src" + File.separator +
            "test" + File.separator + "resources" + File.separator + "app"
            + File.separator + "Android-MyDemoAppRN.1.3.0.build-244.apk";

    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Parameters({"platformName", "deviceName", "udid", "mobileOSVersion"})
    @BeforeMethod
    public void initializeDriver(String platformName, String deviceName, String udid, String mobileOSVersion) throws IOException {
        dateTime = TestUtils.getDateTime();
        platform = platformName;
        try {
            props = new Properties();
            String propsFileName = "config.properties";
            String xmlStringFilePath = "strings/strings.xml";
            inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);
            props.load(inputStream);
            // strings xml file
            stringInputStream = getClass().getClassLoader().getResourceAsStream(xmlStringFilePath);
            stringStringHashMap = TestUtils.parseStringXML(stringInputStream);
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, mobileOSVersion);
            //  caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            switch (platformName) {
                case "Android":
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
                    caps.setCapability(MobileCapabilityType.UDID, udid);
                    caps.setCapability(MobileCapabilityType.APP, androidAppPath);
                    caps.setCapability("shouldTerminateApp",false);
                    //   caps.setCapability(MobileCapabilityType.NO_RESET, true);
                    //   caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
                    //  caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
                    URL url = new URL(props.getProperty("appiumURL"));
                    driver = new AndroidDriver(url, caps);
                    break;

                case "IOS":
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("IOSAutomationName"));
                    caps.setCapability("browserstack.user", props.getProperty("BROWSERSTACK_USERNAME"));
                    caps.setCapability("browserstack.key", props.getProperty("BROWSERSTACK_ACCESS_KEY"));
                    caps.setCapability("app", props.getProperty("browserstack_app_url"));
//                    caps.setCapability("appPackage", props.getProperty("IOSAppPackage"));
//                    caps.setCapability("appActivity", props.getProperty("IOSAppActivity"));
                    URL IOSBrowserstackURL = new URL("https://" + props.getProperty("BROWSERSTACK_USERNAME") + ":" + props.getProperty("BROWSERSTACK_ACCESS_KEY") + "@hub.browserstack.com/wd/hub");
                    driver = new IOSDriver(IOSBrowserstackURL, caps);
                    break;

                default:
                    throw new Exception("In-valid platform name: " + platformName);

            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (Exception e) {
            e.printStackTrace();
            //   throw new RuntimeException("Driver not initialize!!");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (stringInputStream != null) {
                stringInputStream.close();
            }
        }

        ((CanRecordScreen) driver).startRecordingScreen();
    }


    public AppiumDriver getDriver(){
        return driver;
    }

    public void closeApp() throws Exception {
        switch (platform){
            case "Android":
                ((InteractsWithApps) driver).terminateApp(props.getProperty("androidAppPackage"));
                break;
            case "IOS":
                ((InteractsWithApps) driver).terminateApp(props.getProperty("IOSAppPackage"));
            default:
                throw new Exception("App is not closed for appPackage: " + props.getProperty("androidAppPackage") + " Platform: " + platform);

        }
    }


    public void launchApp() throws Exception {
        switch (platform){
            case "Android":
                ((InteractsWithApps) driver).activateApp(props.getProperty("androidAppPackage"));
                System.out.println(props.getProperty("androidAppPackage"));
                System.out.println(platform);
                break;
            case "IOS":
                ((InteractsWithApps) driver).activateApp(props.getProperty("IOSAppPackage"));
                System.out.println(props.getProperty("androidAppPackage"));
                System.out.println(platform);
            default:
                throw new Exception("App is not closed for appPackage: " + props.getProperty("androidAppPackage") + " Platform: " + platform);

        }
    }

    public AppiumDriver terminateApp() {
        String appID = null;
        if (driver != null) {
            try {
                if (driver instanceof AndroidDriver) {
                    appID = (String) driver.getCapabilities().getCapability(props.getProperty("androidAppPackage"));
                } else if (driver instanceof IOSDriver) {
                    appID = String.valueOf(driver.getCapabilities().getCapability(props.getProperty("IOSAppPackage")));
                } else
                    Assert.fail("unknown driver type");
                if (appID != null)
                    ((InteractsWithApps) driver).terminateApp(appID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    // get date time

    public String getDate_Time(){
        return dateTime;
    }

    @AfterMethod

    public void tearDown(ITestResult result) {
        String  media = ((CanRecordScreen)driver).stopRecordingScreen();

        Map<String,String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();

        String dir = "Videos" + File.separator + params.get("platformName") + "_"
                + params.get("deviceName") + "_OS_V_" + params.get("mobileOSVersion") + File.separator + getDate_Time()
                + File.separator + result.getTestClass().getRealClass().getSimpleName();

        File videosDir = new File(dir);

        if(!videosDir.exists()){
            videosDir.mkdirs();
        }

        try {
            FileOutputStream stream = new FileOutputStream(videosDir + File.separator + result.getName() + ".mp4");
            stream.write(Base64.getDecoder().decode(media));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
    }
}