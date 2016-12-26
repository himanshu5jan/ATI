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

public class LinkCheckDataProviderInArr {

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
	
	@DataProvider
	public Object[][] LoginData() {
		Object[][] retObj={
				{"Admin Role","/xxxxx/sxxxxn.do","pxxxxx@xxxx.com","xxxxx"},
				{"Shop Role","/xxxxx/sxxxxp.do","pxxxxxx@xxxx.com","xxxxx"},
				{"Vendor Role","/xxxxx/sxxxxxr.do","xxxxxx@xxxxx.com","xxxxx"},
				{"Accounts Role","/xxx/sxxxxxt.do","pxxxxx@xxxx.com","xxxxxx"}
		};
		return retObj;
	}
	
	public boolean activeLinks(WebDriver d) throws IOException {
		boolean passFlag=true;
    	List<WebElement> links=d.findElements(By.tagName("a"));
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
    		d.get(str);
    		if (d.getPageSource().contains(d.getTitle().split("|")[1]) && (connection.getResponseCode()==200)) {
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
    	System.out.println("SIZE full :"+links.size());
    	System.out.println("SIZE active:"+link_list.size());
    	//Assert.assertTrue(passFlag);
    	return passFlag;
}
	 
	
	@Test(dataProvider="LoginData")
	public void loginTest(String role, String link,String username, String password) throws IOException {
		boolean passflag=false;
		boolean linkflag=false;
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
    	System.out.println("************"+role+"***************");
    	System.out.println("Checking all links for : "+url);
    	linkflag=activeLinks(driver);
    	Assert.assertTrue(passflag && linkflag);
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
