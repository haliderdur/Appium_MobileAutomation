package HalidTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class MobileBrowserTest extends BaseBrowser {

    @Test
    public void browserTest() {
        driver.get("https://google.com");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1500)", ""); // Scroll on the mobile Chrome browser
        driver.findElement(By.cssSelector("button[id='W0wltc']")).click(); // decline cookies
        driver.findElement(By.name("q")).sendKeys("Java tutorials"); // send keys to search bar
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }
}
