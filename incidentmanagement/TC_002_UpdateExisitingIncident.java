package incidentmanagement;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_002_UpdateExisitingIncident {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		JavascriptExecutor js = (JavascriptExecutor) driver;
	
	
		driver.manage().window().maximize();
		driver.get("https://dev103117.service-now.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();

		// Get incident number
		String incidentNo = driver.findElement(By.xpath("//input[@class='form-control']")).getAttribute("value");
		System.out.println(incidentNo);

		// Selecting urgency as high
		WebElement dropDown = driver.findElement(By.xpath("//select[@id='incident.urgency']"));
		Select dd = new Select(dropDown);
		dd.selectByValue("1");

		Thread.sleep(2000);

		// selecting state as Inprogress
		driver.findElement(By.xpath("//option[text()='In Progress']")).click();
	WebElement workNotes = driver.findElement(By.xpath("(//span[text()='Work notes'])[3]/following::textarea"));
	//	js.executeScript("arguments[0].scrollIntoView();",workNotes );
		
		//WebElement workNotes = driver.findElement(By.xpath("//textarea[@id='activity-stream-textarea']"));
		
		Actions builder = new Actions(driver);
		builder.moveToElement(workNotes).perform();
		// js.executeScript("window.scrollBy(0,5000)");
		//Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Work notes'])[3]/following::textarea")).sendKeys("test Sample");
		
		
		
		// update
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Update']")).click();
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(incidentNo, Keys.ENTER);

		boolean moderate = driver.findElement(By.xpath("//td[contains(text(),'Moderate')]")).isDisplayed();
		boolean progress = driver.findElement(By.xpath("//td[contains(text(),'Progress')]")).isDisplayed();

		// verification
		if (moderate && progress) {
			System.out.println("The incident is updated successfully");
		} else {
			System.out.println("The incident is not updated successfully");
		}
		driver.close();

	}

}
