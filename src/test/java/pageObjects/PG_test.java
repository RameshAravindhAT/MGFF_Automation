package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import projectSpecifications.BaseClass;
import utils.ExtentReportManager;
import utils.TestContext;

public class PG_test extends BaseClass{
	public PG_test(WebDriver driver) {
		// Initialize the driver and page elements
		TestContext.setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "id_email")
	public WebElement username;

	@FindBy(id = "id_password")
	public WebElement password;
	
	@FindBy(xpath = "//button[@name='login']")
	public WebElement loginbutton;
	
	public PG_test Enter_User_Name1(String userName) {
		ExtentReportManager.reportStep("Opening the Browser", "pass");
		ExtentReportManager.reportStep("Navigating to the URL"+properties.getProperty("url"), "pass");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			username.sendKeys(userName);
			ExtentReportManager.reportStep(methodName + " " + userName, "pass");
			TestContext.getLogger().info(methodName + " " + userName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName + " " + userName);
			e.printStackTrace();
		}
		return this;
	}
	
	public PG_test Enter_Password1(String passwordvales) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			password.sendKeys(passwordvales);
			ExtentReportManager.reportStep(methodName + " " + passwordvales, "pass");
			TestContext.getLogger().info(methodName + " " + passwordvales);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName + " " + passwordvales);

			e.printStackTrace();
		}
		return this;
	}
	public PG_test Click_Login_Button1() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			loginbutton.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}
		return this;
	}
	public PG_test Verifiy_toast_message1(String scenario, String name, String role) throws InterruptedException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			if (scenario.equalsIgnoreCase("positive")) {

				String text = TestContext.getWait()
						.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//div[@class='h5 font-weight-normal mb-2']")))
						.getText();
				Thread.sleep(3000);
				System.out.println(text);
				Assert.assertEquals(text, "Hi " + name + " ! Welcome To MGFF " + role + " Dashboard");
				/*
				 * } else if (scenario.equalsIgnoreCase("negative")) { String text =
				 * TestContext.getWait() .until(ExpectedConditions
				 * .visibilityOfElementLocated(By.
				 * xpath("//div[@class= 'toast-container']//div/p"))) .getText();
				 * System.out.println(text); Assert.assertEquals(text,
				 * "Invalid email or password.");
				 */
			} else if (scenario.equalsIgnoreCase("negative")) {
				WebElement toastMessage = TestContext.getWait().until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Please fill in this field.')]")));

				// Retrieve the text of the toast message
				String messageText = toastMessage.getText();

				// Verify the message if necessary
				if (messageText.equals("Please fill in this field.")) {
					System.out.println("Toast message displayed correctly: " + messageText);
				} else {
					System.out.println("Unexpected toast message: " + messageText);
				}

			}
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}
		return this;
	}

}
