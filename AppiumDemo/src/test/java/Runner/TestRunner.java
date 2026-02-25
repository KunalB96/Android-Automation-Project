package Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"StepDefination", "tests"},
      //  plugin = {"pretty"},
        plugin = {"pretty","html:target/cucumber-reports/reports_html.html"},
        dryRun = false,
        monochrome = true
)
public class TestRunner {
}
 