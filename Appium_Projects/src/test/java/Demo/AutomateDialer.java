package Demo;

import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AutomateDialer {

    public static void main(String[] args) {

        AndroidDriver driver = null;

        try {
            UiAutomator2Options options = new UiAutomator2Options();
   
            /*
            options.setPlatformName("Android");
            options.setAutomationName("UiAutomator2");
            options.setDeviceName("RMX3853");
            options.setPlatformVersion("15");
            options.setAppPackage("com.oplus.dialer");
            options.setAppActivity("com.android.contacts.DialtactsActivityAlias");
            options.setAutoGrantPermissions(true);
         //   options.setNoReset(true);
            options.setIgnoreHiddenApiPolicyError(true);
            
            options.setNoReset(false);
            options.setFullReset(false);
            options.setCapability("appium:forceAppLaunch", true);
   
                  */
            
            options.setPlatformName("Android");
            options.setAutomationName("UiAutomator2");
            options.setDeviceName("RMX3853");
            options.setPlatformVersion("15");

            options.setAppPackage("com.oplus.dialer");
            options.setAppActivity("com.android.contacts.DialtactsActivityAlias");

            options.setAutoGrantPermissions(true);
            options.setIgnoreHiddenApiPolicyError(true);

            // ✅ CORRECT for Dialer
            options.setNoReset(true);
            options.setFullReset(false);
            options.setCapability("appium:forceAppLaunch", true);

            
            

            URL url = URI.create("http://127.0.0.1:4723/").toURL();
            driver = new AndroidDriver(url, options);

            Thread.sleep(5000);
            System.out.println("Dialer app launched");

            // ---- Dial number safely ----
            clickIfPresent(driver, "com.oplus.dialer:id/one");
            clickIfPresent(driver, "com.oplus.dialer:id/eight");
            clickIfPresent(driver, "com.oplus.dialer:id/zero");
            clickIfPresent(driver, "com.oplus.dialer:id/zero");
            clickIfPresent(driver, "com.oplus.dialer:id/eight");
            clickIfPresent(driver, "com.oplus.dialer:id/eight");
            clickIfPresent(driver, "com.oplus.dialer:id/nine");
            clickIfPresent(driver, "com.oplus.dialer:id/nine");
            clickIfPresent(driver, "com.oplus.dialer:id/nine");
            clickIfPresent(driver, "com.oplus.dialer:id/nine");
            clickIfPresent(driver, "com.oplus.dialer:id/nine");

            // ---- Try to connect call ----
            if (isElementPresent(driver, "com.oplus.dialer:id/sim1_dial_btn")) {
                driver.findElement(By.id("com.oplus.dialer:id/sim1_dial_btn")).click();
                System.out.println("Call initiated");
            } else {
                System.out.println("Call button not found. Exiting test.");
                return;
            }

            Thread.sleep(8000);

            // ---- End call safely ----
            driver.pressKey(new KeyEvent(AndroidKey.ENDCALL));
            System.out.println("Call ended");

        } catch (Exception e) {
            System.out.println("❌ Test failed: " + e.getMessage());
        } finally {
            // ---- Cleanup always runs ----
            try {
                if (driver != null) {
                    driver.terminateApp("com.oplus.dialer");
                    driver.quit();
                    System.out.println("Driver session closed");
                }
            } catch (Exception e) {
                System.out.println("Cleanup issue: " + e.getMessage());
            }
        }
    }

    // ---------- Helper Methods ----------

    private static boolean isElementPresent(AndroidDriver driver, String id) {
        try {
            driver.findElement(By.id(id));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private static void clickIfPresent(AndroidDriver driver, String id) {
        if (isElementPresent(driver, id)) {
            driver.findElement(By.id(id)).click();
        } else {
            System.out.println("Element not found: " + id);
        }
    }
}
