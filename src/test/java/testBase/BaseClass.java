package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass
{
	public static WebDriver driver;
	public Logger logger; //Log4j
	public Properties prop;
	
	@BeforeClass(groups={"sanity", "regression", "master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String browser) throws IOException
	{
		//loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		
		prop = new Properties();
		prop.load(file);
		
		String appURL = prop.getProperty("appURL");
		
		logger = (Logger) LogManager.getLogger(this.getClass());
		
		switch(browser.toLowerCase())
		{
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			
			default: System.out.println("Invalid browser name..."); return;
		}
		
		driver.manage().deleteAllCookies();
		
		driver.get(appURL);
		
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"sanity", "regression", "master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		
		return generatedNumber;
	}
	
	public String randomAlphaNumeric()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		
		return (generatedString + "!" + generatedNumber);//added a special character(it can be any special character)
	}
	
	public String captureScreen(String testName) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir" + "\\screenshots\\" + testName + "_" + timeStamp + ".png");
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
				
	}
}
