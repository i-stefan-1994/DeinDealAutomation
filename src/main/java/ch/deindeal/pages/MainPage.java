package ch.deindeal.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{

    String pageUrl = "https://www.deindeal.ch/fr/";
    private By foodDeliveryLocator = By.xpath("//a[@class='withIcon']");

    public MainPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openPage(){
        log.info("Opening page " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    public FoodDeliveryPage clickFoodDelivery(){
        log.info("Clicking on Food Delivery");
        click(foodDeliveryLocator);
        return new FoodDeliveryPage(driver, log);
    }

}
