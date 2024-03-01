package HalidTests;


import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class LongClick extends BaseTest {

    @Test
    public void longClickGesture() throws InterruptedException {

        WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();

        WebElement expandableLists = driver.findElement(AppiumBy.accessibilityId("Expandable Lists"));
        expandableLists.click();

        WebElement customAdapter = driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter"));
        customAdapter.click();

        WebElement peopleNames = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
        /*
        ((JavascriptExecutor) driver).executeScript("mobile:longClickGesture",
                   ImmutableMap.of("elementId", ((RemoteWebElement) peopleNames).getId(), "duration", 2000));
        */
        longClickAction(peopleNames, 2000);

        String peopleName_SampleMenuText = driver.findElement(By.id("android:id/title")).getText();
        Assert.assertEquals(peopleName_SampleMenuText, "Sample menu");
        WebElement peopleName_SampleMenuBtn = driver.findElement(By.id("android:id/title"));
        Assert.assertTrue(peopleName_SampleMenuBtn.isDisplayed());

    }

}
