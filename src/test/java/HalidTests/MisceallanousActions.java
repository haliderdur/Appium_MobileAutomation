package HalidTests;


import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MisceallanousActions extends BaseTest {

    @Test
    public void Misceallanous() {

        // App Package & App Activity
        // before getting activity package and name, we need to open the page in Emulator, then write command in command line
        // adb shell dumpsys window | find "mCurrentFocus" --> command to retrieve package and activity names from Command Line (Windows)
        Activity activity = new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
        // driver.startActivity(activity);  -- @Deprecated
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
                "intent", "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"
        ));


        WebElement wifiCheckBox = driver.findElement(AppiumBy.id("android:id/checkbox"));
        wifiCheckBox.click();


        // Change Device Rotation
        DeviceRotation landScape = new DeviceRotation(0, 0, 90);
        driver.rotate(landScape);


        WebElement wifiSettingsBtn = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='WiFi settings']"));
        wifiSettingsBtn.click();

        WebElement wifiSettingsEditInputBox = driver.findElement(AppiumBy.id("android:id/edit"));
        String wifiSettingsEditTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(wifiSettingsEditTitle, "WiFi settings");

        // copy paste
        // copy to clipboard - paste it clipboard
        driver.setClipboardText("halid wifi"); // instead of sending keys directly to write, we can use setClipboardText() method to copy to test copy functionality
        wifiSettingsEditInputBox.sendKeys(driver.getClipboardText()); // and we use sendKeys method with getClipboardText() to paste to test paste functionality
        //  wifiSettingsEditInputBox.sendKeys("halid wifi");

        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

        WebElement wifiSettingsEditOKbtn = driver.findElements(AppiumBy.className("android.widget.Button")).get(1);
        wifiSettingsEditOKbtn.click();

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));


    }

}
