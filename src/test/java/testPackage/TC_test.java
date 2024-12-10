package testPackage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;
import utils.ExtentReportManager;
import utils.TestContext;

public class TC_test extends BaseClass {

	@BeforeClass
	public void testcasedetails() {

		TestContext.setSheetName("test");

	}

	@Test(dataProvider = "sendData")
	public void validate_login(String testNameDetails, String authorName, String category, String userName,
			String password)
			throws InterruptedException {
		// Initialize the test in Extent Reports using TestContext
		ExtentReportManager.setTest(extent.createTest(testNameDetails)); // Create the test instance in Extent Reports
		ExtentReportManager.getTest().assignAuthor(authorName); // Assign the author for the test
		ExtentReportManager.getTest().assignCategory(category); // Assign the category for the test

		// Perform the login action using the login page object
		TestContext.getLoginPage()
		                        .Enter_User_Name(userName)
		                        .Enter_Password(password)
		                        .Click_Login_Button()
		                        .Verifiy_toast_message(category, userName, password);
		                     

}
	}