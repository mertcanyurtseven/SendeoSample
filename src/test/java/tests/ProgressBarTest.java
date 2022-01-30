package tests;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.ProgressBarFactory;
import pages.SignUpFactory;
import utils.DataProviderSource;

public class ProgressBarTest extends BaseTest {
    @Test(dataProvider="ProgressBarDP",dataProviderClass = DataProviderSource.class,alwaysRun = true)
    public void progressBar(Object[] excelDataList) throws Exception {
        driver = new AppiumDriver<WebElement>(BaseTest.url, BaseTest.desiredCapabilities);
        acceptPermissions();
        ProgressBarFactory progressBarFactory = new ProgressBarFactory(driver);
        SignUpFactory signUpFactory = new SignUpFactory(driver);
        progressBarFactory.waitForProgressBar();
        signUpFactory.registerNewUser(excelDataList);
        Thread.sleep(1000);
        signUpFactory.assertUserDetails(excelDataList);
    }
}
