package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass
{
	@Test(groups={"Regression","Master"})
	public void verify_login()
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
			lp.setUsername(p.getProperty("username"));
			logger.info("Providing the Users' Password");
			lp.setPassword(p.getProperty("password"));
			logger.info("Clicked on the login button.");
			lp.clickLogin();
			
			//MyAccountPage
			MyAccountPage maccpage = new MyAccountPage(driver);
			boolean targetPage = maccpage.isMyAccountPageExists();
			Assert.assertTrue(targetPage);
			
			logger.info("My account page has successfully launched.");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****** Finished the TC02_LoginTest ******");
	}
}
