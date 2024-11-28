package pageObjects;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class CreateNewBatchTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
 @BeforeMethod
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void createNewBatch() {
    driver.get("https://staging.msmp.navadhiti.com/mgff/login/");
    driver.manage().window().setSize(new Dimension(1310, 741));
    driver.findElement(By.name("login")).click();
    driver.findElement(By.cssSelector(".sidebar-sticky > .nav > .nav-item:nth-child(3) > .nav-link > .toggle-button > .fas")).click();
    driver.findElement(By.cssSelector(".nav-item:nth-child(1) .toggle-button > .fas")).click();
    driver.findElement(By.cssSelector("#applicationsSubmenu > .nav-item:nth-child(2) span")).click();
    driver.findElement(By.cssSelector(".btn-toolbar > .btn")).click();
    driver.findElement(By.id("id_name")).click();
    driver.findElement(By.id("id_name")).sendKeys("Bactch Recordin One");
    {
      WebElement dropdown = driver.findElement(By.id("id_template_id"));
      dropdown.findElement(By.xpath("//option[. = 'New Template']")).click();
    }
    {
      WebElement element = driver.findElement(By.id("id_template_id"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.id("id_template_id"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    {
      WebElement dropdown = driver.findElement(By.id("id_interview_masters"));
      dropdown.findElement(By.xpath("//option[. = 'Jeevitha Murthy - Child Education expert']")).click();
    }
    driver.findElement(By.id("id_description")).click();
    driver.findElement(By.id("id_description")).sendKeys("Recording Test Batch");
    {
      WebElement dropdown = driver.findElement(By.id("id_third_party_verifiers"));
      dropdown.findElement(By.xpath("//option[. = 'Saanvi']")).click();
    }
    driver.findElement(By.id("id_start_date")).click();
    driver.findElement(By.id("id_start_date")).sendKeys("2024-11-27");
    driver.findElement(By.id("id_end_date")).click();
    driver.findElement(By.id("id_end_date")).sendKeys("2024-11-30");
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.close();
  }
}
