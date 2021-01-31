package ch.deindeal.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;
    private Logger log;

    public BrowserDriverFactory(String browser, Logger log){
        this.browser = browser.toLowerCase();
        this.log = log;
    }

    public WebDriver createDriver(){
        System.out.println("Create driver: " + browser);

        switch(browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "Resources/Drivers/geckodriver");
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "Resources/Drivers/geckodriver");
                driver.set(new FirefoxDriver());
                break;
            default:
                System.setProperty("webdriver.gecko.driver", "Resources/Drivers/geckodriver");
                driver.set(new FirefoxDriver());
                break;
        }

        return driver.get();
    }
}
