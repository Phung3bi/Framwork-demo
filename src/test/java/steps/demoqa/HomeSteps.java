package steps.demoqa;

import core.Common;
import core.CorePage;
import core.GlobalConstants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import languges.Vietnamese;
import org.apache.log4j.Logger;
import org.junit.Assert;
import pages.demoqa.HomePage;
import pagesDynamicLocator.BasePageLocator;

import java.util.List;
import java.util.Map;


public class HomeSteps extends CorePage {
    private static final Logger log = Logger.getLogger(HomeSteps.class);

    @Given("I Open {string} page")
    public void iOpenPage(String textPage) throws Exception{
        log.info(" ==== Toi mo demoqa tren trinh duyet ==== ");
        HomePage.openDemoQaOnBrowser();
    }
    @And("I click to button by contains text {string}")
    public void ClickToModule(String module) {
        log.info(" ==== Toi Vao Menu ==== ");
        homePage.clickToModule(module);
        homePage.waitForLoadPage(GlobalConstants.WAIT_EXPLICIT);
    }

}
