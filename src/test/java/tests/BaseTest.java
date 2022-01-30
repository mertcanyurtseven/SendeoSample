package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    static AppiumDriver <WebElement> driver;
    public static URL url;
    public static DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    @BeforeTest
    public void setup() throws Exception {
        runAppiumServer();
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"ANDROID");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"AndroidSDK");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID,"emulator-5554");
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,60);
        desiredCapabilities.setCapability(MobileCapabilityType.APP,"src/test/resources/apps/selendroid-test-app-0.17.0.apk");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"io.selendroid.testapp");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"io.selendroid.testapp.HomeScreenActivity");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        url = new URL("http://127.0.0.1:4723/wd/hub");
    }

    @AfterTest
    public void killServer()throws InterruptedException {
        driver.quit();
        try {
            Runtime.getRuntime().exec("taskkill /f /im node.exe");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void runAppiumServer() throws InterruptedException {
        try {
            if(!isSocketAlive()) {
                Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"appium\"");
                Thread.sleep(5000);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static boolean isSocketAlive() {
        boolean isAlive = false;
        SocketAddress socketAddress = new InetSocketAddress("localhost", 4723);
        Socket socket = new Socket();
        int timeOut = 2000;
        try {
            socket.connect(socketAddress,timeOut);
            socket.close();
            isAlive=true;
        } catch (SocketTimeoutException e) {
        }catch (IOException e) {
        }
        return isAlive;
    }
    public void acceptPermissions(){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(3, TimeUnit.SECONDS).ignoring(java.util.NoSuchElementException.class);
        try {
            if (wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.permissioncontroller:id/continue_button"))).isDisplayed()){
                wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.permissioncontroller:id/continue_button"))).click();
                Thread.sleep(1000);
            }
            if (wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1"))).isDisplayed()){
                wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1"))).click();
            }
        }catch (NoSuchElementException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
