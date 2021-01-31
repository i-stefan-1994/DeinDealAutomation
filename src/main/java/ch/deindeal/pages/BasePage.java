package ch.deindeal.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected Logger log;

    public BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /**
     Open page with given URL
     */
    protected void openUrl(String url){
        driver.get(url);
    }

    /**
     * Find element by given locator
     * @param locator
     * @return
     */
    protected WebElement find(By locator){
        return driver.findElement(locator);
    }

    /**
     * Return all elements contained in the given locator
     * @param locator
     * @return
     */
    protected List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    /**
     * Finds all elements by given locator
     * @param locator
     * @return
     */
    protected List<WebElement> findAll(By locator){
        return driver.findElements(locator);
    }


    /**
     * Click element when locator is visible
     * @param locator
     */
    protected void click(By locator){
        waitForVisibilityOf(locator, 5);
        find(locator).click();
    }

    /**
     * Input String of text into given field
     * @param text
     * @param locator
     */
    protected void type(String text, By locator){
        waitForVisibilityOf(locator);
        find(locator).sendKeys(text);
    }

    /**
     * Wait for specific ExpectedCondition for the given amount of time in seconds
     * @param condition
     * @param timeOutSeconds
     */
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutSeconds){
        timeOutSeconds = timeOutSeconds != null ? timeOutSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.until(condition);
    }

    /**
     * Wait for visibiliy of element in the given amount of time in seconds
     * @param locator
     * @param timeoutInSeconds
     */
    protected void waitForVisibilityOf(By locator, Integer ... timeoutInSeconds){
        int attempts = 0;
        while(attempts < 2){
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeoutInSeconds.length) > 0 ? timeoutInSeconds[0] : null);
                break;
            }catch (StaleElementReferenceException e){
                e.printStackTrace();
            }
            attempts++;
        }
    }

    /**
     * Returns current URL
     */
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    /**
     * Checks if current URL ends with given String
     * @param end
     * @return
     */
    public boolean urlEndsWith(String end){
        return getCurrentUrl().endsWith(end);
    }

    /**
     * Checks if given element is disabled
     * @param locator
     * @return
     */
    public boolean elementEnablement(By locator){
        return driver.findElement(locator).isEnabled();
    }

    /**
     * Check if an element is selected in a list
     * @param locator
     * @return
     */
    public boolean checkIfElementIsSelectedByVisibleText(By locator){
        WebElement listElement = find(locator);
        return listElement.isEnabled();


    }
}