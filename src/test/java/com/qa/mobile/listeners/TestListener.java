package com.qa.mobile.listeners;

import com.qa.mobile.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

public class TestListener implements ITestListener {

    StringWriter stringWriter;
    PrintWriter printWriter;
    BaseTest baseTest;
    public void onTestFailure(ITestResult result) {
        if (result.getThrowable() != null) {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            result.getThrowable().printStackTrace(printWriter);
            System.out.println(stringWriter.toString());
        }
        baseTest = new BaseTest();
        File file = ((TakesScreenshot)baseTest.getDriver()).getScreenshotAs(OutputType.FILE);


        Map<String,String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();

        String imagePath = "Screenshots" + File.separator + params.get("platformName") + "_"
                + params.get("deviceName") + "_OS_V_" + params.get("mobileOSVersion") + File.separator + baseTest.getDate_Time()
                + File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator
                + result.getName() + ".png";

//        String imagePath = "Screenshots" + "/" + params.get("platformName") + "_"
//                + params.get("deviceName") + "_OS_V_" + params.get("mobileOSVersion") + "/" + baseTest.getDate_Time()
//                + "/" + result.getTestClass().getRealClass().getSimpleName() + "/"
//                + result.getName() + ".png";

        String completeImagePath = System.getProperty("user.dir") + "/" + imagePath;
        System.out.println(completeImagePath);

        try {
            FileUtils.copyFile(file,new File(imagePath));
            Reporter.log("Screenshots of Failed Test Case");
            Reporter.log("<a href='"+ completeImagePath +"'><img src ='"+ completeImagePath +"' height='100' width='100'/> </a>");
            System.out.println("<a href='"+ completeImagePath +"'><img src ='"+ completeImagePath +"' height='100' width='100'/> </a>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTestSuccess(ITestResult result) {
        if (result.getThrowable() != null) {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            result.getThrowable().printStackTrace(printWriter);
            System.out.println(stringWriter.toString());
        }
    }

}