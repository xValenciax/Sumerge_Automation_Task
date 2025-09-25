package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
    String excelPath = ConfigReader.get("excelPath");
    String sheetName = ConfigReader.get("sheetName");

    @DataProvider(name = "searchData")
    public Object[][] getSearchData() {
        String path = System.getProperty("user.dir") + excelPath;
        try {
            return ExcelUtils.getData(path, sheetName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
