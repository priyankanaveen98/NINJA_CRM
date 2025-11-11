package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericutilities.WebDriverUtility;

public class HomePage {
	 WebDriver driver;
	 public HomePage(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 @FindBy(linkText = "Campaigns") private WebElement campaignLink;
	 @FindBy(linkText = "Products") private WebElement productsLink;
	 @FindBy(className =  "user-icon") private WebElement userIcon;
	 @FindBy(xpath = "//div[text()='Logout ']") private WebElement logoutBtn;
	 @FindBy(xpath = "//div[@role='alert']") private WebElement toastBtn;
	 @FindBy(xpath = "//button[@aria-label='close']") private WebElement closeToastMsg;
	 public WebElement getCampaignLink() {
		 return campaignLink;
	 }
	 public WebElement getProductsLink() {
		 return productsLink;
	 }
	 public WebElement getUserIcon() {
		 return userIcon;
	 }
	 public WebElement getLogoutBtn() {
		 return logoutBtn;
	 }
	 public WebElement getToastBtn() {
		 return toastBtn;
	 }
	 public WebElement getCloseToastMsg() {
		 return closeToastMsg;
	 }
	 public void logout() {
		 WebDriverUtility wLib = new WebDriverUtility();
		 wLib.mouseHoverOnWebElement(driver, userIcon);   
		   wLib.clickOnWebElement(driver, logoutBtn);
	 }

}
