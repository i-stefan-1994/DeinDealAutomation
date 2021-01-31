package ch.deindeal.baseTest;

import ch.deindeal.base.BaseTest;

public class TestUtilities extends BaseTest {

    protected void sleep(long s){
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
