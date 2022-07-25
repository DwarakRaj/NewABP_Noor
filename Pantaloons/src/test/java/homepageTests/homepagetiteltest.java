package homepageTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import genricutility.Baseclass;
import pageobjects.HomePageObjects;

public class homepagetiteltest extends Baseclass {

	HomePageObjects hpom;

	@Test(priority = 1, description = "PLP page verification")
	public void TCTCPLP_001() {
		hpom = new HomePageObjects(driver);

		System.out.println("--------" + driver.getTitle());

		assertTrue(hpom.getSearchbaricon().isDisplayed());
		Reporter.log("Search bar is Displayed.");
		assertTrue(hpom.getAccounticon().isDisplayed(), "account statemetn check ------");
		Reporter.log("Account Icon is Displayed");
		assertTrue(hpom.getWishlisticon().isDisplayed());
		Reporter.log("Wishlist Iconis Displayed.");
		assertTrue(hpom.getCarticon().isDisplayed());
		Reporter.log("CartIcon is Displayed.");

	}

	@Test(priority = 2, description = "Click on any sub category in the main menu")
	public void TCTCPLP_002() throws InterruptedException {

		hpom = new HomePageObjects(driver);

		hpom.getMenbutton().click();

		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
		hpom.getCasualShirts().click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		Thread.sleep(3000);
		String headerName = hpom.getHeaderName().getText();
		System.out.println(headerName);
		assertEquals(headerName, "Casual Shirts");
		Reporter.log("the header name is matching --" + headerName);
		
		

	}
	@Test(priority = 3, description = "Click on any sub category in the main menu")
	public void TCTCPLP_003() throws InterruptedException {

		hpom = new HomePageObjects(driver);

		hpom.getMenbutton().click();

		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
		hpom.getCasualShirts().click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		Thread.sleep(3000);
		String productcount = hpom.getNumofproductstext().getText();
		System.out.println(productcount);
		
		String productnum[]=productcount.split(" ");
		System.out.println("the product count --" +productnum[0]);
		
		assertEquals(productnum[0], "2831");
		Reporter.log("the product count --" + productnum[0]);
		
		

	}
	
	@Test(priority = 4, description = "Click on any sub category in the main menu")
	public void TCTCPLP_004() throws InterruptedException {

		hpom = new HomePageObjects(driver);

		hpom.getMenbutton().click();

		assertTrue(hpom.getFilterby().isDisplayed());
		Reporter.log("Filter By is Displayed.");
		assertTrue(hpom.getSortby().isDisplayed());
		Reporter.log("Sort By is Displayed");
		


	}
	
	@Test(priority = 5, description = "Verify that user is able to view Product Name,Price in PLP")
	public void TCTCPLP_005() throws InterruptedException {

		hpom = new HomePageObjects(driver);

		hpom.getMenbutton().click();

		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
		hpom.getCasualShirts().click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		Thread.sleep(3000);
		
		for (int i = 0; i < hpom.getListofbrandname().size(); i++) {
			
			assertTrue(hpom.getListofbrandname().get(i).isDisplayed());
			Reporter.log(hpom.getListofbrandname().get(i).getText()+" ---is Displayed.");
			

			assertTrue(hpom.getListofproductname().get(i).isDisplayed());
			Reporter.log(hpom.getListofproductname().get(i).getText()+" ---is Displayed.");
			
			
			assertTrue(hpom.getListofprices().get(i).isDisplayed());
			Reporter.log(hpom.getListofprices().get(i).getText()+" ---is Displayed.");
			
			
			
			System.out.println("\n-- the product details are  ---\n");
			System.out.println(hpom.getListofbrandname().get(i).getText());
			System.out.println(hpom.getListofproductname().get(i).getText());
			System.out.println(hpom.getListofprices().get(i).getText());

		}
	}
		
		@Test(priority = 6, description = "Verify that user is able to view path of the page by navigating to PLP")
		public void TCTCPLP_06() {

			hpom = new HomePageObjects(driver);

			hpom.getMenbutton().click();

			assertTrue(hpom.getPath().isDisplayed());
			Reporter.log("Path is Displayed.");
			
           
	}

	
	
	@Test(priority = 8, description = "Verify that count of products in PLP is same as count present in the applied filter")
	public void TCTCPLP_008() throws InterruptedException {

		hpom = new HomePageObjects(driver);

		hpom.getMenbutton().click();

		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
		hpom.getCasualShirts().click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		Thread.sleep(3000);
		hpom.getProductType().click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        hpom.getShirtcheckbox().click();
        
       // driver.findElement(By.xpath("//div[contains(text(),'Shirt ')]//ancestor :: div [@class = 'PlpWeb_filter-values__1rWUV'] // descendant :: input[@type='checkbox']"));
        
        Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        
       String productcount = hpom.getNumofproductstext().getText();
		System.out.println("product count is----"+productcount);
		
		String productnum[]=productcount.split(" ");
		System.out.println("num of products in the header-----"+productnum[0]);
		
		String numofproducts[]=hpom.getShirtcheckboxtext().getText().split("[ ()]");
		System.out.println("num of products near checkbox-----"+numofproducts[2]);
		
		assertEquals(productnum[0], numofproducts[2]);
        Reporter.log("the counts are matching");
        
        
	}
	
       
}

