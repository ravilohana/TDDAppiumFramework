package com.qa.mobile.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.PrintWriter;
import java.io.StringWriter;

public class TestListener implements ITestListener {

    StringWriter stringWriter;
    PrintWriter printWriter;
    public void onTestFailure(ITestResult result) {
        if (result.getThrowable() != null) {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            result.getThrowable().printStackTrace(printWriter);
            System.out.println(stringWriter.toString());
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