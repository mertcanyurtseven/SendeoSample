package tests;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.ExceptionFactory;
import utils.DataProviderSource;

public class ExceptionTest extends BaseTest{
    @Test(dataProvider="ExceptionsDP",dataProviderClass = DataProviderSource.class,alwaysRun = true)
    public void exceptionControls(Object[] excelDataList) throws Exception {
        driver = new AppiumDriver<WebElement>(BaseTest.url, BaseTest.desiredCapabilities);
        acceptPermissions();
        ExceptionFactory fact = new ExceptionFactory(driver);
        fact.sendKeysByValue(excelDataList);
    }
}
