package changemanagement;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateRequest {

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

//		WebElement firstNum = driver.findElement(By.xpath("(//td[@class='vt'])[1]/a"));
//		String firstNumTxt = driver.findElement(By.xpath("(//td[@class='vt'])[1]/a")).getText();
//		System.out.println("First Displayed Number :- " +firstNumTxt);
//		firstNum.click();

		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("CHG0030043", Keys.ENTER);

		driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).click();

		Thread.sleep(2000);

		// driver.switchTo().frame(iframe);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Schedule']")).click();

		WebElement startDate = driver
				.findElement(By.xpath("(//button[@id='change_request.start_date.ui_policy_sensitive'])[2]/span"));
		startDate.click();

		WebElement firstDate = driver.findElement(By.xpath("//a[@id='GwtDateTimePicker_day15']"));
		firstDate.click();

		driver.findElement(By.xpath("//button[@id='GwtDateTimePicker_ok']")).click();

		WebElement endDate = driver
				.findElement(By.xpath("(//button[@class='btn btn-default btn-ref date_time_trigger'])[2]/span"));
		endDate.click();

		WebElement secondDate = driver.findElement(By.xpath("//a[@id='GwtDateTimePicker_day17']"));
		secondDate.click();

		driver.findElement(By.xpath("//button[@id='GwtDateTimePicker_ok']")).click();

		driver.findElement(By.xpath("(//label[@class=' checkbox-label'])[2]")).click();

		driver.findElement(By.xpath("//span[@class='input-group-btn']/a")).click();

		driver.findElement(By.xpath("//a[@id='GwtDateTimePicker_day20']")).click();

		driver.findElement(By.xpath("//button[@id='sysverb_update']")).click();

		WebElement text = driver.findElement(By.xpath("(//div[@class='datex date-calendar'])[1]"));
		if (text.isDisplayed()) {
			System.out.println("Updated");

		} else {
			System.out.println("Not Updated");
		}
	}
}
