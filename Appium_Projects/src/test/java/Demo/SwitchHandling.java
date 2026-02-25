package Demo;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class SwitchHandling {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		  AndroidDriver driver = null;

	        
          UiAutomator2Options options = new UiAutomator2Options();
 
     
          options.setPlatformName("Android");
          options.setAutomationName("UiAutomator2");
          options.setDeviceName("RMX3853");
          options.setPlatformVersion("15");

       //   io.appium.android.apis/io.appium.android.apis.view.Controls1
       //   options.setAppPackage("io.appium.android.apis");
       //   options.setAppActivity("io.appium.android.apis.view.Controls1");
          options.setAppPackage("io.appium.android.apis");
          options.setAppActivity(".ApiDemos");

          options.setAutoGrantPermissions(true);
          options.setIgnoreHiddenApiPolicyError(true);

          // ✅ CORRECT for Dialer
          options.setNoReset(true);
          options.setFullReset(false);
          options.setCapability("appium:forceAppLaunch", true);
          
          URL url = URI.create("http://127.0.0.1:4723/").toURL();
          driver = new AndroidDriver(url, options);
          
          
          Thread.sleep(5000);
          System.out.println("API Demos app launched");

          
       // Views
          driver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
          
          
        // Scroll on web page
          String MobElementToScroll = "Switches";
          
          WebElement SwitchElement = driver.findElement(
        	        AppiumBy.androidUIAutomator(
        	                "new UiScrollable(new UiSelector().scrollable(true))" +
        	                ".scrollIntoView(new UiSelector().text(\"" + MobElementToScroll + "\"))"
        	        )
        	);        
          
          
          SwitchElement.click();
          
          
          //Switches
          WebElement mointoredSwitch = driver.findElement(By.id("io.appium.android.apis:id/monitored_switch"));
         
          
          if(mointoredSwitch.isSelected() == true)
          {
        	  System.out.println("Monitored Switch ON");
          }
          else 
          {
        	  System.out.println("Monitored Switch OFF");
        	  mointoredSwitch.click();
          }
          
          
          
          Thread.sleep(5000);
          
          driver.terminateApp("io.appium.android.apis");
          
          
       
          
          

	}

}
