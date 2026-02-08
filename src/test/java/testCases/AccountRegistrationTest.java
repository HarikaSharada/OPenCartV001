package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterAccountPage;
import testBase.BaseTest;
import utilities.TestDataGenerator;


public class AccountRegistrationTest extends BaseTest {

   HomePage HomePageObj;
   RegisterAccountPage RegisterAccountObj;
   

	@Test
	  public void Register() {
		
		logger.info(" ------ Started executing TC001_AccountRegistrationTest ------" );
		HomePageObj = new HomePage(driver);
		
		HomePageObj.clickOnMyAccount();
		logger.info("Clicked on My Account" );
		HomePageObj.clickOnRegister();
		logger.info("Clicked on Register" );
		
		logger.debug("Adding customer information");
		
		RegisterAccountObj = new RegisterAccountPage(driver);
		RegisterAccountObj.enterFirstName(TestDataGenerator.generateRandomString(6));
		RegisterAccountObj.enterLastName(TestDataGenerator.generateRandomString(6));
		RegisterAccountObj.enterEmail(TestDataGenerator.generateRandomEmail(5));
		RegisterAccountObj.enterTelephoneNumber(TestDataGenerator.generateRandomNumber(10));
		String password=TestDataGenerator.generateRandomString(8);
		RegisterAccountObj.enterPassword(password);
		RegisterAccountObj.confirmPassword(password);
		RegisterAccountObj.clickOnAgree();
		RegisterAccountObj.clickOnContinue();
		logger.info("Clicked on continue" );
		logger.debug("Verifying confirmation message");
		String confirmationMessage = RegisterAccountObj.getConfirmationMsg();
		  Assert.assertEquals("Your Account Has Been Created!", confirmationMessage);
		  
		  logger.info("----- Completed test-----" );
	  }
}

