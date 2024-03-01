package HalidTests;


import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class AppiumBasics extends BaseTest {

    @Test
    public void wifiSettingName() {

        // driver.findElement(AppiumBy.  ) --> comes from Appium library
        // driver.findElement(By.        ) --> comes from Selenium library
        WebElement preferenceBtn = driver.findElement(AppiumBy.accessibilityId("Preference"));
        preferenceBtn.click();

        WebElement preferenceDependenciesBtn = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']"));
        preferenceDependenciesBtn.click();

        WebElement wifiCheckBox = driver.findElement(AppiumBy.id("android:id/checkbox"));
        wifiCheckBox.click();

        WebElement wifiSettingsBtn = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='WiFi settings']"));
        wifiSettingsBtn.click();


        WebElement wifiSettingsEditInputBox = driver.findElement(AppiumBy.id("android:id/edit"));
        String wifiSettingsEditTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(wifiSettingsEditTitle, "WiFi settings");
        //  wifiSettingsEditInputBox.click();
        wifiSettingsEditInputBox.sendKeys("halid wifi");


        WebElement wifiSettingsEditOKbtn = driver.findElements(AppiumBy.className("android.widget.Button")).get(1);
        wifiSettingsEditOKbtn.click();


        // set Wifi name
        // driver.findElement(AppiumBy.)

    }

}
