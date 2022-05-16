package com.automationpractice.session1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {

    // Method to get the path of the Web Drivers
    private static String getPath(){
        File dir = new File("./");
        String path = null;
        try {
            path = dir.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    // Method to initialize Chrome Web Driver
    private static final Supplier<WebDriver> chromeSupplier = ()-> {
        System.setProperty("webdriver.chrome.driver", getPath() + "\\src\\test\\drivers\\chrome100\\chromedriver.exe");
        return new ChromeDriver();
    };

    // Method to initialize Firefox Web Driver
    private static final Supplier<WebDriver> firefoxSupplier = ()-> {
        System.setProperty("webdriver.gecko.driver", getPath() + "\\src\\test\\drivers\\geckodriver.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless");
        return new FirefoxDriver(firefoxOptions);
    };

    // Method to initialize Edge Web Driver
    public static final Supplier<WebDriver> edgeSupplier = () -> {
        System.setProperty("webdriver.edge.driver", getPath() + "\\src\\test\\drivers\\msedgedriver.exe");
        return new EdgeDriver();
    };

    // HashMap to store all the available Web Drivers (ready to use in test)
    private static final Map<String, Supplier<WebDriver>> MAP = new HashMap<>();
    static{
        MAP.put("chrome", chromeSupplier);
        MAP.put("firefox", firefoxSupplier);
        MAP.put("edge",edgeSupplier);
    }

    // Method to get the driver
    public static WebDriver getDriver(String browser){
        return MAP.get(browser).get();
    }

}