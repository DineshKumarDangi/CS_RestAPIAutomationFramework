package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener {
	ExtentSparkReporter htmlReporter; // user interface
	ExtentReports reports; // common information
	ExtentTest test; // entries for test

	public void configureReport() {
		String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		String reportName = "PetStoreAutomationTestReport-" + timestamp + ".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//Reports//" + reportName);

		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);

		// add system information / environment info to reports
		reports.setSystemInfo("Machine", "testpc1");
		reports.setSystemInfo("OS", "windos 11");
		reports.setSystemInfo("user name:", "Prachi");

		// Configuration to change look and feel of report
		htmlReporter.config().setDocumentTitle("Extent Listner Report Demo");
		htmlReporter.config().setReportName("This is my First Report");
		htmlReporter.config().setTheme(Theme.DARK);
	}

	// OnStart method is called when any Test starts.
	public void onStart(ITestContext Result) {
		configureReport();
		System.out.println("On start method invoked....");
	}

	// onFinish method is called after all Tests are executed
	public void onFinish(ITestContext Result) {
		System.out.println("onFinosh method invoked...");
		reports.flush(); // It is mandatory to call flush method to ensure information is
	}

	// When test case get failed, this method is called.
	public void onTestFailure(ITestResult Result) {
		System.out.println("Name of the test method failed:" + Result.getName());
		test = reports.createTest(Result.getName()); // create entry in HTML report
		test.log(Status.FAIL,
				MarkupHelper.createLabel("Name of the failed test case" + Result.getName(), ExtentColor.RED));

		String screenShotPath = System.getProperty("user.dir") + "//ScreenShots//" + Result.getStatus();
		System.out.println(" *********** Screenshot path ****************** "+screenShotPath);
		File screenShotFile = new File(screenShotPath);

		if(screenShotFile.exists()) {
			test.fail("Captured screenshot is below: " + test.addScreenCaptureFromPath(screenShotPath));
		}
	}

	public void onTestSkipped(ITestResult Result) {
		System.out.println("Name of the skipped test case" + Result.getName());
		test = reports.createTest(Result.getName()); // create entry in HTML report
		test.log(Status.FAIL,
				MarkupHelper.createLabel("Name of the failed test case" + Result.getName(), ExtentColor.YELLOW));
	}

	public void onTestSuccess(ITestResult Result) {
		System.out.println("Name of the test method successfully executed: " + Result.getName());
		test = reports.createTest(Result.getName()); // create entry in HTML report
		test.log(Status.PASS,
				MarkupHelper.createLabel("Name of the passed test case is : " + Result.getName(), ExtentColor.GREEN));
	}
}
