package hii;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DataProviderDemo {
	WebDriver driver;
	
@Parameters({"broswer","url"})
	@BeforeMethod
	public void setup(String brName, String URl) throws InterruptedException {
	if(brName.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver();
	}else if (brName.equalsIgnoreCase("firefox")){
		driver = new FirefoxDriver();
	}else if(brName.equalsIgnoreCase("IE")){
	driver = new InternetExplorerDriver();	
	}else {
		System.out.println("Invalid broswer");
	}
	driver.get(URl);
	Thread.sleep(3000);
	}

	@AfterClass
	public void login() {
		driver.quit();
	}

	@Test(dataProvider = "loginTestData")
	public void Main(String username, String password, String isTrue) {

		driver.findElement(By.name("username")).sendKeys("username");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("submit")).click();

		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "Logged In Successfully | Practice Test Automation";
		
		if(isTrue.equalsIgnoreCase("1")) {

	
		Assert.assertEquals(ActualTitle, ExpectedTitle, "Test case fail due to title missmatch");
	
		}else if (isTrue.equals("2")) {
		WebElement invalidErrorElement = driver.findElement(By.xpath("//*[@id=\"form\"]]"));
		Assert.assertTrue(invalidErrorElement.getText().equals("Your username is invalid!"));
	}
		}

	@DataProvider(name="loginTestData")
	public String[][] TestData() {
		// 2D Array-
		String data[][] = new String[3][2];

		// set 1
		data[0][0] = "student";
		data[0][1] = "Password123";
		data[0][2] = "1";
		
		// set 2
		data[1][0] = "dchjbfdjf";
		data[1][1] = "studsefnt1d";
		data[1][2] = "2";
		
		// set 3
		data[2][0] = "!@##$%$%";
		data[2][1] = "&%$%#$@";
		data[2][2] = "3";
		
		return data;

	}
}
