package pageObject.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.user.DashboardPageUI;

public class DashBoardPageObject extends BasePage {
	WebDriver driver;

	public DashBoardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isLoginSuccessIsDisplayed(WebDriver driver) {
		waitForElementVisible(driver, DashboardPageUI.VERIFY_LOGIN_SUCCESS);
		return isElementDisplayed(driver, DashboardPageUI.VERIFY_LOGIN_SUCCESS);
		
	}
}
