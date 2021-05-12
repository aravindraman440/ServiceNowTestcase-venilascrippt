package bussinessapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewBussinessApplication {
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
		
		// step3:search on filter navigater enter the Business Application
		WebElement id = driver.findElementByXPath("//input[@id='filter']");
		id.sendKeys("Business Applications", Keys.ENTER);
		driver.findElementByXPath("(//div[text()='Business Applications'])[1]").click();
		driver.switchTo().defaultContent();
		//step4: click on new Button
		driver.switchTo().frame("gsft_main");
		driver.findElementByXPath("//button[text()='New']").click();
		driver.switchTo().defaultContent();
		//step5: to enter the mandatory filled
		driver.switchTo().frame("gsft_main");
		//enter name
		driver.findElementById("cmdb_ci_business_app.name").sendKeys("Aravind",Keys.ENTER);
		//enter description
		driver.findElementById("cmdb_ci_business_app.short_description").sendKeys("text lead");

		String windowHandle = driver.getWindowHandle();
		driver.findElementByXPath("//button[@id='lookup.cmdb_ci_business_app.it_application_owner']//span[1]").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String>newWindow =new ArrayList<String>(windowHandles);
		driver.switchTo().window(newWindow.get(1));
		WebElement path = driver.findElementByXPath("//span[@class='input-group-addon input-group-select']/following-sibling::input");
		Thread.sleep(2000);
		path.sendKeys("System Administrator",Keys.ENTER);
		driver.findElementByLinkText("System Administrator").click();
		driver.switchTo().window(windowHandle);
		//step6:click on submit button
		driver.switchTo().frame("gsft_main");
		driver.findElementByXPath("//button[@id='sysverb_insert']").click();
		//step7:Verify the newly created Business Application 
		driver.findElementByXPath("//span[@id='cmdb_ci_business_app_hide_search']//input[@placeholder='Search']").sendKeys("Aravinthan",Keys.ENTER);
		String text = driver.findElementByXPath("(//td[@class='vt']/a)[1]").getText();
		System.out.println(text);
		if(text.equals("Aravind")) {
			System.out.println("The Business Application verified");
		}else
		{
			System.out.println("The Business Application not verified");
		}

	}
}
