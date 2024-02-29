package HalidTests;


import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class AppiumBasics extends BaseTest {

    @Test
    public void wifiSettingName() throws URISyntaxException, MalformedURLException {

        // driver.findElement(AppiumBy.  ) --> comes from Appium library
        // driver.findElement(By.        ) --> comes from Selenium library
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();

        // set Wifi name
        // driver.findElement(AppiumBy.)

    }

}
