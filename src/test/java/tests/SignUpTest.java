package tests;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.SignUpFactory;
import utils.DataProviderSource;
import utils.ExcelUtils;

import java.io.IOException;

public class SignUpTest extends BaseTest{
    @Test (dataProvider="SignUpDP",dataProviderClass = DataProviderSource.class,alwaysRun = true)
    public void signUp(Object[] excelDataList) throws IOException, InterruptedException {
        driver =new AppiumDriver<WebElement>(BaseTest.url, BaseTest.desiredCapabilities);
        acceptPermissions();
        SignUpFactory fact = new SignUpFactory(driver);
        fact.switchNewUserPage();
        fact.registerNewUser(excelDataList);
        Thread.sleep(1000);
        fact.assertUserDetails(excelDataList);
    }
}
