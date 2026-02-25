package StepDefination;

import pages.GoogleSearchPage;
import tests.CucumberBaseClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.*;

public class GoggleSearchSteps {

    GoogleSearchPage page;

    @Given("Google page is open in browser")
    public void google_page_is_open_in_browser() throws InterruptedException {

        // Open Google
        CucumberBaseClass.driver.get("https://www.google.com");

        // Wait for page to load
        Thread.sleep(5000);

        // Print all available contexts
        for (String context : CucumberBaseClass.driver.getContextHandles()) {
            System.out.println("Available context: " + context);
        }

        // Switch to WEBVIEW
        for (String context : CucumberBaseClass.driver.getContextHandles()) {
            if (context.contains("WEBVIEW")) {
                CucumberBaseClass.driver.context(context);
                break;
            }
        }

        System.out.println("Current context: " + 
                CucumberBaseClass.driver.getContext());

        // Initialize page object AFTER switching
        page = new GoogleSearchPage(CucumberBaseClass.driver);

        CucumberBaseClass.test.info("Opened Google and switched to WebView");
    }


    @When("the search phrase {string} is entered")
    public void the_search_phrase_is_entered(String searchData) {

    	 WebDriverWait wait = new WebDriverWait(
    	            CucumberBaseClass.driver, Duration.ofSeconds(15));

    	    wait.until(ExpectedConditions
    	            .visibilityOfElementLocated(By.cssSelector("textarea[name='q']")));
    	    
        page.EnterSearchData(searchData);
        CucumberBaseClass.test.info("Entered search data: " + searchData);
    }

    @When("enter Key is pressed")
    public void enter_key_is_pressed() {

        page.PressEnterKeyonSearchBox();
        CucumberBaseClass.test.info("Pressed Enter key");
    }

    @Then("results for {string} are shown")
    public void results_for_are_shown(String text) {

        if (CucumberBaseClass.driver.getPageSource().contains(text)) {
            CucumberBaseClass.test.pass("Results displayed successfully");
        } else {
            CucumberBaseClass.test.fail("Results not found");
        }
    }
}
