package steps;

import core.CorePage;
import core.Keyword;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.en.And;
import org.apache.log4j.Logger;
import org.junit.Assert;
import pagesDynamicLocator.BasePageLocator;
import pages.TemplatePage;

public class TemplateSteps extends CorePage {

    private static final Logger log = Logger.getLogger(TemplateSteps.class);
    private Scenario scenario;

    //============================ELEMENT========================================


    //============================TEMPLATE FUNCTION===========================================================


    @And("CLICK TO DIV ALERT BY CONTAINS TEXT {string}")
    public void cLICKTOALERTBYCONTAINSTEXT(String text) {

        try {
            templatePage.waitElementVisibleWithFluentWait(webDriver, BasePageLocator.DYNAMIC_SELECT_BY_NAME, text);
            templatePage.clickToDynamicDivByContainsText(webDriver, text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @And("CLICK TO ITEM ALERT BY CONTAINS TEXT {string}")
    public void clickTOITEMALERTBYCONTAINSTEXT(String text) {
        try {
            templatePage.waitElementVisibleWithFluentWait(webDriver, BasePageLocator.DYNAMIC_ITEM_CONTAINS_TEXT, text);
            templatePage.clickToDynamicDivByContainsText(webDriver, text);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("loi cmnr");
        }
    }

//    @And("I Open {string} page")
//    public void iOpenPage(String textPage) {
//        templatePage.clickToDynamicDivByContainsText(webDriver, textPage);
//        templatePage.waitForLoadPage();
//    }

    @And("I Open Exactly {string} page")
    public void iOpenPageExactly(String textPage) {
        templatePage.clickToDynamicDivByContainsText(webDriver, textPage);
        templatePage.waitForLoadPage();
    }

    @And("I INPUT TO TEXTBOX BY ID {string} WITH DATA {string}")
    public void iINPUTTOTEXTBOXBYIDWITHDATA(String id, String data) {
        templatePage.clickToDynamicDivByContainsText(webDriver, data);
    }

    @And("I INPUT TO TEXTBOX BY NAME {string} WITH DATA {string}")
    public void iINPUTTOTEXTBOXBYNAMEWITHDATA(String name, String data) {
        templatePage.clickToDynamicDivByContainsText(webDriver, data);
    }

    @And("I INPUT TO TEXTBOX BY FORMCONTROLNAME {string} WITH DATA {string}")
    public void iINPUTTOTEXTBOXBYFORMCONTROLNAMEWITHDATA(String formcontrolname, String data) {
        templatePage.clickToDynamicDivByContainsText(webDriver, data);
    }

    @And("I INPUT TO TEXTBOX BY PLACEHOLDER {string} WITH DATA {string}")
    public void iINPUTTOTEXTBOXBYPLACEHOLDERWITHDATA(String placeholder, String data) {
        templatePage.clickToDynamicDivByContainsText(webDriver, data);
    }

    @And("I INPUT TO TEXTBOX BY VALUE {string} WITH DATA {string}")
    public void iINPUTTOTEXTBOXBYVALUEWITHDATA(String value, String data) {
        templatePage.clickToDynamicDivByContainsText(webDriver, data);
    }

    @And("I CLICK TO TEXTBOX BY ID {string}")
    public void iCLICKTOTEXTBOXBYID(String id) {
        templatePage.clickToDynamicDivByContainsText(webDriver, id);
    }

    @And("I CLICK TO TEXTBOX BY NAME {string}")
    public void iCLICKTOTEXTBOXBYNAME(String name) {
        templatePage.clickToDynamicDivByContainsText(webDriver, name);;
    }

    @And("I CLICK TO TEXTBOX BY CONTAINS TEXT {string}")
    public void iCLICKTOTEXTBOXBYCONTAINSTEXT(String text) {
        templatePage.clickToDynamicDivByContainsText(webDriver, text);
    }

    @And("I CLICK TO TEXTBOX BY TEXT {string}")
    public void iCLICKTOTEXTBOXBYTEXT(String text) {
        templatePage.clickToDynamicDivByContainsText(webDriver, text);
    }

    @And("I CLICK TO TEXTBOX BY VALUE {string}")
    public void iCLICKTOTEXTBOXBYVALUE(String value) {
        templatePage.clickToDynamicDivByContainsText(webDriver, value);
    }

    @And("I CLICK TO BUTTON BY ID {string}")
    public void iCLICKTOBUTTONBYID(String id) {
        templatePage.clickToDynamicDivByContainsText(webDriver, id);
    }

    @And("I CLICK TO BUTTON BY VALUE {string}")
    public void iCLICKTOBUTTONBYVALUE(String value) {
        templatePage.clickToDynamicDivByContainsText(webDriver, value);
    }

    @And("I CLICK TO BUTTON BY NAME {string}")
    public void iCLICKTOBUTTONBYNAME(String name) {
        templatePage.clickToDynamicDivByContainsText(webDriver, name);
    }

    @And("I CLICK TO BUTTON BY TITLE {string}")
    public void iCLICKTOBUTTONBYTITLE(String title) {
        templatePage.clickToDynamicDivByContainsText(webDriver, title);
    }

    @And("I CLICK TO BUTTON BY CONTAINS TEXT {string}")
    public void iCLICKTOBUTTONBYCONTAINSTEXT(String text) {
        templatePage.clickToDynamicDivByContainsText(webDriver, text);
    }

    @And("I CLICK TO BUTTON BY SPAN TEXT {string}")
    public void iCLICKTOBUTTONBYSPANTEXT(String text) {
        templatePage.clickToDynamicDivByContainsText(webDriver, text);
    }

    @And("I CLICK TO BUTTON BY SPAN CONTAINS TEXT {string}")
    public void iCLICKTOBUTTONBYSPANCONTAINSTEXT(String text) {
        templatePage.clickToDynamicDivByContainsText(webDriver, text);
    }


    @And("VERIFY DIV BY CONTAINS TEXT WITH MESSAGE {string} DISPLAYED SUCCESS")
    public void verifyDIVBYCONTAINSTEXTWITHMESSAGEDISPLAYEDSUCCESS(String message) {
    //    Assert.assertTrue(templatePage.clickToDynamicDivByContainsText(webDriver, message));

    }

    @And("VERIFY ITEM BY CONTAINS TEXT WITH MESSAGE {string} DISPLAYED SUCCESS")
    public void verifyITEMBYCONTAINSTEXTWITHMESSAGEDISPLAYEDSUCCESS(String message) {
     //   Assert.assertTrue(templatePage.clickToDynamicDivByContainsText(webDriver, message));
    }

    @And("VERIFY LINK BY CONTAINS TEXT WITH MESSAGE {string} DISPLAYED SUCCESS")
    public void verifyLINKBYCONTAINSTEXTWITHMESSAGEDISPLAYEDSUCCESS(String message) {

    }


    @And("I CLICK TO LINK BY CONTAIN TEXT {string}")
    public void iCLICKTOLINKBYCONTAINTEXT(String text) {
        templatePage.clickToDynamicDivByContainsText(webDriver, text);
    }

    @And("I CLICK TO ITEM BY CONTAIN TEXT {string}")
    public void iCLICKTOITEMBYCONTAINTEXT(String text) {
        templatePage.clickToDynamicDivByContainsText(webDriver, text);
    }

}
