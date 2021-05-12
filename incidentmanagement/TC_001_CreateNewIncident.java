package incidentmanagement;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.jsoup.select.Evaluator.Id;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_001_CreateNewIncident {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.get("https://dev103117.service-now.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// login with credentials
		driver.switchTo().frame(0);
		driver.findElement(By.id("user_name")).clear();
		driver.findElement(By.id("user_name")).sendKeys("admin");

		driver.findElement(By.id("user_password")).clear();
		driver.findElement(By.id("user_password")).sendKeys("India@123");

		driver.findElement(By.id("sysverb_login")).click();

		// search incident on field navigator
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident", Keys.ENTER);

		// clicking on create new
		driver.findElement(By.xpath("//div[text()='Create New']")).click();
		driver.switchTo().frame(0);
		Thread.sleep(3000);

		// Get incident number
		String incidentNo = driver.findElement(By.xpath("//input[@class='form-control']")).getAttribute("value");
		System.out.println(incidentNo);
		
		// Filling mandatory fields
		driver.findElement(By.xpath("//span[@class='icon icon-search']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winHand1 = new ArrayList<String>(windowHandles);
		driver.switchTo().window(winHand1.get(1));
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("Abel Tuter",Keys.ENTER);
		driver.findElement(By.linkText("Abel Tuter")).click();
		driver.switchTo().window(winHand1.get(0));
		driver.switchTo().frame(0);

		driver.findElement(By.id("incident.short_description")).sendKeys("Saheel TestLeaf");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='sysverb_insert_bottom']")).click();

		// Search with created incident no
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(incidentNo, Keys.ENTER);
		boolean displayed = driver.findElement(By.xpath("//a[@class='linked formlink']")).isDisplayed();

		// verification
		if (displayed) {
			System.out.println("The incident is created successfully");
		} else {
			System.out.println("The incident is not created successfully");
		}
		driver.close();

	}

}
