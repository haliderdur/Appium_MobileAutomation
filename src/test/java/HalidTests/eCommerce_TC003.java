package HalidTests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class eCommerce_TC003 extends BaseTest {

    @Test
    public void fillForm() throws InterruptedException {

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

        //  WebElement jordanLiftOff = driver.findElement(By.xpath("//android.widget.TextView[@text='Jordan Lift Off']"));

        driver.findElement(AppiumBy.androidUIAutomator(scrollToText("Jordan Lift Off")));
        int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for (int i = 0; i < productCount; i++) {
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();

            if (productName.equalsIgnoreCase("Jordan Lift Off")) {
                WebElement addToCartBtn = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i);
                addToCartBtn.click();
                break;
            }
        }

        Thread.sleep(2000);

        WebElement cartBtn = driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart"));
        cartBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//android.widget.TextView[@text='Cart']"))));

        String lastPageFirstProduct = driver.findElement(By.xpath("(//android.widget.TextView)[2]")).getText();
        Assert.assertEquals(lastPageFirstProduct, "Jordan Lift Off");

        WebElement termsAndConditions = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        longClickAction(termsAndConditions, 2000);

        WebElement termsAndConditionsTitle = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle"));
        Assert.assertEquals(termsAndConditionsTitle.getText(), "Terms Of Conditions");

        WebElement closeBtn = driver.findElement(By.xpath("//android.widget.Button[@text='CLOSE']"));
        closeBtn.click();

        WebElement checkbox = driver.findElement(AppiumBy.className("android.widget.CheckBox"));
        checkbox.click();

        WebElement visitWebSiteBtn = driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed"));
        visitWebSiteBtn.click();

    }
}
