package hii;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ParameterDemo {

	WebDriver driver;

	@Parameters({"browser","url"})
	@BeforeMethod
	public void setup(String brName, String URl) throws InterruptedException {

		if(brName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if (brName.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}
		else if(brName.equalsIgnoreCase("IE")){
			driver = new InternetExplorerDriver();
		}
		else {
			System.out.println("Invalid browser");
		}

		driver.get(URl);
		Thread.sleep(3000);
	}

	@AfterMethod
	public void closeBrowser() {

			driver.quit();
		}
	

	@Test(dataProvider = "loginTestData")
	public void Main(String username, String password, String isTrue) {

		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("submit")).click();

		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "Logged In Successfully | Practice Test Automation";

		if(isTrue.equalsIgnoreCase("1")) {

			Assert.assertEquals(ActualTitle, ExpectedTitle);

		}
		else if (isTrue.equals("2")) {

			WebElement invalidErrorElement = driver.findElement(By.id("error"));
			Assert.assertTrue(invalidErrorElement.getText().equals("Your username is invalid!"));

		}
	}

	@DataProvider(name="loginTestData")
	public Object[][] TestData() {

		Object data[][] = new Object[3][3];

		data[0][0] = "student";
		data[0][1] = "Password123";
		data[0][2] = "1";

		data[1][0] = "wrongUser";
		data[1][1] = "Password123";
		data[1][2] = "2";

		data[2][0] = "student";
		data[2][1] = "wrongPass";
		data[2][2] = "2";

		return data;
	}
}
