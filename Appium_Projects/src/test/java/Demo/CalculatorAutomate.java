package Demo;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class CalculatorAutomate {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		
		UiAutomator2Options options = new UiAutomator2Options();

		options.setPlatformName("Android");
		options.setAutomationName("UiAutomator2");
		options.setDeviceName("RMX3853");
		options.setPlatformVersion("15");

		// Realme Calculator
		options.setAppPackage("com.coloros.calculator");
		options.setAppActivity("com.android.calculator2.Calculator");
		options.setAppWaitActivity("*");
		options.setAutoGrantPermissions(true);
		options.setNoReset(true);
		options.setFullReset(false);
		options.setAutoGrantPermissions(true);
		options.setIgnoreHiddenApiPolicyError(true);


		// Try this URL first
		URL url = new URL("http://127.0.0.1:4723/");

		// If 404, use:
		// URL url = new URL("http://127.0.0.1:4723/wd/hub");

		AndroidDriver driver = new AndroidDriver(url, options);
	        
	        Thread.sleep(5000);

			System.out.println("Application Started");

			// click on number 8
			WebElement num8 = driver.findElement(By.id("com.coloros.calculator:id/digit_8"));
			num8.click(); // perform click opration

			// click on plus sign
			WebElement plusicon = driver.findElement(By.id("com.coloros.calculator:id/img_op_add"));
			plusicon.click();

			// click on number 2
			WebElement num2 = driver.findElement(By.id("com.coloros.calculator:id/digit_2"));
			num2.click(); // perform click opration

			// click on equal option
			WebElement equal = driver.findElement(By.id("com.coloros.calculator:id/img_eq"));
			equal.click(); // perform click opration

			// check for result

			WebElement result = driver.findElement(By.id("com.coloros.calculator:id/result"));
			String resultstring = result.getText();
			
			if(resultstring.equals("10"))
			{
				System.out.println("Pass");
			}
			else
			{
				System.out.println("Fail");
			}
			
			// Force close app in Realme / ColorOS
			driver.terminateApp("com.coloros.calculator");

			//Quit Session
			driver.quit(); // close application 

	             
	}

}
