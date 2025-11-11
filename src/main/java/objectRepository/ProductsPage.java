package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	WebDriver driver;
	 public ProductsPage(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 @FindBy(xpath = "//button[@class='btn btn-info']")private WebElement addProductsBtn;
	 public WebElement getAddProductsBtn() {
		 return addProductsBtn;
	 }
	 
}
