package HalidTests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Swipe extends BaseTest {

    @Test
    public void swipe() {
        WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();

        WebElement gallery = driver.findElement(AppiumBy.accessibilityId("Gallery"));
        gallery.click();

        WebElement galleryPhotos = driver.findElement(AppiumBy.accessibilityId("1. Photos"));
        galleryPhotos.click();

        WebElement firstImage = driver.findElement(By.xpath("(//android.widget.Gallery[@resource-id='io.appium.android.apis:id/gallery'])[1]"));

        Assert.assertEquals(firstImage.getAttribute("focusable"), "true");
        //swipe
        swipeAction(firstImage, "left", 0.75);

        Assert.assertEquals(firstImage.getAttribute("focusable"), "false");

        //   swipeToTheEnd("right", 0.6);


    }
}
