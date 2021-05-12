package changemanagement;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssessRequest {

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

		WebElement search = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		search.sendKeys("CHG0030043", Keys.ENTER);

		Thread.sleep(2000);
		String firstEle = driver.findElement(By.xpath("(//td[@class='vt'])[1]/a")).getText();
		System.out.println(firstEle);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//td[@class='vt'])[1]/a")).click();

		// WebElement iframe2 =
		// driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		// driver.switchTo().frame(iframe2);

		WebElement state = driver.findElement(By.id("change_request.state"));
		Select select = new Select(state);
		select.selectByVisibleText("Assess");

		driver.findElement(By.xpath("//button[@id='lookup.change_request.assignment_group']")).click();

		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
		String title = driver.getTitle();
		System.out.println(title);

		driver.findElement(By.xpath("//a[text()='Software']")).click();

		driver.switchTo().window(winList.get(0));

		WebElement iframe2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe2);

		driver.findElement(By.xpath("//button[@id='lookup.change_request.assigned_to']")).click();

		Set<String> winSet1 = driver.getWindowHandles();
		List<String> winList1 = new ArrayList<String>(winSet1);
		driver.switchTo().window(winList1.get(1));
		String title1 = driver.getTitle();
		System.out.println(title1);

		driver.findElement(By.xpath("//a[text()='ITIL User']")).click();

		driver.switchTo().window(winList.get(0));

		WebElement iframe3 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(iframe3);

		driver.findElement(By.xpath("//button[@id='sysverb_update']")).click();

		String Assess = driver.findElement(By.xpath("(//td[@class='vt'])[4]")).getText();
		if (Assess.equals("Assess")) {
			System.out.println("Assess the Change Request");

		} else {
			System.out.println("Does notAssess the Change Request");
		}
	}
}
