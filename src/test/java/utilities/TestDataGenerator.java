package utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class TestDataGenerator {
	
	 
	  
	  public static String generateRandomString(int numOfDigits)
	  {
		 String randomString=RandomStringUtils.secure().nextAlphabetic(numOfDigits);				 
			return randomString;
	  }

	  public static String generateRandomNumber(int numOfDigits) {
		  String randomNumber = RandomStringUtils.secure().nextNumeric(numOfDigits);
		  return randomNumber;
	  }
	  
	  public static String generateRandomEmail(int numOfDigits) {
		  String randomNumber = RandomStringUtils.secure().nextNumeric(numOfDigits);
		  return randomNumber+"@gmail.com";
	  }
	  public static String generateAlphanumeric(int numOfDigits) {
		  String randomAlphanumeric = RandomStringUtils.secure().nextAlphanumeric(numOfDigits);
		  return randomAlphanumeric;
	  }

}
