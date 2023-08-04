package com.qa.mobile.base;

import com.qa.mobile.utils.TestUtils;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    protected static ThreadLocal<Properties> props = new ThreadLocal<>();
    protected static ThreadLocal<HashMap<String, String>> stringStringHashMap = new ThreadLocal<>();

    protected static ThreadLocal<String> platform = new ThreadLocal<>();
    protected static ThreadLocal<String> deviceName = new ThreadLocal<>();

    protected static ThreadLocal<String> dateTime = new ThreadLocal<>();

   TestUtils testUtils;

    private static AppiumDriverLocalService server;


    public Properties getProps() {
        return props.get();
    }

    public void setProps(Properties prop) {
        props.set(prop);
    }

    public HashMap<String, String> getStrings() {
        return stringStringHashMap.get();
    }

    public void setStrings(HashMap<String, String> strings2) {
        stringStringHashMap.set(strings2);
    }

    public String getPlatform() {
        return platform.get();
    }

    public void setPlatform(String platform2) {
        platform.set(platform2);
    }

    public ThreadLocal<String> getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(ThreadLocal<String> deviceName) {
        BaseTest.deviceName = deviceName;
    }

    String androidAppPath = System.getProperty("user.dir") + File.separator + "src" + File.separator +
            "test" + File.separator + "resources" + File.separator + "app"
            + File.separator + "Android-MyDemoAppRN.1.3.0.build-244.apk";


    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }


    // Getter and Setter

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }


    public String getDate_Time() {
        return dateTime.get();
    }

    public void setDate_Time(String dt) {
        dateTime.set(dt);
    }


    @Parameters({"platformName", "deviceName", "udid", "mobileOSVersion"})
    @BeforeTest
    public void initializeDriver(String platformName, String deviceName, String udid, String mobileOSVersion) throws IOException {

        testUtils = new TestUtils();
        InputStream inputStream = null;
        InputStream stringInputStream = null;
        setDate_Time(testUtils.getDateTime());
        setPlatform(platformName);
        Properties props = new Properties();
        AppiumDriver driver;


        String strFile = "Logs" + File.separator + platformName + "_" + deviceName;
        File logFile = new File(strFile);
        if (!logFile.exists()) {
            logFile.mkdirs();
        }

        ThreadContext.put("ROUTINGKEY", strFile);
        testUtils.log().info("log path: " + strFile);


        try {
            props = new Properties();
            String propsFileName = "config.properties";
            String xmlStringFilePath = "strings/strings.xml";
            inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);
            props.load(inputStream);
            setProps(props);
            // strings xml file
            stringInputStream = getClass().getClassLoader().getResourceAsStream(xmlStringFilePath);
            setStrings(testUtils.parseStringXML(stringInputStream));
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
                    caps.setCapability("shouldTerminateApp", false);
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

            if (getPlatform().equalsIgnoreCase("Android")) {
                ((CanRecordScreen) driver).startRecordingScreen();
            }


            setDriver(driver);
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


    }


    @BeforeSuite
    public void startServer() {
        testUtils = new TestUtils();
        ThreadContext.put("ROUTINGKEY", "ServerLogs");
        server = getAppiumServerWindows();
        if(!server.isRunning()){
            server.start();
            server.clearOutPutStreams();
            testUtils.log().info("Appium Server Started...");
        }else {
            testUtils.log().info("Appium Server is already Running...");
        }
    }

    public boolean checkIfAppiumServerIsRunning(int port){
        boolean isAppiumServerRunning = false;
        ServerSocket socket;
        try{
            socket = new ServerSocket(port);
            socket.close();
        } catch (IOException e) {
            System.out.println("1");
            isAppiumServerRunning = true;
        }finally {
            socket = null;
        }
        return isAppiumServerRunning;
    }

    @AfterSuite
    public void stopServer() {
        server.stop();
        testUtils.log().info("Appium Server Stopped...");
    }

    // Default Server
    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    // for Windows
    public AppiumDriverLocalService getAppiumServerWindows() {
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingPort(4723)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withLogFile(new File("ServerLogs/win_server.log"))
        );
    }

    // for Mac. Update the paths as per your Mac setup
    public AppiumDriverLocalService getAppiumService() {
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("PATH", "/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home/bin:/Users/mac_user/Library/Android/sdk/tools:/Users/omprakashchavan/Library/Android/sdk/platform-tools:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Apple/usr/bin" + System.getenv("PATH"));
        environment.put("ANDROID_HOME", "/Users/mac_user/Library/Android/sdk");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingPort(4723)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//				.withArgument(() -> "--allow-insecure","chromedriver_autodownload")
                .withEnvironment(environment)
                .withLogFile(new File("ServerLogs/server.log")));
    }

    // get date time
    @BeforeMethod
    public void beforeMethod() {

        if (getPlatform().equalsIgnoreCase("Android")) {
            ((CanRecordScreen) getDriver()).startRecordingScreen();
        }

    }

    @AfterMethod
    public synchronized void tearDown(ITestResult result) throws IOException {
        if (getPlatform().equalsIgnoreCase("Android")) {
            String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();

            Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();

            String dir = "Videos" + File.separator + params.get("platformName") + "_"
                    + params.get("deviceName") + "_OS_V_" + params.get("mobileOSVersion") + File.separator + getDate_Time()
                    + File.separator + result.getTestClass().getRealClass().getSimpleName();

            File videosDir = new File(dir);
            synchronized (videosDir) {
                if (!videosDir.exists()) {
                    videosDir.mkdirs();
                }
            }

            FileOutputStream stream = null;
            try {
                stream = new FileOutputStream(videosDir + File.separator + result.getName() + ".mp4");
                stream.write(Base64.getDecoder().decode(media));
                stream.close();
                testUtils.log().info("video path: " + videosDir + File.separator + result.getName() + ".mp4");
            } catch (IOException e) {
                testUtils.log().error("error during video capture" + e.toString());

            } finally {
                if (stream != null) {
                    stream.close();
                }
            }

        }
    }

    @AfterTest
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }
}