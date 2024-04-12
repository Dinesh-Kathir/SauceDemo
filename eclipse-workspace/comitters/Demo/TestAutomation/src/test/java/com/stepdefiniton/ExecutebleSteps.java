package com.stepdefiniton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.utility.Utilities;

import io.cucumber.java.en.*;

public class ExecutebleSteps extends Utilities {

	String url = "https://www.saucedemo.com/";
	List<String> dataList = new ArrayList<>();

	@Given("As a user must be in login page")
	public void as_a_user_must_be_in_login_page() {
		loadDriver();
		launchUrl(url);
	}

	@When("As a user enter {string} ,{string}")
	public void as_a_user_enter(String username, String password) {
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

	}

	@When("clicks on login button")
	public void clicks_on_ogin_button() {
		WebElement login = driver.findElement(By.xpath("//input[@id='login-button']"));
		elementClick(login);
	}

	@When("As a user clicks on menu bar and click about link")
	public void as_a_user_clicks_on_menu_bar_and_click_about_link() {
		driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
		driver.findElement(By.id("about_sidebar_link")).click();
	}

	@Then("verify that redirected to appropriate {string}")
	public void verify_that_redirected_to_appropriate(String nav_url) {
		String actualUrl = driver.getCurrentUrl();
		assertEquals(actualUrl, nav_url);
	}

	@Then("verify user navigated to products page by clicking back button")
	public void verify_user_navigated_to_products_page_by_clicking_back_button() {
		driver.navigate().back();
		WebElement ele = driver.findElement(By.xpath("//div[text()='Swag Labs']"));
		redirectAssert(ele);
		
	}

	@When("As a user select the highest price product to cart")
	public void as_a_user_select_the_highest_price_product_to_cart() {
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

		HashMap<WebElement, Double> map = new HashMap<WebElement, Double>();
		for (int i = 1; i < products.size(); ++i) {
			String text = products.get(i).getText();
			String price = text.replaceAll("[$]", "");
			int index = i + 1;
			WebElement button = driver.findElement(
					By.xpath("(//div[@class='inventory_item_price'])[" + index + "]//following-sibling::button"));
			map.put(button, Double.parseDouble(price));
		}

		Double max = Collections.max(map.values());
		System.out.println("max Price amount is :" + max);

		for (Map.Entry<WebElement, Double> entry : map.entrySet()) {
			if (entry.getValue() == max) {
				entry.getKey().click();
				break;
			}
		}
	}

	@Then("Validate User redirected to cart page by clicking cart menu")
	public void validate_user_redirected_to_cart_page_by_clicking_cart_menu() {
		WebElement cart_element = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
		elementClick(cart_element);
		WebElement yourCart = driver.findElement(By.xpath("//span[text()='Your Cart']"));
		redirectAssert(yourCart);
	}

	@Then("Verify user redirected to checkout : your information page by user clicks on checkout")
	public void verify_user_redirected_to_checkout_your_information_page_by_user_clicks_on_checkout() {
		WebElement checkout = driver.findElement(By.xpath("//button[@id='checkout']"));
		elementClick(checkout);

		WebElement yourInformation = driver.findElement(By.xpath("//span[text()='Checkout: Your Information']"));
		redirectAssert(yourInformation);
	}

	@When("As a user enter {string},{string},{string}")
	public void as_a_user_enter(String string, String string2, String string3) {
		driver.findElement(By.id("first-name")).sendKeys(string);
		driver.findElement(By.id("last-name")).sendKeys(string2);
		driver.findElement(By.id("postal-code")).sendKeys(string3);

	}

	@When("As a user clicks on continue")
	public void as_a_user_clicks_on_continue() {
		WebElement con = driver.findElement(By.id("continue"));
		elementClick(con);

	}

	@Then("Verify user redirected to checkout overview and format of total")
	public void verify_user_redirected_to_checkout_overview() {
		WebElement overview = driver.findElement(By.xpath("//span[text()='Checkout: Overview']"));
		redirectAssert(overview);

		String text = driver.findElement(By.xpath("//div[@data-test='total-label']")).getText();
		String[] ar = text.split(" ");
		assertTrue("Verified...", ar[1].startsWith("$") && ar[1].length() == 6);
		driver.findElement(By.id("finish")).click();

		System.out.println("Verified and program completed...");
		driver.close();
	}

	@Then("Verify that redirected to homepage")
	public void verify_that_redirected_to_homepage() {
		WebElement element = driver.findElement(By.xpath("//div[text()='Swag Labs']"));
		Assert.assertTrue(element.isDisplayed());
		driver.close();
	}

	@When("select a sort menu and select option as {string}")
	public void select_a_sort_menu_and_select_option_as(String sort) {
		WebElement select = driver.findElement(By.xpath("//select[@data-test='product-sort-container']"));
		Select s = new Select(select);
		s.selectByVisibleText(sort);
	}
	@Then("verify that product sorted accordingly")
	public void verify_that_product_sorted_accordingly() {
	
		
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='inventory_item_name ']"));
		for (WebElement element : list) {
			String text = element.getText();
			System.out.println(text);
			dataList.add(text);
		}
		boolean b = checkSorted(dataList);
		Assert.assertTrue(b);
		driver.close();
	}


}
