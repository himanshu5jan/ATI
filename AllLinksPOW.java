package programsCollection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AllLinksPOW {

	public static boolean notSkipped(String str) {
		boolean bool=true;
		
		if (str==null || !str.startsWith("http") || str.endsWith("#") || str.contains("?status") || str.contains("logout")) {
				bool=false;
		}
		
		return bool;
	}
	
	public static void main(String[] args) throws IOException {
		 WebDriver driver;
	        System.setProperty("webdriver.chrome.driver", "c:\\BDrivers\\chromedriver.exe"); 
	    	driver=new ChromeDriver();
	    	driver.get("http://----/POWNew/startadmin.do");
	    	driver.findElement(By.id("email")).sendKeys("powxxxx@yxxxxx.com");
	    	driver.findElement(By.id("password")).sendKeys("powadmin");
	    	driver.findElement(By.id("loginbutton")).click();
	    	if(driver.getTitle().contains("Dashboard")) {
	    		System.out.println("Login Successful !!");
	    	} else {
	    		System.out.println("Login Failed !!");
	    	}
	    	
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
	    			System.out.println("Link is active : "+str);
	    		} else {
	    			System.out.println("Link is not active : "+str);
	    		}
	    		//int respcode=connection.getResponseCode();
	    		connection.disconnect();
	    		//System.out.println("RESPONSE MSGG: "+resp);
	    		//System.out.println("RESPONSE CODE: "+respcode);
	    		
	    		
	    	}
	    	System.out.println("SIZE full :"+links.size());
	    	System.out.println("SIZE active:"+link_list.size());
	    	driver.close();
	    	

	}

}
