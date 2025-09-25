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
    By ReservationSection = By.id("availability_target");
    By ReservationTable = By.id("hprt-table");
    By BedType = By.cssSelector(".rt-bed-type-select");
    By RoomAmount = By.cssSelector("select[data-testid]");
    By SubmitBtn = By.cssSelector("button[type='submit']");
    By CheckIn = By.xpath("//*[@id=\"hp_availability_style_changes\"]/div[3]/div/form/div/div[1]/div/div/button[1]/span");
    By CheckOut = By.xpath("//*[@id=\"hp_availability_style_changes\"]/div[3]/div/form/div/div[1]/div/div/button[2]/span");

    private JavascriptExecutor js;
    private WebDriverWait wait;
    private WebDriver driver;

    public ReservationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    public String[] getCheckInDate() {

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(ReservationSection));

        WebElement CheckInDate = wait.until(ExpectedConditions.visibilityOfElementLocated(CheckIn));
        WebElement CheckOutDate = wait.until(ExpectedConditions.visibilityOfElementLocated(CheckOut));

        return new String[]{CheckInDate.getText(), CheckOutDate.getText()};
    }

    public void selectBedType(String bedType) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ReservationTable));
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(ReservationTable));

        List<WebElement> bedTypes = driver.findElements(BedType);
        WebElement selectedType;
        if (bedType.contains("Queen")) {
            selectedType = bedTypes.getFirst();
        } else {
            selectedType = bedTypes.getLast();
        }

        selectedType.click();
    }

    public void selectRoomAmount(String roomAmount) {
        List<WebElement> roomAmounts = driver.findElements(RoomAmount);
        WebElement firstRoomAmountSelect = roomAmounts.getFirst();
        Select select = new Select(firstRoomAmountSelect);
        select.selectByValue(roomAmount);
    }

    public void submitReservation() {
        List<WebElement> SubmitBtns = driver.findElements(SubmitBtn);
        WebElement reserveBtn = SubmitBtns.getLast();
        reserveBtn.click();
    }
}
