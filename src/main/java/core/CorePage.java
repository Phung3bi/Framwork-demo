package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.TemplatePage;
import pages.TemplatePage;
import pages.TemplatePage;
import pages.demoqa.ElementsPage;
import pages.demoqa.HomePage;

import java.util.Properties;

public class CorePage {
    public static WebDriver webDriver = null;
    public static WebDriverWait webDriverWait = null;
    public static RemoteWebDriver webDriverRemote = null;
//    public Scenario scenario = null;

    public static Properties props = new Properties();


    //====================DEMO

   public static TemplatePage templatePage;
   public static HomePage homePage;
   public static ElementsPage elementsPage;


    // Web Element
    public static String textDiv = "//div[text()='%s']";

    protected static void initWebPage() {
       templatePage = new TemplatePage();
       homePage= new HomePage();
       elementsPage = new ElementsPage();

    }

    protected static void initWebElement() {
        PageFactory.initElements(webDriver, templatePage);
        PageFactory.initElements(webDriver,homePage);
        PageFactory.initElements(webDriver,elementsPage);
    }
}
