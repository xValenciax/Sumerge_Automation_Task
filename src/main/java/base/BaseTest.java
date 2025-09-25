package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void setUp() {
        String browser = ConfigReader.get("browser");
        String baseURL = ConfigReader.get("baseUrl");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
        int implicitWait = Integer.parseInt(ConfigReader.get("implicitWait"));
        int explicitWait = Integer.parseInt(ConfigReader.get("explicitWait"));

        wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));

        // Initialize WebDriver
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                if (headless) options.addArguments("--headless=new");
                driver = new ChromeDriver(options);
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        // setup
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(baseURL);

    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
