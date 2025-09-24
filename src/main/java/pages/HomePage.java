package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Pattern;

public class HomePage {
    By CitySelector = By.id(":rh:");
    By FirstSearchResult = By.cssSelector("#autocomplete-result-0 > div");
    By CheckInBtn = By.xpath("/html/body/div[3]/div[2]/div/form/div/div[2]/div/button");
    By CalendarBtn = By.id("calendar-searchboxdatepicker-tab-trigger");
    By MonthName = By.xpath("/html/body/div[3]/div[2]/div/form/div/div[2]/div/div/div/nav/div[2]/div/div[1]/div/div[1]/h3");
    By NextMonthBtn = By.xpath("/html/body/div[3]/div[2]/div/form/div/div[2]/div/div/div/nav/div[2]/div/div[1]/button");
    //    By DaysTable = By.xpath("/html/body/div[3]/div[2]/div/form/div/div[2]/div/div/div/nav/div[2]/div/div[1]/div/div[1]/table");
    By CheckInDay;
    By CheckOutDay;
    By SearchBtn = By.cssSelector("button[type=submit]");
    WebDriverWait wait;
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectCity(String city) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CitySelector)).sendKeys(city);

        wait.until(ExpectedConditions.textMatches(FirstSearchResult, Pattern.compile(city)));
        driver.findElement(FirstSearchResult).click();
    }

    public void openDatePicker() {
//        driver.findElement(CheckInBtn).click();
        driver.findElement(CalendarBtn).click();
    }

    public void selectDate(String year, String month, String checkInDay, String checkOutDay) {
        driver.findElement(NextMonthBtn).click();

        CheckInDay = By.cssSelector("span[data-date='" + year + "-" + month + "-" + checkInDay + "']");
        CheckOutDay = By.cssSelector("span[data-date='" + year + "-" + month + "-" + checkOutDay + "']");
        driver.findElement(CheckInDay).click();
        driver.findElement(CheckOutDay).click();
    }

    public void submitSearch() {
        driver.findElement(SearchBtn).click();
    }
}
