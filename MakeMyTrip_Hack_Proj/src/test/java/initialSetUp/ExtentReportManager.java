package initialSetUp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final ThreadLocal<ExtentReports> extentThreadLocal = new ThreadLocal<>(); 
    private static final ThreadLocal<ExtentSparkReporter> reporterThreadLocal = new ThreadLocal<>();
    
    @Override
    public void onStart(ITestContext context) {
    	String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String browser = context.getCurrentXmlTest().getParameter("Browser"); //FETCH BROWSER NAME
        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_" + browser + "_" +timestamp + ".html";
 
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Mini Project");
        sparkReporter.config().setReportName("MakeMyTrip - " + browser); 
        sparkReporter.config().setTheme(Theme.STANDARD);
 
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
 
        extent.setSystemInfo("Computer Name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester Name", "Hemachandran");
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Browser", browser);
 
        extentThreadLocal.set(extent);
        reporterThreadLocal.set(sparkReporter);
    }
 
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReports extent = extentThreadLocal.get();
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }
 
    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test case PASSED: " + result.getName());
    }
 
    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test case FAILED: " + result.getName());
        test.get().log(Status.FAIL, "Cause: " + result.getThrowable());
    }
 
    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test case SKIPPED: " + result.getName());
    }
 
    @Override
    public void onFinish(ITestContext context) {
        ExtentReports extent = extentThreadLocal.get(); 
        if (extent != null) {
            extent.flush();
        }
    }
}
