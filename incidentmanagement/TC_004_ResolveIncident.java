package incidentmanagement;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.jsoup.select.Evaluator.Id;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_004_ResolveIncident {

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

		// Click on open
		driver.findElement(By.xpath("//div[text()='Open']")).click();

		driver.switchTo().frame(0);
		Thread.sleep(3000);

		// search for existing incident
		WebElement textSelect = driver.findElement(By.className("form-control"));
		Select dropdown = new Select(textSelect);
		dropdown.selectByValue("zztextsearchyy");
		
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("Saheel", Keys.ENTER);
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();

		// Get incident number
		String incidentNo = driver.findElement(By.xpath("//input[@class='form-control']")).getAttribute("value");
		System.out.println(incidentNo);

		// Setting group as Resolve
		driver.findElement(By.xpath("//option[text()='Resolved']")).click();
	
		// Entering mandatory fields
		driver.findElement(By.xpath("//span[text()='Resolution Information']")).click();
		driver.findElementByXPath("//option[text()='Not Solved (Not Reproducible)']").click();
		
		driver.findElement(By.id("incident.close_notes")).sendKeys("Sample testing");

		// update
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Resolve']")).click();
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(incidentNo, Keys.ENTER);

		boolean State = driver.findElement(By.xpath("//td[text()='Resolved']")).isDisplayed();
		
		// verification
		if (State) {
			System.out.println("The incident is resolved successfully");
		} else {
			System.out.println("The incident is not resolved successfully");
		}
		driver.close();

	}

}
