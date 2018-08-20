package demo.appDirect;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import demo.appDirect.Home;
import demo.appDirect.Common;

public class TestHome extends Common {

	@BeforeMethod
	public void beforeMethod() throws IOException {
		selectBrowser("chrome");
		initTest();
	}

	/*
	 * Test Case - Test if the search results are returning the key word
	 */

	@DataProvider
	public Object[][] getSearchKeyWordInputs() {
		return new Object[][] { { 1, "GoDaddy", "GoDaddy" }, { 2, "godaddy", "GoDaddy" }, { 3, "GODADDY", "GoDaddy" },
				{ 4, "GodAdDy", "GoDaddy" }, { 5, "GODADDYS", "GoDaddy" } };
	}

	@Test(dataProvider = "getSearchKeyWordInputs", priority = 1)
	public void testSearchResultsAreDisplayed(int instanceNumber, String searchKeyWord, String expectedTitleWord)
			throws IOException {
		Home home = new Home(driver);
		home.search(searchKeyWord);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitTillWebElementIsClickable(home.searchResultsContainer, 30);
		boolean flag = false;
		System.out.println("Instance " + instanceNumber + " & Keyword Searched is = " + searchKeyWord + "");
		for (int i = 0; i < home.getTotalNumberOfSearchResults(); i++) {
			String titleOfResult = home.getSearchResultTitle(i);
			if (titleOfResult.toLowerCase().contains(expectedTitleWord.toLowerCase())) {
				flag = true;
			}
		}
		Assert.assertEquals(flag, true);
	}

	/*
	 * Test Case - Test if the search results are NOT returning the an Expected
	 */

	@DataProvider
	public Object[][] getSearchRandomInputs() {
		return new Object[][] { { 1, "random", "GoDaddy" }, { 2, "GO DADDY", "GoDaddy" }, { 3, "daddy", "GoDaddy" } };
	}

	@Test(dataProvider = "getSearchRandomInputs", priority = 2)
	public void testSearchResultsNotDisplayed(int instanceNumber, String searchKeyWord, String notExpectedResult)
			throws IOException {
		Home home = new Home(driver);
		home.search(searchKeyWord);
		waitTillWebElementIsClickable(home.searchResultsContainer, 30);
		boolean flag = false;
		System.out.println("Instance " + instanceNumber + " & Keyword Searched is = " + searchKeyWord + "");
		if (home.getTotalNumberOfSearchResults() > 0) {
			for (int i = 0; i < home.getTotalNumberOfSearchResults(); i++) {
				String titleOfResult = home.getSearchResultTitle(i);
				if (titleOfResult.toLowerCase().contains(notExpectedResult.toLowerCase())) {
					flag = true;
				}
			}
		}
		Assert.assertEquals(flag, false);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
