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

public class SignupTest {

    // Initialize Web Driver
    private WebDriver driver;

    @FindBy(css="a.login")
    private WebElement signupButton;

    @FindBy(id="email_create")
    private WebElement createEmailField;

    @FindBy(id="SubmitCreate")
    private WebElement createAccountButton;

    @FindBy(id="id_gender1")
    private WebElement genderMr;

    @FindBy(id="id_gender2")
    private WebElement genderMrs;

    @FindBy(id="customer_firstname")
    private WebElement firstName;

    @FindBy(id="customer_lastname")
    private WebElement lastName;

    @FindBy(id="passwd")
    private WebElement password;

    @FindBy(id="days")
    private WebElement day;

    @FindBy(id="months")
    private WebElement month;

    @FindBy(id="years")
    private WebElement year;

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

    @FindBy (className = "account")
    private WebElement accountLoggedIn;

    @FindBy(css="a.logout")
    private WebElement signoutButton;


    @BeforeTest
    @Parameters("browser")
    public void setup (@Optional("chrome") String browser){
        driver = DriverFactory.getDriver(browser);
        PageFactory.initElements(driver, this);
    }

    @Test
    public void signupTest() {

        // Create a Logger (instead of writing System.out.println)
        Logger LOG = LoggerFactory.getLogger(this.getClass());

        // The URL which we navigate to
        String projectUrl = System.getProperty("project.url");
        LOG.info("Navigate to {}", projectUrl);
        driver.get(projectUrl);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);

        // Go to sign in page by pressing the Sign In button
        LOG.info("signupButton.isDisplayed() {}", signupButton.isDisplayed());
        signupButton.click();

        // Input the e-mail address in the corresponding field then press the Create Account button
        wait.until(ExpectedConditions.elementToBeClickable(createEmailField));
        createEmailField.sendKeys("iepurasul_de_paste@yahoo.com");
        createAccountButton.click();

        // Choose the gender
        wait.until(ExpectedConditions.elementToBeClickable(genderMr));
        genderMr.click();

        // Input the first name, last name and password
        wait.until(ExpectedConditions.elementToBeClickable(firstName));
        firstName.sendKeys("Andreea");
        lastName.sendKeys("Dragu");
        password.sendKeys("MyPa$$w0rdIsStronk");

        // Select the date of birth
        Select dayDropdown = new Select(day);
        Select monthDropdown = new Select(month);
        Select yearDropdown = new Select(year);
        dayDropdown.selectByValue("16");
        monthDropdown.selectByValue("4");
        yearDropdown.selectByIndex(25);

        // Select the two checkboxes
        newsletterCheckbox.click();
        offersCheckbox.click();

        // Input the address section
        addressFirstName.clear();
        addressFirstName.sendKeys("FirstNameTest");
        addressLastName.sendKeys("LastNameTest");
        addressAddress1.sendKeys("Some address, nr 404");
        addressAddress2.sendKeys("Some address, nr 401");
        addressCity.sendKeys("Bear");
        Select addressState = new Select(addressStateSelect);
        addressState.selectByVisibleText("Delaware");
        addressPostcode.sendKeys("40059");
        homePhone.sendKeys("0765432109");
        mobilePhone.sendKeys("0770563728");
        alias.clear();
        alias.sendKeys("My Account");

        // Scroll until element 'register' is visible
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", register);
        register.click();

        // If Sign in is displayed it means the registration is successful
        Assert.assertThrows(NoSuchElementException.class,()->driver.findElement(By.linkText("Sign in")));
        // Assert.assertEquals(driver.findElement(By.linkText("Sign in")).isDisplayed(),false);

        // Log out from account
        signoutButton.click();
    }

    @AfterTest
    public void cleanUp() {
        driver.quit();
    }

}
