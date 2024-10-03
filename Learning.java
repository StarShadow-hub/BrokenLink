package org.learn;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Learning  {
	
	WebDriver driver;	
	@Parameters({"browse","site"})
	@BeforeClass(groups = {"smoke","sanity"})
	private void WebsiteLaunch(String browser, String url) {
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(browser.equals("edge")) {
			driver= new EdgeDriver();
		}		
		driver.get(url);
	}
	
	@Test(groups = "sanity")
	private void brokenLinks() throws IOException {	
		
		int count = 0;
		int perfectLinkCount = 0;
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		for (WebElement lists : elements) {
			String Links = lists.getAttribute("href");
			URL url = new URL(Links);
			URLConnection openConnection = url.openConnection();
			HttpURLConnection connection = (HttpURLConnection)openConnection;
			int responseCode = connection.getResponseCode();
			
			if(responseCode>=400) {
				System.out.println(Links);
				count++;
			}else {
				perfectLinkCount++;
			}
		}
		System.out.println("Total Broken Links:"+ count);
		System.out.println("perfect Link Count:"+ perfectLinkCount);	
	}
	
	@Test(groups = "smoke")
	private void login() {
		driver.findElement(By.id("email")).sendKeys("vijay");
		driver.findElement(By.id("pass")).sendKeys("pass");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
	}
	
	

	

}
