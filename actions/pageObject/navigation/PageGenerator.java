package pageObject.navigation;

import org.openqa.selenium.WebDriver;

import pageObject.user.AddNewAccountPageObject;
import pageObject.user.DashBoardPageObject;
import pageObject.user.EditAccountPageObject;
import pageObject.user.EditCustomerPageObject;
import pageObject.user.LoginPageObject;
import pageObject.user.NewCustomerPageObject;
import pageObject.user.RegisterPageObject;

public class PageGenerator {

	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static DashBoardPageObject getDashBoardPage(WebDriver driver) {
		return new DashBoardPageObject(driver);
	}
	public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPageObject(driver);
	}
	public static EditCustomerPageObject getEditCustomerPage(WebDriver driver) {
		return new EditCustomerPageObject(driver);
	}
	public static AddNewAccountPageObject getNewAccountPage(WebDriver driver) {
		return new AddNewAccountPageObject(driver);
	}
	public static EditAccountPageObject getEditAccountPage(WebDriver driver) {
		return new EditAccountPageObject(driver);
	}
}
