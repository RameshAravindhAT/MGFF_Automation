package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import projectSpecifications.BaseClass;
import utils.ExtentReportManager;
import utils.Json_content;
import utils.TestContext;

public class PG_002_Template extends BaseClass {

	public PG_002_Template(WebDriver driver) {
		// Initialize the driver and page elements
		TestContext.setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@onclick=\"toggleSubmenu('applicationsSubmenu')\"]//span[@class='fas fa-chevron-down']")
	public WebElement application;

	@FindBy(xpath = "//span[normalize-space()='Application Templates']")
	public WebElement applicationtemplate;

	@FindBy(xpath = "//button[normalize-space()='Add New Template']")
	public WebElement addtemplate;

	@FindBy(id = "id_name")
	public WebElement templatename;

	@FindBy(xpath = "//textarea[@id='id_description']")
	public WebElement description;

	@FindBy(xpath = "//span[contains(text(),'JSON Editor')]")
	public WebElement jsoncontent;
	
	@FindBy(xpath = "//textarea[@aria-label='JSON Editor']")
	public WebElement jsontextarea;

	public PG_002_Template Open_Application() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			application.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template Open_Application_Template() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			applicationtemplate.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template Add_Template() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			addtemplate.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);

		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template Enter_Template_Name(String templateName) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			TestContext.getWait().until(ExpectedConditions.visibilityOf(templatename));
			Thread.sleep(3000);
			templatename.sendKeys(templateName);
			ExtentReportManager.reportStep(methodName + " " + templateName, "pass");
			TestContext.getLogger().info(methodName + " " + templateName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName + " " + templateName);
			e.printStackTrace();
		}
		return this;
	}

	public PG_002_Template Enter_Description(String desc) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			description.sendKeys(desc);
			ExtentReportManager.reportStep(methodName + " " + desc, "pass");
			TestContext.getLogger().info(methodName + " " + desc);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName + " " + desc);
			e.printStackTrace();
		}
		return this;
	}
	public PG_002_Template Click_JsonEditor() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			jsoncontent.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}
	public PG_002_Template Paste_Jsoncontent() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			TestContext.getJsExecutor();
			jsontextarea.click();
			jsontextarea.clear();
			 Json_content jsoncontent = new Json_content(TestContext.getDriver());
            Object[] json = jsoncontent.scholarshipData();
            String dataToSend = ""; // Initialize an empty string to hold the data

            for (Object obj : json) {

                dataToSend += obj.toString() + " "; // Concatenate each item (separated by a space or custom separator)
            }
            jsontextarea.sendKeys(dataToSend);

			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}
}
