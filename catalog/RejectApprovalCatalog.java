package catalog;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RejectApprovalCatalog {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://dev103117.service-now.com");
		driver.switchTo().frame(0);
		driver.findElementById("user_name").sendKeys("admin");
		driver.findElementById("user_password").sendKeys("India@123");
		driver.findElementById("sysverb_login").click();
		driver.findElementByXPath("//input[@id='filter']").sendKeys("My work",Keys.ENTER);
		driver.findElementByXPath("(//div[text()='My Work'])[1]").click();
		
		WebElement frame = driver.findElementById("gsft_main");
		driver.switchTo().frame(frame);
		
		WebElement search = driver.findElementByXPath("//select[@class='form-control default-focus-outline']");
		Select dd = new Select(search);
		dd.selectByVisibleText("Task type");
		
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@class='form-control']").sendKeys("Catalog Task",Keys.ENTER);
		
		
        driver.findElementByXPath("//a[@class='linked formlink']").click();
		
		WebElement approval = driver.findElementById("sc_task.approval");
		Select dd1 = new Select(approval);
		dd1.selectByVisibleText("Rejected");
		
		WebElement state = driver.findElementById("sc_task.state");
		Select dd2 = new Select(state);
		dd2.selectByVisibleText("Closed Skipped");
		
		driver.findElementById("sysverb_update_bottom").click();
		System.out.println("reject catalog");

	}

}
