package demo.appDirect;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home extends Common {

	/* All the Web Elements in the home page is declared below */

	By searchTextBox = By.cssSelector("[id='adsearch'] [type='search']");
	By searchButton = By.cssSelector("[data-auto-action='search:button']");
	By searchResultsContainer = By.cssSelector(".grid-container .listing");
	By searchResultsLists = By.cssSelector(".grid-container .listing .listing-row");
	By searchResultTitle = By.cssSelector(".listing-row-content-title");
	
	/* All the reusable functionalities in home page is defined below */
	
	public void search(String searchKeyWord) {
		driver.findElement(searchTextBox).clear();
		driver.findElement(searchTextBox).sendKeys(searchKeyWord);
		driver.findElement(searchButton).click();
	}
}
