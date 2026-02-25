package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;

public class BaseTest {

    public static AndroidDriver driver;

    public static void initializeDriver() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setDeviceName("AndroidDevice");
        options.setAutomationName("UiAutomator2");

        // IMPORTANT
        options.withBrowserName("Chrome");

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static String captureScreenshot(String name) {

        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            String path = "test-output/screenshots/" + name + ".png";

            FileUtils.copyFile(src, new File(path));

            return path;

        } catch (Exception e) {
            return null;
        }
    }
}
