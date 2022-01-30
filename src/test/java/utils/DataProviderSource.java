package utils;
import org.testng.annotations.DataProvider;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
public class DataProviderSource {


    private static List<Object> iTestCaseRows;
    private static List<Object> testRowNumCounter;
    private static Object[][] testObjArray;
    private static final String testDataPath = "src/main/resources/testData.xlsx" ;

    public static Iterator<Object[]> getObjectArray(String sheetName, String className) throws Exception {
        Collection<Object[]> dp = new LinkedList<Object[]>();
        ExcelUtils.setExcelFile(testDataPath,sheetName);

        testRowNumCounter = ExcelUtils.getRowContains(className,0);
        iTestCaseRows = (List)testRowNumCounter.get(0);


        for (int i=0; i<Integer.parseInt(testRowNumCounter.get(1).toString()); i++)
        {
            testObjArray = ExcelUtils.getTableArray(testDataPath,sheetName,Integer.parseInt(iTestCaseRows.get(i).toString()));
            dp.add(testObjArray);
        }
        return dp.iterator();
    }
    @DataProvider(name="SignUpDP")
    public static Iterator<Object[]> SignUpDP() throws Exception {
        return getObjectArray("SignUp", "signUp"); }

    @DataProvider(name="ProgressBarDP")
    public static Iterator<Object[]> ProgressBarDP() throws Exception {
        return getObjectArray("ProgressBar", "progressBar"); }

    @DataProvider(name="ExceptionsDP")
    public static Iterator<Object[]> ExceptionsDP() throws Exception {
        return getObjectArray("Exceptions", "exceptionControls"); }

    @DataProvider(name="WebViewDP")
    public static Iterator<Object[]> WebViewDP() throws Exception {
        return getObjectArray("WebViewTest", "webView"); }
}
