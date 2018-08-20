package demo.appDirect;

import java.util.concurrent.TimeUnit;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

public class Common {

	public String buildLink = "https://marketplace.appdirect.com/home";
	WebDriver driver;

	public void initTest() throws IOException {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(buildLink);
	}

	public void selectBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
	}

	public boolean isElementDisplayed(String elementCssSelector) {
		if (driver.findElements(By.cssSelector(elementCssSelector)).size() > 0)
			return true;
		else
			return false;
	}

	public void waitTillWebElementIsClickable(String locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
	}

	public void scrollIntoView(String Locator) {
		WebElement element = driver.findElement(By.cssSelector(Locator));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}

	public void wait(int TimeOut) {
		driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
	}

	public void log(String message) {
		System.out.println(message);
	}

}
