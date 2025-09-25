package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ConfirmationPage;
import utils.TestDataProvider;

public class ConfirmationTests extends BaseTest {
    @Test(priority = 1)
    public void TestCurrentStageIsDetails() {
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        String stageTitle = confirmationPage.getCurrStageTitle();

        Assert.assertEquals(stageTitle, "Your details");
    }

    @Test(priority = 2, dataProvider = "hotelData", dataProviderClass = TestDataProvider.class)
    public void TestSelectedHotelNamePresent(String targetedHotel) {
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        String hotelName = confirmationPage.getHotelName();

        Assert.assertTrue(hotelName.contains(targetedHotel), "Wrong hotel have been selected");
    }
}
