package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchResultsPage;
import utils.TestDataProvider;

public class SearchResultsTest extends BaseTest {
    @Test(dataProvider = "hotelData", dataProviderClass = TestDataProvider.class)
    public void TestFindAndBookHotel(String targetedHotel) {
//        driver.get("https://www.booking.com/searchresults.html?ss=Alexandria%2C+Alexandria+Governorate%2C+Egypt&ssne=Ras+El+Bar&ssne_untouched=Ras+El+Bar&label=gen173nr-10CAEoggI46AdIM1gEaEOIAQGYATO4ARfIAQzYAQPoAQH4AQGIAgGoAgG4Aoypz8YGwAIB0gIkZmE1OTRiMTgtYWIyMC00Y2IxLTgxMTUtYWU3OWI5MzMxNzFh2AIB4AIB&sid=177e7f76bab4e8f6005004b8ff6d24fa&aid=304142&lang=en-us&sb=1&src_elem=sb&src=searchresults&dest_id=-290263&dest_type=city&ac_position=0&ac_click_type=b&ac_langcode=en&ac_suggestion_list_length=5&search_selected=true&search_pageview_id=8a2963411c5900dd&ac_meta=GhA4YTI5NjM0MTFjNTkwMGRkIAAoATICZW46AmFsQABKAFAA&checkin=2025-10-01&checkout=2025-10-02&group_adults=2&no_rooms=1&group_children=0");

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.FindAndBookHotel(targetedHotel);

        Assert.assertTrue(driver.getWindowHandles().size() > 1, "New window did not open");
    }
}
