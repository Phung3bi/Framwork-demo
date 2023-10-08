package steps;

import config.BrowserList;
import core.CorePage;
import core.GlobalConstants;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import java.time.Duration;
import java.time.Instant;


import org.openqa.selenium.support.events.EventFiringWebDriver;
import utillities.listener.CustomWebDriverEventListener;


public class Hooks extends CorePage {
    private static final Logger log = Logger.getLogger(Hooks.class);

    //đọc và ghi từ file excecl
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow currentRow;
    public XSSFRow headerRow;
    public XSSFCell cell;

    //thời gian
    Instant endTime;
    Instant startTime;

    //==============SYSTEM PROPERTIES INPUT WHEN RUN CMD======================
    private static final String mode = System.getProperty("mode");
    private static final String environment = System.getProperty("environment");


    //====== DEFINE HOOK========================================


    @Before(order = 1)
    public void beforeScenario() throws Exception {
        log.info(" === This will run before the scenario === ");
        startTime = Instant.now();

        if (GlobalConstants.DEMO_QA.equalsIgnoreCase("demoQA")) {
            if (GlobalConstants.SYSTEM_WEBDRIVER_INITIALIZED) {
                log.info("Webdriver is already initialized");
            } else {
                webDriver = null;
            }
        } else {
            webDriver = null;
        }
        getParamsPropertiesFile(environment);
        openAndQuitBrowser(mode);
        BrowserList browser = BrowserList.valueOf(mode.toUpperCase());
        // customise the capabilities log level
        // Wrap the WebDriver instance with EventFiringWebDriver
        //theo doi hoatj dong trinh duyet
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(webDriver);
        // Register the custom event listener
        eventDriver.register(new CustomWebDriverEventListener());
        webDriver = eventDriver;

        switch (browser) {
            case API:
                break;
            case IOS:
                break;
            case ANDROID:
                break;
            default:
                initWebPage();
                initWebElement();
                break;
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        log.info(" === This will run after the scenario === ");

        // === GET JIRA TIME
        endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);
        String JiraKey = "";
        String JiraSummary = "";
        //==========================

        BrowserList browser = BrowserList.valueOf(mode.toUpperCase());

        switch (browser) {
            case API:
                log.info("Completed api testing .... !");
                break;
            default:
                if (scenario.isFailed()) {
                    Path screenshotPath = Path.of("");
                    try {
                        final byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
                        scenario.embed(screenshot, "image/png");
                        // Save the screenshot as a file
                        String screenshotName = "failed_scenario_screenshot.png";
                        screenshotPath = Paths.get(System.getProperty("user.dir") + "/reports", screenshotName);
                        Files.write(screenshotPath, screenshot);

                    } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                        log.error(somePlatformsDontSupportScreenshots.getMessage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
        }

    }

    public static void getParamsPropertiesFile(String p_environment) throws Exception {
        Properties prop = new Properties();
        FileInputStream ip;
        if (p_environment.equalsIgnoreCase("staging")) {
            ip = new FileInputStream("src/test/resources/staging.properties");
        } else if (p_environment.equalsIgnoreCase("beta")) {
            ip = new FileInputStream("src/test/resources/beta.properties");
        } else if (p_environment.equalsIgnoreCase("demo")) {
            ip = new FileInputStream("src/test/resources/demo.properties");
        }else {
            ip = new FileInputStream("src/test/resources/production.properties");
        }
        prop.load(ip);

        // ==========GET SOME PROPERTIE
        GlobalConstants.LINK_DEMOQA_PAGE = prop.getProperty("link_demo_page");
        GlobalConstants.DEMOQA_ENVIRONMENT = p_environment.toLowerCase();


        ip.close();
    }


    public synchronized static WebDriver openAndQuitBrowser(String browserName) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        String workingDir = System.getProperty("user.dir");
        if (webDriver == null) {
            try {
                if (browser == null) {
                    browser = BrowserList.CHROME;
                }
                switch (browser) {
                    case CHROME:
//                        WebDriverManager.chromedriver().setup();

                        String pathos = "/src/test/resources/chromedriver";
                        String pathwin = "\\src\\test\\resources\\chromedriver.exe";
                        String os = System.getProperty("os.name");
                        if (os.contains("Windows")) {
                            System.setProperty("webdriver.chrome.driver", workingDir + pathwin);
                        }else {
                            System.setProperty("webdriver.chrome.driver", workingDir + pathos);
                        }
                        ChromeOptions options = new ChromeOptions();
                        if (os.contains("Windows")){
                            options.setBinary("C:\\chrome\\chrome.exe");
                        } else {
                            options.setBinary("/Users/v.phunglk2/Documents/software/chrome-mac-arm64/Google Chrome for Testing");
                        }

//                        System.setProperty("webdriver.chrome.driver", workingDir + "/src/test/resources/chromedriver");
//                        ChromeOptions options = new ChromeOptions();
//                        options.setBinary("/Users/v.phunglk2/Documents/software/chrome-mac-arm64/Google Chrome for Testing");
//                        System.setProperty("webdriver.chrome.driver", workingDir + "\\src\\test\\resources\\chromedriver.exe");
//                        ChromeOptions options = new ChromeOptions();
//                        options.setBinary("C:\\chrome\\chrome.exe");
                        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                        options.addArguments("--incognito");
                        webDriver = new ChromeDriver(options);
                        break;
                    case H_CHROME:
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions h_options = new ChromeOptions();
                        h_options.addArguments("headless");
                        //options.addArguments("windows-size=1920x1080");
                        webDriver = new ChromeDriver(h_options);
                        break;
                    case H_FIREFOX:
                        WebDriverManager.firefoxdriver().setup();
                        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
                        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, workingDir + "\\FirefoxUILog.txt");
                        FirefoxOptions fh_options = new FirefoxOptions();
                        fh_options.setHeadless(true);
                        webDriver = new FirefoxDriver(fh_options);
                        break;
                    case FIREFOX:
                        WebDriverManager.firefoxdriver().setup();
                        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
                        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, workingDir + "\\FirefoxUILog.txt");
                        FirefoxOptions f_options = new FirefoxOptions();
//                        f_options.addArguments("-private");
                        webDriver = new FirefoxDriver(f_options);
                        break;
                    case CHEADLESS:
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions ch_options = new ChromeOptions();
                        ch_options.addArguments("headless");
                        ch_options.addArguments("windows-size=1920x1080");
                        webDriver = new ChromeDriver(ch_options);
                        break;
                    case API:
                        break;
                    default:
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions df_options = new ChromeOptions();
                        df_options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        df_options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                        df_options.addArguments("--incognito");
                        webDriver = new ChromeDriver(df_options);
                        break;
                }

            } catch (UnreachableBrowserException e) {
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
            } catch (WebDriverException e) {
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
            webDriver.manage().window().maximize();
            webDriver.manage().deleteAllCookies();
            webDriver.manage().timeouts().implicitlyWait(GlobalConstants.WAIT_IMPLICIT, TimeUnit.SECONDS);
        } else {
            log.info("------------- The browser is already opened -------------");
        }
        return webDriver;
    }

    private static class BrowserCleanup implements Runnable {
        @Override
        public void run() {
            close();
        }
    }

    public static void close() {
        try {
            if (webDriver != null) {
                openAndQuitBrowser(mode).quit();
                log.info("------------- Closed the browser -------------");
            }
        } catch (UnreachableBrowserException e) {
            System.out.println("Can not close the browser");
        }
    }


}