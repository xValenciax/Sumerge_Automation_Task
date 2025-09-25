package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
    String excelPath = ConfigReader.get("excelPath");
    String sheetName1 = ConfigReader.get("sheetName1");
    String sheetName2 = ConfigReader.get("sheetName2");
    String sheetName3 = ConfigReader.get("sheetName3");

    @DataProvider(name = "searchData")
    public Object[][] getSearchData() {
        String path = System.getProperty("user.dir") + excelPath;
        try {
            return ExcelUtils.getData(path, sheetName1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DataProvider(name = "hotelData")
    public Object[][] getHotelData() {
        String path = System.getProperty("user.dir") + excelPath;
        try {
            return ExcelUtils.getData(path, sheetName2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DataProvider(name = "reservationData")
    public Object[][] getReservationData() {
        String path = System.getProperty("user.dir") + excelPath;
        try {
            return ExcelUtils.getData(path, sheetName3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
