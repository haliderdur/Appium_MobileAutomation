package HalidTests;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class eCommerce_TC002 extends BaseMobile {

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

        WebElement letsShopButton = driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"));
        letsShopButton.click();

        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

        WebElement cartBtn = driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id=\"com.androidsample.generalstore:id/appbar_btn_cart\"]"));
        cartBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//android.widget.TextView[@text='Cart']"))));

         /*
           Double cartPageFirstProductPrice = Double.parseDouble(driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice'])[1]")).getText().substring(1));
           Double cartPageSecondProductPrice = Double.parseDouble(driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice'])[2]")).getText().substring(1));
           Double cartPageTotalProductPrice = Double.parseDouble(driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().substring(2));
           Assert.assertTrue(cartPageFirstProductPrice + cartPageSecondProductPrice == cartPageTotalProductPrice, "Total Price is: " + cartPageTotalProductPrice);
         */
        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        int productCount = productPrices.size();
        double sum = 0;
        for (int i = 0; i < productCount; i++) {
            String productPrice = productPrices.get(i).getText();
            Double price = Double.parseDouble(productPrice.substring(1));
            sum += price;
        }

        String totalPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        double displaySum = getFormattedAmount(totalPrice, 2);

        Assert.assertEquals(sum, displaySum);

    }
}
