package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountLogoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;
import utilities.DataProviders;

public class TC003_DataDriverLoginTest extends BaseTest {
  @Test (dataProvider="LoginData", dataProviderClass=DataProviders.class)
  public void dataDrivenLoginTest(String username, String password, String expectedResult) {
	  HomePage homePage= new HomePage(driver);
	  homePage.clickOnMyAccount();
	  homePage.clickOnLogin();
	  
	  LoginPage loginPage= new LoginPage(driver);
	  loginPage.enterUsername(username);
	  loginPage.enterPassword(password);
	  loginPage.clickOnSubmit();

	  
	  MyAccountPage myAccountPage=new MyAccountPage(driver);
	  
	  Boolean loginStatus= myAccountPage.isLoginSuccessful();
	  if(expectedResult.equalsIgnoreCase("valid"))
	  {
		  Assert.assertTrue(loginStatus,"Login should be successful for Valid data");
		 
	  }
	  else
	  {
		  Assert.assertFalse(loginStatus,"Login should be unsuccessful for Invalid data");
		  
	  }
	  
	  if (loginStatus) {
		    myAccountPage.clickOnLogout();
		    AccountLogoutPage accountLogoutPage = new AccountLogoutPage(driver);
		    accountLogoutPage.clickOnContinue();
	 
	  
	  
  }
  }}
