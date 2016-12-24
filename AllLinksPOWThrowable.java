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
import org.testng.annotations.Test;

public class AllLinksPOWThrowable {

	WebDriver driver;
	
	public static boolean notSkipped(String str) {
		boolean bool=true;
		if (str==null || !str.startsWith("http") || str.endsWith("#") || str.contains("?status") || str.contains("logout")) {
				bool=false;
		}
		return bool;
	}
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "c:\\BDrivers\\chromedriver.exe"); 
	    driver=new ChromeDriver();
	}
	
	
	@Test(testName="loginTest", priority=1)
	public void loginTest() {
		boolean passflag=false;
	    driver.get("http://104.238.103.200:8080/POWNew/startadmin.do");
	    driver.findElement(By.id("email")).sendKeys("pow_a32@yahoo.com");
    	driver.findElement(By.id("password")).sendKeys("powadmin");
    	driver.findElement(By.id("loginbutton")).click();
    	if(driver.getTitle().contains("Dashboard")) {
    		System.out.println("Login Successful !!");
    		passflag=true;
    	} else {
    		System.out.println("Login Failed !!");
    		passflag=false;
    	}
    	Assert.assertTrue(passflag);
	}
	
	@Test(testName="adminLinksTest",dependsOnMethods={"loginTest"})
	public void activeLinks() throws IOException {
		boolean passFlag=true;
	    	List<WebElement> links=driver.findElements(By.tagName("a"));
	    	List<String> link_list=new ArrayList<String>();
	    	for(WebElement we:links)
	        {
	            String href = we.getAttribute("href");
            	//System.out.println(href);
            	if(notSkipped(href)) {
            		link_list.add(href);
            	}
            }
	    	
	    	System.out.println("************************************");
	    	for(String str: link_list) {
	    		System.out.println("Pinging ...."+str);
	    		URL linkObj=new URL(str);
	    		HttpURLConnection connection = (HttpURLConnection) linkObj.openConnection();
	    		connection.connect();
	    		//String resp=connection.getResponseMessage();
	    		driver.get(str);
	    		if (driver.getPageSource().contains(driver.getTitle().split("|")[1]) && (connection.getResponseCode()==200)) {
	    //			System.out.println("Link is active : "+str);
	    			
	    		} else {
	    //			System.out.println("Link is not active : "+str);
	    			passFlag=false;
	    		}
	    		//int respcode=connection.getResponseCode();
	    		connection.disconnect();
	    		//System.out.println("RESPONSE MSGG: "+resp);
	    		//System.out.println("RESPONSE CODE: "+respcode);
	    		
	    		
	    	}
	    	//System.out.println("SIZE full :"+links.size());
	    	//System.out.println("SIZE active:"+link_list.size());
	    	Assert.assertTrue(passFlag);
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
			String thwomsg=result.getThrowable().getMessage();
			System.out.println("ERROR MESSAGE : "+thwomsg);
			StringWriter sw=new StringWriter();
			result.getThrowable().printStackTrace(new PrintWriter(sw));
			String strace=sw.toString();
			System.out.println("FULL TRACE: "+strace);
		}
	}
	
	@AfterClass
	public void teardown() {
		driver.close();
	}

}
