package genricutility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {

	public static WebDriver driver;

	public static ExtentReports reports;
	public ExtentSparkReporter spark;
	public ExtentTest extentTest;

	@BeforeSuite
	public void openBrowser() {

		reports = new ExtentReports();
		spark = new ExtentSparkReporter(

				System.getProperty("user.dir") + "/Reports/Extent-Reports/Extent-Test-Report.html");
		spark.config().setTheme(Theme.DARK);
		reports.attachReporter(spark);

		reports.setSystemInfo("Machine", "Noorulla");
		reports.setSystemInfo("Env", "Staging");

		WebDriverManager.chromedriver().setup();

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--test-type");
		option.addArguments("--incognito");
		option.addArguments("--enable-popup-blocking");

		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		driver = new ChromeDriver(option);


		driver.get("https://news.abplive.com/");



		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

	}

	@AfterSuite
	public void teardown() {

		reports.flush();

		driver.close();
		System.out.println("-------Closing the Application-----");
	}

	@AfterMethod
	public void AfterTestResult(ITestResult result) throws Throwable {

		Thread.sleep(5000);
		System.out.println("closing the browser");
		// ITestResult result;
		Webutility webUtil = new Webutility();
		String name = result.getName();

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, result.getName());
			extentTest.log(Status.FAIL, result.getThrowable());
			extentTest.log(Status.INFO, "Test is Failed............");
			String screenshotpath = webUtil.takeScreenShot(name);
			extentTest.fail("Screen Shot : " + extentTest.addScreenCaptureFromPath(screenshotpath, "Test Failure"));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, result.getName());
			extentTest.log(Status.INFO, "Test is Passed................");
			String screenshotpath = webUtil.takeScreenShot(name);

			extentTest.pass("Screen Shot : " + extentTest.addScreenCaptureFromPath(screenshotpath, "Test Success"));

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.skip("Test Case : " + result.getName() + " has been skipped");
			extentTest.log(Status.INFO, "Test is Skipped.............");
		}

	}

}