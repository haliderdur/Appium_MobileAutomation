package HalidTests;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Scroll extends BaseMobile {

    @Test
    public void scroll() {

        WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();

        // where to scroll is already known
        // driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));

        // where to scroll is not known - scrolls till the bottom
        scrollToTheEndAction("down", 3.0);
    }
}
