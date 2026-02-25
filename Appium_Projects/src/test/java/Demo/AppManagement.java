package Demo;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppManagement {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		
		  AndroidDriver driver = null;

	        
          UiAutomator2Options options = new UiAutomator2Options();
 
     
          options.setPlatformName("Android");
          options.setAutomationName("UiAutomator2");
          options.setDeviceName("RMX3853");
          options.setPlatformVersion("15");

      //    options.setAppPackage("io.appium.android.apis");
      //    options.setAppActivity(".ApiDemos");

          options.setAutoGrantPermissions(true);
          options.setIgnoreHiddenApiPolicyError(true);

          // ✅ CORRECT for Dialer
          options.setNoReset(true);
          options.setFullReset(false);
          options.setCapability("appium:forceAppLaunch", true);
          
          URL url = URI.create("http://127.0.0.1:4723/").toURL();
          driver = new AndroidDriver(url, options);
          
          
          Thread.sleep(3000);
       //   System.out.println("API Demos app launched");
          String packageName = "io.appium.android.apis";
          
          //Remove app
          driver.removeApp(packageName);
          
          //check app is already install or not
         if(! driver.isAppInstalled(packageName))
         {
        	  //install application
             driver.installApp("C:\\Users\\KUNAL\\Downloads\\ApiDemos-debug.apk");
             System.out.println("App successfully Installed");
         }
         else
         {
        	 System.out.println("App already Installed");
         }
          
          
          //Activate the application
          driver.activateApp(packageName);
          
          Thread.sleep(2000);
          driver.terminateApp(packageName);
          
          
         
          
          

	}

}
