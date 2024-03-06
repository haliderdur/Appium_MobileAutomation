package HalidTests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BaseTest {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public UiAutomator2Options options;

    @BeforeClass
    public void configureAppium() {

        String mainJS_FilePath = "C:\\Users\\halid\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
        String ipAddress = "127.0.0.1";
        int port = 4723;

        // start server
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(mainJS_FilePath))
                .withIPAddress(ipAddress) // "http://" does not required when using .withIPAddress("127.0.0.1") method
                .usingPort(port)
                .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/")
                .build();
        service.start();

        // AndroidDriver , IOSDriver
        // Appium code --> Appium Server --> Mobile
        options = new UiAutomator2Options();
        options.setDeviceName("HalidAndroidDevice"); // emulator name must match with virtual device name
        //   options.setApp("C:\\Users\\halid\\IdeaProjects\\Appium_MobileAutomation\\src\\test\\java\\resources\\ApiDemos-debug.apk"); // install the app from pc into the mobile device
        options.setApp("C:\\Users\\halid\\IdeaProjects\\Appium_MobileAutomation\\src\\test\\java\\resources\\General-Store.apk"); // install the app from pc into the mobile device

        try {
            driver = new AndroidDriver(new URI("http://" + ipAddress + ":" + port + "/wd/hub").toURL(), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    // clicks and holds on given element for given duration
    public void longClickAction(WebElement element, int duration) {
        ((JavascriptExecutor) driver).executeScript("mobile:longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", duration));
    }

    // scrolls down in the page till the end
    public void scrollToTheEndAction(String direction_up_down, double percent) {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                    ImmutableMap.builder().put("left", 100)
                            .put("top", 100)
                            .put("width", 200)
                            .put("height", 200)
                            .put("direction", direction_up_down) // mandatory value (up or down)
                            .put("percent", percent) // mandatory value (3.0)
                            .build());
        } while (canScrollMore);
    }

    public String scrollToText(String text) {
        String scrollText = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));";
        return scrollText;
    }

    public void swipeAction(WebElement element, String direction, double percent) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.builder()
                .put("elementId", ((RemoteWebElement) element).getId())
                .put("direction", direction) // mandatory value (right or left)
                .put("percent", percent) // mandatory value (0.75)
                .build());
    }


    // close the driver and the appium server
    @AfterClass
    public void tearDown() {
        // quit driver
        driver.quit();
        // stop server
        service.stop();
    }

}
