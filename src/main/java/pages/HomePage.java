package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Month;
import java.util.regex.Pattern;

public class HomePage {
    By CitySelector = By.id(":rh:");
    By FirstSearchResult = By.cssSelector("#autocomplete-result-0 > div");
    By CalendarBtn = By.id("calendar-searchboxdatepicker-tab-trigger");
    By MonthName = By.xpath("//*[@id=\"calendar-searchboxdatepicker\"]/div/div[1]/div/div[1]/h3");
    By NextMonthBtn = By.cssSelector("button[aria-label=\"Next month\"]");
    By SearchBtn = By.cssSelector("button[type=submit]");
    By SearchResultCount = By.xpath("/html/body/div[4]/div/div/div/div[2]/div[3]/div[2]/div[1]/h1");
    By CheckInDay;
    By CheckOutDay;


    private WebDriverWait wait;
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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

    public void selectDate(int year, int month, int checkInDay, int checkOutDay) {

        String MonthValue = Month.of(month).getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH);

        while (!driver.findElement(MonthName).getText().contains(MonthValue)) {
            driver.findElement(NextMonthBtn).click();
        }

        CheckInDay = By.cssSelector("span[data-date='" + String.valueOf(year) + "-" +
                String.format("%02d", month) + "-" + String.format("%02d", checkInDay) + "']");
        CheckOutDay = By.cssSelector("span[data-date='" + String.valueOf(year) + "-" +
                String.format(String.format("%02d", month)) + "-" + String.format("%02d", checkOutDay) + "']");

        driver.findElement(CheckInDay).click();
        driver.findElement(CheckOutDay).click();
    }

    public void submitSearch() {
        driver.findElement(SearchBtn).click();
    }

    public String getSearchResult() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(SearchResultCount)).getText();
    }
}
