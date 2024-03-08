package HalidTests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class eCommerce_TC004_Hybrid extends BaseMobile {

    @Test
    public void fillForm() throws InterruptedException {

        WebElement countryDropDown = driver.findElement(By.id("android:id/text1"));
        countryDropDown.click();

        driver.findElement(AppiumBy.androidUIAutomator(scrollToText("Argentina")));
        WebElement argentinaOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']"));
        argentinaOption.click();

        WebElement nameInputBox = driver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
        nameInputBox.sendKeys("Lena");
        driver.hideKeyboard();

        WebElement femaleRadioButton = driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']"));
        femaleRadioButton.click();

        WebElement letsShopButton = driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"));
        letsShopButton.click();

        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

        WebElement cartBtn = driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart"));
        cartBtn.click();

        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//android.widget.TextView[@text='Cart']"))));

        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        int productCount = productPrices.size();
        double sum = 0;
        for (int i = 0; i < productCount; i++) {
            String productPrice = productPrices.get(i).getText();
            Double price = Double.parseDouble(productPrice.substring(1));
            sum += price;
        }


        String totalPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        double displaySum = getFormattedAmount(totalPrice, 1);

        Assert.assertEquals(sum, displaySum);

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

        Thread.sleep(6000);

        Set<String> contexts = driver.getContextHandles();
        for (String contextName : contexts) {
            System.out.println(contextName); // to print context names for native and webview
        }

        driver.context("WEBVIEW_com.androidsample.generalstore"); // developer will assign a name for webview, there is no generic name

        driver.findElement(AppiumBy.androidUIAutomator(scrollToText("Alle akzeptieren")));
        WebElement acceptCookies = driver.findElement(By.id("L2AGLb"));
        acceptCookies.click();
        driver.findElement(By.name("q")).sendKeys("Java tutorials");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        Thread.sleep(3000);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");


    }
}
