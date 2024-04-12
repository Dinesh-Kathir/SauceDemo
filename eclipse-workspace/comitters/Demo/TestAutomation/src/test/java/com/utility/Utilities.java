package com.utility;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Utilities {
	public static WebDriver driver;
	public void loadDriver() {
	driver = new ChromeDriver();
		
	}
	
	public static void launchUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		impliciltyWait();
	
	}
	
	public static void impliciltyWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	 public static void elementClick(WebElement ele) {
		ele.click();

	}
	 
	 public static void redirectAssert(WebElement ele) {
		 assertTrue(ele.isDisplayed());
	 }
	 
	 public static boolean checkSorted(List<String> list) {
			// TODO Auto-generated method stub
			for(int i=1;i<list.size();i++) {
				if(list.get(i-1).compareTo(list.get(i))<0) {
					return false;
				}
			}
			return true;
		}
	 
	 
}
