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
import org.testng.Assert;
import tests.BaseTest;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class WebViewFactory extends BaseTest {
    private Wait<WebDriver> wait;
    private static AppiumDriver driver;

    @FindBy(id="io.selendroid.testapp:id/buttonStartWebview")
    WebElement webViewButton;

    @FindBy(xpath="*//android.webkit.WebView/android.widget.TextView")
    WebElement helloLabel;

    @FindBy(xpath="*//android.widget.EditText")
    WebElement nameInput;

    @FindBy(xpath="*//android.view.View[4]/android.view.View[2]")
    WebElement carCombo;

    @FindBy(xpath="*//android.view.View/android.widget.Button")
    WebElement submitButton;

    public WebViewFactory(AppiumDriver driver) throws Exception {
        this.driver =driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,60);
    }

    public void switchToWebView() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(webViewButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(helloLabel));
        Assert.assertEquals(helloLabel.getText(),"Hello, can you please tell me your name?");
    }

    public void fillDetails(Object[] excelDataList) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(nameInput)).click();
        nameInput.clear();
        nameInput.sendKeys(excelDataList[0].toString());
        wait.until(ExpectedConditions.elementToBeClickable(carCombo)).click();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[contains(@text, '"+excelDataList[1]+"')]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        Thread.sleep(1000);
    }

    public void writeValuesOnConsole(){
        for(int i = 1; i<driver.findElements(By.xpath("*//android.webkit.WebView/android.webkit.WebView/android.widget.TextView")).size(); i++){
            System.out.println(driver.findElement(By.xpath("*//android.webkit.WebView/android.webkit.WebView/android.widget.TextView["+String.valueOf(i)+"]")).getText()+"\n");
        }
    }


}
