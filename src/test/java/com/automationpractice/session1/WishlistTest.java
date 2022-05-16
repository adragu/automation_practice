package com.automationpractice.session1;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class WishlistTest {

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

    @FindBy(xpath="//*[@id=\"center_column\"]/div/div[2]/ul/li/a")
    private WebElement wishlistButton;

    @FindBy(id="name")
    private WebElement wishlistName;

    @FindBy(id="submitWishlist")
    private WebElement wishlistSubmit;

    @FindBy(xpath="//*[@id=\"mywishlist\"]/ul/li[2]/a")
    private WebElement homeButton;

    @FindBy(xpath="//*[@id=\"block-history\"]/table")
    private WebElement wishlists;

    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[3]/div/div[1]/div/a[1]/img")
    private WebElement printedDress;

    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[3]/div/div[2]/div[2]/a[2]")
    private WebElement more;

    @FindBy(xpath="//*[@id=\"buy_block\"]/div/div[3]/p")
    private WebElement addToWishlist;

    @FindBy(css="a.account")
    private WebElement account;

    @FindBy(css="a.home")
    private WebElement home;

    @FindBy(xpath="//*[@id=\"product\"]/div[2]/div/div/a")
    private WebElement close;


    @BeforeTest
    @Parameters("browser")
    public void setup (@Optional("chrome") String browser) {
        driver = DriverFactory.getDriver(browser);
        PageFactory.initElements(driver, this);
    }


    @Test
    public void createWishlistTest() {

        // Create a Logger (instead of writing System.out.println)
        Logger LOG = LoggerFactory.getLogger(this.getClass());

        // The URL which we navigate to
        String projectUrl = System.getProperty("project.url");
        LOG.info("Navigate to {}", projectUrl);
        driver.get(projectUrl);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Actions action = new Actions(driver);

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

        // Access the wishlist button and create a wishlist
        wishlistButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(wishlistName));
        wishlistName.sendKeys("My Favourite Wishlist");
        wishlistSubmit.click();
        action.moveToElement(wishlists).perform();

        // Assertions
        Assert.assertThrows(NoSuchElementException.class,()->driver.findElement(By.linkText("Sign in")));
        Assert.assertEquals(driver.findElement(By.linkText("My Favourite Wishlist")).isDisplayed(),true);

    }


    @Test
    public void addToWishlist() {

        // Create a Logger (instead of writing System.out.println)
        Logger LOG = LoggerFactory.getLogger(this.getClass());

        // The URL which we navigate to
        String projectUrl = System.getProperty("project.url");
        LOG.info("Navigate to {}", projectUrl);
        driver.get(projectUrl);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions action = new Actions(driver);

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

        // Go to homepage and add an item to wishlist
        home.click();
        action.moveToElement(printedDress).perform();
        more.click();
        addToWishlist.click();

    }
}
