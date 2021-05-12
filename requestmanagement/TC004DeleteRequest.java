package requestmanagement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC004DeleteRequest {
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
	
	//step5:click on delete
	driver.switchTo().frame("gsft_main");
	driver.findElementById("sysverb_delete").click();
	String text2 = driver.findElementByXPath("//div[@style='text-align:left;']/div[text()='Delete this record?']").getText();
	System.out.println(text2);
	driver.findElementById("ok_button").click();
	System.out.println("deleted");
}
}
