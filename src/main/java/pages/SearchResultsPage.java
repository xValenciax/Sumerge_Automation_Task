package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {
    By SearchResultCount = By.xpath("/html/body/div[4]/div/div/div/div[2]/div[3]/div[2]/div[1]/h1");
    By HotelCards = By.cssSelector("div[data-testid='property-card']");
    By HotelTitle = By.cssSelector("div[data-testid='title']");
    By LoadMoreBtn = By.xpath("//button[@type='button']//span[text()='Load more results']");
    By SeeAvailabilityBtn = By.cssSelector("a[data-testid='availability-cta-btn']");

    private WebDriverWait wait;
    private WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void FindAndBookHotel(String targetedHotel) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.presenceOfElementLocated(SearchResultCount));

        boolean hotelFound = false;

        List<WebElement> hotelCards = driver.findElements(HotelCards);

        for (int i = 0; i < hotelCards.size(); i++) {
            WebElement curCard = hotelCards.get(i);
            WebElement titleElement = curCard.findElement(HotelTitle);
            String curTitle = titleElement.getText();

            js.executeScript("arguments[0].scrollIntoView(true);", curCard);

            if (curTitle.contains(targetedHotel)) {
                hotelFound = true;
                curCard.findElement(SeeAvailabilityBtn).click();
                break;
            }

            if (i == hotelCards.size() - 1) {
                try {
                    driver.findElement(LoadMoreBtn).click();
                } catch (Exception e) {
                }
                hotelCards = driver.findElements(HotelCards);
            }
        }

        if (!hotelFound) {
            throw new RuntimeException("Hotel not found");
        }
    }
}
