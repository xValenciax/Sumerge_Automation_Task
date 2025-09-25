package utils;

import org.openqa.selenium.WebDriver;

public class WindowUtils {

    public static void switchToNewWindow(WebDriver driver, String originalWindow) {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public static void switchBackToWindow(WebDriver driver, String windowHandle) {
        driver.switchTo().window(windowHandle);
    }
}

