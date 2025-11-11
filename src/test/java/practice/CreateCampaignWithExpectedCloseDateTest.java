package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
import org.openqa.selenium.support.ui.WebDriverWait;

import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectRepository.CampaignsPage;
import objectRepository.CreateCampaignPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class CreateCampaignWithExpectedCloseDateTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		PropertyFileUtility plib=new PropertyFileUtility();
		String BROWSER = plib.readDataFromPropertyFile("Browser");
	    String URL=plib.readDataFromPropertyFile("URL");
	    String USERNAME=plib.readDataFromPropertyFile("Username");
		String PASSWORD=plib.readDataFromPropertyFile("Password");

		ExcelFileUtility elib=new ExcelFileUtility();
		String CAMPAIGN_NAME = elib.readDataFromExcelFile("Campaigns", 4, 2);
		String TARGET_SIZE = elib.readDataFromExcelFile("Campaigns", 4, 3);
		JavaUtility jlib = new JavaUtility();
		
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
		
		 WebDriverUtility wLib = new WebDriverUtility();
		driver.manage().window().maximize();
		wLib.implicitWait(driver);
		
		//login
		LoginPage loginpage=new LoginPage(driver);
		loginpage.loginToApp(URL, USERNAME, PASSWORD);
		
		CampaignsPage campaignsPage=new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage=new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
	   createCampaignPage.getExpectedCloseDateTF().sendKeys(jlib.getRequiredDate(50));
	    createCampaignPage.getCreateCampaignBtn().click();
	
	HomePage homepage=new HomePage(driver);
	   WebElement toastMsg=homepage.getToastBtn();
	   wLib.waitUntilElementToBeVisible(driver, toastMsg);
	  String msg=toastMsg.getText();
	  if(msg.contains("Successfully Added"))
		  System.out.println("Campaign Created");
	  else
		  System.out.println("Campaign Not Created");
	   homepage.getCloseToastMsg().click();
	   
	 //logout
	   homepage.logout();
   
   driver.quit();

	}

}
