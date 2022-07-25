package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects {

	public WebDriver driver;

	public HomePageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "")
	private WebElement firstelement;

	@FindBy(xpath = "//span[text()='MEN']")
	private WebElement menbutton;

	@FindBy(xpath = "//a[text()='Casual Shirts']")
	private WebElement casualShirts;

	@FindBy(xpath = "//div[@class='cart-icon']")
	private WebElement Carticon;

	@FindBy(xpath = "//div[@class='nav-header-wishlist-icon']")
	private WebElement Wishlisticon;

	@FindBy(xpath = "//div[@class='account-icon']")
	private WebElement accounticon;

	@FindBy(xpath = "//div[@class='search-bar-content-textSearch-icon']")
	private WebElement Searchbaricon;

// @FindBy(xpath = "//h1[text()='Casual Shirts']// ancestor :: div [@class='MuiGrid-root MuiGrid-container']")
	@FindBy(xpath = "//h1[@class='PlpWeb_plp-title__pyqPB']")
	private WebElement headerName;

	@FindBy(xpath = "//div[@class='PlpWeb_products-count__38RjJ']")
	private WebElement numofproductstext;

	@FindBy(xpath = "//div[text()='FILTER BY']")
	private WebElement filterby;

	@FindBy(xpath = "//div[text()='SORT BY']")
	private WebElement sortby;

	@FindBy(xpath = "//div[text()='Bare Denim']")
	private WebElement Brandname;

	@FindBy(xpath = "//div[text()='Yellow Checkered Shirt']")
	private WebElement productname;

	@FindBy(xpath = "//div[@class='PlpWeb_product-price__2A3Lf']")
	private List<WebElement> Listofprices;

	@FindBy(xpath = "//div[@class='PlpWeb_product-brand__1Hs5D']")
	private List<WebElement> Listofbrandname;

	@FindBy(xpath = "//div[@class='PlpWeb_product-name__1_7kw']")
	private List<WebElement> Listofproductname;
	
	@FindBy(xpath = "//p[text()='Product Type']")
	private WebElement ProductType;
	
	@FindBy(xpath = "//div[contains(text(),'Shirt ')]//ancestor :: div [@class = 'PlpWeb_filter-values__1rWUV'] // descendant :: input[@type='checkbox']")
	private WebElement shirtcheckbox;
//	
	@FindBy(xpath = "//div[@class='PlpWeb_filter-value-text__2GtKE' and contains(text(),'Shirt ')]")
	private WebElement shirtcheckboxtext;
	
	@FindBy(xpath = "//ol[@class='MuiBreadcrumbs-ol']")
	private WebElement path;
//	
//	@FindBy(xpath = "")
//	private WebElement firstelement;
//	
//	@FindBy(xpath = "")
//	private WebElement firstelement;
//	
//	@FindBy(xpath = "")
//	private WebElement firstelement;
//	
//	@FindBy(xpath = "")
//	private WebElement firstelement;
//	
//	@FindBy(xpath = "")
//	private WebElement firstelement;
	
	
	
	
	
	
	
	
	
	
	
	public WebElement getPath() {
		return path;
	}

	public WebElement getShirtcheckboxtext() {
		return shirtcheckboxtext;
	}

	public WebElement getShirtcheckbox() {
		return shirtcheckbox;
	}

	public WebElement getProductType() {
		return ProductType;
	}

	public List<WebElement> getListofprices() {
		return Listofprices;
	}
	
	public List<WebElement> getListofbrandname() {
		return Listofbrandname;
	}
	
	public List<WebElement> getListofproductname() {
		return Listofproductname;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public WebElement getSortby() {
		return sortby;
	}

	public WebElement getFilterby() {
		return filterby;
	}

	public WebElement getHeaderName() {
		return headerName;
	}

	public WebElement getCarticon() {
		return Carticon;
	}

	public WebElement getWishlisticon() {
		return Wishlisticon;
	}

	public WebElement getAccounticon() {
		return accounticon;
	}

	public WebElement getSearchbaricon() {
		return Searchbaricon;
	}

	public WebElement getCasualShirts() {
		return casualShirts;
	}

	public WebElement getMenbutton() {
		return menbutton;
	}

	public WebElement getFirstelement() {
		return firstelement;
	}

	public WebElement getNumofproductstext() {
		return numofproductstext;
	}

}