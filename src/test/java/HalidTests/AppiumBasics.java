package HalidTests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AppiumBasics {

    @Test
    public void AppiumTest() throws MalformedURLException, URISyntaxException {

        // AndroidDriver , IOSDriver
        // Appium code --> Appium Server --> Mobile
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("HalidAndroidDevice"); // emulator
        options.setApp("C:\\Users\\halid\\IdeaProjects\\Appium_MobileAutomation\\src\\test\\java\\resources\\ApiDemos-debug.apk");


        AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);

        // Actual automation
    }


}
