package pageObject.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.user.BasePageUI;
import pageUI.user.NewAccountPageUI;

public class AddNewAccountPageObject extends BasePage {
	WebDriver driver;

	public AddNewAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void slectAccountType(WebDriver driver, String itemDropdown) {
		waitForElementVisible(driver, NewAccountPageUI.SELECT_ACCOUNT_TYPE);
		selectItemDefaultDropdow(driver, NewAccountPageUI.SELECT_ACCOUNT_TYPE, itemDropdown);
	}

	public String getAccountUID(WebDriver driver, String infoValue) {
		waitForElementVisible(driver, BasePageUI.GET_VALUE_TABLE, infoValue);
		return getTextElement(driver, BasePageUI.GET_VALUE_TABLE, infoValue);
	}
}
