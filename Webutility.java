package genricutility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class Webutility extends Baseclass{
	
	
	

	public void scrollTo(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int yaxis = element.getLocation().getY();
		js.executeScript("window.scrollBy(0," + yaxis + ")", element);
	}
	
	public static  String takeScreenShot(String screenshotName) throws IOException{


		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String screenshotpath = 
				System.getProperty("user.dir")+ 
				"/Reports/Screen-shots/" + screenshotName +"--.png";
		File destination = new File(screenshotpath);
		Files.copy(source, destination);
		return screenshotpath;
	}
	
	public void waitForElementtobeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
