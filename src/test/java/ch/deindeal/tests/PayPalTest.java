package ch.deindeal.tests;

import ch.deindeal.baseTest.TestUtilities;
import ch.deindeal.pages.MainPage;
import org.testng.annotations.Test;

public class PayPalTest extends TestUtilities {

    @Test
    public void loginTest(){
        MainPage mainPage = new MainPage(driver, log);
        mainPage.openPage();
        mainPage.clickOnConnect();
//        mainPage.login("abecede123@gmail.com",
//                "abecede123");
        mainPage.typeLoginEmail("qa*190122021@deindeal.ch");
        mainPage.typeLoginPassword("abecede123");

        mainPage.clickOnLogin();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
