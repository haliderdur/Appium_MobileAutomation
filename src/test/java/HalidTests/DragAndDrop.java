package HalidTests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDrop extends BaseTest {

    @Test
    public void dragAndDrop() throws InterruptedException {

        WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();

        WebElement dragAndDrop = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Drag and Drop']"));
        dragAndDrop.click();

        WebElement firstCircle = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));

        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) firstCircle).getId(),
                "endX", 860,
                "endY", 760
        ));

        Thread.sleep(3000);

        WebElement droppedSuccessText = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text"));
        Assert.assertTrue(droppedSuccessText.isDisplayed());

    }
}
