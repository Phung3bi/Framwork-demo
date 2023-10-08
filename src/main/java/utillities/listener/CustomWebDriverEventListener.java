package utillities.listener;

import core.GlobalConstants;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CustomWebDriverEventListener extends AbstractWebDriverEventListener {

    private static final Logger log = Logger.getLogger(CustomWebDriverEventListener.class);

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        GlobalConstants.SYSTEM_LOG_EXCEPTION = throwable.getMessage().trim();
//        log.info("===Exception thrown: " + throwable.getMessage() + "===");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        // Perform any actions before clicking on an element
    }

    // Implement other event listener methods as needed

    // Example: overriding the afterClickOn method
    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
//        System.out.println("Clicked on element: " + element);
    }

    private void saveScreenshot(WebDriver driver) {
        // Cast the WebDriver to TakesScreenshot
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;

        // Capture the screenshot as a file
        File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);
        try {
            // Create a directory to store screenshots (if it doesn't exist)
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdir();
            }
            // Save the screenshot file to the "screenshots" directory
            String screenshotFilePath = "screenshots/screenshot_" + System.currentTimeMillis() + ".png";
            Files.move(screenshotFile.toPath(), new File(screenshotFilePath).toPath());
            System.out.println("Screenshot saved: " + screenshotFilePath);
        } catch (IOException e) {
            System.err.println("Failed to save the screenshot: " + e.getMessage());
        }
    }
}