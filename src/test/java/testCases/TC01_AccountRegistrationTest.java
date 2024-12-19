package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC01_AccountRegistrationTest extends BaseClass
{
	
	@Test(groups={"Sanity","Master"})
	public void verify_account_registration()
	{
		logger.info("******** Stated TC01_AccountRegistrationTest ********");
		try
		{
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link...");
			hp.clickRegister();
			logger.info("Clicked on Register Link...");
			
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			logger.info("Providing Customer Details...");
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString()+"@gmail.com");
			regpage.setTelephone(randomeNumber());
			
			String password = randomePassword();
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			
			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			logger.info("Validating Expected Message");
			String confirmsg = regpage.getConfirmationMsg();
			if (confirmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				logger.info("Test Failed....");
				logger.debug("Debug logs...");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("\"******** Finished TC01_AccountRegistrationTest ********\"");
	}
		
}
