package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver){
		super(driver);
	}

	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement btnLogout;
	public void clickOnLogout() {
		btnLogout.click(); 
	}
	
	public Boolean isLoginSuccessful()
	{
		try
		{
			return (driver.getTitle().equalsIgnoreCase("My Account"));
			
		} catch(Exception e)
		{
			return false;
		}
	}
	
	
	
	
	
	
}
