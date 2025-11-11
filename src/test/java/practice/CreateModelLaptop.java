package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectRepository.AddProductPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.ProductsPage;

public class CreateModelLaptop {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		PropertyFileUtility plib=new PropertyFileUtility();
		String BROWSER = plib.readDataFromPropertyFile("Browser");
	    String URL=plib.readDataFromPropertyFile("URL");
	    String USERNAME=plib.readDataFromPropertyFile("Username");
		String PASSWORD=plib.readDataFromPropertyFile("Password");
		
		ExcelFileUtility elib=new ExcelFileUtility();
		String PRODUCTNAME = elib.readDataFromExcelFile("Product", 4, 2);
		String SELECTCATEGORY = elib.readDataFromExcelFile("Product", 4, 3);
		String QUANTITY = elib.readDataFromExcelFile("Product", 4, 4);
		String PRICEPERUNIT = elib.readDataFromExcelFile("Product", 4, 5);
		String SELECTVENDOR = elib.readDataFromExcelFile("Product", 4, 6);

		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();	
		
		ChromeOptions settings = new ChromeOptions(); 
		Map<String, Object> prefs = new HashMap<>(); 
		prefs.put("profile.password_manager_leak_detection", false); 
		settings.setExperimentalOption("prefs", prefs);
		
		WebDriver driver =null;
		if(BROWSER.equalsIgnoreCase("Edge"))
			driver=new EdgeDriver();
		else if(BROWSER.equalsIgnoreCase("Chrome"))
			driver=new ChromeDriver(settings);
		else if(BROWSER.equalsIgnoreCase("Firefox"))
			driver=new FirefoxDriver();
		else if(BROWSER.equalsIgnoreCase("Safari"))
			driver=new SafariDriver();
		
		driver.manage().window().maximize();
		wLib.implicitWait(driver);
		
		LoginPage loginpage=new LoginPage(driver);
		loginpage.loginToApp(URL, USERNAME, PASSWORD);
   
		HomePage homePage=new HomePage(driver);
		homePage.getProductsLink().click();
		ProductsPage productsPage=new ProductsPage(driver);
   		productsPage.getAddProductsBtn().click();
   		
   		AddProductPage addProductPage=new AddProductPage(driver);
   		addProductPage.getProductNameTF().sendKeys(PRODUCTNAME+jlib.generateRandomNumber());
   WebElement product = addProductPage.getProductCategoryDD();
   wLib.select(product, SELECTCATEGORY);
   WebElement quantity=addProductPage.getQuantityTF();
   quantity.clear();
   quantity.sendKeys(QUANTITY);
   WebElement price=addProductPage.getPriceTF();
   price.clear();
   price.sendKeys(PRICEPERUNIT);
   //addProductPage.getPriceTF().sendKeys(PRICEPERUNIT);
 WebElement vendorIdDD = addProductPage.getVendorIdDD();
 wLib.select(vendorIdDD, SELECTVENDOR);
 addProductPage.getAddBtn().click();
 
 HomePage homepage=new HomePage(driver);
 WebElement toastMsg=homepage.getToastBtn();
 wLib.waitUntilElementToBeVisible(driver, toastMsg);
String msg=toastMsg.getText();
if(msg.contains("Successfully Added"))
	  System.out.println("Product Created");
else
	  System.out.println("Product Not Created");
 homepage.getCloseToastMsg().click();
 
//logout
 homepage.logout();
    
    
    driver.quit();

	}

}
