package Demo;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class SwipOrScroll {

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

          
        //Get Screen size
  		Dimension size = driver.manage().window().getSize();
  		
  		//find the position where you need to touch
  		int startX = size.getWidth() / 2;
  		int startY = size.getHeight() / 2;
  		
  		//position till you want to move your finger to swipe
  		int endX = startX;
  		int endY = (int) (size.getHeight() * 0.25);
  		
  		//PointerInput class to create a sequence of actions 
  		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
  		
  		//Sequence object, which is a list of actions that will be performed on the device
  		Sequence sequence = new Sequence(finger1, 1)
  				.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
  				.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
  				.addAction(new Pause(finger1, Duration.ofMillis(200))) //wait for some time
  				.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
  				.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); 

  		//perform the Sequence of action
  		driver.perform(Collections.singletonList(sequence));
  		
  		Thread.sleep(2000);
  		driver.terminateApp("io.appium.android.apis");

          
          
          
	}

}
