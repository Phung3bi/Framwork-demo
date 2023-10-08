package runner;

import core.GlobalConstants;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.*;
import org.junit.runner.RunWith;
import utillities.DateTimeUtil;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static core.Common.*;


@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, glue = {"steps"}, plugin = {"pretty", "json:target/cucumber-reports/cucumber.json", "json:reports/cucumber.json", "html:target/cucumber-reports"})

public class Runner {
    private static String strOutputDirectory = null;
    private static File outputDirectory = null;
    private static String sExecuteTime = null;

    private static final Logger log = Logger.getLogger(Runner.class);

    public static final String application = System.getProperty("application");

    @BeforeClass
    public static void setUp() throws Exception {
        sExecuteTime = getTime();
        strOutputDirectory = "reports/" + sExecuteTime;
        outputDirectory = new File(strOutputDirectory);

        GlobalConstants.DEMO_QA = application;
                //!= null ? application : "demo";
        String log4jConfPath = "src/test/resources/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        RestAssured.useRelaxedHTTPSValidation();

    }

    @AfterClass
    public static void teardown() throws Exception {
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("reports/cucumber.json");
        Configuration config = new Configuration(outputDirectory, "Automation-Framework");
        config.addClassifications("Environment", "demo Environment");
        config.addClassifications("Owner", "Phung Le");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
        reportBuilder.generateReports();
        }

    private static String getComputerName() {
        String computerName = "";
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            computerName = localhost.getHostName();
        } catch (UnknownHostException e) {
            log.info(e.getMessage());
        }
        System.out.println("Computer Name = " + computerName);
        return computerName;
    }
}