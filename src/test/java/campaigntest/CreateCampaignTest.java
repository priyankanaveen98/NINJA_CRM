package campaigntest;

import java.io.IOException;
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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericutilities.BaseClass;
import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectRepository.CampaignsPage;
import objectRepository.CreateCampaignPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

@Listeners(genericutilities.ListenerImplementation.class)
 class CreateCampaignTest extends BaseClass {
	
	@Test(groups = {"smoke","regression"})
	public void createCampaignWithMandatoryFieldsTest() throws IOException {
		
		String CAMPAIGN_NAME = elib.readDataFromExcelFile("Campaigns", 1, 2);
		String TARGET_SIZE = elib.readDataFromExcelFile("Campaigns", 1, 3);
		
		CampaignsPage campaignsPage=new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage=new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
	createCampaignPage.getCreateCampaignBtn().click();
	
	HomePage homepage=new HomePage(driver);
	   WebElement toastMsg=homepage.getToastBtn();
	   wLib.waitUntilElementToBeVisible(driver, toastMsg);
	  String msg=toastMsg.getText();
	  homepage.getCloseToastMsg().click();
	  Assert.assertTrue(msg.contains("Successfully Added"));
	  System.out.println("Bye");
	   
	}
	
	@Test(groups = "regression")
	public void createCampaignWithStatusTest() throws IOException {
	
		String CAMPAIGN_NAME = elib.readDataFromExcelFile("Campaigns", 4, 2);
		String TARGET_SIZE = elib.readDataFromExcelFile("Campaigns", 4, 3);
		String STATUS = elib.readDataFromExcelFile("Campaigns", 4, 4);

		CampaignsPage campaignsPage=new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage=new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getCampaignStatusTF().sendKeys(STATUS);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
	createCampaignPage.getCreateCampaignBtn().click();
	
	HomePage homepage=new HomePage(driver);
   WebElement toastMsg=homepage.getToastBtn();
   wLib.waitUntilElementToBeVisible(driver, toastMsg);
  String msg=toastMsg.getText();
  homepage.getCloseToastMsg().click();
  Assert.assertTrue(msg.contains("Successfully Added"));
		
	}
	@Test(groups = "regression")
	public void createCampaignWithExpectedCloseDateTest() throws IOException {
	
		String CAMPAIGN_NAME = elib.readDataFromExcelFile("Campaigns", 4, 2);
		String TARGET_SIZE = elib.readDataFromExcelFile("Campaigns", 4, 3);
		
		
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
	  homepage.getCloseToastMsg().click();
	  Assert.assertTrue(msg.contains("Successfully Added"));

	}
}
