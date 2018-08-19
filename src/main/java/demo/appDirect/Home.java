package demo.appDirect;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home extends Common {
	
	public Home(WebDriver driver) {
		this.driver=driver;
	}

	/* All the Web Elements CssLocators in the home page is declared below */

	String searchTextBox = "[id='adsearch'] [type='search']";
	String searchButton = "[data-auto-action='search:button']";
	String searchResultsContainer = "[id='sectionContent'] [data-component='ListingContent']";
	String searchResultsLists = ".grid-container .listing .listing-row";
	String searchResultTitle = ".listing-row-content-title";
	
	/* All the reusable functionalities in home page is defined below */
	
	public void search(String searchKeyWord) {
		driver.findElement(By.cssSelector(searchTextBox)).clear();
		driver.findElement(By.cssSelector(searchTextBox)).sendKeys(searchKeyWord);
		driver.findElement(By.cssSelector(searchButton)).click();
	}
	
	public String getSearchResultTitle(int rowIndex) {
		List<WebElement> rowCount =driver.findElements(By.cssSelector(searchResultsLists));
		String resultTitle = rowCount.get(rowIndex).getText();
		return resultTitle;
	}
}
