package ticketmanagement;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateTicket {

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
		driver.findElementByXPath("//button[text()='New']").click();
		
		driver.findElementByLinkText("Ticket").click();
		
		driver.findElementById("ticket.short_description").sendKeys("TestLeaf");
		
		Thread.sleep(3000);
		driver.findElementById("sysverb_insert_bottom").click();
		
		
		

	}

}
