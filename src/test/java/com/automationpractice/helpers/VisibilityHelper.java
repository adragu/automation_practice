package com.automationpractice.helpers;

import com.automationpractice.runners.Hook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


@Component
public class VisibilityHelper {

    @Autowired
    private Hook hooks;
    private static final Logger LOGGER = LoggerFactory.getLogger(VisibilityHelper.class);
    /**
     * Waits until the given element is visible.
     * The element must be present on the DOM before the waiting starts
     * 
     * @param element Element to check
     */
    public void waitForVisibilityOf(WebElement element) {
        LOGGER.info(new StringBuilder().append("waitForVisibilityOf of `element` ").append(element.toString()).toString());
        hooks.getWait().until(visibilityOf(element));
    }

    /**
     * Waits for presence and visibility of the element matched by given selector.
     * The element can be present in the DOM or not before the waiting starts
     * 
     * @param by Selector of the element
     */
    public void waitForPresenceOf(By by) {
        LOGGER.info(new StringBuilder().append("waitForPresenceOf by ").append(by.toString()).toString());
        hooks.getWait().until(visibilityOfElementLocated(by));
    }
}
