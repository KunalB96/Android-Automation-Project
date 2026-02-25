package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPage {

    WebDriver driver;

    By searchBox = By.name("q");
    By results = By.cssSelector("h3");

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchText(String text) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(text);
    }

    public void pressEnter() {
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }

    public boolean isResultsDisplayed() {
        return driver.findElements(results).size() > 0;
    }
}
