package HalidTests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseBrowser {

    public AndroidDriver driver;

    public AppiumDriverLocalService service;
    public UiAutomator2Options options;

   /*
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();
   */

    @BeforeClass
    public void configureAppium() throws MalformedURLException, URISyntaxException {

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
        options.setChromedriverExecutable("C:\\Users\\halid\\testAutomationDrivers\\chromedriver_win32\\chromedriver.exe"); // added chromedriver version 83.0.4103.106 that is compatible with browser version
        options.setCapability("browserName", "Chrome");

         /*
         WebDriverManager.chromedriver().setup();
         ChromeOptions options = new ChromeOptions();
         System.setProperty("webdriver.chrome.driver","C:\\Users\\halid\\testAutomationDrivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
         options.setBinary("C:\\Users\\halid\\testAutomationDrivers\\chrome-win64\\chrome-win64\\chrome.exe");
         driverPool.set(new ChromeDriver(options));
         driverPool.get().manage().window().maximize();
         driverPool.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
         */

        driver = new AndroidDriver(new URI("http://" + ipAddress + ":" + port + "/wd/hub").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    public Double getFormattedAmount(String amount, int index) {
        Double price = Double.parseDouble(amount.substring(index));
        return price;
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
