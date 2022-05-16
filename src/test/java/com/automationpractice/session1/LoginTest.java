package com.automationpractice.session1;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {

    // Initialize Web Driver
    private WebDriver driver;

    @FindBy(css="a.login")
    private WebElement signupButton;

    @FindBy(id="email")
    private WebElement emailField;

    @FindBy(id="passwd")
    private WebElement passwordField;

    @FindBy(id="SubmitLogin")
    private WebElement signinButton;

    @FindBy(css="a.logout")
    private WebElement signoutButton;


    @BeforeTest
    @Parameters("browser")
    public void setup (@Optional("chrome") String browser) {
        driver = DriverFactory.getDriver(browser);
        PageFactory.initElements(driver, this);
    }


    @Test
    public void loginTest() {

        // Create a Logger (instead of writing System.out.println)
        Logger LOG = LoggerFactory.getLogger(this.getClass());

        // The URL which we navigate to
        String projectUrl = System.getProperty("project.url");
        LOG.info("Navigate to {}", projectUrl);
        driver.get(projectUrl);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);

        // Go to log in page by pressing the Sign In button
        LOG.info("signupButton.isDisplayed() {}", signupButton.isDisplayed());
        signupButton.click();

        // Input the e-mail address and the password in the corresponding fields then press the Sign In Button
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.clear();
        emailField.sendKeys("andre@mailinator.com");
        passwordField.clear();
        passwordField.sendKeys("MyPa$$w0rdIsStronk");
        signinButton.click();

        // If Sign in is displayed it means the authentication is successful
        Assert.assertThrows(NoSuchElementException.class,()->driver.findElement(By.linkText("Sign in")));
        // Assert.assertEquals(driver.findElement(By.linkText("Sign in")).isDisplayed(),false);

        // Log out from account
        signoutButton.click();
    }

    @AfterTest
    public void cleanUp() {driver.quit();}
}
