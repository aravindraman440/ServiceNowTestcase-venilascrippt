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

public class TC_006_GettingError {

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
		
		//click on submit button
		driver.findElement(By.xpath("//button[@id='sysverb_insert_bottom']")).click();

		
		boolean errorMsg = driver.findElement(By.className("outputmsg_text")).isDisplayed();

		// verification
		if (errorMsg) {
			System.out.println("Error message is displayed");
		} else {
			System.out.println("Error message is not displayed");
		}
		driver.close();

	}

}
