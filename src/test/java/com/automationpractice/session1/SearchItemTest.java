package com.automationpractice.session1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchItemTest {

    // Initialize Web Driver
    private WebDriver driver;

    @FindBy(id="search_query_top")
    private WebElement searchField;

    @FindBy(xpath="//*[@id=\"searchbox\"]/button")
    private WebElement searchButton;

    @FindBy(xpath="//*[@id=\"center_column\"]/ul/li[2]/div/div[1]/div/a[1]/img")
    private WebElement printedDress;


    @BeforeTest
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        driver = DriverFactory.getDriver(browser);
        PageFactory.initElements(driver, this);
    }


    @Test
    public void searchItemTest() {

        // Create a Logger (instead of writing System.out.println)
        Logger LOG = LoggerFactory.getLogger(this.getClass());

        // The URL which we navigate to
        String projectUrl = System.getProperty("project.url");
        LOG.info("Navigate to {}", projectUrl);
        driver.get(projectUrl);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Locate the search field and search button
        LOG.info("searchField.isDisplayed() {}", searchField.isDisplayed());
        LOG.info("searchButton.isDisplayed() {}", searchButton.isDisplayed());

        // Search for the printed dress
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.sendKeys("printed dress");
        searchButton.click();

        // Show the Printed Dress object
        js.executeScript("arguments[0].scrollIntoView();", printedDress);

        // Assertions
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div/div[1]/div/a[1]/img")).isDisplayed(), true);
    }
}
