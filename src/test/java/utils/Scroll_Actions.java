package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scroll_Actions extends TestContext {

    // Assuming you have a WebDriver instance
    WebDriver driver = TestContext.getDriver();  // Assuming TestContext.getDriver() gives you the WebDriver instance

    // This method will scroll the page to the bottom
    public void scrollToBottom() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // This method will scroll the page to the top
    public void scrollToTop() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }

    // This method will scroll the page to a specific element
    public void scrollToElement(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // This method will scroll the page by a certain number of pixels (e.g., 500px)
    public void scrollByPixels(int x, int y) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }
}
