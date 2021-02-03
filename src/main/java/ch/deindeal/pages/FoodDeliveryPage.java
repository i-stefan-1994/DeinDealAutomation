package ch.deindeal.pages;

import io.restassured.RestAssured;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static java.lang.Thread.sleep;

public class FoodDeliveryPage extends BasePageMethods {

    private String url = "https://www.deindeal.ch/fr/restaurant/";
    private By zurichCityLocator = By.cssSelector("div.Layout:nth-child(3) div.Food div.Food__cities ul.Food__cities-list li.Food__city-wrapper:nth-child(2) a.Link > img.Food__city-image");
    private By buttonLocator = By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/button[1]");
    private By searchFieldLocator = By.className("InputLocation");
    private By dropdownLocator = By.className("Button__prediction-item");
    private By foodFilterLocator = By.xpath("//div[@class='FoodFilters__wrapper']//button[@type='button']");
    private By healthyFilterLocator = By.xpath("//label[@class='Input__label--radio food-popular-filter']//span[@class='Input__label-content'][contains(text(),'Healthy')]");
    private By offerInListLocator = By.xpath("/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li[5]/div[1]/label[1]/span[1]");
    private By savedElementsLocator = By.xpath("//div[@class='SalesList']//div[@class='SalesList__list']//a[@*]");
    private String apiCall = "https://testfoodios.herokuapp.com/food_city/";
    private String cityId;
    private String filterValue;
    private int response;
    private List<String> savedElements = new ArrayList<>();

    public FoodDeliveryPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void navigateToRestaurantPage(){
        openUrl(url);
    }

    public boolean checkIfUrlEndsWithGivenString(String str){
        log.info("Checking if current URL ends with " + str);
        return urlEndsWith(str);
    }


    public void selectZurich(){
        log.info("Clicking on Zurich");
        click(zurichCityLocator);
    }

    public void saveCityId(){
        log.info("Saving city id");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentUrl = getCurrentUrl();
        String[] cityUrl = currentUrl.split("restaurant/");
        this.cityId = cityUrl[1];
        log.info("City id saved as " + this.cityId);

    }

    public String returnSavedCityId(){
        return this.cityId;
    }

    public boolean checkIfButtonEnabled(){
        log.info("Checking to see if the button is enabled");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return elementEnablement(buttonLocator);
    }

    public void insertAddress(String address){
        log.info("Inserting " + address + " address");
        type(address, searchFieldLocator);
    }

    /**
     * Select first recommended restaurant
     */
    public void selectFirstRecommendation(){
        log.info("Selecting first recommandation");
        waitForVisibilityOf(dropdownLocator, 10);
        click(dropdownLocator);
    }

    /**
     * Click the Find Restaurants button
     */
    public void clickFindRestaurantsButton(){
        log.info("Clicking the find restaurant button");
        click(buttonLocator);
    }

    /**
     * Clicks on the food filter arrow
     */
    public void clickFoodFilterArrow(){
        log.info("Clicking on the food filter arrow");
//        waitForVisibilityOf(dropdownLocator, 10);
        click(foodFilterLocator);
    }

    /**
     * Clicks on the offers filter
     */
    public void clickOffersFilter(){
        log.info("Clicking on the offers filter");
        click(healthyFilterLocator);
    }

    /**
     * Checks if the offer filter is also active in the list
     * @return
     */
    public boolean checkListEnablement(){
        log.info("Checking list enablement");
        return checkIfElementIsSelectedByVisibleText(offerInListLocator);
    }

    /**
     * Saves the value in the selected filter
     */
    public void saveFilterValue(){
        log.info("Saving the value of the filter");
        String currentUrl = getCurrentUrl();
        String[] filtervalue = currentUrl.split("sortBy=");
        this.filterValue = filtervalue[1];
        System.out.println(this.filterValue);
        log.info("Filter saved as " + this.filterValue);
    }

    /**
     * Returns saved elements
     * @return
     */
    public void saveElements(){
        log.info("Saving all elements in the given locator");
        List<String> element = findElements(savedElementsLocator);
            for(String individualId: element){
                this.savedElements.add(individualId);
                System.out.println("Id saved: " + individualId);
            }
    }

    /**
     * Returns status code of the given URL
     * @return
     */
    public int makeApiCall(){
        String apiCall = this.apiCall + cityId + ".com";
        RestAssured.baseURI = apiCall;
        openUrl(apiCall);
        this.response = get().getStatusCode();
        return this.response;
    }


}
