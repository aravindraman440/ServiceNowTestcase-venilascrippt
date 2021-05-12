package article;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateArticle {
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
	
	// step3:search on filter navigater enter the Knowledge
	 driver.findElementByXPath("//input[@id='filter']").sendKeys("Knowledge", Keys.ENTER);
    driver.findElementByXPath("(//div[text()='Knowledge'])[1]").click();
	//step4:click on create an Article
    driver.switchTo().frame("gsft_main");
	
	driver.findElementByXPath("//span[text()='Create an Article']/..").click();
	driver.switchTo().defaultContent();
	//step5:fil the manatory filled
	driver.switchTo().frame("gsft_main");
	String windowHandle = driver.getWindowHandle();
	WebElement knowledgeBase = driver.findElementByXPath("(//span[@class='input-group-btn']//span[@class='icon icon-search'])[1]");
	Thread.sleep(2000);
	knowledgeBase.click();
	Set<String> windowHandles = driver.getWindowHandles();
	List<String> newWindow=new ArrayList<String>(windowHandles);
	driver.switchTo().window(newWindow.get(1));
	driver.findElementByXPath("//tr[@collapsed='true']//a").click();
	driver.switchTo().window(windowHandle);
	driver.switchTo().frame("gsft_main"); //exception no such element exception
	driver.findElementByXPath("//input[@data-type='string']").sendKeys("article is created");
	//step6:click on SubmitButton
	driver.findElementByXPath("//button[@id='sysverb_insert']").click();
	System.out.println("Article Created");
}
}
