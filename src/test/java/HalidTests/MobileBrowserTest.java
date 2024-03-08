package HalidTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class MobileBrowserTest extends BaseBrowser {

    @Test
    public void browserTest() {
        driver.get("https://google.com");
        driver.findElement(By.name("q")).sendKeys("Java tutorials");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }
}
