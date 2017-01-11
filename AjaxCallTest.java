package programsCollection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AjaxCallTest {

	@Test
	public void AjaxTest() {
		WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "c:\\BDrivers\\chromedriver.exe"); 
    	driver=new ChromeDriver();
    	driver.get("http://www.w3schools.com/xml/ajax_intro.asp");
    	
    	//Locate the Text and print it visible before the AJAX Call
    	WebElement we = driver.findElement(By.xpath("//div[@id='demo']/h2"));
    	String we_text=we.getText();
    	System.out.println("Text Before AJAX Call :: "+we_text);
    	
    	//Click the button to initiate the ajax call
    	driver.findElement(By.xpath("//button[contains(text(),'Change Content')]")).click();
    	
    	/*
    	 * Using Explicit Wait here. 
    	 * Explicit wait is used to tell the Web Driver to wait for certain condition.
    	 * Here the element driver.findElement(By.xpath("//div[@id='demo']/h1")) might take a while to get visible 
    	 * before we could work on on and using wait with visibilityOf helps to not get exception
    	 */
    	//wait object created here defines the max time in seconds it should wait for before timeout
    	WebDriverWait wait = new WebDriverWait(driver, 15);
    	WebElement we2 = driver.findElement(By.xpath("//div[@id='demo']/h1"));
    	
    	//as soon as the element we2 is visible control moves to the next line
    	wait.until(ExpectedConditions.visibilityOf(we2));
    	String we2_text=we2.getText();
    	
    	//printing the updated text after ajax call
    	System.out.println("Text After AJAX Call :: "+we2_text);
    	
	}
	
}
