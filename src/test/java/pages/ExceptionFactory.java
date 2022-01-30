package pages;

import io.appium.java_client.AppiumDriver;
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

public class ExceptionFactory extends BaseTest {
    private Wait<WebDriver> wait;
    private static AppiumDriver driver;

    @FindBy(id="io.selendroid.testapp:id/my_text_field")
    WebElement textField;

    @FindBy(id="io.selendroid.testapp:id/exceptionTestField")
    WebElement exceptionTextField;

    @FindBy(id="io.selendroid.testapp:id/topLevelElementTest")
    WebElement focusBtn;

    @FindBy(id="io.selendroid.testapp:id/focusedText")
    WebElement focusWarning;

    public ExceptionFactory(AppiumDriver driver) throws Exception {
        this.driver =driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,60);
    }

    public void sendKeysByValue(Object[] excelDataList){
        if(excelDataList[0].toString() != null && !excelDataList[0].toString().equals("")){
            /*This block will be failed*/
            wait.until(ExpectedConditions.elementToBeClickable(exceptionTextField)).click();
            exceptionTextField.sendKeys(excelDataList[0].toString());
        }else {
            wait.until(ExpectedConditions.elementToBeClickable(focusBtn)).click();
            Assert.assertEquals(focusWarning.getText(), "Should only be found once");
            focusBtn.click();
            wait.until(ExpectedConditions.elementToBeClickable(textField)).sendKeys(excelDataList[1].toString());
        }

    }
}
