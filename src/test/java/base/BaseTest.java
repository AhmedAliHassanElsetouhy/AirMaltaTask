package base;

import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.List;

public class BaseTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public WebDriver getDriver() {
        return this.driver.get();
    }

    @BeforeTest
    public void setup() {
        WebDriver driver = new DriverFactory().initializeDriver();
        setDriver(driver);
    }

    @AfterTest
    public void tearDown() {
        getDriver().quit();
    }

    private Select select;

    private static WebDriverWait wait ;

    protected static void waitForElementToBeVisible(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected static void clickElement(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(findElement(driver, locator))).click();
    }

    //To find elements that have same selector
    protected static List<WebElement> findListOfElements(WebDriver driver, By locator) {
//        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(driver.findElements(locator))).click();
        return driver.findElements(locator);
    }

    //To count elements that have same selector to use specified element
    protected static int getCountForSameElement(WebDriver driver, By locator) {
        List<WebElement> elements = findListOfElements(driver, locator);
        return elements.size();
    }

    //To click on the specific element by index when elements have same selector
    protected static void clickElementByIndex(WebDriver driver, By locator, int index) {
        findListOfElements(driver, locator).get(index).click();
    }

    //To fins element where have a selector
    protected static WebElement findElement(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //To send values to a specified element where send the selector
    protected static void sendKeysElement(WebDriver driver, By locator, String keys) {
        findElement(driver, locator).sendKeys(keys);
    }

    //To move into element where passing the locator
    protected void moveToElement(WebDriver driver, By locator) {
        Actions generateReportHoverAction = new Actions(driver);
        generateReportHoverAction.moveToElement(findElement(driver, locator)).perform();
    }

    protected static boolean isElementDisplayed(WebDriver driver, By locator){
        return findElement(driver, locator).isDisplayed();
    }

    public boolean assertThatElementAppear(WebDriver driver, By locator){
        Assert.assertTrue(findElement(driver, locator).isDisplayed());
        return true;
    }
}