package ch.deindeal.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePageMethods {

    String pageUrl = "https://www.deindeal.ch/fr/";
    private By foodDeliveryLocator = By.xpath("//a[@class='withIcon']");
    private By connectLocator = By.xpath("//a[@class='signin hide-for-small-only']");
    private By newAccountLocator = By.xpath("//body[@class='my-customchannel-channel-view bg-img content-loaded']/div[@id='signin-modal']/div[@class='reveal-modal-inner']/div[@class='row']/div[@class='panel panel_connection']/div[@class='row']/div[2]/div[1]/button[1]");
    private By mailLocator = By.xpath("//div[@class='input-container ui-widget empty']//input[@type='email']");
    private By passwordLocator = By.xpath("//div[@class='input-container pass empty']//input[@type='password']");
    private By citySelectLocator = By.xpath("//select[@class='register-cityselect validate-select required-entry chosen']");
    private By sexSelectLocator = By.xpath("//div[@class='columns small-4']//div[@class='radio-container']//label[@class='inline']//input[@type='radio']");
    private By submitRegistryLocator = By.xpath("//div[@class='columns a-center f-right']//button[@type='submit'][contains(text(),\"S'inscrire\")]");
    private By registerMailLocator = By.xpath("//input[@name='login[username]']");
    private By registerPasswordLocator = By.xpath("//input[@name='login[password]']");
    private By registerConnectLocator = By.xpath("//div[@class='small-12 columns a-center']//button[@type='submit'][contains(text(),'Se connecter')]");


    public MainPage(WebDriver driver, Logger log) {
        super(driver, log);
    }


    /**
     * Types given mail String in the register section
     * @param email
     */
    public void typeLoginEmail(String email){
        waitForVisibilityOf(registerPasswordLocator, 15);
        type(email, registerMailLocator);
    }

    /**
     * Types given password String in the register section
     * @param password
     */
    public void typeLoginPassword(String password){
        click(registerPasswordLocator);
        type(password, registerPasswordLocator);
    }

    /**
     * Logs into the register section
     */
    public void clickOnLogin(){
        click(registerConnectLocator);
    }


    /**
     * Opens given page
     */
    public void openPage(){
        log.info("Opening page " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    /**
     * Clicks on the delivery button
     * @return
     */
    public FoodDeliveryPage clickFoodDelivery(){
        log.info("Clicking on Food Delivery");
        click(foodDeliveryLocator);
        return new FoodDeliveryPage(driver, log);
    }

    /**
     * Clicks on the connect button
     */
    public void clickOnConnect(){
        log.info("Clicking on register");
        click(connectLocator);
    }

    /**
     * Clicks on the new account button
     */
    public void clickOnNewAccount(){
        waitForVisibilityOf(newAccountLocator, 10);
        log.info("Clicking on new account");
        click(newAccountLocator);
    }

    /**
     * Types given password and mail String into the register section
     * @param mail
     * @param password
     */
    public void register(String mail, String password){
        type(mail, mailLocator);
        type(password, passwordLocator);
    }

    /**
     * Selects given city
     */
    public void selectCity(){
        selectOption(citySelectLocator, "basel");
    }

    /**
     * Selects given sex
     */
    public void selectSex(){
        sendKeys(Keys.SPACE, sexSelectLocator);
    }

    /**
     * Clicks on submit button on the registry page
     */
    public void submitRegistry(){
        click(submitRegistryLocator);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
