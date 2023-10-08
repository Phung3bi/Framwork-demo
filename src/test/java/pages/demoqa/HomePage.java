package pages.demoqa;

import core.GlobalConstants;
import core.Keyword;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static core.CorePage.webDriver;
import static core.Keyword.clickWebElement;

public class HomePage extends Keyword {
    private static final Logger log = Logger.getLogger(HomePage.class);

    @FindBy(xpath = "//a[@class='fixed-content-sidebar__brand-anchor']")
    private WebElement linkElement;

    public static void openDemoQaOnBrowser() {
        log.info("test1");
        webDriver.get(GlobalConstants.LINK_DEMOQA_PAGE);
    }


    public void clickToModule(String module) {
        clickToLinkByContainsText(webDriver, module);
    }
}
