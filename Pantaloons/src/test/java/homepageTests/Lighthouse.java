package homepageTests;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import genricutility.Baseclass;
import genricutility.Webutility;

public class Lighthouse extends Baseclass{
	
	
	

	public String lighoutviewer = "https://googlechrome.github.io/lighthouse/viewer/?psiurl=";

	@Test
	public void abpreport() throws IOException, InterruptedException {
		extentTest = reports.createTest("lighthouse-report_performance_percentage_test").assignAuthor("Noor");

		Webutility wLib = new Webutility();

		for (int i = 1; i <= 18; i++) {
//		Thread.sleep(2000);
			System.out.println("opening the Story number ---->" + i + "\n");
			WebElement shortnews = driver
					.findElement(By.xpath("(//div[@class='uk-grid-collapse uk-grid'])[" + i + "]"));

			Thread.sleep(3000);
			wLib.waitForElementtobeVisible(shortnews);
			wLib.scrollTo(shortnews);
			shortnews.click();
			Thread.sleep(2000);
			try {

				driver.switchTo().frame("twitter-widget-0");

				WebElement Socialapp = driver.findElement(By.xpath("(//div[@id='app'])[1]"));
//				wLib.scrollTo(Socialapp);

				if (Socialapp.isDisplayed()) {
					System.out.println("twitter is visible");

					// take screen shot
					String screenshotpath = Webutility.takeScreenShot("twitter is visible in story num -" + i);
					extentTest.pass("Screen shot for the story num -" + i
							+ extentTest.addScreenCaptureFromPath(screenshotpath));

					extentTest.log(Status.INFO, "twitter is visible in story num -" + i);

					Thread.sleep(2000);
					driver.navigate().back();

				}
			} catch (Exception e) {
				

				System.out.println("Social app is not present in this story");
				Thread.sleep(2000);

				String currentstoryurl= driver.getCurrentUrl();
				String parentwindow = driver.getWindowHandle();
				Thread.sleep(2000);
				
				//opening a new window for light house html 
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.open('');");
				Set<String> newwindow = driver.getWindowHandles();
				Iterator<String> I1 = newwindow.iterator();

				while (I1.hasNext()) {

					String child_window = I1.next();

					if (!parentwindow.equals(child_window)) {
						driver.switchTo().window(child_window);
					}
				}

				driver.get(lighoutviewer + currentstoryurl);
				Thread.sleep(70000);
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement percentage = driver.findElement(By.xpath(
						"//div[@class='lh-category-headercol'] // descendant:: div[@class='lh-gauge__percentage']"));

				wait.until(ExpectedConditions.visibilityOf(percentage));
				String text = percentage.getText();
				System.out.println("-------------" + text + "------------");

				// assertion soft
				int percentnum = Integer.parseInt(text);
				Boolean status = percentnum > 50;
				SoftAssert sa = new SoftAssert();
				sa.assertTrue(status, "Performance percentage is not greater than 50");
				extentTest.log(Status.INFO,
						"light house report for the story --->" + i + "\nThe Performance Percentage is- " + text
								+ "\nPerformance Percentage Assertion status--" + status);
				// take screen shot
				String screenshotpath = Webutility.takeScreenShot("light house report for the story - " + i);
				if (status == true) {
					extentTest.pass("light house report  Screen shot for the story ---> " + i
							+ extentTest.addScreenCaptureFromPath(screenshotpath));
				} else {
					extentTest.fail("light house report  Screen shot for the story ---> " + i
							+ extentTest.addScreenCaptureFromPath(screenshotpath));
					System.out.println("url of Performance percentage less than 50 is ------> "+currentstoryurl);

				}
				

				Thread.sleep(2000);

				driver.close();
				driver.switchTo().window(parentwindow);

				driver.navigate().back();

			}

//			Assert.assertEquals(ele.isDisplayed(), true);
		}

		// -----------------------

//		 Desktop.getDesktop().browse(new File("C:\\o\\pantaloons\\lighthouseReports\\newsABP.report.html").toURI());

	}

//	@Test
//	public void javascriptforapp() {
////	public static void main(String[] args) {
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("const fs = require('fs');\r\n" + "const lighthouse = require('lighthouse');\r\n"
//				+ "const chromeLauncher = require('chrome-launcher');\r\n" + "\r\n" + "(async () => {\r\n"
//				+ "  const chrome = await chromeLauncher.launch({chromeFlags: ['--headless']});\r\n"
//				+ "  const options = {logLevel: 'info', output: 'html', onlyCategories: ['performance'], port: chrome.port};\r\n"
//				+ "  const runnerResult = await lighthouse('https://news.abplive.com/news/india/monsoon-session-centre-to-table-bill-to-amend-weapons-of-mass-destruction-act-know-all-about-it-1543045', options);\r\n"
//				+ "\r\n" + "  // `.report` is the HTML report as a string\r\n"
//				+ "  const reportHtml = runnerResult.report;\r\n"
//				+ "  fs.writeFileSync('lhreport.html', reportHtml);\r\n" + "\r\n"
//				+ "  // `.lhr` is the Lighthouse Result as a JS object\r\n"
//				+ "  console.log('Report is done for', runnerResult.lhr.finalUrl);\r\n"
//				+ "  console.log('Performance score was', runnerResult.lhr.categories.performance.score * 100);\r\n"
//				+ "\r\n" + "  await chrome.kill();\r\n" + "})();");
//
//	}

}
