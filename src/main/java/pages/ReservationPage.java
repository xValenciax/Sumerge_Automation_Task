package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ReservationPage {
    By HotelHeader = By.cssSelector("h2.pp-header__title");
    By BedType = By.cssSelector(".rt-bed-type-select");
    By RoomAmount = By.cssSelector("select[data-testid]");
    By SubmitBtn = By.cssSelector("button[type='submit']");
    private WebDriverWait wait;
    private WebDriver driver;

    public ReservationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getHotelTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(HotelHeader)).getText();
    }

    public void selectBedType(String bedType) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> bedTypes = driver.findElements(BedType);
        WebElement selectedType;
        if (bedType.contains("Queen")) {
            selectedType = bedTypes.getFirst();
        } else {
            selectedType = bedTypes.getLast();
        }

        js.executeScript("arguments[0].scrollIntoView(true);", selectedType);
        selectedType.click();
    }

    public void selectRoomAmount(String roomAmount) {
        List<WebElement> roomAmounts = driver.findElements(RoomAmount);
        WebElement firstRoomAmountSelect = roomAmounts.getFirst();
        Select select = new Select(firstRoomAmountSelect);
        select.selectByValue(roomAmount);
    }

    public void submitReservation() {
        driver.findElement(SubmitBtn).click();
    }
}
