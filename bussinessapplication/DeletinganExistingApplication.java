package bussinessapplication;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeletinganExistingApplication {
public static void main(String[] args) throws InterruptedException {
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
	Thread.sleep(1000);
	// step3:search on filter navigater enter the Business Application
	WebElement id = driver.findElementByXPath("//input[@id='filter']");
	id.sendKeys("Business Applications", Keys.ENTER);
	driver.findElementByXPath("(//div[text()='Business Applications'])[1]").click();
	driver.switchTo().defaultContent();
	//step4:click on first resultingApplications
	driver.switchTo().frame("gsft_main");
	String text = driver.findElementByXPath("(//td[@class='vt']/a)[1]").getText();
	System.out.println(text);
	driver.findElementByXPath("(//td[@class='vt']/a)[1]").click();
	driver.switchTo().defaultContent();
	driver.switchTo().frame("gsft_main");
	//step5:click on delete button
	driver.findElementByXPath("//button[@id='sysverb_delete']").click();
	//step6:confirm on promptAlert
	driver.findElementByXPath("//div[@class='modal-footer']/button[@id='ok_button']").click();
	driver.switchTo().defaultContent();
	//step7:Verify the deleted Application
	driver.switchTo().frame("gsft_main");
	Thread.sleep(2000);
	driver.findElementByXPath("//span[@class='input-group-addon input-group-select']/following::input").sendKeys(text,Keys.ENTER);
	String text2 = driver.findElementByXPath("(//td[@class='vt']/a)[1]").getText();
	System.out.println(text2);
	if(text.equals(text2)) {
		System.out.println("The Business Application not Deletedd");
	}else
	{
		System.out.println("The Business Application  Deleted");
	}
	
}
}
