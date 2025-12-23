package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountLogoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;

public class TC002_LoginTest extends BaseTest{
  @Test
  public void loginTest() {
	  HomePage homePage= new HomePage(driver);
	  homePage.clickOnMyAccount();
	  homePage.clickOnLogin();
	  
	  LoginPage loginPage= new LoginPage(driver);
	  loginPage.enterUsername(properties_obj.getProperty("username"));
	  loginPage.enterPassword(properties_obj.getProperty("password"));
	  loginPage.clickOnSubmit();

	  
	  MyAccountPage myAccountPage=new MyAccountPage(driver);
	  if(myAccountPage.isLoginSuccessful()) {
		  Assert.assertTrue(true);
		  myAccountPage.clickOnLogout();
		  
		  AccountLogoutPage accountLogoutPage = new AccountLogoutPage(driver);
		  accountLogoutPage.clickOnContinue();
	  }
	  else {
		  Assert.fail();
	  }
		  
	 
	  
  }
}
