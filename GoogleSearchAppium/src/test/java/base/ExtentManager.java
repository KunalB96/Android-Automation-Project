package base;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static ExtentReports extent;
    public static ExtentTest test;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            spark.config().setReportName("Google Search Automation");
            spark.config().setDocumentTitle("Appium Cucumber Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}
