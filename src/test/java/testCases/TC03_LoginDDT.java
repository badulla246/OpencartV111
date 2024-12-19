package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC03_LoginDDT extends BaseClass
{
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")
	public void verify_login(String username, String password, String result)
	{
		logger.info("****** Initiated the TC02_LoginTest ******");
		try
		{
			//HomePage
			HomePage hp = new HomePage(driver);
			logger.info("Clicked on the My Account Link.");
			hp.clickMyAccount();
			logger.info("Clicked on the Login Link...");
			hp.clickLogin();
			
			//LoginPage
			LoginPage lp = new LoginPage(driver);
			logger.info("Providing the Users' Email");
			lp.setUsername(username);
			logger.info("Providing the Users' Password");
			lp.setPassword(password);
			logger.info("Clicked on the login button.");
			lp.clickLogin();
			
			//MyAccountPage
			MyAccountPage maccpage = new MyAccountPage(driver);
			boolean targetPage = maccpage.isMyAccountPageExists();
			
			if (result.equalsIgnoreCase("Valid"))
			{
				if (targetPage == true)
				{
					maccpage.clickLogout();
					Assert.assertTrue(true);
					logger.info("My account page has successfully launched.");
				}
				else
				{
					Assert.assertTrue(false);
					logger.info("My account page has unsuccessfull.");
				}
			}
			
			if (result.equalsIgnoreCase("Invalid"))
			{
				if (targetPage == true)
				{
					maccpage.clickLogout();
					Assert.assertTrue(false);
					logger.info("My account page has successfully launched.");
				}
				else
				{
					Assert.assertTrue(true);
					logger.info("My account page has unsuccessfull.");
				}
			}
			
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****** Finished the TC02_LoginTest ******");
	}
}
