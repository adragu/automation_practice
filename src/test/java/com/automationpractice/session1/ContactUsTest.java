package com.automationpractice.session1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class ContactUsTest {

    // Initialize Web Driver
    private WebDriver driver;

    @FindBy(xpath="//*[@id=\"contact-link\"]/a")
    private WebElement contactUsButton;

    @FindBy(id="id_contact")
    private WebElement subjects;

    @FindBy(xpath="//*[@id=\"email\"]")
    private WebElement email;

    @FindBy(id="id_order")
    private WebElement orderReference;

    @FindBy(id="fileUpload")
    private WebElement chooseFileButton;

    @FindBy(id="message")
    private WebElement message;

    @FindBy(id="submitMessage")
    private WebElement send;


    @BeforeTest
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        driver = DriverFactory.getDriver(browser);
        PageFactory.initElements(driver, this);
    }


    @Test
    public void contactUsTest() {

        // Create a Logger (instead of writing System.out.println)
        Logger LOG = LoggerFactory.getLogger(this.getClass());

        // The URL which we navigate to
        String projectUrl = System.getProperty("project.url");
        LOG.info("Navigate to {}", projectUrl);
        driver.get(projectUrl);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Navigate to Contact Us page
        LOG.info("contactUsButton.isDisplayed() {}", contactUsButton.isDisplayed());
        contactUsButton.click();

        // Fill in the fields and send the message
        wait.until(ExpectedConditions.elementToBeClickable(email));
        Select subject = new Select(subjects);
        subject.selectByValue("1");
        email.sendKeys("andre@mailinator.com");
        orderReference.sendKeys("0000");
        chooseFileButton.sendKeys("C:\\Users\\ADragu\\test.png");
        message.sendKeys("Some message");
        send.click();

        // Assertion
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"center_column\"]/p")).isDisplayed(), true);

    }
}
