package ch.deindeal.tests;

import ch.deindeal.baseTest.TestUtilities;
import ch.deindeal.pages.MainPage;
import org.testng.annotations.Test;

public class MainPageTest extends TestUtilities {

    @Test
    public void clickFoodDelivery(){
        MainPage mainPage = new MainPage(driver, log);

        //Navigating to "https://deindeal.ch/fr/"
        mainPage.openPage();

        //Clicking on Food Delivery
        mainPage.clickFoodDelivery();

    }

}
