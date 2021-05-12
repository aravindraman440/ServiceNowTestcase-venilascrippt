package article;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpadatedArticle {
public static void main(String[] args) throws InterruptedException {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	// step1: enter url
	driver.get("https://dev111236.service-now.com");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	// step2:Login with positive credencial
	driver.switchTo().frame("gsft_main");
	driver.findElementById("user_name").sendKeys("admin");
	driver.findElementById("user_password").sendKeys(" ");
	driver.findElementById("sysverb_login").click();
	Thread.sleep(1000);
	// step3:search on filter navigater enter the Knowledge
	WebElement id = driver.findElementByXPath("//input[@id='filter']");
	id.sendKeys("Knowledge", Keys.ENTER);
    driver.findElementByXPath("(//div[text()='Knowledge'])[1]").click();
	driver.switchTo().frame("gsft_main");
	Thread.sleep(2000);
	driver.findElementByXPath("//span[text()='Create an Article']/..").click();
	driver.switchTo().defaultContent();
	driver.switchTo().frame("gsft_main");
	String windowHandle = driver.getWindowHandle();
	Thread.sleep(2000);
	WebElement path = driver.findElementByXPath("(//span[@class='input-group-btn']//span[@class='icon icon-search'])[1]");
	Thread.sleep(2000);
	path.click();
	Set<String> windowHandles = driver.getWindowHandles();
	List<String> newWindow=new ArrayList<String>(windowHandles);
	driver.switchTo().window(newWindow.get(1));
	Thread.sleep(2000);
	
	driver.findElementByXPath("(//a[@class='glide_ref_item_link'])[2]").click();
	driver.switchTo().window(windowHandle);
	driver.switchTo().frame("gsft_main");
	
	

	driver.findElementByXPath("(//button[@class='btn btn-default'])[2]").click();
	Set<String> windowHandles2 = driver.getWindowHandles();
	List<String> newWindow2=new ArrayList<String>(windowHandles2);
	driver.switchTo().window(newWindow2.get(0));
	//driver.switchTo().frame("gsft_main");
	driver.findElementByXPath("//table[@id='window.kb_categories_dialog']").click();
	driver.findElementByXPath("//div[@id='kbCategoriesJSON-columnview-container']/div[2]/div[4]/span[1]").click();
	driver.findElementByXPath("//button[contains(@class,'btn btn-primary')]").click();
	driver.switchTo().defaultContent();
	driver.findElementByXPath("//input[@data-type='string']").sendKeys("article is created");
	
	
}
}
