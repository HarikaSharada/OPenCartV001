package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		}

	
	@FindBy(css="input#input-email") WebElement txtUsername;
	@FindBy(css="input#input-password") WebElement txtPassword;
	@FindBy(xpath="//input[@value='Login' and @class='btn btn-primary']") WebElement btnSubmit;
	
	
	public void enterUsername(String username) {
		txtUsername.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}
	public void clickOnSubmit()
	{
		btnSubmit.click();
	}
	
}
