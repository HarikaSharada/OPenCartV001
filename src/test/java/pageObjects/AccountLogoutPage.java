package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountLogoutPage extends BasePage{
	
	public AccountLogoutPage(WebDriver driver){
		super(driver);
	}
	 @FindBy(xpath="//a[text()='Continue']") WebElement btnContinue;
	 
	 public void clickOnContinue() {
		 btnContinue.click();
	 }

}
