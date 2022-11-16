package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	WebDriver driver;
	protected final Log log;

	public BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case CHROME:
//			driver = WebDriverManager.chromedriver().create();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		default:
			throw new RuntimeException("Invalid Browser");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(GlobalConstants.LIVE_USER_URL);
		return driver;

	}

	protected WebDriver getBrowserDriver(String browserName, String urlValue) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case FIREFOX: // GỌI CÁC BROWSER TỪ ENUM CLASS (BROWSERLIST)
			//driver = WebDriverManager.firefoxdriver().create();
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case CHROME:
//			driver = WebDriverManager.chromedriver().create();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		default:
			throw new RuntimeException("Invalid Browser");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(urlValue);
		return driver;

	}

	protected boolean verifyTrue(boolean condition) {
		boolean status = true;
		try {
			Assert.assertTrue(condition);
			log.info("--------------------Passed-------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("--------------------Failed-------------------");
		}
		return status;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean status = true;
		try {
			Assert.assertTrue(condition);
			log.info("--------------------Passed-------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("--------------------Failed-------------------");
		}
		return status;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean status = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info("--------------------Passed-------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("--------------------Failed-------------------");
		}
		return status;

	}
	/* Delete data in folder */
	/*
	 * @BeforeSuite public void deleteAllFilesInReportNGScreenshot() { System.out.println("---------- START delete file in folder ----------");
	 * deleteAllFileInFolder(); System.out.println("---------- END delete file in folder ----------"); } public void deleteAllFileInFolder() { try { File
	 * file = new File(GlobalConstants.REPORTNG_SCREENSHOT); File[] listOfFiles = file.listFiles(); for (int i = 0; i < listOfFiles.length; i++) { if
	 * (listOfFiles[i].isFile()) { System.out.println(listOfFiles[i].getName()); new File(listOfFiles[i].toString()).delete(); } } } catch (Exception e) {
	 * log.info(e.getMessage()); } }
	 */

	public WebDriver getWebDriver() {
		return this.driver;
	}

	protected int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

}
