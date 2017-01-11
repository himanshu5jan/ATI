package programsCollection;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class ButtonControlsTest { 
	
	@Test
	public void signUpTest() throws InterruptedException {
		WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "c:\\BDrivers\\chromedriver.exe"); 
    	driver=new ChromeDriver();
    	driver.get("https://www.facebook.com/");
    	driver.findElement(By.id("u_0_1")).sendKeys("Sam");
    	driver.findElement(By.id("u_0_3")).sendKeys("Sung");
    	driver.findElement(By.id("u_0_5")).sendKeys("042022022");
    	driver.findElement(By.id("u_0_8")).sendKeys("042022023");
    	driver.findElement(By.id("u_0_a")).sendKeys("abc1234");
    //Locating dropdown code 0 start	
    	//Locate the dropdown web element
    	WebElement WEDay=driver.findElement(By.id("day"));
    	
    	/*Use Select Class and create an object which will provide methods to select from the dropdown
    	 *Pass the day dropdown WebElement found above to Select class constructor 
    	*/
    	Select sday=new Select(WEDay);
    	
    	//Select the third value based on index where first value has index 0
    	sday.selectByIndex(3);
    	
    	
    	/* Select value based on value attribute
    	 * <select id="month" class="_5dba" aria-label="Month" name="birthday_month" title="Month">
				<option value="0" selected="1">Month</option>
				<option value="1">Jan</option>
				<option value="2">Feb</option>
				<option value="3">Mar</option>
				<option value="4">Apr</option>
				<option value="5">May</option>
				<option value="6">Jun</option>
				<option value="7">Jul</option>
				<option value="8">Aug</option>
				<option value="9">Sept</option>
				<option value="10">Oct</option>
				<option value="11">Nov</option>
				<option value="12">Dec</option>    	
    	 */
    	WebElement WEMon=driver.findElement(By.id("month"));
    	Select smon=new Select(WEMon);
    	smon.selectByValue("8");
    	
    	/* Select the code by visible text on html
    	 * <select id="year" class="_5dba" aria-label="Year" name="birthday_year" title="Year">
			<option value="0" selected="1">Year</option>
			<option value="2017">2017</option>
			<option value="2016">2016</option>
			<option value="2015">2015</option>
			<option value="2014">2014</option>
			<option value="2013">2013</option>
			<option value="2012">2012</option>
			<option value="2011">2011</option>
    	 */
    	WebElement WEYyy=driver.findElement(By.id("year"));
    	Select syr=new Select(WEYyy);
    	syr.selectByVisibleText("2017");
    //-------------------------------Locating dropdown code 0 end
    	
    //Locating Radio buttons start
    //input button type radio both have same name "sex" and selecting one will deselect other
    //findElements(By.name("sex")) will return a List<WebElement> and 'Female' will be index 0 and 'Male' will be index 1
    //Use get(index) method of List to find the Male radio button and click()
    	/*
     * <span id="u_0_k" class="_5k_3" data-type="radio" data-name="gender_wrapper">
			<span class="_5k_2 _5dba">
			<input id="u_0_h" name="sex" value="1" type="radio"/>
			<label class="_58mt" for="u_0_h">Female</label>
			</span>
			<span class="_5k_2 _5dba">
			<input id="u_0_i" name="sex" value="2" type="radio"/>
			<label class="_58mt" for="u_0_i">Male</label>
			</span>
			</span>
     */
    	List<WebElement> radButtons=driver.findElements(By.name("sex"));
    	radButtons.get(1).click();
    	
    	driver.findElement(By.id("u_0_e")).click();
    	
	}

}
