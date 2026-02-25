package Demo;

import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class CalculatorAutomateExcel {

    public static void main(String[] args) throws Exception {

        ExcelUtils.loadExcel("D:\\Android Automation Project\\Appium_Projects\\src\\test\\java\\Demo\\testdata.xlsx",
                "Sheet1");

        // 👉 Start Appium driver only ONCE
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("RMX3853");
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setPlatformVersion("15");
        options.setAppPackage("com.coloros.calculator");
        options.setAppActivity("com.android.calculator2.Calculator");
        options.setNoReset(true);
        options.setFullReset(false);
        options.setAutoGrantPermissions(true);
        options.setIgnoreHiddenApiPolicyError(true);

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);


        Thread.sleep(1500);
        printAllButtons(driver);

        // 👉 LOOP through Excel rows (driver stays open)
        for (int i = 1; i < ExcelUtils.getRowCount(); i++) {

            int num1 = (int) Double.parseDouble(ExcelUtils.getCell(i, 0));
            String op = ExcelUtils.getCell(i, 1).trim();
            int num2 = (int) Double.parseDouble(ExcelUtils.getCell(i, 2));

            // Reset Calculator screen
            clearScreen(driver);

            clickDigits(driver, num1);
            clickOperator(driver, op);
            clickDigits(driver, num2);

            driver.findElement(By.id("com.coloros.calculator:id/img_eq")).click();
            Thread.sleep(800);

            WebElement resultEl = driver.findElement(By.id("com.coloros.calculator:id/result"));
            String actualText = resultEl.getText().trim();

            int actual = Integer.parseInt(actualText);
            int expected = calculate(num1, num2, op);

            String status = (actual == expected) ? "PASS" : "FAIL";

            System.out.println(num1 + " " + op + " " + num2 + " = " + actual + " → " + status);

            ExcelUtils.setCell(i, 3, String.valueOf(expected));
            ExcelUtils.setCell(i, 4, String.valueOf(actual));
            ExcelUtils.setCell(i, 5, status);
        }

        ExcelUtils.saveExcel();

        // 👉 Now close calculator ONLY ONCE
        driver.terminateApp("com.coloros.calculator");
        driver.quit();

        System.out.println("✓ All Operations Completed — Calculator Closed.");
    }

    private static void clickDigits(AndroidDriver driver, int num) {
        for (char d : String.valueOf(num).toCharArray()) {
            driver.findElement(By.id("com.coloros.calculator:id/digit_" + d)).click();
        }
    }

    private static void clickOperator(AndroidDriver driver, String op) {
        switch (op) {
            case "+":
                driver.findElement(By.id("com.coloros.calculator:id/img_op_add")).click();
                break;
            case "-":
                driver.findElement(By.id("com.coloros.calculator:id/img_op_sub")).click();
                break;
            case "*":
                driver.findElement(By.id("com.coloros.calculator:id/img_op_mul")).click();
                break;
            case "/":
                driver.findElement(By.id("com.coloros.calculator:id/img_op_div")).click();
                break;
            case "%":
                driver.findElement(By.id("com.coloros.calculator:id/img_op_pct")).click();
                break;
        }
    }

    private static int calculate(int a, int b, String op) {
        return switch (op) {
        case "+" -> a + b;
        case "-" -> a - b;
        case "*" -> a * b;
        case "/" -> a / b;
        case "%" -> a % b;
        default -> 0;
        };
    }

    private static void clearScreen(AndroidDriver driver) {
        try {
            driver.findElement(By.id("com.coloros.calculator:id/img_clr")).click();
        } catch (Exception e) {
            System.out.println("Clear button not found, ignoring...");
        }
    }
    
    private static void printAllButtons(AndroidDriver driver) {
        System.out.println("\n===== AVAILABLE BUTTONS ON DEVICE =====");
        for (WebElement el : driver.findElements(By.className("android.widget.Button"))) {
            System.out.println(el.getAttribute("resource-id") + " → " + el.getText());
        }
        System.out.println("=======================================\n");
    }

}
