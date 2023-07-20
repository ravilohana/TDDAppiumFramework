package com.qa.mobile.utils;

import com.qa.mobile.base.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

public class TestUtils extends BaseTest {

    // ************ Generic Methods for Waits ***********
    /*
     *
     * @param driver
     * @param element
     */
    public static final long WAIT = 20;
    public static void waitUntilIsVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    /*
     *
     * @param driver
     * @param element
     */
    public static void waitUntilIsVisible(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }



    /*
    public static boolean waitUntilIsVisible(WebDriver driver, WebElement element) {
        Wait wait = new WebDriverWait(driver, 8).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }
    */


    /*
     *
     * @param driver
     * @param element
     */
    public static void waitUntilIsPresent(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }


    /*
     *
     * @param driver
     * @param element
     */
    public static void waitUntilIsDisappears(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }


    /*
     *
     * @param driver
     * @param element
     */
    public static void waitUntilIsDisappears(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }


    /*
     *
     * @param driver
     * @param element
     */
    public static void waitUntilIsAppearsAndDisappears(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(2), Duration.ofSeconds(100));
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }


    /*
     *
     * @param driver
     * @param element
     */
    public static void waitUntilIsAppearsAndDisappears(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(2), Duration.ofSeconds(100));
        wait2.until(ExpectedConditions.invisibilityOf(element));
    }


    /*
     *
     * @param driver
     * @param element
     */
    public static void waitUntilIsClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2), Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /*
     * @param driver
     * @param element
     * @param attribute
     */
    public static void waitUntilAttributeToBeNotEmpty(WebDriver driver, WebElement element, String attribute) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT), Duration.ofSeconds(50));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
    }


    /*
     *
     * @param wait
     * @param element
     * @param childrenSelector
     * @param currentSize
     */
    private static void waitUntilSizeChange(WebDriverWait wait, WebElement element, String childrenSelector, int currentSize) {
        wait.until((ExpectedCondition<Boolean>) (WebDriver d) -> {
            int size = element.findElements(By.xpath(childrenSelector)).size();
            return currentSize != size;
        });
    }


    /*
     *
     * @param driver
     * @param element
     * @param value
     */
    private static void waitUntilHasValue(WebDriver driver, WebElement element, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until((ExpectedCondition<Boolean>) (WebDriver f) -> !"".equals(element.getAttribute(value)));
    }

    /*
     *
     * @param driver
     * @param element
     */
    public static void waitUntilElementStaleness(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT), Duration.ofSeconds(50));
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public static Boolean webElementIsDisplayed(WebDriver driver, WebElement element){
        return element.isDisplayed();
    }


    // Generic function for click an element

    public static void click(WebDriver driver,WebElement ele){
        waitUntilIsVisible(driver,ele);
        ele.click();
    }


    // Generic function for click an element

    // Get Center of the element
    public static Point getCenterOfElement(Point location, Dimension dimension) {
        return new Point(location.getX() + (dimension.getWidth() / 2),
                location.getY() + (dimension.getHeight() / 2));
    }
    public static void tap(AppiumDriver driver, WebElement element) {
        Point openMenuPoints = element.getLocation();
        Dimension openMenuDimension = element.getSize();
        Point centerPoint = getCenterOfElement(openMenuPoints, openMenuDimension);
        System.out.println("Center Points: " + centerPoint);
        PointerInput pointerInput_finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(pointerInput_finger1, 1)
                .addAction(pointerInput_finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerPoint))
                .addAction(pointerInput_finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(pointerInput_finger1, Duration.ofMillis(200)))
                .addAction(pointerInput_finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singleton(sequence));
    }

    // Generic function for send keys to element

    public static void sendKeys(WebDriver driver,WebElement ele,String txt){
        waitUntilIsVisible(driver,ele);
        ele.clear();
        ele.sendKeys(txt);
    }

    // Generic function to get attribute of an element

    public static String getAttribute(WebDriver driver,WebElement ele,String attribute){
        waitUntilIsVisible(driver,ele);
        return  ele.getAttribute(attribute);
    }

    // Read XML file String attribute

    public static HashMap<String,String> parseStringXML(InputStream IS_XML_file) throws ParserConfigurationException, IOException, SAXException {
        HashMap<String,String> stringHashMap = new HashMap<>();
        // Get Document Builder factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Build Document
        Document document = builder.parse(IS_XML_file);

        // Normalize the XML Structure , It is just too important
        document.getDocumentElement().normalize();

        // Here comes the root node
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        // Get all the elements
        NodeList nodeList = document.getElementsByTagName("string");
        System.out.println("=================================");

        for (int temp = 0; temp < nodeList.getLength() ; temp++) {
            Node node = nodeList.item(temp);
            System.out.println(" "); // Just a Separator
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                // Store each element key value in map
                stringHashMap.put(element.getAttribute("name"),element.getTextContent());

            }
        }

        return stringHashMap;
    }



}
