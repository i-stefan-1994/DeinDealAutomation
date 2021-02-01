package ch.deindeal.tests;

import ch.deindeal.baseTest.TestUtilities;
import ch.deindeal.pages.FoodDeliveryPage;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FoodDeliveryPageTest extends TestUtilities {

    FoodDeliveryPage foodDeliveryPage = null;

    @Test
    private void checkUrl(){
        foodDeliveryPage = new FoodDeliveryPage(driver, log);
        foodDeliveryPage.navigateToRestaurantPage();
        //Assert that the url ends with restaurant
        Assert.assertTrue(foodDeliveryPage.checkIfUrlEndsWithGivenString("restaurant/"));

        foodDeliveryPage.selectZurich();
        sleep(3000);
        foodDeliveryPage.saveCityId();

        Assert.assertFalse(foodDeliveryPage.checkIfButtonEnabled());

        foodDeliveryPage.insertAddress("Rue Emma-Kammacher 9");
        foodDeliveryPage.selectFirstRecommendation();
        //check to see if the find restaurant button is enabled
        Assert.assertTrue(foodDeliveryPage.checkIfButtonEnabled());
        //click find restaurant button
        foodDeliveryPage.clickFindRestaurantsButton();
        //click food filter arrow
        foodDeliveryPage.clickFoodFilterArrow();

        foodDeliveryPage.clickOffersFilter();
        boolean offersInList = foodDeliveryPage.checkListEnablement();
        Assert.assertTrue(offersInList);
        foodDeliveryPage.saveFilterValue();
        //Saves the id's of elements found in the recommended section
        foodDeliveryPage.saveElements();
        int actualResponseCode = foodDeliveryPage.makeApiCall();
        Assert.assertEquals(actualResponseCode, 200);

    }
}
