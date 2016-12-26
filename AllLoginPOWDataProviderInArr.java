package programsCollection;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AllLoginPOWDataProviderInArr {

	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/BDrivers/chromedriver.exe"); 
	    driver=new ChromeDriver();
	}
	
	@DataProvider
	public Object[][] LoginData() {
		//Object[][] retObj=null;
		
		//retObj=new Object[2][3];
		
		Object[][] retObj={
				{"Admin Role","/xxxxxx/startadmin.do","poxxx@xxxx.com","pxxxx"},
				{"Shop Role","/xxxxx/startshop.do","poxxx@xxxxx.com","xxxxx"},
				{"Vendor Role","/xxxx/startvendor.do","danxxxx@xxxxx.com","xxxx"},
				{"Accounts Role","/xxxxx/startaccount.do","pxxxx@xxxxx.com","xxxx"}
		};
		return retObj;
	}

	@Test(dataProvider="LoginData")
	public void loginTest(String role, String link,String username, String password) {
		boolean passflag=false;
		String url="http://000.000.000.000:8080"+link;
		driver.get(url);
	    driver.findElement(By.id("email")).sendKeys(username);
    	driver.findElement(By.id("password")).sendKeys(password);
    	driver.findElement(By.id("loginbutton")).click();
    	System.out.println("Testing Logging for role : "+role);
    	if(driver.getTitle().contains("Dashboard")) {
    		System.out.println("Login Successful !!");
    		passflag=true;
    	} else {
    		System.out.println("Login Failed !!");
    		passflag=false;
    	}
    	Assert.assertTrue(passflag);
	}
	
	
	@AfterMethod
	public void afterTest(ITestResult result) {
		//get test result
		boolean resultValue=result.isSuccess();
		//get testmethodname
		String testMethodName=result.getName();
		if(result.getStatus() == ITestResult.FAILURE) {
			//if successful 
			System.out.println("Test Case Result : "+resultValue);
			System.out.println("Test Case Method Name : "+testMethodName);
		
		} else {
			//if failed
			System.out.println("Test Case Result : "+resultValue);
			System.out.println("Test Case Method Name : "+testMethodName);
		}
	}
	
	@AfterClass
	public void teardown() {
		driver.close();
	}

}
