package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	 WebDriver driver;
	 public LoginPage(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 @FindBy(id="username")private WebElement usernameTF;
	 @FindBy(id="inputPassword")private WebElement passwordTF;
	 @FindBy(xpath="//button[@type='submit']")private WebElement signinBtn;
	 public WebElement getUsernameTF() {
		 return usernameTF;
	 }
	 public WebElement getPasswordTF() {
		 return passwordTF;
	 }
	 public WebElement getSigninBtn() {
		 return signinBtn;
	 }
	 
	 public void loginToApp(String url,String username,String password) {
		 driver.get(url);
		 usernameTF.sendKeys(username);
		 passwordTF.sendKeys(password);
		 signinBtn.click();
	 }
}
