package pageObject.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class EditCustomerPageObject extends BasePage {
	WebDriver driver;

	public EditCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
