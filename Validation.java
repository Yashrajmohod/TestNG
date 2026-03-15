package hii;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Validation {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = new ChromeDriver();
		driver.get("https://practicetestautomation.com/practice-test-login/");
	}

	@AfterClass
	public void login() {
		driver.quit();
	}

	@Test(priority = 1)
	public void Main() {

		driver.findElement(By.name("username")).sendKeys("student");

		driver.findElement(By.id("password")).sendKeys("Password123");

		driver.findElement(By.id("submit")).click();

		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "Logged In Successfully | Practice Test Automation";

		Assert.assertEquals(ActualTitle, ExpectedTitle, "Test case fail due to title missmatch");
	}

	@Test(priority = 2, enabled = false)
	public void TestValidation() {

		driver.findElement(By.name("username")).sendKeys("student132");

		driver.findElement(By.id("password")).sendKeys("Password444444123");

		driver.findElement(By.id("submit")).click();

		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "Logged In Successfully | Practice Test Automation";

		Assert.assertEquals(ActualTitle, ExpectedTitle, "Test case fail due to title missmatch");

	}

	@Test(priority = 3, enabled = false)
	public void TestValidationData() {

		driver.findElement(By.name("username")).sendKeys("!@#$$%%^%");

		driver.findElement(By.id("password")).sendKeys("^%$#@!");

		driver.findElement(By.id("submit")).click();

		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "Logged In Successfully | Practice Test Automation";

		Assert.assertEquals(ActualTitle, ExpectedTitle, "Test case fail due to title missmatch");

	}

	@Test(priority = 2)
	public void TestLogout() {
		WebElement btnLogOut = driver.findElement(By.linkText("Log out"));
		Assert.assertTrue(btnLogOut.isDisplayed());
	}
}
