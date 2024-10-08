package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
	@Test(groups={"regression", "master"})
	public void verify_account_registration() throws InterruptedException
	{
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		
		try
		{
			HomePage homePage = new HomePage(driver);
			
			homePage.clickMyAccount();
			logger.info("Clicked on MyAccount Link");
			homePage.clickRegister();
			logger.info("Clicked on Register Link");
			
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			
			logger.info("Providing customer details");
			regPage.setFirstName(randomString().toUpperCase());
			regPage.setLastName(randomString().toUpperCase());
			regPage.setEmail(randomString() + "@gmail.com");
			regPage.setTelephone(randomNumber());
			
			String password = randomAlphaNumeric();
			
			regPage.setPassword(password);
			regPage.setConfirmPassword(password);
			
			regPage.setPrivacyPolicy();
			regPage.clickContinue();
			
			Thread.sleep(1000);
			
			logger.info("Validating expected message");
			
			String confMsg = regPage.getConfirmationMsg();
			
			Assert.assertEquals("Your Account Has Been Created!", confMsg);
		}
		catch(Exception e)
		{
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
	}
	
	
}
