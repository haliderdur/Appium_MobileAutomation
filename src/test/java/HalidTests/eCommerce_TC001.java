package HalidTests;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class eCommerce_TC001 extends BaseMobile {

    @Test
    public void fillForm() {

        WebElement nameInputBox = driver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
        nameInputBox.sendKeys("Lena");

        driver.hideKeyboard();

        WebElement femaleRadioButton = driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']"));
        femaleRadioButton.click();

        WebElement countryDropDown = driver.findElement(By.id("android:id/text1"));
        countryDropDown.click();

        driver.findElement(AppiumBy.androidUIAutomator(scrollToText("Argentina")));
        WebElement argentinaOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']"));
        argentinaOption.click();

        nameInputBox.clear();
        WebElement letsShopButton = driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"));
        letsShopButton.click();

        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");

        nameInputBox.sendKeys("Lena");
        letsShopButton.click();

        driver.findElement(AppiumBy.androidUIAutomator(scrollToText("Jordan 6 Rings")));
        WebElement jordan6Rings = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Jordan 6 Rings\"]"));

        int productList = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for (int i = 0; i < productList; i++) {
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();

            if (productName.equalsIgnoreCase("Jordan 6 Rings")) {
                WebElement addToCartBtn = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i);
                addToCartBtn.click();
            }
        }
        WebElement cartBtn = driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id=\"com.androidsample.generalstore:id/appbar_btn_cart\"]"));
        cartBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//android.widget.TextView[@text='Cart']"))));
        //  wait.until(ExpectedConditions.attributeContains(driver.findElement(By.xpath("//android.widget.TextView[@Text='Cart']")), "text", "Cart"));


        String lastPageFirstProduct = driver.findElement(By.xpath("(//android.widget.TextView)[2]")).getText();
        Assert.assertEquals(lastPageFirstProduct, "Jordan 6 Rings");

    }
}
