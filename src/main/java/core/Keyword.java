package core;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import pagesDynamicLocator.BasePageLocator;
import utillities.util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Keyword extends CorePage {

    private static final Logger log = Logger.getLogger(Keyword.class);


    public static String FM_SPAN_TEXT_ELEMENT = "//span[contains(text(),'%s')]/../span/input";
    public static String FM_SPAN_TEXT_ELEMENT1 = "//span[contains(text(),'%s')]/../../span/input";
    String dropDownList = "//div[@class=\"rc-virtual-list-holder-inner\"]/div[@item=\"[object Object]\"]/div/span";
    String dropDownList1 = "//div[@class=\"rc-virtual-list-holder-inner\"]/div/div/span";

    private WebElement rangeTime;
    public static int DEFAULT_TIME_WAIT = 10000;
    private Alert alert;
    private WebElement element;
    private List<WebElement> elements;
    private Select select;
    private WebDriverWait explicitWait;
    private JavascriptExecutor jsExecutor;
    private Actions action;


    private FluentWait<WebDriver> fluentDriver;
    private FluentWait<WebElement> fluentElement;


//    public static void sendKeyMobileElement(WebElement ele, String text) {
//        WebDriverWait wait = new WebDriverWait(androidDriver, 30);
//        wait.until(ExpectedConditions.visibilityOf(ele));
//        ele.clear();
//        ele.sendKeys(text);
//    }

    public static void sendKeyWebElement(WebElement ele, String text) {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        ele.clear();
        ele.sendKeys(text);
    }

    public static void sendKeyWebElementDynamicXpath(String strXpath, String strValueXpath, int index, String txt) {
        String xpathIndex = strXpath + "[" + index + "]";
        String xpath = String.format(xpathIndex, strValueXpath);
        WebElement e = waitElementClickAbleStr(xpath);
        clickWithJavascript(e);
        e.clear();
        e.sendKeys(txt);


    }

    public static void sendKeyWebElementDynamicXpathEnter(String strXpath, String strValueXpath, int index, String txt) {
        String xpathIndex = strXpath + "[" + index + "]";
        String xpath = String.format(xpathIndex, strValueXpath);
        WebElement e = waitElementClickAbleStr(xpath);
        clickWithJavascript(e);
        e.clear();
        e.sendKeys(txt);
        sleepInSecond(2);
        e.sendKeys(Keys.ENTER);


    }

    public static void sendKeyAndEnterWebElement(WebElement ele, String text) {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.visibilityOf(ele));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        ele.clear();
        ele.sendKeys(text);
        ele.sendKeys(Keys.ENTER);
    }


    public WebElement waitElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(webDriver, GlobalConstants.WAIT_EXPLICIT);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement wait4ElementVisible(String xpath) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void waitColumnVisible(String xpath, int timeOut) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOut);
        wait.pollingEvery(Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void waitColumnVisible(WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOut);
        wait.pollingEvery(Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void wait4ElementVisibleWithTagSpanAndEnter(String txt, WebElement ele) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        String xpath = String.format(FM_SPAN_TEXT_ELEMENT, txt);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        ele.sendKeys(Keys.ENTER);
    }

    public void wait4ElementVisible(WebElement e) {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void sendEnterKeyOnElement(WebElement e) {
        WebDriverWait wait = new WebDriverWait(webDriver, 50);
        wait.until(ExpectedConditions.visibilityOf(e));
        e.sendKeys(Keys.ENTER);

    }

    public boolean retrySendEnterKeyOnElement(WebElement e, WebElement status, int t, String expected) {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        for (int i = 0; i < t; i++) {
            if (!getTextWebElement(status).contains(expected)) {
                e.sendKeys(Keys.ENTER);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//i[contains(@class, 'anticon-loading')]")));
            } else {
                return true;
            }
        }
        return false;
    }

    public void wait4ElementTalbeVisibleAndClickElementOnField(WebElement table, WebElement fld) {
        WebDriverWait wait = new WebDriverWait(webDriver, 50);
        wait.until(ExpectedConditions.visibilityOf(table));
        fld.sendKeys(Keys.ENTER);

    }

//
//    public static void clickMobileElement(WebElement ele) {
//        WebDriverWait wait = new WebDriverWait(androidDriver, GlobalConstants.WAIT_EXPLICIT);
//        wait.until(ExpectedConditions.elementToBeClickable(ele));
//        ele.click();
//    }

    public static void

    clickWebElement(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(webDriver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        ele.click();
    }

    public static void clickWebElementContainsText(String xPath, String txt) {
        String xpath = String.format(xPath, txt);
        WebElement e = webDriver.findElement(By.xpath(xpath));
        e.click();
    }

    public static void clickWebElementTime(WebElement ele, int time) {
        WebDriverWait wait = new WebDriverWait(webDriver, time);
        wait.until(ExpectedConditions.visibilityOf(ele));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        ele.click();
    }


    public static void waitElementVisibleAndClickAble(WebElement e) {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.visibilityOf(e));
        wait.until(ExpectedConditions.elementToBeClickable(e));
    }

    public static WebElement waitElementClickAbleStr(String xPath) {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }


//    public static String getTextMobileElement(WebElement ele) {
//        WebDriverWait wait = new WebDriverWait(androidDriver, GlobalConstants.WAIT_EXPLICIT);
//        wait.until(ExpectedConditions.visibilityOf(ele));
//        return ele.getText();
//    }

    public static String getTextWebElement(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(webDriver, GlobalConstants.WAIT_EXPLICIT);
        wait.until(ExpectedConditions.visibilityOf(ele));
        return ele.getText();
    }

    public static WebElement scrollToElement(String strXpath) {
        WebElement element = null;
        int numberOfTimes = 10;
        Dimension dimension = webDriver.manage().window().getSize();

        Double scrollHightStart = dimension.getHeight() * 0.5;
        int scrollStart = scrollHightStart.intValue();

        Double scrollHightEnd = dimension.getHeight() * 0.2;
        int scrollEnd = scrollHightEnd.intValue();

        for (int i = 0; i < numberOfTimes; i++) {
            new TouchAction((PerformsTouchActions) webDriver)
                    .press(PointOption.point(0, scrollStart))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                    .moveTo(PointOption.point(0, scrollEnd))
                    .release().perform();
            if (webDriver.findElements(By.xpath(strXpath)).size() == 1) {
                element = webDriver.findElement(By.xpath(strXpath));
                break;
            }
        }
        return element;
    }

    public static boolean isWebElementDisplayed(String strXpath, int iLoop) throws Exception {
        boolean bResult = false;
        for (int i = 0; i < iLoop; i++) {
            if (webDriver.findElements(By.xpath(strXpath)).size() != 0) {
                bResult = true;
                break;
            } else {
                Thread.sleep(1000);
            }
        }
        return bResult;
    }

    public static boolean isWebElementDisplayedContainsText(String strXpath, String strTxt, int iLoop) throws Exception {
        boolean bResult = false;
        String e = String.format(strXpath, strTxt);
        for (int i = 0; i < iLoop; i++) {
            if (webDriver.findElements(By.xpath(e)).size() != 0) {
                bResult = true;
                break;
            } else {
                Thread.sleep(1000);
            }
        }
        return bResult;
    }


//    public static boolean isElementDisplayed(String strXpath, int iLoop) throws Exception {
//        boolean bResult = false;
//        for (int i = 0; i < iLoop; i++) {
//            if (androidDriver.findElements(By.xpath(strXpath)).size() != 0) {
//                bResult = true;
//                break;
//            } else {
//                Thread.sleep(GlobalConstants.WAIT_EXPLICIT);
//            }
//        }
//        return bResult;
//    }
//
//    public static boolean validateElementDisplayed(String strXpath, String... arg) throws Exception {
//        boolean bResult = false;
//        for (int i = 0; i < 30; i++) {
//            if (androidDriver.findElements(MobileBy.xpath(String.format(strXpath, arg))).size() == 1) {
//                bResult = true;
//                break;
//            } else {
//                Thread.sleep(GlobalConstants.WAIT_EXPLICIT);
//            }
//        }
//        return bResult;
//    }

//    public static WebElement getMobileElementByXpath(String strXpath, String... arg) throws Exception {
//        WebElement ele = null;
//
//        for (int i = 0; i < 30; i++) {
//            if (androidDriver.findElements(MobileBy.xpath(String.format(strXpath, arg))).size() == 1) {
//                ele = androidDriver.findElement(MobileBy.xpath(String.format(strXpath, arg)));
//                break;
//            } else {
//                waitSomeSecond(3);
//            }
//        }
//        return ele;
//    }

    public static WebElement getWebElementByXpath(String strXpath, String... arg) throws Exception {
        WebElement ele = null;

        for (int i = 0; i < 30; i++) {
            if (webDriver.findElements(By.xpath(String.format(strXpath, arg))).size() == 1) {
                ele = webDriver.findElement(By.xpath(String.format(strXpath, arg)));
                break;
            } else {
                waitSomeSecond(3);
            }
        }
        return ele;
    }

//    public static List<WebElement> getMobileElementsByXpath(String strXpath, int iLoop, String... arg) throws Exception {
//        List<WebElement> eles = null;
//
//        for (int i = 0; i < iLoop; i++) {
//            if (androidDriver.findElements(MobileBy.xpath(String.format(strXpath, arg))).size() != 0) {
//                eles = androidDriver.findElements(MobileBy.xpath(String.format(strXpath, arg)));
//                break;
//            } else {
//                waitSomeSecond(3);
//            }
//        }
//        return eles;
//    }

    public static List<WebElement> getWebElementsByXpath(String strXpath, int iLoop, String... arg) throws Exception {
        List<WebElement> eles = null;

        for (int i = 0; i < iLoop; i++) {
            if (webDriver.findElements(By.xpath(String.format(strXpath, arg))).size() != 0) {
                eles = webDriver.findElements(By.xpath(String.format(strXpath, arg)));
                break;
            } else {
                waitSomeSecond(3);
            }
        }
        return eles;
    }

    public static String getXpathString(String xpath, String... arg) {
        String strXpath = null;

        strXpath = String.format(xpath, arg);
        return strXpath;
    }

    public static void waitSomeSecond(int second) throws Exception {
        Thread.sleep(second * 1000);
    }

//    public static void inputTextByMobileShell(String digits) {
//        List enterText = Arrays.asList("text", digits);
//        Map<String, Object> cmd = ImmutableMap
//                .of("command", "input", "args", enterText);
//        androidDriver.executeScript("mobile: shell", cmd);
//        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
//    }
//
//    public static boolean isMobileElementExists(String xpathOfElement) {
//        return androidDriver.findElements(By.xpath(xpathOfElement)).size() > 0;
//    }

//    public static boolean waitMobileForElement(int timeInSeconds, String xpathOfElement) {
//        try {
//            for (int i = 0; i < timeInSeconds; i++) {
//                if (isMobileElementExists(xpathOfElement))
//                    return true;
//                Thread.sleep(1000);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }

    public static boolean isElementExists(String xpathOfElement) {
        return webDriver.findElements(By.xpath(xpathOfElement)).size() > 0;
    }

    public static boolean waitForElement(int timeInSeconds, String xpathOfElement) {
        try {
            for (int i = 0; i < timeInSeconds; i++) {
                if (isElementExists(xpathOfElement))
                    return true;
                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void verifyContentWebElement(WebElement ele, Object expected) throws Exception {
        try {
            Object actual = ele.getText();
            System.out.println("actual: " + actual);
            org.junit.Assert.assertEquals(expected, actual);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void verifyValueWebElement(WebElement ele, Object expected) throws Exception {
        try {
            Object actual = ele.getAttribute("value");
            System.out.println("actual: " + actual);
            org.junit.Assert.assertEquals(expected, actual);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void waitElementToClickable(int timeInSeconds, String xpathOfElement, String data) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, timeInSeconds);
            wait.withTimeout(Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElementByXpath(xpathOfElement, data)));
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
    }

    public static void waitElementToClickable(int timeInSeconds, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, timeInSeconds);
            wait.withTimeout(Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public WebElement waitForElementVisible(WebElement webElement, int timeout) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebElement waitForElementVisible(WebElement webElement) {
        return waitForElementVisible(webElement, GlobalConstants.WAIT_EXPLICIT);
    }

    public WebElement waitForElementVisible(String dynamicXpath, int timeout) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));
    }

    public WebElement waitForElementVisible(String dynamicXpath) {
        return this.waitForElementVisible(dynamicXpath, GlobalConstants.WAIT_EXPLICIT);
    }


    public void waitElementLoadAndSendKeys(WebElement element, String keysToSend) {
        waitForElementVisible(element);
        waitForElementClickable(element).clear();
        element.sendKeys(keysToSend);
    }

    public WebElement waitForElementClickable(String locator) {
        return waitForElementClickable(waitForElementVisible(locator), GlobalConstants.WAIT_EXPLICIT);
    }

    public WebElement waitForElementClickable(WebElement webElement) {

        return waitForElementClickable(webElement, GlobalConstants.WAIT_EXPLICIT);
    }

    public WebElement waitForElementClickable(WebElement webElement, int timeout) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }


    public void clickAndSendKeyByTagSpanInput(String strName, String text) {
        String xpath = String.format(FM_SPAN_TEXT_ELEMENT, strName);
        WebElement e = waitForElementVisible(xpath);
        clickWebElement(e);
        sendKeyWebElement(e, text);
        e.sendKeys(Keys.ENTER);
        waitForLoadPage();
    }

    public void clickAndSendKeyByTagSpanInput1(String strName, String text) {
        String xpath = String.format(FM_SPAN_TEXT_ELEMENT1, strName);
        WebElement e = waitForElementVisible(xpath);
        clickWebElement(e);
//        sendKeyWebElement(e,text);
//        e.sendKeys(Keys.ENTER);
        waitForLoadPage();
    }

    public void clickAndSendKey(WebElement element, String text) {
        WebElement e = waitForElementVisible(element);
        clickWebElement(e);
        e.clear();
        sendKeyWebElement(e, text);
        e.sendKeys(Keys.ENTER);
    }

    public void hoverToElement(WebElement element) {
        WebElement e = waitForElementVisible(element);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element).build().perform();
    }

    public void wait4ElementClickable(WebElement webElement, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, timeout);
            wait.withTimeout(Duration.ofSeconds(timeout));
            wait.pollingEvery(Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

    }

    public String extractDigits(String src) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public static void waitForLoadPage(long timeout) {
        ExpectedCondition<Boolean> expectation = driverWait -> {
            assert driverWait != null;
            return ((JavascriptExecutor) driverWait).executeScript("return document.readyState").toString().equalsIgnoreCase("complete");
        };
        try {
            Thread.sleep(3500);
            WebDriverWait wait = new WebDriverWait(webDriver, timeout);
            wait.until(expectation);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (WebDriverException e) {
            if (!e.getMessage().contains("Target frame detached !")) {
                throw new Error(e.getMessage());
            }
        }
    }

    public static void waitForLoadPage() {
        waitForLoadPage(GlobalConstants.WAIT_EXPLICIT);
    }

    public void selectItemInListByText(WebElement dropdown, String searchText) {
        clickWebElement(dropdown);
        List<WebElement> options = webDriver.findElements(By.xpath(dropDownList));
        for (WebElement option : options) {
            if (option.getText().equals(searchText)) {
                waitElementVisibleAndClickAble(option);
                option.click(); // click the desired option
                break;
            }
        }
    }

    public void selectItemInListByText1(WebElement dropdown, String dropDownOptions, String searchText) {
        clickWithJavascript(dropdown);
        List<WebElement> options = webDriver.findElements(By.xpath(dropDownOptions));

        for (WebElement option : options) {
            if (option.getText().contains(searchText)) {
                waitElementVisibleAndClickAble(option);
                option.click(); // click the desired option
                break;
            }
        }
    }


    public void selectItemInListContainsText(WebElement dropdown, String searchText) throws InterruptedException {
        //dropdown.click();// assuming you have to click the "dropdown" to open it
        clickWebElement(dropdown);
        List<WebElement> options = webDriver.findElements(By.xpath(dropDownList));
        for (WebElement option : options) {
            if (option.getText().contains(searchText)) {
                waitElementVisibleAndClickAble(option);
                option.click(); // click the desired option
                break;
            }
        }
    }

    public void selectItemInListTrangThai(WebElement dropdown, String searchText) throws InterruptedException {
        //dropdown.click();// assuming you have to click the "dropdown" to open it
        clickWebElement(dropdown);
        List<WebElement> options = webDriver.findElements(By.xpath(dropDownList1));
        for (WebElement option : options) {
            waitElementVisibleAndClickAble(option);
            if (option.getText().equals(searchText)) {
                waitElementVisibleAndClickAble(option);
                option.click(); // click the desired option
                break;
            }
        }
    }

//    public void clickOnElement(WebElement webElement) {
//        clickOnElement(webElement, GlobalConstants.WAIT_EXPLICIT);
//    }

//    public void clickOnElement(WebElement webElement, int timeout) {
//        if (isSafariBrowser()) {
//            waitForElementClickable(webElement);
//            clickWithJavascript(webElement);
//        } else {
//            tryToClick(webElement, timeout);
//        }
//    }

//    public boolean isSafariBrowser() {
//        return getBrowserName().contains(Vietnamese.SAFARIBROWSER);
//    }

    public String getBrowserName() {
        return webDriverRemote.getCapabilities().getBrowserName().toLowerCase();
    }

    public static void clickWithJavascript(WebElement webElement) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        String script = "arguments[0].click();";
        try {
            jsExecutor.executeScript(script, webElement);
            Thread.sleep(200);
        } catch (StaleElementReferenceException se) {
            waitForElementRefreshedAndVisible(webElement);
            jsExecutor.executeScript(script, webElement);
        } catch (WebDriverException e) {
//            log.fail(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static WebElement waitForElementRefreshedAndVisible(WebElement element) {
        return waitForElementRefreshedAndVisible(element, GlobalConstants.WAIT_EXPLICIT);
    }

    public static WebElement waitForElementRefreshedAndVisible(WebElement element, int timeOut) {
        return new WebDriverWait(webDriver, timeOut)
                .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));

    }

    protected void tryToClick(WebElement element, int timeout) {
        try {
            waitForElementClickable(element, timeout).click();
        } catch (StaleElementReferenceException e) {
            boolean isClicked = false;
            int loop = 3;
            while (loop > 0 && !isClicked) {
                try {
                    waitForElementRefreshedAndVisible(element, timeout);
                    waitForElementClickable(element, timeout).click();
                    isClicked = true;
                } catch (StaleElementReferenceException | TimeoutException ignored) {
//                    log.info("Attempting to click on element");
                    loop--;
                }
            }
            if (!isClicked) {
//                log.fail("Click on element {} as failed", element);
            }
        } catch (ElementClickInterceptedException e2) {
            clickWithJavascript(element);
        }
    }

    public static void refreshPage() {
        webDriver.navigate().refresh();
    }

    public static void waitForPageLoad(int timeInSeconds) {
        webDriver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
    }

    public void saveData(String strFileName, String strKey, String strValue) throws Exception {
        String path = "src\\test\\resources\\";
        String currentPath = "src\\test\\resources\\CiAMS1.properties";
        Properties prop = new Properties();
        path = StringUtil.getPathByOS(path);
        FileInputStream in = new FileInputStream(path + strFileName + ".properties");
        currentPath = StringUtil.getPathByOS(currentPath);
        FileOutputStream out = new FileOutputStream(currentPath);


        prop.load(in);
        Set<Object> set = prop.keySet();
        boolean flag = false;
        for (Object o : set)
            if (String.valueOf(o).equalsIgnoreCase(strKey)) {
                flag = true;
                prop.setProperty(strKey, strValue);
            }
        if (!flag) {
            prop.setProperty(strKey, strValue);
        }
        prop.store(out, "This file is to save the value");
        in.close();
        out.close();

        // Delete file
        if (Files.deleteIfExists(Paths.get(path + strFileName + ".properties"))) {
            System.out.println("File is deleted !");
        } else {
            System.out.println("Sorry, unable to delete the file.");
        }

        // Change filename
        Path source = Paths.get(currentPath);
        Files.move(source, source.resolveSibling(strFileName + ".properties"));
    }

    public String getData(String fileName, String fld) throws Exception {

        String path = "src\\test\\resources\\" + fileName + ".properties";
        path = StringUtil.getPathByOS(path);
        FileInputStream inputStream = new FileInputStream(path);
        try {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String code = props.getProperty(fld);
        inputStream.close();
        return code;
    }

    public void retryingFindClick(WebElement ele) {
        int attempts = 0;
        while (attempts < 5) {
            try {
                ele.click();
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }

    public void uploadImg(String nameImg, String uploadXpath) throws Exception {
        if (StringUtils.isNotBlank(nameImg)) {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            WebElement uploadElement = getWebElementByXpath(uploadXpath);
            executor.executeScript("arguments[0].style.display='block';", uploadElement);
            String imagePath = System.getProperty("user.dir") + "\\src\\test\\resources\\" + nameImg;
            File imageFile = new File(imagePath);
            if (imageFile.exists() && !imageFile.isDirectory()) {
                uploadElement.sendKeys(imagePath);
            }
        }
    }


    // ===========================NORMAL KEYWORD===============================

    public static void setTextoElement(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void clickToElement(WebDriver driver, String xpathValue) {
        element = findElement(driver, xpathValue);
        element.click();
    }

    public String getInnerText(WebElement element) {
        return element.getAttribute("innerText");
    }

    public WebElement findElement(WebDriver driver, String xpathValue) {
        return driver.findElement(byXpath(xpathValue));
    }

    public List<WebElement> findElements(WebDriver driver, String xpathValue) {
        return driver.findElements(byXpath(xpathValue));
    }

    public List<WebElement> findDynamicElements(WebDriver driver, String xpathValue, String... values) {
        String xpathValues = getDynamicLocator(xpathValue, values);
        return driver.findElements(byXpath(xpathValues));
    }

    public By byXpath(String xpathValue) {
        return By.xpath(xpathValue);
    }

    public String getAttributevalue(String xpathValue, String attributeName) {
        element = findElement(webDriver, xpathValue);
        return element.getAttribute(attributeName);
    }


    public int getNumberElement(WebDriver driver, String xpathValue, String... values) {
        int size = 0;
        try {
            elements = findElements(webDriver, getDynamicLocator(xpathValue, values));
            size = elements.size();
        } catch (Exception ex) {
        }
        return size;
    }


    // ===========================DROPDOWN LIST===============================

    public String getSelectedItemInDropdown(String xpathValue) {
        select = new Select(findElement(webDriver, xpathValue));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(String xpathValue) {
        select = new Select(findElement(webDriver, xpathValue));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(String parentLocator, String childItemLocator, String expectedItem, boolean isSetText) {

        overrideGlobalTimeout(webDriver, GlobalConstants.WAIT_OVERRIDE_SHORT_TIMEOUT);
        jsExecutor = (JavascriptExecutor) webDriver;
        if (!parentLocator.isEmpty()) {
            element = findElement(webDriver, parentLocator);
//            sleepInSecond(1); // wait to load list item
            // 1 - click on parent locator to show all list
            element.click();
//            sleepInSecond(1); // wait to load list item
            // 2 - wait to show all list item
//            explicitWait = new WebDriverWait(webDriver, GlobalConstants.WAIT_EXPLICIT);
            try {
                waitElementVisible(webDriver, childItemLocator);
            } catch (Exception ex) {
                log.info("selectItemInCustomDropdown error1");
            }
            // truong hop bi loi UI
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);

            if (isSetText) {
                setTextoElement(webDriver, parentLocator, expectedItem, "");
            }
        }
        // 3 - get list to check
        elements = webDriver.findElements(By.xpath(childItemLocator));
        // truong hop item chua load kip khi click dropdownlist
        try {
            if (!parentLocator.isEmpty()) {
                // CHECK IF LIST ITEM DID LOAD
                if (elements.size() == 0) {
                    tryToClick(element, GlobalConstants.WAIT_EXPLICIT);
                    tryToClick(element, GlobalConstants.WAIT_EXPLICIT);
                    waitElementVisible(webDriver, childItemLocator);
                    elements = webDriver.findElements(By.xpath(childItemLocator));
                }
            }
        } catch (Exception ex) {
            log.info("selectItemInCustomDropdown error2");
            if (!parentLocator.isEmpty()) {
                tryToClick(element, GlobalConstants.WAIT_EXPLICIT);
                tryToClick(element, GlobalConstants.WAIT_EXPLICIT);
                waitElementVisible(webDriver, childItemLocator);
                elements = webDriver.findElements(By.xpath(childItemLocator));
            }
        }
        // 4 - for to list
        for (WebElement item : elements) {
//            log.info(item.getAttribute("innerHTML").toString());
            // 4 - check item with expected text
            if (item.getText().equals(expectedItem)) {
                // 5 - scroll
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
//                sleepInSecond(1);
                // 6 - click this element
                item.click();
//                sleepInSecond(1);
                break;
            }
        }
        overrideGlobalTimeout(webDriver, GlobalConstants.WAIT_IMPLICIT);
    }

    public void selectFirstItemInCustomDropdown(String parentLocator, String childItemLocator) {
        element = findElement(webDriver, parentLocator);
        jsExecutor = (JavascriptExecutor) webDriver;

        sleepInSecond(1); // wait to load list item

        // 1 - click on parent locator to show all list
        element.click();

        // 2 - wait to show all list item
        explicitWait = new WebDriverWait(webDriver, GlobalConstants.WAIT_EXPLICIT);
        try {
            explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));
        } catch (Exception ex) {
        }

        // truong hop bi loi UI
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);

        // 3 - get list to check
        elements = webDriver.findElements(By.xpath(childItemLocator));

        // truong hop item chua load kip khi click dropdownlist
        try {
            // CHECK IF LIST ITEM DID LOAD
            if (elements.size() == 0) {
                tryToClick(element, GlobalConstants.WAIT_EXPLICIT);
                tryToClick(element, GlobalConstants.WAIT_EXPLICIT);
                explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));
                elements = webDriver.findElements(By.xpath(childItemLocator));
            }
        } catch (Exception ex) {
            tryToClick(element, GlobalConstants.WAIT_EXPLICIT);
            tryToClick(element, GlobalConstants.WAIT_EXPLICIT);
            explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));
            elements = webDriver.findElements(By.xpath(childItemLocator));
        }

        // 4 - for to list
        for (WebElement item : elements) {
            // 5 - scroll
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
            sleepInSecond(1);
            // 6 - click this element
            item.click();
            break;
        }
    }

    public void SelectMultiItemInDropdownList(String parentXpath, String allItemXpath, String allItemSelectedXpath, List<String> listSelectedText) throws InterruptedException {

        element = findElement(webDriver, parentXpath);

        sleepInSecond(1); // wait to load list item

        // 01: CLICK VAO xpath cha de show ra list menu trong childxpath
        element.click();

        // 2 - wait to show all list item
        explicitWait = new WebDriverWait(webDriver, GlobalConstants.WAIT_EXPLICIT);
        try {
            explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
        } catch (Exception ex) {
        }

        // 3 - get list to check
        elements = webDriver.findElements(By.xpath(allItemXpath));

        // truong hop item chua load kip khi click dropdownlist
        try {
            // CHECK IF LIST ITEM DID LOAD
            if (elements.size() == 0) {
                tryToClick(element, GlobalConstants.WAIT_EXPLICIT);
                tryToClick(element, GlobalConstants.WAIT_EXPLICIT);
                explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
                elements = webDriver.findElements(By.xpath(allItemXpath));
            }
        } catch (Exception ex) {
            tryToClick(element, GlobalConstants.WAIT_EXPLICIT);
            tryToClick(element, GlobalConstants.WAIT_EXPLICIT);
            explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
            elements = webDriver.findElements(By.xpath(allItemXpath));
        }

        // 04: check item trong list item: co text trung voi text cua cac item dang muon
        // click
        for (WebElement element : elements) {
            // duyet qua list cua cac item dang muon click
            for (String element2 : listSelectedText) {
                if (element.getText().equals(element2.toString())) {
                    if (element.isDisplayed()) {
                        //System.out.println("Selenium - Option is: " + element.getText());
                        element.click();
                        Thread.sleep(1000);
                    } else {
                        //System.out.println("Javascript scroll ---- ");
                        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
                        Thread.sleep(1000);
                        //System.out.println("JS Click - Option is: " + element.getText());
                        jsExecutor.executeScript("arguments[0].click();", element);
                    }
                    List<WebElement> listSelectedOption = webDriver.findElements(By.xpath(allItemSelectedXpath));
                    if (listSelectedOption.size() == listSelectedText.size()) {
                        break;
                    }
                }
            }
        }
        // close popup list select
        WebElement parentDropdown = webDriver.findElement(By.xpath(parentXpath));
        jsExecutor.executeScript("arguments[0].click();", parentDropdown);

    }

    public static void sleepInSecond(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //==================DATA TABLE===================================
    public String GetTextByRownName(String rowName) {

        String rowXPath = "//td[text()='" + rowName + "']/following-sibling::td";
        return webDriver.findElement(By.xpath(rowXPath)).getText();
    }

    public void setTextElementByColumnNameAtRowIndex(String dynamicColumnXPath, String rownNumber, String value) {
        int columnIndex = getColumnIndex(dynamicColumnXPath);
        element = webDriver.findElement(By.xpath("//tr[" + rownNumber + "]//td[" + columnIndex + "]//input"));
        element.clear();
        element.sendKeys(value);
    }

    public void clickElementByColumnNameAtRowIndex(String dynamicXPath, String rownNumber) {
        int columnIndex = getColumnIndex(dynamicXPath);
        String itemXPath = "//tr[" + rownNumber + "]//td[" + columnIndex + "]";
        webDriver.findElement(By.xpath(itemXPath)).click();
    }

    public void clickDynamicElementByColumnNameAtRowIndex(WebDriver driver, String dynamicXpathColumnIndex, String rownNumber, String dynamicXpathItem, String... valuesXpathColumn) {
        int columnIndex = getDynamicColumnIndex(driver, dynamicXpathColumnIndex, valuesXpathColumn);
        String itemXPath = "//tr[" + rownNumber + "]//td[" + columnIndex + "]" + dynamicXpathItem;
        webDriver.findElement(By.xpath(itemXPath)).click();
    }

    public void doubleClickElementByColumnNameAtRowIndex(String dynamicColumnXPath, String rowIndex) {
        int columnIndex = getColumnIndex(dynamicColumnXPath);
        Actions actions = new Actions(webDriver);
        String itemXPath = "//tr[" + rowIndex + "]//td[" + columnIndex + "]";
        element = webDriver.findElement(By.xpath(itemXPath));
        actions.doubleClick(element).perform();
    }

    public String getInnerTextByColumnNameAtRowIndex(String dynamicXPath, String rownNumber) {
        int columnIndex = getColumnIndex(dynamicXPath);
        String itemXPath = "//tr[" + rownNumber + "]//td[" + columnIndex + "]//a";
        return getInnerText(webDriver, itemXPath, "");
    }

    public void waitDynamicElementByColumnNameAtRowIndex(WebDriver driver, String dynamicXpathColumnIndex, String rownNumber, String dynamicXpathItem, String... valuesXpathColumn) {
        int columnIndex = getDynamicColumnIndex(driver, dynamicXpathColumnIndex, valuesXpathColumn);
        String itemXPath = "//tr[" + rownNumber + "]//td[" + columnIndex + "]" + dynamicXpathItem;
        waitElementClickable(webDriver, itemXPath);
    }

    public int getColumnIndex(String Xpath) {
        List<WebElement> listColumnNameElement = webDriver.findElements(By.xpath(Xpath));
        int columnIndex = listColumnNameElement.size() + 1;
        return columnIndex;
    }

    public int getDynamicColumnIndex(WebDriver driver, String dynamicXpath, String... values) {
        List<WebElement> listColumnNameElement = findElements(driver, getDynamicLocator(dynamicXpath, values));
        int columnIndex = listColumnNameElement.size() + 1;
        return columnIndex;
    }


    // ===========================KEYWORD ACTION WITH DYNAMIC LOCATOR===============================

    public String getDynamicLocator(String xpathValue, String... values) {
        xpathValue = String.format(xpathValue, (Object[]) values);
        return xpathValue;
    }

    //==============SELECT
//    public void selectToDynamicSelectOptionByText(WebDriver driver, String selectName, String textValue) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_SELECT_BY_NAME, selectName);
//        selectOptionToElement(driver, BasePageLocator.DYNAMIC_SELECT_BY_NAME, textValue, selectName);
//    }

    //=========TEXTBOX
    public void inputToDynamicTextbox(WebDriver driver, String xpathDynamic, String typeValue, String Value) {
        waitElementVisible(driver, xpathDynamic, typeValue);
        setTextoElement(driver, xpathDynamic, Value, typeValue);
    }

//    public void inputToDynamicTextboxByName(WebDriver driver, String nameAttribute, String Value) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_NAME, nameAttribute);
//        setTextoElement(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_NAME, Value, nameAttribute);
//    }
//
//    public void inputToDynamicTextboxByPlaceHolder(WebDriver driver, String placeholder, String Value) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_PLACEHOLDER, placeholder);
//        setTextoElement(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_PLACEHOLDER, Value, placeholder);
//    }
//
//    public void inputToDynamicTextboxByID(WebDriver driver, String id, String Value) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_ID, id);
//        setTextoElement(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_ID, Value, id);
//    }
//
//    public void inputToDynamicTextboxByFormControlName(WebDriver driver, String formcontrolname, String value) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_FORMCONTROLNAME, formcontrolname);
//        setTextoElement(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_FORMCONTROLNAME, value, formcontrolname);
//    }
//
//    public void inputToDynamicTextboxByValue(WebDriver driver, String value, String textValue) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_VALUE, value);
//        setTextoElement(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_VALUE, textValue, value);
//    }
//
//    public void clickToDynamicTextboxByValue(WebDriver driver, String value) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_VALUE, value);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_VALUE, value);
//    }
//
//    public void clickToDynamicTextboxByID(WebDriver driver, String id) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_ID, id);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_ID, id);
//    }
//
//    public void clickToDynamicTextboxByName(WebDriver driver, String name) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_NAME, name);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_NAME, name);
//    }
//
//    public void clickToDynamicTextboxByContainsText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_CONTAINS_TEXT, text);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_CONTAINS_TEXT, text);
//    }
//
//    public void clickToDynamicTextboxByText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_TEXT, text);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_TEXT, text);
//    }
//
//    public void clickToDynamicTextboxByPlaceHolder(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_PLACEHOLDER, text);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_PLACEHOLDER, text);
//    }
//
//
//    //=========DYNAMIC TEXTAREA
//    public void inputToDynamicTextAreaByName(WebDriver driver, String nameAttribute, String Value) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTAREA_BY_NAME, nameAttribute);
//        setTextoElement(driver, BasePageLocator.DYNAMIC_TEXTAREA_BY_NAME, Value, nameAttribute);
//    }
//
//    public void inputToDynamicTextAreaByID(WebDriver driver, String id, String Value) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTAREA_BY_ID, id);
//        setTextoElement(driver, BasePageLocator.DYNAMIC_TEXTAREA_BY_ID, Value, id);
//    }
//
//    public void inputToDynamicTextAreaByFormControlName(WebDriver driver, String formcontrolname, String value) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTAREA_BY_FORMCONTROLNAME, formcontrolname);
//        setTextoElement(driver, BasePageLocator.DYNAMIC_TEXTAREA_BY_FORMCONTROLNAME, value, formcontrolname);
//    }
//
//
//    //=========DYNAMIC BUTTON
//
//    public void clickToDynamicButtonByID(WebDriver driver, String id) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_BUTTON_BY_ID, id);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_BUTTON_BY_ID, id);
//    }
//
//    public void clickToDynamicButtonByValue(WebDriver driver, String buttonValue) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_BUTTON_BY_VALUE, buttonValue);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_BUTTON_BY_VALUE, buttonValue);
//    }
//
//    public void clickToDynamicButtonByName(WebDriver driver, String buttonName) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_BUTTON_BY_NAME, buttonName);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_BUTTON_BY_NAME, buttonName);
//    }
//
//    public void clickToDynamicButtonByTitle(WebDriver driver, String buttonTitle) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_BUTTON_BY_TITLE, buttonTitle);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_BUTTON_BY_TITLE, buttonTitle);
//    }
//
//    public void clickToDynamicButtonBySpanText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_BUTTON_BY_SPAN_TEXT, text);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_BUTTON_BY_SPAN_TEXT, text);
//    }
//
//    public void clickToDynamicButtonBySpanContainsText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_BUTTON_BY_SPAN_CONTAINS_TEXT, text);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_BUTTON_BY_SPAN_CONTAINS_TEXT, text);
//    }
//
//    public void clickToDynamicBySpanText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_BUTTON_BY_SPAN_TEXT, text);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_BUTTON_BY_SPAN_TEXT, text);
//    }
//
//    public void clickToDynamicButtonByContainsText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_BUTTON_CONTAINS_TEXT, text);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_BUTTON_CONTAINS_TEXT, text);
//    }
//
//
//    public void clickToItemByContainsText(WebDriver driver, String text) {
//        try {
//            waitElementVisible(driver, BasePageLocator.DYNAMIC_ITEM_CONTAINS_TEXT, text);
//            waitElementClickable(driver, BasePageLocator.DYNAMIC_ITEM_CONTAINS_TEXT, text);
//            clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_ITEM_CONTAINS_TEXT, text);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
    public void clickToLinkByContainsText(WebDriver driver, String text) {
        try {
            waitElementVisible(driver, BasePageLocator.DYNAMIC_ITEM_CONTAINS_TEXT, text);
            waitElementClickable(driver, BasePageLocator.DYNAMIC_ITEM_CONTAINS_TEXT, text);
            clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_ITEM_CONTAINS_TEXT, text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//    public void clickToLinkByEqualText(WebDriver driver, String text) {
//        try {
//
//            waitElementClickable(driver, BasePageLocator.DYNAMIC_ITEM_EQUAL_TEXT, text);
//            clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_ITEM_EQUAL_TEXT, text);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void clickToDynamicButtonByDivContainsText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_BUTTON_BY_DIV_CONTAINS_TEXT, text);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_BUTTON_BY_DIV_CONTAINS_TEXT, text);
//    }
//
//    //=========DYNAMIC RADIO BUTTON
//    public void clickToDynamicRadioButtonByValue(WebDriver driver, String radioValue) {
//        waitElementClickable(driver, BasePageLocator.DYNAMIC_RADIO_BUTTON_BY_VALUE, radioValue);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_RADIO_BUTTON_BY_VALUE, radioValue);
//    }
//
//    //=========DYNAMIC ITEM
//    public void clickToDynamicLinkByID(WebDriver driver, String linkID) {
//        waitElementClickable(driver, BasePageLocator.DYNAMIC_LINK_BY_ID, linkID);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_LINK_BY_ID, linkID);
//    }
//
//    public void clickToDynamicLinkByText(WebDriver driver, String linkText) {
//        waitElementClickable(driver, BasePageLocator.DYNAMIC_LINK_BY_TEXT, linkText);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_LINK_BY_TEXT, linkText);
//    }
//
//    public void clickToDynamicSpanByText(WebDriver driver, String spanText) {
//        waitElementClickable(driver, BasePageLocator.DYNAMIC_SPAN_BY_TEXT, spanText);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_SPAN_BY_TEXT, spanText);
//    }
//
//    public void clickToDynamicLabelByText(WebDriver driver, String labelText) {
//        waitElementClickable(driver, BasePageLocator.DYNAMIC_LABEL_BY_TEXT, labelText);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_LABEL_BY_TEXT, labelText);
//    }
//
//    public void clickToDynamicDivByText(WebDriver driver, String divText) {
//        waitElementClickable(driver, BasePageLocator.DYNAMIC_DIV_BY_TEXT, divText);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_DIV_BY_TEXT, divText);
//    }
//
//    public void clickToDynamicItemByText(WebDriver driver, String itemText) {
//        waitElementClickable(driver, BasePageLocator.DYNAMIC_ITEM_BY_TEXT, itemText);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_ITEM_BY_TEXT, itemText);
//    }
//
//
//    //=========DYNAMIC ITEM CONTAINS
//    public void clickToDynamicLinkByContainsText(WebDriver driver, String linkText) {
//        waitElementClickable(driver, BasePageLocator.DYNAMIC_LINK_CONTAINS_TEXT, linkText);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_LINK_CONTAINS_TEXT, linkText);
//    }
//
//    public void clickToDynamicSpanByContainsText(WebDriver driver, String spanText) {
//        waitElementClickable(driver, BasePageLocator.DYNAMIC_SPAN_CONTAINS_TEXT, spanText);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_SPAN_CONTAINS_TEXT, spanText);
//    }
//
//    public void clickToDynamicLabelByContainsText(WebDriver driver, String labelText) {
//        waitElementClickable(driver, BasePageLocator.DYNAMIC_LABEL_CONTAINS_TEXT, labelText);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_LABEL_CONTAINS_TEXT, labelText);
//    }
//
    public void clickToDynamicDivByContainsText(WebDriver driver, String divText) {
        waitElementClickable(driver, BasePageLocator.DYNAMIC_DIV_CONTAINS_TEXT, divText);
        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_DIV_CONTAINS_TEXT, divText);
    }
//
//    public void clickToDynamicItemByContainsText(WebDriver driver, String itemText) {
//        waitElementClickable(driver, BasePageLocator.DYNAMIC_ITEM_CONTAINS_TEXT, itemText);
//        clickToDynamicXpathElement(driver, BasePageLocator.DYNAMIC_ITEM_CONTAINS_TEXT, itemText);
//    }
//
//    //================DYNAMIC GET TEXT FROM ELEMENT
//    public String getTextFromDynamicLinkByContansText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_LINK_CONTAINS_TEXT, text);
//        String returnText = getTextElement(driver, BasePageLocator.DYNAMIC_LINK_CONTAINS_TEXT, text);
//        return returnText;
//    }
//
//    public String getInnerTextFromLinkByContansText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_ITEM_CONTAINS_TEXT, text);
//        String returnText = getInnerText(driver, BasePageLocator.DYNAMIC_ITEM_CONTAINS_TEXT, text);
//        return returnText;
//
//    }


    //================DYNAMIC SOME ITEM


    public void clickToElement(WebDriver driver, WebElement element) {
        element.click();
    }

    public void clickToDynamicXpathElement(WebDriver driver, String xpathValue, String... values) {
        element = findElement(driver, getDynamicLocator(xpathValue, values));
        tryToClick(element, GlobalConstants.WAIT_OVERRIDE_SHORT_TIMEOUT);

    }

    public String getTextElement(WebDriver driver, String xpathValue, String... values) {
        element = findElement(driver, getDynamicLocator(xpathValue, values));
        return element.getText();
    }

    public String getInnerText(WebDriver driver, String xpathValue, String... values) {
        element = findElement(driver, getDynamicLocator(xpathValue, values));
        return element.getAttribute("innerText");
    }

    public String getInnerHTML(WebDriver driver, String xpathValue, String... values) {
        element = findElement(driver, getDynamicLocator(xpathValue, values));
        return element.getAttribute("innerHTML");
    }

    public void setTextoElement(WebDriver driver, String xpathValue, String text, String... values) {
        element = findElement(driver, getDynamicLocator(xpathValue, values));
        element.clear();
        element.sendKeys(text);
    }


    public void clearTextElement(WebDriver driver, String xpathValue, String... values) {
        element = findElement(driver, getDynamicLocator(xpathValue, values));
        element.clear();
    }

    public void selectOptionToElement(WebDriver driver, String xpathValue, String text, String... values) {
        Select elementDropdown = new Select(findElement(driver, getDynamicLocator(xpathValue, values)));
        elementDropdown.selectByVisibleText(text);
    }

    public String getAttributevalue(WebDriver driver, String xpathValue, String attributeName, String... values) {
        element = findElement(driver, getDynamicLocator(xpathValue, values));
        return element.getAttribute(attributeName);

    }

    public void hoverToDynamicXpath(WebDriver driver, String xpathValue, String... values) {
        element = findElement(driver, getDynamicLocator(xpathValue, values));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }


    //==================WAIT================================

    public void waitElementVisible(WebDriver driver, String xpathValue) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.WAIT_EXPLICIT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(xpathValue)));
    }


    public void waitElementVisibleWithFluentWait(WebElement element) {
        fluentDriver = new FluentWait<WebDriver>(webDriver);
        fluentDriver.withTimeout(Duration.ofSeconds(GlobalConstants.WAIT_IMPLICIT))
                .pollingEvery(Duration.ofMillis(GlobalConstants.WAIT_FLUENT_MINISECONDS))
                .ignoring(NoSuchElementException.class);
        fluentDriver.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementVisibleWithFluentWait(WebDriver driver, String xpathValue, String... values) {
        String xpath = getDynamicLocator(xpathValue, values);
        WebElement e = findElement(driver, xpath);
        fluentDriver = new FluentWait<WebDriver>(driver);
        fluentDriver.withTimeout(Duration.ofSeconds(GlobalConstants.WAIT_IMPLICIT))
                .pollingEvery(Duration.ofMillis(GlobalConstants.WAIT_FLUENT_MINISECONDS))
                .ignoring(NoSuchElementException.class);
        fluentDriver.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitElementInVisibleWithFluentWait(WebElement element) {

        fluentDriver = new FluentWait<WebDriver>(webDriver);
        fluentDriver.withTimeout(Duration.ofSeconds(GlobalConstants.WAIT_IMPLICIT))
                .pollingEvery(Duration.ofMillis(GlobalConstants.WAIT_FLUENT_MINISECONDS))
                .ignoring(NoSuchElementException.class);
        fluentDriver.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitElementClickableWithFluentWait(WebElement element) {

        fluentDriver = new FluentWait<WebDriver>(webDriver);
        fluentDriver.withTimeout(Duration.ofSeconds(GlobalConstants.WAIT_IMPLICIT))
                .pollingEvery(Duration.ofMillis(GlobalConstants.WAIT_FLUENT_MINISECONDS))
                .ignoring(NoSuchElementException.class);
        fluentDriver.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitTextToBePresentInElement(WebElement ele, String str) {
        WebDriverWait wait = new WebDriverWait(webDriver, GlobalConstants.WAIT_EXPLICIT);
        wait.until(ExpectedConditions.textToBePresentInElement(ele, str));
        return ele;
    }

    public WebElement waitElementNotVisible(WebElement e) {
        WebDriverWait wait = new WebDriverWait(webDriver, GlobalConstants.WAIT_EXPLICIT);
        wait.until(ExpectedConditions.invisibilityOf(e));
        return e;
    }

    public WebElement waitElementStaleness(WebElement e) {
        WebDriverWait wait = new WebDriverWait(webDriver, GlobalConstants.WAIT_EXPLICIT);
        wait.until(ExpectedConditions.stalenessOf(e));
        return e;
    }

    public void waitElementClickable(WebDriver driver, WebElement e) {

        explicitWait = new WebDriverWait(driver, GlobalConstants.WAIT_EXPLICIT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(e)).clear();
    }


    //==================DYNAMIC WAIT================================


    public void waitElementClickable(WebDriver driver, String xpathValue, String... values) {

        explicitWait = new WebDriverWait(driver, GlobalConstants.WAIT_EXPLICIT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(getDynamicLocator(xpathValue, values))));
    }

    public void waitElementVisible(WebDriver driver, String xpathValue, String... values) {

        explicitWait = new WebDriverWait(driver, GlobalConstants.WAIT_EXPLICIT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(getDynamicLocator(xpathValue, values))));
    }

    public void waitElementNotVisible(WebDriver driver, String xpathValue, String... values) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.WAIT_EXPLICIT);
        explicitWait.until(ExpectedConditions.invisibilityOf(findElement(webDriver, getDynamicLocator(xpathValue, values))));
    }

    public void waitElementStaleness(WebDriver driver, String xpathValue, String... values) {
        element = findElement(driver, getDynamicLocator(xpathValue, values));
        explicitWait = new WebDriverWait(driver, GlobalConstants.WAIT_EXPLICIT);
        explicitWait.until(ExpectedConditions.stalenessOf(element));

    }


    //=======================================VERIFY=====================================

    public boolean isElementDisplayed(WebDriver driver, String xpathValue) {
        element = findElement(driver, xpathValue);
        return element.isDisplayed();
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    public boolean isElementNotDisplayed(WebElement element) {
        return !element.isDisplayed();
    }

    // ====================DYNAMIC XPATH VERIFY========================

//    public boolean verifyDynamicLinkDisplayedByContainsText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_LINK_CONTAINS_TEXT, text);
//        return isElementDisplayed(driver, BasePageLocator.DYNAMIC_LINK_CONTAINS_TEXT, text);
//    }
//
//    public boolean verifyDynamicItemDisplayedByContainsText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_ITEM_CONTAINS_TEXT, text);
//        return isElementDisplayed(driver, BasePageLocator.DYNAMIC_ITEM_CONTAINS_TEXT, text);
//    }
//
//    public boolean verifyDynamicDivDisplayedByContainsText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_DIV_CONTAINS_TEXT, text);
//        return isElementDisplayed(driver, BasePageLocator.DYNAMIC_DIV_CONTAINS_TEXT, text);
//    }
//
//    public boolean verifyDynamicButtonDisplayedBySpanText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_BUTTON_BY_SPAN_TEXT, text);
//        return isElementDisplayed(driver, BasePageLocator.DYNAMIC_BUTTON_BY_SPAN_TEXT, text);
//    }
//
//    public boolean verifyDynamicButtonDisplayedByText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_BUTTON_CONTAINS_TEXT, text);
//        return isElementDisplayed(driver, BasePageLocator.DYNAMIC_BUTTON_CONTAINS_TEXT, text);
//    }
//
//    public boolean verifyDynamicTextboxDisplayByID(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_ID, text);
//        return isElementDisplayed(driver, BasePageLocator.DYNAMIC_TEXTBOX_BY_ID, text);
//    }
//
//    public boolean verifyDynamicSpanDisplayedByContainsText(WebDriver driver, String text) {
//        waitElementVisible(driver, BasePageLocator.DYNAMIC_SPAN_CONTAINS_TEXT, text);
//        return isElementDisplayed(driver, BasePageLocator.DYNAMIC_SPAN_CONTAINS_TEXT, text);
//    }


    public boolean isElementDisplayed(WebDriver driver, String xpathvalue, String... values) {
        try {
            element = findElement(driver, getDynamicLocator(xpathvalue, values));
            return element.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    // ====================JAVASCRIPT========================

    public Object executeForBrowserByJS(WebDriver driver, String javaScript) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerTextByJS(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerTextByJS(WebDriver driver, String textExpected) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElementByJS(WebDriver driver, String xpathValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = findElement(driver, xpathValue);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String xpathValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", findElement(driver, xpathValue));
    }

    public String getValueNGZorro(WebDriver driver, int index) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.querySelectorAll(\"input[inputmode='decimal']\")['" + index + "'].value");
    }


    public void scrollToElementByJS(WebDriver driver, String xpathValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElement(driver, xpathValue));
    }

    public void setTextToElementByJS(WebDriver driver, String xpathValue, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", findElement(driver, xpathValue));
    }

    public void setTextToElementByJS(WebDriver driver, String xpathValue, String value, String... values) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        element = findElement(driver, getDynamicLocator(xpathValue, values));
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public void removeAttributeInDOMByJS(WebDriver driver, String xpathValue, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", findElement(driver, xpathValue));
    }

    public void removeAttributeInDOMByJS(WebDriver driver, String xpathValue, String attributeRemove, String... values) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        element = findElement(driver, getDynamicLocator(xpathValue, values));
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    public void scrollIntoBootomPageByJS(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public boolean waitPageLoadedByJS() {
        WebDriverWait explicitWait = new WebDriverWait(webDriver, GlobalConstants.WAIT_OVERRIDE_SHORT_TIMEOUT);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                return true;
            }
        };
        ExpectedCondition<Boolean> jsLoad = driver -> jsExecutor.executeScript("return document.readyState").toString().equals("complete");
        try {
            sleepInSecond(1);
            return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
        } catch (WebDriverException e) {
            if (!e.getMessage().contains("Target frame detached !")) {
                throw new Error(e.getMessage());
            }
        }
        return true;
    }

    public String getElementValidationMessageByJS(WebDriver driver, String xpathValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", findElement(driver, xpathValue));
    }

    public boolean isImageLoadedByJS(WebDriver driver, String xpathValue) {
        element = findElement(driver, xpathValue);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", findElement(driver, xpathValue));
        return status;
    }


    // ====================WINDOWS===========================================

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        WebDriverWait wait = new WebDriverWait(webDriver, GlobalConstants.WAIT_EXPLICIT);
        wait.until(newdriver -> !newdriver.getWindowHandles().equals(parentID));
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public int getlistWindowsTab(WebDriver driver) {
        Set<String> allWindows = driver.getWindowHandles();
        return allWindows.size();
    }

    public void closeCurrentWindows(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.close();
                driver.switchTo().window(runWindows);
            }
        }
    }

    public String getWindowsID(WebDriver driver) {
        String DefaultWindowsID = driver.getWindowHandle();
        return DefaultWindowsID;
    }

    public String getWindowsByIndex(WebDriver driver, int index) {
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        System.out.println(newTab.size());
        String indexWindowsID = String.valueOf(driver.switchTo().window(newTab.get(index)));
        return indexWindowsID;
    }

    public static void waitForTableLoaded(String strXpath, int iLoop, String... arg) throws Exception {
        for (int i = 0; i < iLoop; i++) {
            if (webDriver.findElements(By.xpath(String.format(strXpath, arg))).size() != 0) {
                break;
            } else {
                waitSomeSecond(3);
            }
        }
    }

    public static int getDataOfColumnAtIndex(String xpath, String rowIndex, String columnIndex) throws Exception {
        WebElement columnElement = webDriver.findElement(By.xpath(String.format(xpath, rowIndex, columnIndex)));
        String data = columnElement.getText().replace(",", "");
        return StringUtils.isNotBlank(data) ? Integer.parseInt(data) : 0;
    }

    public void switchToIframe(WebDriver driver, String xpathIframe) {
        driver.switchTo().frame(findElement(driver, xpathIframe));
    }

    public void switchToFrameDefault(WebDriver driver) {
        webDriver.switchTo().defaultContent();
    }

    // wwait for javascript to loaded and jqery loaded
    public void waitLoadedFULL() {
        ExpectedCondition<Boolean> expection = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            wait.until(expection);
            wait.until(jQueryLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang.");
        }
    }
    public WebElement deleteText(WebElement element){
        WebDriverWait wait = new WebDriverWait(webDriver, GlobalConstants.WAIT_EXPLICIT);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        element.sendKeys(Keys.CONTROL,"a");
        element.sendKeys(Keys.DELETE);
        return element;
    }
}

