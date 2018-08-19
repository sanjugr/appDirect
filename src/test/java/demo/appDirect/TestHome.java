package demo.appDirect;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import demo.appDirect.Home;
import demo.appDirect.Common;

public class TestHome extends Common {

	@BeforeMethod
	public void beforeMethod() throws IOException {
		driver = new ChromeDriver();
		initTest();
	}

	/*
	 * Test Case - Test if the search results are returning the key word
	 */

	@DataProvider
	public Object[][] getSearchKeyWordInputs() {
		return new Object[][] { { 1, "GoDaddy" }, { 2, "godaddy" }, { 3, "GODADDY" }, { 4, "GodAdDy" } }; 
	}

	@Test(dataProvider = "getSearchKeyWordInputs", priority = 1)
	public void testSearchResultsAreDisplayed(int instanceNumber, String searchKeyWord) throws IOException {
		Home home = new Home(driver);
		home.search(searchKeyWord);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitTillWebElementIsClickable(home.searchResultsContainer, 30);
		String str2 = home.getSearchResultTitle(0);
		System.out.println("Instance " + instanceNumber + " & Keyword Searched is = " + searchKeyWord + "");
		String str1 = searchKeyWord;
		boolean flag = false;
		if (str2.toLowerCase().contains(str1.toLowerCase())) {
			flag = true;
		}
		Assert.assertEquals(flag, true);
	}

	/*
	 * Test Case - Test if the search results are NOT returning the an Expected
	 */

	@DataProvider
	public Object[][] getSearchRandomInputs() {
		return new Object[][] { { 1, "random", "GoDaddy" }, { 2, "GO DADDY", "GoDaddy" }, { 3, "GODADDYS", "GoDaddy" },
				{ 4, "daddy", "GoDaddy" } };
	}

	@Test(dataProvider = "getSearchRandomInputs", priority = 2)
	public void testSearchResultsNotDisplayed(int instanceNumber, String searchKeyWord, String notExpectedResult)
			throws IOException {
		Home home = new Home(driver);
		home.search(searchKeyWord);
		waitTillWebElementIsClickable(home.searchResultsContainer, 30);
		boolean flag = false;
		if (driver.findElements(By.cssSelector(home.searchResultsLists)).size() > 0) {
			String str2 = home.getSearchResultTitle(0);
			System.out.println("Instance " + instanceNumber + " & Keyword Searched is = " + searchKeyWord + "");
			String str1 = notExpectedResult;

			if (str2.toLowerCase().contains(str1.toLowerCase())) {
				flag = true;
			}
		}
		Assert.assertEquals(flag, false);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
