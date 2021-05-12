package requestmanagement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC003CancelRequest {
public static void main(String[] args) {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	// step1: enter url
	driver.get("https://dev103117.service-now.com");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	// step2:Login with positive credencial
	driver.switchTo().frame("gsft_main");
	driver.findElementById("user_name").sendKeys("admin");
	driver.findElementById("user_password").sendKeys("India@123");
	driver.findElementById("sysverb_login").click();
	//step3:enter my work in filter navigater
	WebElement id = driver.findElementByXPath("//input[@id='filter']");
	id.sendKeys("My Work", Keys.ENTER);
	//select under my work
	driver.findElementByXPath("(//div[text()='My Work'])[1]").click();
	driver.switchTo().defaultContent();

	//step4:open the existing Request
	driver.switchTo().frame("gsft_main");
	String text = driver.findElementByXPath("(//td[@class='vt']/a)[1]").getText();
	System.out.println("existing Request:"+text);
	driver.findElementByXPath("(//td[@class='vt']/a)[1]").click();
	driver.switchTo().defaultContent();
	//step5:click on cancel request
	driver.switchTo().frame("gsft_main");
	driver.findElementByXPath("(//button[text()='Cancel Request'])[1]").click();
	driver.switchTo().defaultContent();
	
	
	//verify the request is cancelled
	driver.switchTo().frame("gsft_main");
	String text2 = driver.findElementByXPath("(//caption[@class='sr-only']/following::tr)[3]").getText();
	System.out.println("text2"+text2);
}
}
