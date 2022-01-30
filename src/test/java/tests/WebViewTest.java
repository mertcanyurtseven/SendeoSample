package tests;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.WebViewFactory;
import utils.DataProviderSource;

public class WebViewTest extends BaseTest{

    @Test(dataProvider="WebViewDP",dataProviderClass = DataProviderSource.class,alwaysRun = true)
    public void webView(Object[] excelDataList) throws Exception {
        driver = new AppiumDriver<WebElement>(BaseTest.url, BaseTest.desiredCapabilities);
        acceptPermissions();
        WebViewFactory fact = new WebViewFactory(driver);
        fact.switchToWebView();
        fact.fillDetails(excelDataList);
        fact.writeValuesOnConsole();
    }

}
