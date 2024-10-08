package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 
 Data is valid - login success - test passed - logout
 Data is valid - login failed - test failed
 
 Data is invalid - login success - test failed - logout
 Data is invalid - login failed - test passed
 
 */

public class TC003_LoginDataDriverTest extends BaseClass
{
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="dataDriven")//getting DP from different class
	public void verify_loginDDT(String email, String password, String expectedResult) throws InterruptedException
	{
		logger.info("***** Starting TC003_LoginDataDriverTest *****");
		
		try
		{ 
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
			
			if(expectedResult.equalsIgnoreCase("Valid"))
			{
				if(isMsgDisplayed == true)
				{
					myAccountPage.clickLogout();
					
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(expectedResult.equalsIgnoreCase("Invalid"))
			{
				if(isMsgDisplayed == true)
				{
					myAccountPage.clickLogout();
					
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
			
			
//			Assert.assertEquals(isMsgDisplayed, true, "Login failed");
			Assert.assertTrue(isMsgDisplayed);
		}
		catch(Exception e)
		{
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info("***** Finished TC003_LoginDataDriverTest *****");
		
	}
	
	
}
