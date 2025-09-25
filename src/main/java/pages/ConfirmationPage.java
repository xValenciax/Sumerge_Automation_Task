package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmationPage {
    By StageTitle = By.xpath("//*[@id=\"bodyconstraint-inner\"]/div[3]/div[2]/div/div/div/div/ol/li[2]/strong");
    By HotelName = By.xpath("//*[@id=\"bodyconstraint-inner\"]/div[3]/div[3]/aside/div/div[1]/div/div/div/div[2]/div/div/div/div[1]/div[1]/h1");
    private JavascriptExecutor js;
    private WebDriverWait wait;
    private WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getCurrStageTitle() {
        WebElement stageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(StageTitle));
        return stageTitle.getText();
    }

    public String getHotelName() {
        WebElement hotelName = wait.until(ExpectedConditions.visibilityOfElementLocated(HotelName));
        return hotelName.getText();
    }
}
