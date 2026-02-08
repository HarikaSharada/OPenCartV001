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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
	  logger= LogManager.getLogger(this.getClass());
	  
	//enabling the user to set Browser and OS in run time using mvn commands 
		 String cmdBrowser  = System.getProperty("Browser");
		 String cmdOS = System.getProperty("OS");
		 
		 if(cmdBrowser!= null) {
			 browser= cmdBrowser;
		 }
		 
		 if(cmdOS != null) {
			 os = cmdOS;
		 }
		 
		 
		 
	  
	  properties_obj = new Properties();
	  FileReader propertiesFile=new FileReader(".//src/test/resources/config.properties");
	  properties_obj.load(propertiesFile);		  
	  String executionEnv=properties_obj.getProperty("execution_environment");
	  String applicationEnv = properties_obj.getProperty("app_env").toUpperCase();
	  String platformName=getPlatformName(os);
	  //Browser & OS	
	  
	  logger.info("Execution Environment: " + executionEnv);
	  logger.info("Application Environment: " + applicationEnv);
	  logger.info("Browser: " + browser);
	  logger.info("OS: " + os);
	 
	 
	 // Browser- remote
	  if(executionEnv.equalsIgnoreCase("remote"))
	  {		  
		  switch(browser.toLowerCase())
		  {
		  case "chrome": ChromeOptions chromeoptions = new ChromeOptions();
		  				 chromeoptions.setPlatformName(platformName);
	        			 driver = new RemoteWebDriver(new URL("http://localhost:4444"),chromeoptions);
	        			 break;
	        			 
		  case "edge":   EdgeOptions edgeoptions = new EdgeOptions();
		  			     edgeoptions.setPlatformName(platformName);
	        		     driver = new RemoteWebDriver(new URL("http://localhost:4444"),edgeoptions);
	        		     break;
			 
		  case "firefox": FirefoxOptions firefoxoptions = new FirefoxOptions();
		  				  firefoxoptions.setPlatformName(platformName);
		  				  driver = new RemoteWebDriver(new URL("http://localhost:4444"),firefoxoptions);
		  				  break;
	        			 
		  				  
		  default:
	            throw new IllegalArgumentException("Invalid browser for remote execution");
		  }		  
		  
	  }
	  
	  //Browser- local
	  
	  if(executionEnv.equalsIgnoreCase("local")) {
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
	  driver.get(properties_obj.getProperty(applicationEnv+"_url"));
	  //driver.manage().window().maximize();	
	  logger.info("Launching application URL for " + applicationEnv + " environment");
  }
  
  
  
      
  // setting OS
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