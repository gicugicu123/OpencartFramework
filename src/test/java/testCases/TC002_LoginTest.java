package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups={"sanity", "master"})
	public void verify_login() throws InterruptedException
	{
		logger.info("***** Starting TC002_LoginTest *****");
		
		try
		{ 
			String email = prop.getProperty("email");
			String password = prop.getProperty("password");
			
			HomePage homePage = new HomePage(driver);
			
			homePage.clickMyAccount();
			logger.info("Clicked on MyAccount Link");
			homePage.clickLogin();
			logger.info("Clicked on Login Link");
			
			LoginPage loginPage = new LoginPage(driver);
			
			logger.info("Providing user credentials");
			loginPage.setEmail(email);
			loginPage.setPassword(password);
			
			loginPage.clickLogin();
			logger.info("Clicked on Login Button");
			
			Thread.sleep(1000);
			
			logger.info("Validating expected message");
			
			MyAccountPage myAccountPage = new MyAccountPage(driver);
			
			boolean isMsgDisplayed = myAccountPage.isMyAccountPageExists();
			
//			Assert.assertEquals(isMsgDisplayed, true, "Login failed");
			Assert.assertTrue(isMsgDisplayed);
		}
		catch(Exception e)
		{
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info("***** Finished TC002_LoginTest *****");
		
	}
	
	
}
