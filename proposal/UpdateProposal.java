package proposal;


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

public class UpdateProposal {

	public static void main(String[] args) throws InterruptedException {
		// Open the Browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Launch the url
		driver.get("https://dev63235.service-now.com/navpage.do");

		// Enter into a frame
		driver.switchTo().frame(0);

		// Login with Credential data's
		 driver.findElement(By.id("user_name")).sendKeys("admin");
		 driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();

		// Enter P
		Thread.sleep(2000);
		WebElement filter = driver.findElement(By.xpath("//input[@id='filter']"));
		filter.sendKeys("proposal", Keys.ENTER);
		WebElement Proposal = driver.findElement(By.xpath("//div[text()='My Proposals']"));
		Proposal.click();

		Thread.sleep(5000);
		WebElement iframe2 = driver.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(iframe2);

		 driver.findElement(By.xpath("(//a[@class='linked formlink'])[5]")).click();

		 driver.findElement(By.id("lookup.std_change_proposal.assigned_to")).click();

		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
		String title = driver.getTitle();
		System.out.println(title);

		driver.findElement(By.xpath("//a[text()='Change Manager']")).click();

		driver.switchTo().window(winList.get(0));

		String title2 = driver.getTitle();
		System.out.println(title2);

		WebElement iframe3 = driver.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(iframe3);

		Thread.sleep(5000);
		WebElement DD = driver.findElement(By.xpath("//select[@id='std_change_proposal.state']"));
		Select select = new Select(DD);
		select.selectByVisibleText("In Progress");

		WebElement category = driver.findElement(By.xpath("//button[@id='lookup.std_change_proposal.category']"));
		category.click();

		Set<String> winSet1 = driver.getWindowHandles();
		List<String> winList1 = new ArrayList<String>(winSet1);
		driver.switchTo().window(winList1.get(1));

		 driver.findElement(By.xpath("//a[text()='Template Management']")).click();

		driver.switchTo().window(winList.get(0));

		WebElement iframe4 = driver.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(iframe4);

		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[text()='Change Request values'])[1]")).click();

	driver.findElement(By.xpath("//button[@class='icon-search btn btn-default filerTableAction']")).click();

		Set<String> winSet2 = driver.getWindowHandles();
		List<String> winList2 = new ArrayList<String>(winSet2);
		driver.switchTo().window(winList2.get(1));

		 driver.findElement(By.xpath("//a[text()='Change Management']")).click();

		driver.switchTo().window(winList.get(0));

		WebElement iframe5 = driver.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(iframe5);

		 driver.findElement(By.xpath("(//textarea[@class='filerTableInput form-control'])[5]")).sendKeys("Justification");

		 driver.findElement(By.xpath("(//textarea[@class='filerTableInput form-control'])[6]")).sendKeys("Risk and Impact Analysis");

		 driver.findElement(By.xpath("//button[@id='sysverb_update']")).click();

	}

}
