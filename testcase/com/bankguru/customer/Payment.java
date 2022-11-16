package com.bankguru.customer;

import java.lang.reflect.Method;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObject.navigation.PageGenerator;
import pageObject.user.AddNewAccountPageObject;
import pageObject.user.DashBoardPageObject;
import pageObject.user.EditAccountPageObject;
import pageObject.user.EditCustomerPageObject;
import pageObject.user.LoginPageObject;
import pageObject.user.NewCustomerPageObject;
import reportConfigs.ExtentManager;

public class Payment extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashBoardPageObject dashBoardPage;
	NewCustomerPageObject newCustomerPage;
	EditCustomerPageObject editCustomerPage;
	AddNewAccountPageObject addNewAccountPage;
	EditAccountPageObject editNewAccountPage;
	String customerUID;
	String accountUID;

	@Parameters({ "browser", "Url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);

		loginPage = PageGenerator.getLoginPage(driver);
	}

	@Description("Login with valid email and password")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 1)
	public void loginWithValidUsernameAndPassword(Method method) {

		ExtentManager.startTest(method.getName(), "Start Login to Guru Account ");

		ExtentManager.getTest().log(Status.INFO, "Login: Input UID");
		loginPage.inputToTextboxByName(driver, RegisterPage.username, "uid");

		ExtentManager.getTest().log(Status.INFO, "Login: Input Password");
		loginPage.inputToTextboxByName(driver, RegisterPage.password, "password");

		ExtentManager.getTest().log(Status.INFO, "Login: Click submit");
		loginPage.clickToButtonByType(driver, "submit");

		dashBoardPage = PageGenerator.getDashBoardPage(driver);

		ExtentManager.getTest().log(Status.INFO, "Login: Verify login successfully");
		Assert.assertTrue(dashBoardPage.isLoginSuccessIsDisplayed(driver));

	}

	@Description("Create new Customer Success")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void createNewCustomerSuccess(Method method) {
		String email = "mentor.qa7" + randomNumber() + "@gmail.com";
		String customerName = "Automation Testing";
		String birthdate = "04/25/2000";
		String address = "45 Phan Dang Luu";
		String city = "DA NANG";
		String state = "Viet Nam";
		String pin = "240418";
		String phone = "+84986769815";

		ExtentManager.startTest(method.getName(), "Create new customer successfully with valid user info");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Click to New customer link");
		dashBoardPage.clickToMenuButton(driver, "New Customer");
		newCustomerPage = PageGenerator.getNewCustomerPage(driver);
		newCustomerPage.sleepInSecond(1);
		newCustomerPage.refreshCurrentPage(driver);
		newCustomerPage.clickToMenuButton(driver, "New Customer");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Input customer name");
		newCustomerPage.inputToTextboxByName(driver, customerName, "name");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Input birthdate");
		newCustomerPage.inputToTextboxByName(driver, birthdate, "dob");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Input address");
		newCustomerPage.inputToTextboxByName(driver, address, "addr");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Input city");
		newCustomerPage.inputToTextboxByName(driver, city, "city");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Input state");
		newCustomerPage.inputToTextboxByName(driver, state, "state");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Input pin");
		newCustomerPage.inputToTextboxByName(driver, pin, "pinno");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Input phone");
		newCustomerPage.inputToTextboxByName(driver, phone, "telephoneno");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Input Email");
		newCustomerPage.inputToTextboxByName(driver, email, "emailid");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Input password");
		newCustomerPage.inputToTextboxByName(driver, "Estine123#", "password");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Click submit button");
		newCustomerPage.clickToButtonByType(driver, "submit");

		Assert.assertEquals(newCustomerPage.getSuccessMessage(driver, "Customer Registered Successfully!!!"), "Customer Registered Successfully!!!");
		Assert.assertEquals(newCustomerPage.getCustomerInformation(driver, "Customer Name"), customerName);
		Assert.assertEquals(newCustomerPage.getCustomerInformation(driver, "Birthdate"), "2000-04-25");
		Assert.assertEquals(newCustomerPage.getCustomerInformation(driver, "Address"), address);
		Assert.assertEquals(newCustomerPage.getCustomerInformation(driver, "City"), city);
		Assert.assertEquals(newCustomerPage.getCustomerInformation(driver, "State"), state);
		Assert.assertEquals(newCustomerPage.getCustomerInformation(driver, "Pin"), pin);
		Assert.assertEquals(newCustomerPage.getCustomerInformation(driver, "Mobile No."), phone);
		Assert.assertEquals(newCustomerPage.getCustomerInformation(driver, "Email"), email);

	}

	@Description("Edit customer")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void editCustomerSuccess(Method method) {
		String newAddress = "45 Phan Dang Luu, TP HA NOI";
		String newCity = "HA NOI";
		String newState = "CAMBODIA";
		String newPin = "24052000";
		ExtentManager.startTest(method.getName(), "Edit customer successfully with valid new customer info");

		customerUID = newCustomerPage.getCustomerUID(driver, "Customer ID");

		newCustomerPage.clickToMenuButton(driver, "Edit Customer");
		editCustomerPage = PageGenerator.getEditCustomerPage(driver);

		ExtentManager.getTest().log(Status.INFO, "Edit Customer: Input Customer ID");
		editCustomerPage.inputToTextboxByName(driver, customerUID, "cusid");

		ExtentManager.getTest().log(Status.INFO, "Edit Customer: Click Submit button");
		editCustomerPage.clickToButtonByType(driver, "submit");

		ExtentManager.getTest().log(Status.INFO, "Edit Customer: Input address");
		editCustomerPage.inputToTextboxByName(driver, newAddress, "addr");

		ExtentManager.getTest().log(Status.INFO, "Edit Customer: Input city");
		editCustomerPage.inputToTextboxByName(driver, newCity, "city");

		ExtentManager.getTest().log(Status.INFO, "Edit Customer: Input state");
		editCustomerPage.inputToTextboxByName(driver, newState, "state");

		ExtentManager.getTest().log(Status.INFO, "Edit Customer: Input pin");
		editCustomerPage.inputToTextboxByName(driver, newPin, "pinno");

		ExtentManager.getTest().log(Status.INFO, "Edit Customer: Click submit button");
		newCustomerPage.clickToButtonByType(driver, "submit");

		Assert.assertEquals(editCustomerPage.getCustomerInformation(driver, "Address"), newAddress);
		Assert.assertEquals(editCustomerPage.getCustomerInformation(driver, "City"), newCity);
		Assert.assertEquals(editCustomerPage.getCustomerInformation(driver, "State"), newState);
		Assert.assertEquals(editCustomerPage.getCustomerInformation(driver, "Pin"), newPin);
	}

	@Description("Add new Account")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void addNewAccountSuccess(Method method) {
		String initialDeposit = "50000";

		ExtentManager.startTest(method.getName(), "Add new account successfully with valid account info");

		editCustomerPage.clickToMenuButton(driver, "New Account");
		addNewAccountPage = PageGenerator.getNewAccountPage(driver);

		addNewAccountPage.inputToTextboxByName(driver, customerUID, "cusid");

		addNewAccountPage.slectAccountType(driver, "Savings");

		addNewAccountPage.inputToTextboxByName(driver, initialDeposit, "inideposit");

		addNewAccountPage.clickToButtonByType(driver, "submit");

		accountUID = addNewAccountPage.getAccountUID(driver,"Account ID");
		Assert.assertEquals(newCustomerPage.getSuccessMessage(driver, "Account Generated Successfully!!!"), "Account Generated Successfully!!!");
		Assert.assertEquals(addNewAccountPage.getCustomerInformation(driver, "Current Amount"), initialDeposit);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
}
