package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ExtentReportManager;
import utils.TestContext;

public class PG_003_Logout {
	public PG_003_Logout(WebDriver driver) {
		// Initialize the driver and page elements
		TestContext.setDriver(driver);
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//i[@class='fas fa-user-circle']")
	public WebElement profile;	

	@FindBy(xpath = "//div[@class='h5 font-weight-normal mb-2']")
	public WebElement homepage;
	
	@FindBy(xpath = "//a[@class='dropdown-item font-weight-bold']")
	public WebElement logout;
	
	
	public PG_003_Logout Click_Profile() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try {
		
				profile.click();
				ExtentReportManager.reportStep(methodName, "pass");
				TestContext.getLogger().info(methodName); //for debugging purpose 
			}
			
		catch (Exception e) {
			TestContext.getLogger().error(methodName);

			 e.printStackTrace();
		}
		return this;
	
	}


	
	public PG_003_Logout Click_Logout() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			logout.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}
		return this;
	}

	
	public PG_003_Logout Verify_the_Logout_URL()
	{
	String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
	{
		try {
			
				String currentUrl = TestContext.getDriver().getCurrentUrl();
				Assert.assertEquals(currentUrl, "https://staging.msmp.navadhiti.com/mgff/login/");
			
			ExtentReportManager.reportStep(methodName , "pass");
			TestContext.getLogger().info(methodName); //for debugging purpose 
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			// e.printStackTrace();
		}
		return this;
	}
	}
}
