package requestmanagement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC001CreateRequest {
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
	//step4: create new request
	driver.switchTo().frame("gsft_main");
	driver.findElementById("sysverb_new").click();
	driver.switchTo().defaultContent();
	//select request
	driver.switchTo().frame("gsft_main");
	driver.findElementByLinkText("Request").click();
	driver.switchTo().defaultContent();
	//create request with default values
	driver.switchTo().frame("gsft_main");
	String attribute = driver.findElementById("sc_request.number").getAttribute("value");
	System.out.println(attribute);
	//driver.findElementById("sc_request.description").sendKeys("new Create Request  ha s Created");
	driver.findElementById("sysverb_insert").click();
	driver.switchTo().defaultContent();
	//step5:verify the new request verified
	driver.switchTo().frame("gsft_main");
	driver.findElementByXPath("(//input[@class='form-control'])[1]").sendKeys(attribute,Keys.ENTER);
	String text = driver.findElementByXPath("(//td[@class='vt']/a)[1]").getText();
	System.out.println(text);
	if(text.equals(attribute)) {
		System.out.println("The Request management has created");
	}else
	{
		System.out.println("The Request management has not created");
	}
}
}
