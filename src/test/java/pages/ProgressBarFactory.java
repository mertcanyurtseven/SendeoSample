package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ProgressBarFactory extends BaseTest {
    private Wait<WebDriver> wait;
    private static AppiumDriver driver;

    @FindBy(id="io.selendroid.testapp:id/waitingButtonTest")
    WebElement progressBarBtn;

    public ProgressBarFactory(AppiumDriver driver) throws Exception {
        this.driver =driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,60);

    }

    public void waitForProgressBar() throws InterruptedException {
        By progressBar = By.id("android:id/parentPanel");
        wait.until(ExpectedConditions.elementToBeClickable(progressBarBtn)).click();
        Thread.sleep(1000);
        boolean disp = driver.findElement(progressBar).isDisplayed();
        if (disp) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(progressBar));
            Thread.sleep(1000);
        }
    }
}
