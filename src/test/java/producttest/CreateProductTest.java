package producttest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericutilities.BaseClass;
import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectRepository.AddProductPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.ProductsPage;

public class CreateProductTest extends BaseClass{
	@Test(groups = {"smoke","regression"})
	public void createProductMobile() throws IOException {
		
		String PRODUCTNAME = elib.readDataFromExcelFile("Product", 1, 2);
		String SELECTCATEGORY = elib.readDataFromExcelFile("Product", 1, 3);
		String QUANTITY = elib.readDataFromExcelFile("Product", 1, 4);
		String PRICEPERUNIT = elib.readDataFromExcelFile("Product", 1, 5);
		String SELECTVENDOR = elib.readDataFromExcelFile("Product", 1, 6);
   
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
 homepage.getCloseToastMsg().click();
 Assert.assertTrue(msg.contains("Successfully Added"));
 
	}
	
	@Test(groups = {"smoke","regression"})
	public void createProductLaptop() throws IOException {

		String PRODUCTNAME = elib.readDataFromExcelFile("Product", 4, 2);
		String SELECTCATEGORY = elib.readDataFromExcelFile("Product", 4, 3);
		String QUANTITY = elib.readDataFromExcelFile("Product", 4, 4);
		String PRICEPERUNIT = elib.readDataFromExcelFile("Product", 4, 5);
		String SELECTVENDOR = elib.readDataFromExcelFile("Product", 4, 6);
		
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
homepage.getCloseToastMsg().click();
Assert.assertTrue(msg.contains("Successfully Added"));
 
	}

}
