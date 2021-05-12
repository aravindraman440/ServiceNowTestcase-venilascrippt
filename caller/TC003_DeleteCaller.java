package caller;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC003_DeleteCaller {

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
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("caller", Keys.ENTER);

		// Click on Caller
		driver.findElement(By.xpath("//div[text()='Callers']")).click();

		// Search for existing Caller
		driver.switchTo().frame(0);
		WebElement dropDown = driver.findElement(By.className("form-control"));
		Select dd1 = new Select(dropDown);
		dd1.selectByValue("zztextsearchyy");
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("creating sample test", Keys.ENTER);

		driver.findElement(By.xpath("(//a[@class='linked formlink'])")).click();

		//Deleting the caller
		driver.findElement(By.id("sysverb_delete")).click();
		driver.findElement(By.id("ok_button")).click();
		
	// Verification
		
	boolean verification = driver.findElement(By.xpath("//td[text()='No records to display']")).isDisplayed();
	
	
		if (verification) {
		System.out.println("The caller is deleted successfully");
		}else {
			System.out.println("The caller is not deleted successfully");
		}	
	driver.close();

	}

}
