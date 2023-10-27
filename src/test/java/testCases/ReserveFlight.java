package testCases;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.HomePage;

@Feature("ServeFlight Feature")

public class ReserveFlight extends BaseTest {
    @Story("Serve Flight")
    @Test(description= "Should be able to get the lowest price flight from Vienna to Malta if found")
    public void BookingFlight() throws InterruptedException {
        new HomePage(
                driver.get()).
                loadHomePage().closeCookiesPopup().searchDepartingFrom().searchFlyingTo()
                .selectDepartureDate().selectReturnDate().selectMinimumPriceFlight();
    }
}
