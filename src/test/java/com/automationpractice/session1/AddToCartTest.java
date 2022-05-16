package com.automationpractice.session1;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.time.Duration;

public class AddToCartTest {

    // Initialize WebDriver
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[3]/div/div[1]/div/a[1]/img")
    private WebElement printedDress;

    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[3]/div/div[2]/div[2]/a[2]")
    private WebElement more;

    @FindBy(id="quantity_wanted")
    private WebElement quantity;

    @FindBy(id="group_1")
    private WebElement sizes;

    @FindBy(id="color_13")
    private WebElement color;

    @FindBy(xpath="//*[@id=\"add_to_cart\"]/button")
    private WebElement addToCartButton;

    @FindBy(xpath="//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")
    private WebElement proceedCheckoutButton1;

    @FindBy(xpath = "//*[@id=\"center_column\"]/p[2]/a[1]")
    private WebElement proceedCheckoutButton2;

    @FindBy(id="email")
    private WebElement email;

    @FindBy(id="passwd")
    private WebElement password;

    @FindBy(id="SubmitLogin")
    private WebElement loginButton;

    @FindBy(id="email_create")
    private WebElement emailCreate;

    @FindBy(id="SubmitCreate")
    private WebElement createAccountButton;

    @FindBy(id="id_gender1")
    private WebElement genderMr;

    @FindBy(id="id_gender2")
    private WebElement genderMrs;

    @FindBy(id="customer_firstname")
    private WebElement customerFirstName;

    @FindBy(id="customer_lastname")
    private WebElement customerLastName;

    @FindBy(id="email")
    private WebElement customerEmail;

    @FindBy(id="passwd")
    private WebElement customerPassword;

    @FindBy(id="days")
    private WebElement days;

    @FindBy(id="months")
    private WebElement months;

    @FindBy(id="years")
    private WebElement years;

    @FindBy(id="newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id="optin")
    private WebElement offersCheckbox;

    @FindBy(id="firstname")
    private WebElement addressFirstName;

    @FindBy(id="lastname")
    private WebElement addressLastName;

    @FindBy(id="company")
    private WebElement addressCompany;

    @FindBy(id="address1")
    private WebElement addressAddress1;

    @FindBy(id="address2")
    private WebElement addressAddress2;

    @FindBy(id="city")
    private WebElement addressCity;

    @FindBy(id="id_state")
    private WebElement addressStateSelect;

    @FindBy(id="postcode")
    private WebElement addressPostcode;

    @FindBy (id = "id_country")
    private WebElement addressCountry;

    @FindBy(id="other")
    private WebElement addressInfo;

    @FindBy(id="phone")
    private WebElement homePhone;

    @FindBy(id="phone_mobile")
    private WebElement mobilePhone;

    @FindBy(id="alias")
    private WebElement alias;

    @FindBy(id="submitAccount")
    private WebElement register;

    @FindBy(className = "header_user_info")
    private WebElement userInfo;

    @FindBy(xpath = "//*[@id=\"ordermsg\"]/textarea")
    private WebElement comment;

    @FindBy(xpath = "//*[@id=\"center_column\"]/form/p/button")
    private WebElement proceedCheckoutButton3;

    @FindBy(id="cgv")
    private WebElement agreement;

    @FindBy(xpath = "//*[@id=\"form\"]/p/button")
    private WebElement proceedCheckoutButton4;

    @FindBy(css="a.bankwire")
    private WebElement wirePay;

    @FindBy(xpath = "//*[@id=\"cart_navigation\"]/button")
    private WebElement confirmOrder;

    @BeforeTest
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        driver = DriverFactory.getDriver(browser);
        PageFactory.initElements(driver, this);
    }


    @Test
    public void addToCartLoginTest() throws InterruptedException {

        // Create a Logger (instead of writing System.out.println)
        Logger LOG = LoggerFactory.getLogger(this.getClass());

        // The URL which we navigate to
        String projectUrl = System.getProperty("project.url");
        LOG.info("Navigate to {}", projectUrl);
        driver.get(projectUrl);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);

        // Scroll until element printedDress is visible
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", printedDress);
        js.executeScript("arguments[0].click();", more);

        // Set the quantity, size and color
        wait.until(ExpectedConditions.elementToBeClickable(quantity));
        quantity.clear();
        quantity.sendKeys("3");
        Select size = new Select(sizes);
        size.selectByVisibleText("M");
        color.click();

        // Press the Add to Cart button
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        js.executeScript("arguments[0].scrollIntoView();", addToCartButton);
        addToCartButton.click();

