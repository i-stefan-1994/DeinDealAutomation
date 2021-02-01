package ch.deindeal.tests;

import ch.deindeal.baseTest.TestUtilities;
import ch.deindeal.pages.MainPage;
import org.testng.annotations.Test;

public class RegisterTest extends TestUtilities {

    @Test
    public void registerTest(){
        MainPage mainPage = new MainPage(driver, log);
        mainPage.openPage();
        mainPage.clickOnConnect();
        mainPage.clickOnNewAccount();
        mainPage.register("qa*190122021@deindeal.ch",
                "abecede123");
        mainPage.selectCity();
        mainPage.selectSex();
        mainPage.submitRegistry();

    }
}
