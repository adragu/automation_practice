package com.automationpractice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HomePage implements BasePage {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

    @FindBy(how = How.CSS, using = "#search_form_input_homepage")
    private WebElement searchInput;

    @FindBy(how = How.CSS, using = "#search_button_homepage")
    private WebElement searchButton;

    public void inputSearch(String search){
        LOGGER.info("Input search query: " + search + " using locator: " + searchInput.toString());
        searchInput.sendKeys(search);
    }

    public void pressSearchButton(){
        LOGGER.info("Clicking on search button using locator: " + searchButton.toString());
        searchButton.click();
    }
}