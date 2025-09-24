package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    By CitySelector = By.id(":rh:");
    By CheckInBtn = By.xpath("/html/body/div[3]/div[2]/div/form/div/div[2]/div/button");
    By CalendarBtn = By.id("calendar-searchboxdatepicker-tab-trigger");
    By MonthName = By.xpath("/html/body/div[3]/div[2]/div/form/div/div[2]/div/div/div/nav/div[2]/div/div[1]/div/div[1]/h3");
    By NextMonthBtn = By.xpath("/html/body/div[3]/div[2]/div/form/div/div[2]/div/div/div/nav/div[2]/div/div[1]/button");
    By DaysTable = By.xpath("/html/body/div[3]/div[2]/div/form/div/div[2]/div/div/div/nav/div[2]/div/div[1]/div/div[1]/table");
    By SearchBtn = By.cssSelector("button[type=submit]");

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectCity(String city) {
        driver.findElement(CitySelector).sendKeys(city);
    }

    public void openDatePicker() {
        driver.findElement(CheckInBtn).click();
        driver.findElement(CalendarBtn).click();
    }

    public void selectDate(String month, int checkInDay, int checkOutDay) {
        while (!driver.findElement(MonthName).getText().contains(month)) {
            driver.findElement(NextMonthBtn).click();
        }

        List<WebElement> rows = driver.findElement(DaysTable).findElements(By.tagName("tr"));

        WebElement CheckInDate = rows.getFirst().findElements(By.tagName("td")).get(checkInDay + 1);
        CheckInDate.click();

        WebElement CheckOutDate = rows.get(2).findElements(By.tagName("td")).get(checkOutDay + 1);
        CheckOutDate.click();
    }

    public void submitSearch() {
        driver.findElement(SearchBtn).click();
    }
}