        // Proceed to checkout
        wait.until(ExpectedConditions.elementToBeClickable(proceedCheckoutButton1));
        js.executeScript("arguments[0].click();", proceedCheckoutButton1);

        // Check the order details and proceed further to checkout
        js.executeScript("arguments[0].scrollIntoView();", proceedCheckoutButton2);
        proceedCheckoutButton2.click();

        // Login to continue with the order
        wait.until(ExpectedConditions.elementToBeClickable(email));
        email.sendKeys("andre@mailinator.com");
        password.sendKeys("MyPa$$w0rdIsStronk");
        loginButton.click();

        // Write additional info for order
        wait.until(ExpectedConditions.elementToBeClickable(comment));
        comment.sendKeys("Additional Info for My Order");
        proceedCheckoutButton3.click();

        // Agree to the terms on service and finish the order
        agreement.click();
        proceedCheckoutButton4.click();

        // Paying method
        wirePay.click();
        confirmOrder.click();

        // Assertions
        Assert.assertEquals(driver.findElement(By.linkText("Back to orders")).isDisplayed(),true);
        Assert.assertThrows(NoSuchElementException.class,()->driver.findElement(By.linkText("Sign in")));
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p/strong")).isDisplayed(),true);
    }


    @Test
    public void addToCartSignupTest() {

        // Create a Logger (instead of writing System.out.println)
        Logger LOG = LoggerFactory.getLogger(this.getClass());

        // The URL which we navigate to
        String projectUrl = System.getProperty("project.url");
        LOG.info("Navigate to {}", projectUrl);
        driver.get(projectUrl);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll until element 'register' is visible
        js.executeScript("arguments[0].scrollIntoView();", printedDress);
        js.executeScript("arguments[0].click();", more);

        // Set the quantity, size and color
        wait.until(ExpectedConditions.elementToBeClickable(quantity));
        quantity.clear();
        quantity.sendKeys("3");
        Select size = new Select(sizes);
        size.selectByVisibleText("M");
        color.click();

        // Press the Add to Cart button
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        js.executeScript("arguments[0].scrollIntoView();", addToCartButton);
        addToCartButton.click();

        // Proceed to checkout
        wait.until(ExpectedConditions.elementToBeClickable(proceedCheckoutButton1));
        js.executeScript("arguments[0].click();", proceedCheckoutButton1);

        // Check the order details and proceed further to checkout
        js.executeScript("arguments[0].scrollIntoView();", proceedCheckoutButton2);
        proceedCheckoutButton2.click();

        // Create an account to continue with order
        wait.until(ExpectedConditions.elementToBeClickable(emailCreate));
        emailCreate.sendKeys("johndoe@johndoe.com");
        createAccountButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(genderMr));
        genderMr.click();
        customerFirstName.sendKeys("John");
        customerLastName.sendKeys("Doe");
        customerPassword.sendKeys("MyPa$$w0rdIsStronk");
        Select day = new Select(days);
        Select month = new Select(months);
        Select year = new Select(years);
        day.selectByValue("1");
        month.selectByValue("1");
        year.selectByValue("2001");
        newsletterCheckbox.click();
        offersCheckbox.click();
        addressFirstName.sendKeys("AFirstName");
        addressLastName.sendKeys("ALastName");
        addressCompany.sendKeys("ACompany");
        addressAddress1.sendKeys("Some address, 1");
        addressAddress2.sendKeys("Some address, 2");
        addressCity.sendKeys("City");
        Select state = new Select(addressStateSelect);
        state.selectByVisibleText("Iowa");
        addressPostcode.sendKeys("00000");
        addressInfo.sendKeys("Some Additional Info");
        homePhone.sendKeys("0987654321");
        mobilePhone.sendKeys("0987654321");
        alias.clear();
        alias.sendKeys("My Alias");
        register.click();

        // Write additional info for order
        wait.until(ExpectedConditions.elementToBeClickable(comment));
        comment.sendKeys("Additional Info for My Order");
        proceedCheckoutButton3.click();

        // Agree to the terms on service and finish the order
        agreement.click();
        proceedCheckoutButton4.click();

        // Paying method
        wirePay.click();
        confirmOrder.click();

        // Assertions
        Assert.assertEquals(driver.findElement(By.linkText("Back to orders")).isDisplayed(),true);
        Assert.assertThrows(NoSuchElementException.class,()->driver.findElement(By.linkText("Sign in")));
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p/strong")).isDisplayed(),true);
    }

    @AfterTest
    public void cleanUp() {driver.quit();}
}
