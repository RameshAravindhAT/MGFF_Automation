package pageObjects;

//Testing Purpose

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import projectSpecifications.BaseClass;
import utils.ExtentReportManager;
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

	@FindBy(xpath = "//button[@title='Import']//*[name()='svg']")
	public WebElement importjsonfile;

	@FindBy(xpath = "//span[text()='Preview']")
	public WebElement preview;

	@FindBy(xpath = "//div[@class='col-md-12 d-flex justify-content-end']//button[@type='submit'][normalize-space()='Save']")
	public WebElement save;

	@FindBy(xpath = "//a[@id='inactive-tab']")
	public WebElement inactive;

	@FindBy(xpath = "//tbody/tr[1]/td[4]/a[1]")
	public WebElement edit;

	@FindBy(xpath = "//input[@id='id_is_active']")
	public WebElement isactive;

	@FindBy(xpath = "//tbody/tr[3]/td[1]/a[1]")
	public WebElement doubleclickontemplate;
	
	@FindBy(xpath = "//span[normalize-space()='Application Batches']")
	public WebElement applicationbatch;
	
	
	
    @FindBy(css = "#applicationsSubmenu > .nav-item:nth-child(2) span")
    public WebElement batchLinkSubMenuButton;


	public PG_002_Template Click_on_Applications() {
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

	public PG_002_Template Click_on_Application_Templates(String operation,String templeName) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			
			applicationtemplate.click();
			Thread.sleep(5000);
			if(operation.equalsIgnoreCase("delete")) {
				
				
			List<WebElement> templateHeaders = TestContext.getDriver().findElements(By.xpath("(//table[@id='templateTable']//tr)[1]//following::tr/td[1]/a"));
		
			for (int i=0;i<=templateHeaders.size();i++)
			{
				if(templateHeaders.get(i).getText().equalsIgnoreCase(templeName))
				{
					WebElement recordOne=TestContext.getDriver().findElement(By.xpath("//table[@id='templateTable']//tbody/tr[1]/td[1]//a[text()='"+templeName+"']"));
					recordOne.findElement(By.xpath("//following::button[contains(@class,'deleteBtn')]")).click();
					String expectedToast = "This template is enrolled in a batch and can't be edited.";
					String warningToast = TestContext.getWait().until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-container']//p")))
							.getText();
					Assert.assertEquals(warningToast,"This template is enrolled in a batch and can't be edited.");
					ExtentReportManager.reportStep(methodName+"Deleting the template"+ "Expected Toast is "+ warningToast, "pass");
					ExtentReportManager.getNode("Click on Delete icon")
					.info("Verify the toast message"+ " "+ expectedToast);
					TestContext.getDriver().close();
					
				}
			}
				
			}
			else if(operation.equals("Create"))
			{
				addtemplate.click();
			}
			
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template Click_on_Add_Template_Button() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			Thread.sleep(2000);
			addtemplate.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);

		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template Enter_Template_Name_and_Description(String templateName, String desc) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			TestContext.getWait().until(ExpectedConditions.visibilityOf(templatename));
			Thread.sleep(3000);
			templatename.sendKeys(templateName);
			description.sendKeys(desc);

			ExtentReportManager.reportStep(methodName + " " + templateName, "pass");
			TestContext.getLogger().info(methodName + " " + templateName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName + " " + templateName);
			e.printStackTrace();
		}
		return this;
	}

	public PG_002_Template Click_JsonEditor_and_Upload_the_JsonFile() throws InterruptedException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			// Scroll down to make the element visible
			TestContext.getJsExecutor().scrollDownByPixels(350);
			TestContext.getWait().until(ExpectedConditions.visibilityOf(jsoncontent));
			jsoncontent.click(); // Click to focus on the json editor
			TestContext.getWait().until(ExpectedConditions.elementToBeClickable(jsontextarea));

			// Click the JSON textarea
			jsontextarea.click();
			jsontextarea.clear(); // Clear existing content
			Thread.sleep(3000); // Consider replacing this with an explicit wait

			importjsonfile.click();

			Thread.sleep(3000);

			Robot robot = new Robot();

			String filePath = "/home/sumo/Downloads/survey.json";
			StringSelection stringSelection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			// Step 6: Simulate pressing Enter to select the file
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(5000);
			preview.click();
			Thread.sleep(3000);

			// Save the result (if applicable)
			// save.click(); // Uncomment this if needed

			// Report step as pass
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);

		} catch (ElementClickInterceptedException e) {
			// Handle case where element cannot be clicked
			TestContext.getJsExecutor().clickElementUsingJS(jsoncontent);
		} catch (Exception e) {
			// Handle other exceptions
			e.printStackTrace();
			TestContext.getLogger().error(methodName);
		}

		return this;
	}

	/* public PG_002_Template Click_JsonEditor_and_Paste_the_JsconContent() throws InterruptedException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			TestContext.getJsExecutor().scrollDownByPixels(350);
			Thread.sleep(3000);
			System.out.println("Scrolling is happening");
			TestContext.getWait().until(ExpectedConditions.visibilityOf(jsoncontent)).click();
			TestContext.getWait().until(ExpectedConditions.elementToBeClickable(jsontextarea));
			jsontextarea.click();
			Thread.sleep(3000);
			jsontextarea.clear();
			Thread.sleep(3000);
			importjsonfile.click();
			Thread.sleep(5000);
			TestContext.getDriver().findElement(By.xpath("//button[@title='Import']//*[name()='svg']"))
					.sendKeys("/home/nd/Downloads/empty-03-1.jpg");
			String jsonpath = "/home/sumo/Downloads/survey.json";
			importjsonfile.sendKeys(jsonpath);
			Actions actions = new Actions(TestContext.getDriver());
			actions.sendKeys(Keys.ENTER).build().perform();
			StringSelection stringSelection = new StringSelection(jsonpath);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);

			Thread.sleep(5000);
			preview.click();
			Thread.sleep(3000);
			// save.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (ElementClickInterceptedException e) {
			TestContext.getJsExecutor().clickElementUsingJS(jsoncontent);
		} catch (Exception e)

		{
			e.printStackTrace();
			TestContext.getLogger().error(methodName);
		}

		return this;
	} */

	public PG_002_Template Save_Template() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			Thread.sleep(2000);
			save.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);

		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template Verifiy_toast_message(String scenario, String name, String role)
			throws InterruptedException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			if (scenario.equalsIgnoreCase("positive")) {
				String text = TestContext.getWait().until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-container']//p")))
						.getText();
				System.out.println(text);
				Assert.assertEquals(text, "Template added successfully");
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
				ExtentReportManager.reportStep(methodName, "pass");
				TestContext.getLogger().info(methodName);
			}
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}
		return this;
	}

	public PG_002_Template Verifiy_All_Template(String scenario) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			if (scenario.equalsIgnoreCase("positive")) {
				String text = TestContext.getWait().until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/main/div[2]/div[1]/h2")))
						.getText();
				System.out.println(text);
				Assert.assertEquals(text, "All Templates");
			}
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);

		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}
		return this;
	}

	public PG_002_Template Inactive_Tab() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			Thread.sleep(2000);
			inactive.click();
	         Thread.sleep(3000);
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);

		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template Edit_Template_and_Save() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
           TestContext.getWait().until(ExpectedConditions.elementToBeClickable(edit)).click();
	           TestContext.getWait().until(ExpectedConditions.elementToBeClickable(isactive)).click();
	           TestContext.getWait().until(ExpectedConditions.elementToBeClickable(save)).click();
     			String text = TestContext.getWait().until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-container']//p")))
					.getText();
			System.out.println(text);
			Assert.assertEquals(text, "Template updated successfully");
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);

		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template Double_click_on_Template() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try {
			TestContext.getWait().until(ExpectedConditions.elementToBeClickable(doubleclickontemplate));
			Actions actions = new Actions(TestContext.getDriver());
			actions.doubleClick(doubleclickontemplate).perform();
			String text = TestContext.getWait()
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='h4']"))).getText();
			Thread.sleep(3000);
			System.out.println(text);
			Assert.assertEquals(text, "Template Detail");
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);

		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}
	
	public PG_004_CreateNewBatch Click_on_application_Batch() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {

			applicationbatch.click();
			Thread.sleep(3000);
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}
		return new PG_004_CreateNewBatch(TestContext.getDriver());
	}
	

	
	    public PG_007_ApplicationBatch clickBatchLinkSubMenuButton() {
	        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
	        try {
	            batchLinkSubMenuButton.click();
	            ExtentReportManager.reportStep(methodName, "pass");
	            TestContext.getLogger().info(methodName);
	        } catch (Exception e) {
	            ExtentReportManager.reportStep(methodName, "fail");
	            TestContext.getLogger().error(methodName);
	            e.printStackTrace();
	        }
	        return new PG_007_ApplicationBatch(TestContext.getDriver());
	    }
	
	 public PG_006_ApplicationFormFilling scrollPageDown() {
	    	TestContext.getJsExecutor().scrollByPixels(0, 500);  // Scroll down 500px
	    	TestContext.getJsExecutor().scrollToBottom();
	        ExtentReportManager.reportStep("Scrolled the page down", "pass");
	        TestContext.getLogger().info("Scrolled the page down");
	        return new PG_006_ApplicationFormFilling(TestContext.getDriver());
	    }
	}
		
