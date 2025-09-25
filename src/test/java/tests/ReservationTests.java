package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ReservationPage;
import utils.TestDataProvider;
import utils.WindowUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationTests extends BaseTest {
    @Test(priority = 1, dataProvider = "searchData", dataProviderClass = TestDataProvider.class)
    public void TestCheckInAndOutDate(String Location, String CheckIn, String CheckOut) {
        ReservationPage reservationPage = new ReservationPage(driver);

        String OrigWindowHandle = driver.getWindowHandle();
        WindowUtils.switchToNewWindow(driver, OrigWindowHandle);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
        LocalDate CheckIndateInput = LocalDate.parse(CheckIn, inputFormatter);
        LocalDate CheckOutdateInput = LocalDate.parse(CheckOut, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEE d MMM");
        String formattedCheckInDate = CheckIndateInput.format(outputFormatter);
        String formattedCheckOutDate = CheckOutdateInput.format(outputFormatter);

        String[] ReservationDate = reservationPage.getCheckInDate();

        Assert.assertEquals(formattedCheckInDate, ReservationDate[0]);
        Assert.assertEquals(formattedCheckOutDate, ReservationDate[1]);
    }

    @Test(priority = 2, dataProvider = "reservationData", dataProviderClass = TestDataProvider.class)
    public void TestRoomReservation(String Bed, String Amount) {
        ReservationPage reservationPage = new ReservationPage(driver);
        reservationPage.selectBedType(Bed);
        reservationPage.selectRoomAmount(Amount.replace(".0", ""));
        reservationPage.submitReservation();
    }
}
