package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties properties_obj;
  
@BeforeClass
  @Parameters({"OS","Browser"})
  public void setup(String os, String browser) throws IOException {
	  properties_obj = new Properties();
	  FileReader propertiesFile=new FileReader(".//src/test/resources/config.properties");
	  properties_obj.load(propertiesFile);
	  
	  logger= LogManager.getLogger(this.getClass());
	  
		  
	  String env=properties_obj.getProperty("execution_environment");
	  String platformName=getPlatformName(os);
	  //Browser & OS
	  if(env.equals("remote"))
	  {
		 if(browser.equalsIgnoreCase("chrome")) {
			 ChromeOptions options = new ChromeOptions();
		        options.setPlatformName(platformName);

		        driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
		 }
		 if(browser.equalsIgnoreCase("edge")) {
			 EdgeOptions options = new EdgeOptions();
		        options.setPlatformName(platformName);

		        driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
		 }
		 if(browser.equalsIgnoreCase("firefox")) {
			 FirefoxOptions options = new FirefoxOptions();
		        options.setPlatformName(platformName);

		        driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
		 }
	  }
	  
	  //Browser & OS - local
	  
	  if(properties_obj.getProperty("execution_environment").equals("local")) {
		  switch(browser.toLowerCase())
		  {
		  case "chrome": driver=new ChromeDriver(); break;
		  case "edge": driver=new EdgeDriver(); break;
		  case "firefox": driver=new FirefoxDriver(); break;
		  default: System.out.println("Invalid browser name"); return;
		  }	  
		  
	  }	  
	  
	  driver.manage().deleteAllCookies();	  
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	  
	  driver.get(properties_obj.getProperty("appURL"));
	  //driver.manage().window().maximize();	  
  }
  
  
  public String getPlatformName(String os) {
		switch(os.toLowerCase()) {
		case "windows":
          return "WINDOWS";
      case "mac":
          return "MAC";
      case "linux":
          return "LINUX";
      default:
          throw new IllegalArgumentException("Invalid OS value: " + os + ". Allowed values: windows, mac, linux");
		}
	  }
    
	  @AfterClass
	   public void tearDown() {
		  //driver.close();  
		  driver.quit();
	  }	  
	  
	  public String generateRandomString(int numOfDigits)
	  {
		 String randomString=RandomStringUtils.secure().nextAlphabetic(numOfDigits);				 
			return randomString;
	  }
   
	  public String generateRandomNumber(int numOfDigits) {
		  String randomNumber = RandomStringUtils.secure().nextNumeric(numOfDigits);
		  return randomNumber;
	  }
	  
	  public String generateRandomEmail(int numOfDigits) {
		  String randomNumber = RandomStringUtils.secure().nextNumeric(numOfDigits);
		  return randomNumber+"@gmail.com";
	  }
	  public String generateAlphanumeric(int numOfDigits) {
		  String randomAlphanumeric = RandomStringUtils.secure().nextAlphanumeric(numOfDigits);
		  return randomAlphanumeric;
	  }
	  
	  
	  //@Listeners
	  public String captureScreen(String result) throws IOException {
		  String timeStamp = new  SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  TakesScreenshot ts = (TakesScreenshot) driver;
		  File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		  String targetPath = System.getProperty("user.dir")
		            + "/screenshots/"
		            + result + "_" + timeStamp + ".png";
		  File targetFile = new File(targetPath);
		  FileUtils.copyFile(sourceFile, targetFile);
		  
		  return targetPath;
	  }
   
   
}