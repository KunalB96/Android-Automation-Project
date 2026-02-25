package stepdefinitions;

import base.BaseTest;
import base.ExtentManager;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import pages.GoogleSearchPage;

import static org.junit.Assert.assertTrue;

public class GoogleSteps {

    GoogleSearchPage page;

    @Before
    public void setup(Scenario scenario) throws Exception {

        BaseTest.initializeDriver();

        ExtentManager.extent = ExtentManager.getInstance();
        ExtentManager.test =
                ExtentManager.extent.createTest(scenario.getName());
    }

    @Given("Google page is open in browser")
    public void google_page_is_open_in_browser() {

        BaseTest.driver.get("https://www.google.com");
        page = new GoogleSearchPage(BaseTest.driver);

        ExtentManager.test.info("Opened Google website");
    }

    @When("the search phrase {string} is entered")
    public void the_search_phrase_is_entered(String query) {

        page.enterSearchText(query);
        ExtentManager.test.info("Entered query: " + query);
    }

    @When("enter Key is pressed")
    public void enter_key_is_pressed() {

        page.pressEnter();
        ExtentManager.test.info("Pressed Enter");
    }

    @Then("results should be displayed")
    public void results_should_be_displayed() {

        assertTrue(page.isResultsDisplayed());
        ExtentManager.test.pass("Results displayed successfully");
    }

    @After
    public void tearDown(Scenario scenario) {

        String screenshot =
                BaseTest.captureScreenshot(scenario.getName());

        if (scenario.isFailed()) {
            ExtentManager.test.fail("Test Failed")
                    .addScreenCaptureFromPath(screenshot);
        } else {
            ExtentManager.test.pass("Test Passed")
                    .addScreenCaptureFromPath(screenshot);
        }

        BaseTest.quitDriver();
        ExtentManager.extent.flush();
    }
}
