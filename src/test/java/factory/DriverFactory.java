package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    //Use this function to avoid problem of mismatching browser version because no available driver manager match
    public WebDriver initializeDriver() {
        String browser = System.getProperty("browser", "CHROME");
        WebDriver driver;
        switch (browser) {
            case "CHROME":
//                WebDriverManager.chromedriver().setup();
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\ahmed\\IdeaProjects\\AirMaltaSystem\\src\\test\\java\\factory\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "SAFARI":
                // Assuming Safari driver is already available in the system
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("The Browser is not supported");
        }
        driver.manage().window().maximize();
        return driver;
    }
}