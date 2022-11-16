package pageObject.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.user.BasePageUI;
import pageUI.user.NewCustomerPageUI;

public class NewCustomerPageObject extends BasePage{
	WebDriver driver;

	public NewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getCustomerUID(WebDriver driver, String infoValue) {
		waitForElementVisible(driver, BasePageUI.GET_VALUE_TABLE,infoValue);
		return getTextElement(driver, BasePageUI.GET_VALUE_TABLE,infoValue);
	}
	
	

	public void clickToTextboxByName(WebDriver driver) {
		waitForElementVisible(driver, NewCustomerPageUI.ADDRESS_TEXTBOX_TEXTAREA);
		clickToElement(driver, NewCustomerPageUI.ADDRESS_TEXTBOX_TEXTAREA);
	}
	

	public void inputToTextboxByName(WebDriver driver, String sendkeyValue) {
		waitForElementVisible(driver, NewCustomerPageUI.ADDRESS_TEXTBOX_TEXTAREA);
		sendkeyToElement(driver, NewCustomerPageUI.ADDRESS_TEXTBOX_TEXTAREA, sendkeyValue);
	}
	
	public String getFieldLabel(WebDriver driver, String dynamicLocator) {
		waitForElementVisible(driver, BasePageUI.GET_FIELD_LABEL, dynamicLocator);
		return getTextElement(driver,  BasePageUI.GET_FIELD_LABEL, dynamicLocator);
	}
	
	
	public String getErrorMessageAddressTextarea(WebDriver driver) {
		waitForElementVisible(driver, NewCustomerPageUI.ADDRESS_TEXTBOX_TEXTAREA_ERROR_MESSAGE);
		return getTextElement(driver, NewCustomerPageUI.ADDRESS_TEXTBOX_TEXTAREA_ERROR_MESSAGE);
	}
}
