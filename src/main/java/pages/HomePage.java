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
    By CalendarBtn = By.id("calendar-searchboxdatepicker-tab-trigger");
    By MonthName = By.xpath("/html/body/div[3]/div[2]/div/form/div/div[2]/div/div/div/nav/div[2]/div/div[1]/div/div[1]/h3");
    By NextMonthBtn = By.cssSelector("#calendar-searchboxdatepicker > div > div.a2142b454f.fb6f2a3ebc > button");
    By CheckInDay;
    By CheckOutDay;
    By SearchBtn = By.cssSelector("button[type=submit]");
    By SearchResultCount = By.xpath("/html/body/div[4]/div/div/div/div[2]/div[3]/div[2]/div[1]/h1");


    private WebDriverWait wait;
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

//    public void closePopUp() {
//        try {
//            WebDriverWait PopUpwait = new WebDriverWait(driver, Duration.ofSeconds(2));
//            PopUpwait.until(ExpectedConditions.visibilityOfElementLocated(ClosePopUpBtn)).click();
//        } catch (TimeoutException e) {
//            System.out.println("No popup appeared within 5 seconds.");
//        }
//    }

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
        driver.findElement(NextMonthBtn).click();

        CheckInDay = By.cssSelector("span[data-date='" + String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.format("%02d", checkInDay) + "']");
        CheckOutDay = By.cssSelector("span[data-date='" + String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.format("%02d", checkOutDay) + "']");

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
