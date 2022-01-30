package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
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

public class SignUpFactory extends BaseTest {
    private Wait<WebDriver> wait;
    private AppiumDriver driver;
    @FindBy(id="io.selendroid.testapp:id/buttonStartWebview")
    WebElement webViewBtn;

    @FindBy(id="io.selendroid.testapp:id/startUserRegistration")
    WebElement registerNewUserBtn;

    @FindBy(id="io.selendroid.testapp:id/my_text_field")
    WebElement textField;

    @FindBy(id="io.selendroid.testapp:id/waitingButtonTest")
    WebElement showProgressBarBtn;

    @FindBy(id="io.selendroid.testapp:id/visibleButtonTest")
    WebElement displayTextBtn;

    @FindBy(id="io.selendroid.testapp:id/showToastButton")
    WebElement displayToastBtn;

    @FindBy(id="io.selendroid.testapp:id/showPopupWindowButton")
    WebElement displayPopUpBtn;

    @FindBy(id="io.selendroid.testapp:id/exceptionTestButton")
    WebElement getExceptionBtn;

    @FindBy(id="io.selendroid.testapp:id/input_adds_check_box")
    WebElement acceptAddsCbxHomePage;

    @FindBy(id="io.selendroid.testapp:id/exceptionTestField")
    WebElement getGetExceptionTextField;

    @FindBy(id="io.selendroid.testapp:id/topLevelElementTest")
    WebElement focusBtn;

    @FindBy(id="io.selendroid.testapp:id/touchTest")
    WebElement touchActionsBtn;

    @FindBy(id="io.selendroid.testapp:id/inputUsername")
    WebElement userNameTextField;

    @FindBy(id="io.selendroid.testapp:id/inputEmail")
    WebElement emailTextField;

    @FindBy(id="io.selendroid.testapp:id/inputPassword")
    WebElement passwordTextField;

    @FindBy(id="io.selendroid.testapp:id/inputName")
    WebElement nameTextField;

    @FindBy(id="io.selendroid.testapp:id/input_preferedProgrammingLanguage")
    WebElement plCombo;

    @FindBy(id="io.selendroid.testapp:id/input_adds")
    WebElement acceptAddsCbx;

    @FindBy(id="io.selendroid.testapp:id/btnRegisterUser")
    WebElement saveUserBtn;

    @FindBy(id="io.selendroid.testapp:id/label_name_data")
    WebElement nameLbl;

    @FindBy(id="io.selendroid.testapp:id/label_username_data")
    WebElement uNameLbl;

    @FindBy(id="io.selendroid.testapp:id/label_password_data")
    WebElement passLbl;

    @FindBy(id="io.selendroid.testapp:id/label_email_data")
    WebElement eMailLbl;

    @FindBy(id="io.selendroid.testapp:id/label_preferedProgrammingLanguage_data")
    WebElement plLbl;

    @FindBy(id="io.selendroid.testapp:id/label_acceptAdds_data")
    WebElement acceptAddsLbl;

    @FindBy(id="io.selendroid.testapp:id/buttonRegisterUser")
    WebElement saveUsrBtnOnDataPage;

    public SignUpFactory(AppiumDriver driver) {
        this.driver =driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,60);
    }
    public void switchNewUserPage(){
        wait.until(ExpectedConditions.elementToBeClickable(registerNewUserBtn)).click();
    }
    public void registerNewUser(Object[] excelDataList) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(userNameTextField)).click();
        userNameTextField.sendKeys(excelDataList[0].toString());
        wait.until(ExpectedConditions.elementToBeClickable(emailTextField)).click();
        emailTextField.sendKeys(excelDataList[1].toString());
        wait.until(ExpectedConditions.elementToBeClickable(passwordTextField)).click();
        passwordTextField.sendKeys(excelDataList[2].toString());
        wait.until(ExpectedConditions.elementToBeClickable(nameTextField)).click();
        if(!nameTextField.getText().equals(excelDataList[3].toString())){
            nameTextField.clear();
            nameTextField.sendKeys(excelDataList[3].toString());
        }
        driver.hideKeyboard();
        wait.until(ExpectedConditions.elementToBeClickable(plCombo)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.CheckedTextView[contains(@text, '"+excelDataList[4]+"')]")).click();
        if (excelDataList[5].toString().equals("true")){
            wait.until(ExpectedConditions.elementToBeClickable(acceptAddsCbx)).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(saveUserBtn)).click();
    }
    public void assertUserDetails(Object[] excelDataList){
        Assert.assertEquals(wait.until(ExpectedConditions.elementToBeClickable(nameLbl)).getText(),excelDataList[3]);
        Assert.assertEquals(wait.until(ExpectedConditions.elementToBeClickable(uNameLbl)).getText(),excelDataList[0]);
        Assert.assertEquals(wait.until(ExpectedConditions.elementToBeClickable(passLbl)).getText(),excelDataList[2]);
        Assert.assertEquals(wait.until(ExpectedConditions.elementToBeClickable(eMailLbl)).getText(),excelDataList[1]);
        Assert.assertEquals(wait.until(ExpectedConditions.elementToBeClickable(plLbl)).getText(),excelDataList[4]);
        Assert.assertEquals(wait.until(ExpectedConditions.elementToBeClickable(acceptAddsLbl)).getText(),excelDataList[5]);
        wait.until(ExpectedConditions.elementToBeClickable(saveUsrBtnOnDataPage)).click();
    }
}
