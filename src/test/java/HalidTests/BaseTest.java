package HalidTests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
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
    public void configureAppium() throws URISyntaxException, MalformedURLException {

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
        options.setApp("C:\\Users\\halid\\IdeaProjects\\Appium_MobileAutomation\\src\\test\\java\\resources\\ApiDemos-debug.apk"); // install the app from pc into the mobile device
        driver = new AndroidDriver(new URI("http://" + ipAddress + ":" + port + "/wd/hub").toURL(), options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterClass
    public void tearDown() {
        // quit driver
        driver.quit();
        // stop server
        service.stop();
    }

}
