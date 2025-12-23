package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterAccountPage extends BasePage {
	
	

	public RegisterAccountPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txt_firstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txt_lastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txt_email;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txt_telephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txt_password;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txt_confirmPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement chk_agree;
	@FindBy(xpath="//input[@value='Continue']") WebElement btn_continue;
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']") WebElement successMsg;
	
	
	
	public void enterFirstName(String fName) {
		txt_firstName.sendKeys(fName);
	}
	
	public void enterLastName(String lName) {
		txt_lastName.sendKeys(lName);
	}
	
	public void enterEmail(String eMail) {
		txt_email.sendKeys(eMail);
	}
	
	public void enterTelephoneNumber(String teleNumber) {
		txt_telephone.sendKeys(teleNumber);
	}
	public void enterPassword(String pwd) {
		txt_password.sendKeys(pwd);
	}
	public void confirmPassword(String confirmPwd) {
		txt_confirmPassword.sendKeys(confirmPwd);
	}
	public void clickOnAgree() {
		chk_agree.click();
	}
	public void clickOnContinue() {
		btn_continue.click();
	}
	public String getConfirmationMsg() {
		try{
			return successMsg.getText();
			}	catch(Exception e)
				{
				  return e.getMessage();
				}
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	

