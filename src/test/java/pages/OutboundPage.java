package pages;

import base.BaseTest;
import config.OutPoundPageLocatorsValues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutboundPage extends BaseTest {
    private WebDriver driver;
    public OutboundPage(WebDriver driver){
        this.driver = driver;
    }
    By flights = By.xpath(OutPoundPageLocatorsValues.FLIGHTS);
    public double selectMinimumPriceFlight(){
        List<WebElement> flightElements = findListOfElements(driver, flights);
        int count = getCountForSameElement(driver, flights);
        List<Double> flightPrices = new ArrayList<>();

        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String flightText = flightElements.get(i).getText();
                double price = extractPriceFromText(flightText);
                flightPrices.add(price);
            }

            double minPrice = Collections.min(flightPrices);
            System.out.println("Minimum price: " + minPrice);

            // Find the index of the element with the minimum price
            int minPriceIndex = flightPrices.indexOf(minPrice);

            // Click on the element with the minimum price
            flightElements.get(minPriceIndex).click();
        } else {
            System.out.println("No flights found.");
        }
//        return this.selectMinimumPriceFlight();
        return 0;
    }

    private double extractPriceFromText(String flightText) {
        // Implement your logic to extract the price from the flight text
        // Adjust the logic based on the format of the flight text

        // Example logic assuming the price is in the format "$123.45"
        String priceText = flightText.replaceAll("[^\\d.]", ""); // Remove non-digit characters except decimal point
        return Double.parseDouble(priceText);
    }

}
