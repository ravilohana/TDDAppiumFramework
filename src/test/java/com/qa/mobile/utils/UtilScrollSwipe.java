package com.qa.mobile.utils;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

public class UtilScrollSwipe {

    private static Dimension windowSize;
    private static final Duration SCROLL_DUR = Duration.ofMillis(4000);
    private static final double SCROLL_RATIO = 0.8;
    private static int ANDROID_SCROLL_DIVISOR = 3;

    /*public static void scrollDown(){
        Dimension dimension = AppDriver.getDriver().manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.5);
        int scrollEnd = (int) (dimension.getHeight() * 0.2);

        new TouchAction((PerformsTouchActions) AppDriver.getDriver())
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release().perform();
    }*/

    public static void scrollNClick(AppiumDriver driver , By listItems, String Text){
        boolean flag = false;

        while(true){
            for(WebElement el: driver.findElements(listItems)){
                if(el.getAttribute("text").equals(Text)){
                    el.click();
                    flag=true;
                    break;
                }
            }
            if(flag)
                break;
            else
                //scrollDown();
                scroll(driver ,ScrollDirection.DOWN, SCROLL_RATIO);
        }
    }

    public static void scrollNClick(AppiumDriver driver , WebElement el){
        int retry = 0;
        while(retry <= 5){
            try{
                el.click();
                break;
            }catch (NoSuchElementException e){
                //scrollDown();
                scroll(driver, ScrollDirection.DOWN, SCROLL_RATIO);
                retry++;
            }
        }
    }

    public static void scrollIntoView(AppiumDriver driver,String Text){
        //https://developer.android.com/reference/androidx/test/uiautomator/UiSelector


        String mySelector = "new UiSelector().text(\"" + Text + "\").instance(0)";
        String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + mySelector + ");";
        driver.findElement(AppiumBy.androidUIAutomator(command));

        /*((AndroidDriver<MobileElement>) AppDriver.getDriver()).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + Text + "\").instance(0))").click();
                */
    }

    public static void scrollTo(AppiumDriver driver,String Text){
        //https://appium.io/docs/en/writing-running-appium/ios/ios-xctest-mobile-gestures/

        if(driver instanceof AndroidDriver){
            scrollIntoView(driver , Text);
        }else
        if(driver instanceof IOSDriver){
            final HashMap<String, String> scrollObject = new HashMap<String, String>();
            scrollObject.put("predicateString", "value == '" + Text + "'");
            scrollObject.put("toVisible", "true");
            ((IOSDriver) driver).executeScript("mobile: scroll", scrollObject);
        }
    }


    public enum ScrollDirection {
        UP, DOWN, LEFT, RIGHT
    }

    private static Dimension getWindowSize(AppiumDriver driver) {
        if (windowSize == null) {
            windowSize = driver.manage().window().getSize();
        }
        return windowSize;
    }

    public static void scroll(AppiumDriver driver , ScrollDirection dir, double scrollRatio) {
        if (scrollRatio < 0 || scrollRatio > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }
        Dimension size = getWindowSize(driver);
        Point midPoint = new Point((int)(size.width * 0.5), (int)(size.height * 0.5));
        int top = midPoint.y - (int)((size.height * scrollRatio) * 0.5);
        int bottom = midPoint.y + (int)((size.height * scrollRatio) * 0.5);
        int left = midPoint.x - (int)((size.width * scrollRatio) * 0.5);
        int right = midPoint.x + (int)((size.width * scrollRatio) * 0.5);
        if (dir == ScrollDirection.UP) {
            swipe(driver , new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
        } else if (dir == ScrollDirection.DOWN) {
            swipe(driver , new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
        } else if (dir == ScrollDirection.LEFT) {
            swipe(driver , new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
        } else {
            swipe(driver ,new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
        }
    }

    public static void swipe(AppiumDriver driver ,Point start, Point end, Duration duration) {
        boolean isAndroid = driver instanceof AndroidDriver;

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ofSeconds(3), PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        if (isAndroid) {
            duration = duration.dividedBy(ANDROID_SCROLL_DIVISOR);
        } else {
            swipe.addAction(new Pause(input, duration));
            duration = Duration.ZERO;
        }
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        ((AppiumDriver) driver).perform(ImmutableList.of(swipe));
    }



    public static void drawing(AppiumDriver driver,WebElement drawingPanel) throws InterruptedException {
        Point location =  drawingPanel.getLocation();
        Dimension size = drawingPanel.getSize();
        // Vertical line
        Point pSource = new Point(location.x + size.getWidth()/2, location.y + 10);
        System.out.println(location.x + "----" + size.getWidth() + "-----"+ size.getWidth()/2);
        Point pdest = new Point(location.x + size.getWidth()/2, location.y + size.getHeight() -10);
        Thread.sleep(1000);
        System.out.println(location.x + "----" + size.getWidth() + "-----"+ size.getWidth()/2);
        swipe(driver,pSource,pdest,SCROLL_DUR);

        Thread.sleep(5000);

        // Horizontal line
        pSource = new Point( location.x + 40,location.y + size.getHeight()/2);
        Thread.sleep(1000);
        System.out.println(location.x + "----" + location.y + "-----"+ size.getHeight()/2 + "------" + size.getHeight());
        pdest = new Point(location.x + size.getWidth(), location.y + size.getHeight()/2);
        System.out.println(location.x + "----" + location.y + "-----"+ size.getHeight()/2 + "------" + size.getHeight());
        swipe(driver,pSource,pdest,SCROLL_DUR);

        Thread.sleep(5000);

        //  x = 0 diagonal line
        pSource = new Point(location.x+20 , location.y+20);
        Thread.sleep(1000);
        System.out.println(location.x + "----" + size.getWidth() + "-----"+ size.getWidth()/2 + "----" + location.y);
        pdest = new Point(location.x + size.getWidth() - 20 , location.y + size.getHeight() - 20);
        System.out.println(location.x + "----" + size.getWidth() + "-----"+ size.getWidth()/2 + "----" + location.y);
        Thread.sleep(1000);
        swipe(driver,pSource,pdest,SCROLL_DUR);

        //  x = x.width diagonal line
        Thread.sleep(5000);
        pSource = new Point(location.x + size.getWidth() - 20 , location.y+20);
        Thread.sleep(1000);
        System.out.println(location.x + "----" + size.getWidth() + "-----"+ size.getWidth()/2 + "----" + location.y);
        pdest = new Point(location.x +  20 , location.y + size.getHeight() - 20);
        System.out.println(location.x + "----" + size.getWidth() + "-----"+ size.getWidth()/2 + "----" + location.y);
        Thread.sleep(1000);
        swipe(driver,pSource,pdest,SCROLL_DUR);

    }


}
