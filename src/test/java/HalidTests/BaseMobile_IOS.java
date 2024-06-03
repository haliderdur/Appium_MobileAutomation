package HalidTests;

import com.github.dockerjava.api.model.Driver;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BaseMobile_IOS {

    public IOSDriver driver;
    public AppiumDriverLocalService service;
    public XCUITestOptions options;

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

        // IOSDriver
        options = new XCUITestOptions();
        options.setDeviceName("iPhone 13 Pro");
        options.setApp("C:\\Users\\halid\\ios-uicatalog-master\\UIKitCatalog\\UIKitCatalog.app"); // address of the .app file in pc
        options.setPlatformVersion("15.5");
        // Appium --> WebDriver Agent --> IOS Apps
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));


        try {
            driver = new IOSDriver(new URI("http://" + ipAddress + ":" + port + "/wd/hub").toURL(), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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
