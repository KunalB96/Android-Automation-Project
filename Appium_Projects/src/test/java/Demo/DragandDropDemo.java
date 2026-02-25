package Demo;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;


import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

public class DragandDropDemo {

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
          
          
          Thread.sleep(3000);
          System.out.println("API Demos app launched");

          
       // Views
          driver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
          
          
        //select Drag and Drop option
          driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Drag and Drop\"]")).click();
         
          
        //find drag and drop
          WebElement source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
          
          
          WebElement target = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2"));
          
          /*
          //by using touchAction class ----it is old method
        TouchAction action = new TouchAction(driver);
          
   	action.longPress(longPressOptions().withElement(element(source))).moveTo(element(target)).release().perform();
    	
    	
    	*/
         
          
          //Used belwo W3CActions Method:
          
       // Create finger input
          PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

          // Create action sequence
          Sequence dragAndDrop = new Sequence(finger, 1);

          // Move finger to source
          dragAndDrop.addAction(
                  finger.createPointerMove(
                          Duration.ZERO,
                          PointerInput.Origin.fromElement(source),
                          0, 0)
          );

          // Finger down (long press)
          dragAndDrop.addAction(
                  finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
          );

          // Move to target
          dragAndDrop.addAction(
                  finger.createPointerMove(
                          Duration.ofMillis(800),
                          PointerInput.Origin.fromElement(target),
                          0, 0)
          );

          // Finger up (drop)
          dragAndDrop.addAction(
                  finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
          );

          // Perform
          driver.perform(Arrays.asList(dragAndDrop));   
          
    Thread.sleep(2000);
    
    driver.terminateApp("io.appium.android.apis");   
          
          
          
	}

}
