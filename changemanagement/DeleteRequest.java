package changemanagement;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteRequest {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("https://dev63235.service-now.com/navpage.do");

		driver.switchTo().frame(0);

		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");

		driver.findElement(By.id("sysverb_login")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("change", Keys.ENTER);

		driver.findElement(By.xpath("(//div[text()='Open'])[3]")).click();

		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe);

		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("CHG0030094", Keys.ENTER);

		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();

		driver.findElement(By.xpath("(//button[text()='Delete'])[1]")).click();

		driver.findElement(By.xpath("//button[@id='ok_button']")).click();

		String noRecords = driver.findElement(By.xpath("//td[text()='No records to display']")).getText();
		if (noRecords.contains("No records")) {
			System.out.println("Request Deleted");

		} else {
			System.out.println("Request not Deleted");
		}

	}

}
