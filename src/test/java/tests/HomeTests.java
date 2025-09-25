package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TestDataProvider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HomeTests extends BaseTest {

    @Test(dataProvider = "searchData", dataProviderClass = TestDataProvider.class)
    public void TestSearchForHotel(String Location, String CheckIn, String CheckOut) {
        HomePage homePage = new HomePage(driver);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
        LocalDate CheckInDate = LocalDate.parse(CheckIn, formatter);
        LocalDate CheckOutDate = LocalDate.parse(CheckOut, formatter);

        homePage.selectCity(Location);
        homePage.openDatePicker();
        homePage.selectDate(CheckInDate.getYear(), CheckInDate.getMonthValue(), CheckInDate.getDayOfMonth(), CheckOutDate.getDayOfMonth());
        homePage.submitSearch();

        Assert.assertTrue(homePage.getSearchResult().contains(Location), "Search result does not contain the location");
    }
}