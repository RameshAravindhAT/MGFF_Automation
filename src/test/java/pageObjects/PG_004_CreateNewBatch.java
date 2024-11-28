package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import projectSpecifications.BaseClass;
import utils.ExtentReportManager;
import utils.TestContext;

public class PG_004_CreateNewBatch extends BaseClass {
	
	public PG_004_CreateNewBatch(WebDriver driver) {
		// Initialize the driver and page elements
		TestContext.setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//button[normalize-space()='Add New Batch']")
	public WebElement addnewbatch;
	
	@FindBy(xpath = "//select[@id='id_interview_masters'][1]")
	public WebElement selectinterviewer;
	
	@FindBy(xpath = "//select[@id='id_third_party_verifiers'][1]")
	public WebElement selectTPV;

	@FindBy(id = "//input[@id='id_name']")
	public WebElement enterbatchname;
	
	@FindBy(id = "//textarea[@id='id_description']")
    public WebElement batchdescription;

	@FindBy(id = "//select[@id='id_template_id']")
    public WebElement selecttemplate;
	
	@FindBy(id = "//input[@id='id_start_date']")
    public WebElement selectstartdate;
			  
	@FindBy(id = "//input[@id='id_end_date']")
    public WebElement selectenddate;
	
	@FindBy(id = "//button[@type='submit']")
    public WebElement save;


	public PG_004_CreateNewBatch Click_on_Add_new_Batch() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try {
	        Thread.sleep(3000);
			addnewbatch.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}
	public PG_004_CreateNewBatch Select_Start_and_End_Date(String startDate, String endDate){
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		
		       

		try {
			TestContext.getWait().until(ExpectedConditions.visibilityOf(selectstartdate));
			Thread.sleep(3000);
			selectstartdate.click();
			selectstartdate.sendKeys(startDate);
			Thread.sleep(3000);
			selectenddate.click();
			selectenddate.sendKeys(endDate);

			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	
	public PG_004_CreateNewBatch Enter_Batch_Name_and_Description(String batchName, String desc) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
            WebElement dropdownElement = TestContext.getDriver().findElement(By.id("id_template_id"));
            Select dropdown = new Select(dropdownElement);
            dropdown.selectByVisibleText("New Template");

			selectinterviewer.click();
			selectTPV.click();
			TestContext.getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("id_name")));
			Thread.sleep(3000);
			enterbatchname.click();
			enterbatchname.sendKeys(batchName);
			Thread.sleep(3000);
			batchdescription.click();
			batchdescription.sendKeys(desc);
			

			ExtentReportManager.reportStep(methodName + " " + batchName, "pass");
			TestContext.getLogger().info(methodName + " " + batchName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName + " " + batchName);
			e.printStackTrace();
		}
		return this;
	}
	public PG_004_CreateNewBatch Save_Batch() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			TestContext.getJsExecutor().scrollDownByPixels(450);
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
	public PG_004_CreateNewBatch Verifiy_toast_message(String scenario, String name, String role)
			throws InterruptedException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			if (scenario.equalsIgnoreCase("positive")) {
				String text = TestContext.getWait().until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-container']//p")))
						.getText();
				System.out.println(text);
				Assert.assertEquals(text, "Batch added successfully");
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

}


