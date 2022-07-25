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
import genricutility.Excelutility;
import genricutility.Webutility;

public class Duplicate extends Baseclass{
	

	public String lighoutviewer = "https://googlechrome.github.io/lighthouse/viewer/?psiurl=";

	@Test
	public void abpreport() throws Throwable {
		extentTest = reports.createTest("lighthouse-report_performance_percentage_test").assignAuthor("Noor");

		Webutility wLib = new Webutility();
		Excelutility eutil=new Excelutility("/Users/liciouss/eclipse-workspace/Pantaloons/excel/Lighthouse data 1.xlsx");
		
		//wLib.writedataSS("Article num", "Sheet3", 0, 1);
		//wLib.writedataSS("Performance %","Sheet3", 0, 2);
		//wLib.writedataSS("Url", "Sheet3", 0, 3);



		for (int i = 1; i <= 18; i++) {
//		Thread.sleep(2000);
			
			System.out.println("opening the Story number ---->" + i + "\n");
			eutil.writeDataInBlankcell("Sheet1", "" + i + "", 0);
			WebElement shortnews = driver
					.findElement(By.xpath("(//div[@class='uk-grid-collapse uk-grid'])[" + i + "]"));
			
			//wLib.writedataIS(i, "Sheet3", i, 1);
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
					eutil.writeDataInBlankcell("Sheet1", "Yes", 1);
					eutil.writeDataInBlankcell("Sheet1", "N/A", 2);
					eutil.writeDataInBlankcell("Sheet1", "N/A", 3);

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
				eutil.writeDataInBlankcell("Sheet1", "No", 1);
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
				eutil.writeDataInBlankcell("Sheet1", text, 2);

				// assertion soft
				int percentnum = Integer.parseInt(text);
				
				//wLib.writedataIS(percentnum, "Sheet3", i, 2);
				
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
					//System.out.println("url of Performance percentage is greater than 50 ------> "+currentstoryurl);
					eutil.writeDataInBlankcell("Sheet1", "> 50", 3);

				} else {
					extentTest.fail("light house report  Screen shot for the story ---> " + i
							+ extentTest.addScreenCaptureFromPath(screenshotpath));
					System.out.println("url of Performance percentage is not greater than 50 ------> "+currentstoryurl);
					eutil.writeDataInBlankcell("Sheet1", currentstoryurl, 3);
					//wLib.writedataSS(currentstoryurl, "Sheet3", i, 3);
				}
				

				Thread.sleep(2000);

				driver.close();
				driver.switchTo().window(parentwindow);

				driver.navigate().back();

			}
			
			

//			Assert.assertEquals(ele.isDisplayed(), true);
		}
	
	}
	

}
