package pages;

import base.BaseTest;
import config.HomePageLocatorsValues;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.ConfigUtils;

public class HomePage extends BaseTest {

    private WebDriver driver;
    private By cookieModelPopupLocator = By.id(HomePageLocatorsValues.COOKIE_MODEL_POPUP_LOCATOR);
    private By closeCookieLocator = By.id(HomePageLocatorsValues.CLOSE_COOKIE_LOCATOR);

    private By departingFromSearchListLocator = By.xpath(HomePageLocatorsValues.DEPARTING_FROM_SEARCH_LIST_LOCATOR);
    private By departingFromTxtFieldLocator = By.xpath(HomePageLocatorsValues.DEPARTING_FROM_TXT_FIELD_LOCATOR);
    private By departingFromSelectedOptionLocator = By.xpath(HomePageLocatorsValues.DEPARTING_FROM_SELECTED_OPTION_LOCATOR);

    private By flyingToSearchListLocator = By.xpath(HomePageLocatorsValues.FLYING_TO_SEARCH_LIST_LOCATOR);
    private By flyingToTxtFieldLocator = By.xpath(HomePageLocatorsValues.FLYING_TO_TXT_FIELD_LOCATOR);
    private By flyingToSelectedOptionLocator = By.xpath(HomePageLocatorsValues.FLYING_TO_SELECTED_OPTION_LOCATOR);

    private By departureDateLocator = By.xpath(HomePageLocatorsValues.DEPARTING_DATE_LOCATOR);
    private By dayPickerInputLocator = By.xpath(HomePageLocatorsValues.DAY_PICKER_INPUT_LOCATOR);
    private By departureCalenderTodayLocator = By.xpath(HomePageLocatorsValues.DEPARTURE_CALENDER_TODAY_LOCATOR);

    private By returnDateLocator = By.xpath(HomePageLocatorsValues.RETURN_DATE_LOCATOR);
    private By returnCalendarLocator = By.xpath(HomePageLocatorsValues.RETURN_CALENDER_LOCATOR);
    private By findFlightLocator = By.xpath(HomePageLocatorsValues.FIND_FLIGHT_LOCATOR);

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage loadHomePage() {
        driver.get(ConfigUtils.getInstance().getBaseUrl());
        Assert.assertTrue(driver.getTitle().contains(ConfigUtils.getInstance().homePageTitle()));
        assertThatElementAppear(driver, cookieModelPopupLocator);
        return this;
    }

    public HomePage closeCookiesPopup() {
        clickElement(driver, cookieModelPopupLocator);
        clickElement(driver, closeCookieLocator);
        if(!isElementDisplayed(driver,closeCookieLocator)) {
            assertThatElementAppear(driver, departingFromSearchListLocator);
        }
        return this;
    }

    public HomePage searchDepartingFrom() {
        clickElement(driver, departingFromSearchListLocator);
        sendKeysElement(driver, departingFromTxtFieldLocator, ConfigUtils.getInstance().getDepartingFrom());
        sendKeysElement(driver, departingFromTxtFieldLocator, Keys.ENTER.toString());
//        waitForElementToBeVisible(driver, departingFromSelectedOptionLocator);
        clickElement(driver, departingFromSelectedOptionLocator);
        assertThatElementAppear(driver, flyingToSearchListLocator);
        return this;
    }

    public HomePage searchFlyingTo() {
        clickElement(driver, flyingToSearchListLocator);
        sendKeysElement(driver, flyingToTxtFieldLocator, ConfigUtils.getInstance().getFlyingTo());
        clickElement(driver, flyingToSelectedOptionLocator);
        assertThatElementAppear(driver, departureDateLocator);
        return this;
    }

    public HomePage selectDepartureDate() throws InterruptedException {
        Thread.sleep(500);
        clickElement(driver, departureDateLocator);
        moveToElement(driver, dayPickerInputLocator);
//        clickElement(driver, departureCalenderTodayLocator);
        clickElementByIndex(driver, departureCalenderTodayLocator, (getCountForSameElement(driver, departureCalenderTodayLocator)));
        assertThatElementAppear(driver, returnDateLocator);
        return this;
    }

    public OutboundPage selectReturnDate() {
        clickElement(driver, returnDateLocator);
        moveToElement(driver, dayPickerInputLocator);
        clickElementByIndex(driver, returnCalendarLocator, (getCountForSameElement(driver, returnCalendarLocator)) - 1);
        clickElement(driver, findFlightLocator);
        return new OutboundPage(driver);
    }

}