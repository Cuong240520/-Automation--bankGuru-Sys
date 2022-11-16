package pageObject.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.user.RegisterPageUI;

public class RegisterPageObject extends BasePage{
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isSuccessPageDisplayed(WebDriver driver) {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_NOTIFY);
		return isElementDisplayed(driver, RegisterPageUI.REGISTER_SUCCESS_NOTIFY);
	}

	public String getUserName(WebDriver driver) {
		return getWebElement(driver, RegisterPageUI.USER_ID).getText();
	}
	
	public String getPassword(WebDriver driver) {
		return getWebElement(driver, RegisterPageUI.USER_PASSWORD).getText();
	}


	
   
     
}
