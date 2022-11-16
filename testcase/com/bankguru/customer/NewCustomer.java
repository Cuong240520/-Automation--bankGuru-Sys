package com.bankguru.customer;

import java.lang.reflect.Method;
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

public class NewCustomer extends BaseTest {
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
	public void TC_01_LoginWithValidUsernameAndPassword(Method method) {

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

	@Description("Verify that the error message is displayed when the name field  is filled invalid")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void TC_02_VerifyNameField(Method method) {

		ExtentManager.startTest(method.getName(), "Verify that the error message is displayed when the name field  is filled invalid");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Click to New customer link");
		dashBoardPage.clickToMenuButton(driver, "New Customer");
		newCustomerPage = PageGenerator.getNewCustomerPage(driver);
		newCustomerPage.sleepInSecond(1);
		newCustomerPage.refreshCurrentPage(driver);
		newCustomerPage.clickToMenuButton(driver, "New Customer");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Click to name filed ");
		newCustomerPage.clickToTextboxByName(driver, "name");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Leave the name field blank and move to the next field");
		newCustomerPage.clickToTextboxByName(driver, "dob");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Customer name must not be blank' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "name"), "Customer name must not be blank");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter numeric to name field ");
		newCustomerPage.inputToTextboxByName(driver, "name123", "name");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Numbers are not allowed' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "name"), "Numbers are not allowed");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter special characters to name field ");
		newCustomerPage.inputToTextboxByName(driver, "^%####$^name", "name");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Special characters are not allowed' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "name"), "Special characters are not allowed");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter first character as blank space to name field ");
		newCustomerPage.inputToTextboxByName(driver, "     deantran", "name");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'First character can not have space' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "name"), "First character can not have space");

	}

	@Description("Verify that the error message is displayed when the address field  is filled invalid")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void TC_03_VerifyAddressField(Method method) {

		ExtentManager.startTest(method.getName(), "Verify that the error message is displayed when the address field  is filled invalid");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Click to address filed ");
		newCustomerPage.clickToTextboxByName(driver);

		ExtentManager.getTest().log(Status.INFO, "Create customer: Leave the address field blank and move to the next field");
		newCustomerPage.clickToTextboxByName(driver, "city");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Address Field must not be blank' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessageAddressTextarea(driver), "Address Field must not be blank");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter first character as blank space to address field ");
		newCustomerPage.inputToTextboxByName(driver, "     HA NOI");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'First character can not have space' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessageAddressTextarea(driver), "First character can not have space");
	}

	@Description("Verify that the error message is displayed when the city field  is filled invalid")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void TC_04_VerifyCityField(Method method) {

		ExtentManager.startTest(method.getName(), "Verify that the error message is displayed when the city field  is filled invalid");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Click to city filed ");
		newCustomerPage.clickToTextboxByName(driver, "city");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Leave the city field blank and move to the next field");
		newCustomerPage.clickToTextboxByName(driver, "state");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'City Field must not be blank' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "city"), "City Field must not be blank");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter numeric to city field ");
		newCustomerPage.inputToTextboxByName(driver, "123 Nha Trang", "city");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Numbers are not allowed' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "city"), "Numbers are not allowed");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter special characters to name field ");
		newCustomerPage.inputToTextboxByName(driver, "^%####$^Nha Trang", "city");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Special characters are not allowed' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "city"), "Special characters are not allowed");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter first character as blank space to city field ");
		newCustomerPage.inputToTextboxByName(driver, "     Nha Trang", "city");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'First character can not have space' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "city"), "First character can not have space");
	}

	@Description("Verify that the error message is displayed when the state field  is filled invalid")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 5)
	public void TC_05_VerifyStateField(Method method) {

		ExtentManager.startTest(method.getName(), "Verify that the error message is displayed when the state field  is filled invalid");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Click to state filed ");
		newCustomerPage.clickToTextboxByName(driver, "state");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Leave the state field blank and move to the next field");
		newCustomerPage.clickToTextboxByName(driver, "pinno");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'State must not be blank' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "state"), "State must not be blank");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter numeric to state field ");
		newCustomerPage.inputToTextboxByName(driver, "123 State", "state");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Numbers are not allowed' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "state"), "Numbers are not allowed");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter special characters to state field ");
		newCustomerPage.inputToTextboxByName(driver, "^%####$^State", "state");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Special characters are not allowed' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "state"), "Special characters are not allowed");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter first character as blank space to state field ");
		newCustomerPage.inputToTextboxByName(driver, "     Nha Trang", "state");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'First character can not have space' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "state"), "First character can not have space");
	}

	@Description("Verify that the error message is displayed when the state field is filled invalid")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 6)
	public void TC_06_VerifyPINField(Method method) {

		ExtentManager.startTest(method.getName(), "PIN field canot be empty");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Click to PIN filed ");
		newCustomerPage.clickToTextboxByName(driver, "pinno");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Leave the PIN field blank and move to the next field");
		newCustomerPage.clickToTextboxByName(driver, "telephoneno");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'PIN Code must not be blank' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "pinno"), "PIN Code must not be blank");

		ExtentManager.startTest(method.getName(), "PIN cannot have characters");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter characters to PIN field ");
		newCustomerPage.inputToTextboxByName(driver, "123PIN", "pinno");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Characters are not allowed' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "pinno"), "Characters are not allowed");

		ExtentManager.startTest(method.getName(), "PIN cannot have special characters");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter special characters to PIN field ");
		newCustomerPage.inputToTextboxByName(driver, "^%####$^", "pinno");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Special characters are not allowed' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "pinno"), "Special characters are not allowed");

		ExtentManager.startTest(method.getName(), "PIN cannot have first character as blank space");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter first character as blank space to PIN field ");
		newCustomerPage.inputToTextboxByName(driver, "     657777", "pinno");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'First character can not have space' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "pinno"), "First character can not have space");

	}

	@Description("Verify that the error message is displayed when the Telephone field is filled invalid")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 7)
	public void TC_07_VerifyTelephoneField(Method method) {

		ExtentManager.startTest(method.getName(), "Verify that the error message is displayed when the Telephone field is filled invalid");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Click to Telephone filed ");
		newCustomerPage.clickToTextboxByName(driver, "telephoneno");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Leave the Telephone field blank and move to the next field");
		newCustomerPage.clickToTextboxByName(driver, "emailid");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Mobile no must not be blank' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "telephoneno"), "Mobile no must not be blank");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter first character as blank space to Telephone field ");
		newCustomerPage.inputToTextboxByName(driver, "     0986769815", "telephoneno");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'First character can not have space' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "telephoneno"), "First character can not have space");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter blank space to Telephone field ");
		newCustomerPage.inputToTextboxByName(driver, "098766   15", "telephoneno");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Characters are not allowed' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "telephoneno"), "Characters are not allowed");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter special characters to PIN field ");
		newCustomerPage.inputToTextboxByName(driver, "^%####$^85434", "telephoneno");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Special characters are not allowed' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "telephoneno"), "Special characters are not allowed");

	}

	@Description("Verify that the error message is displayed when the email field is filled invalid")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 8)
	public void TC_08_VerifyEmailField(Method method) {

		ExtentManager.startTest(method.getName(), "Verify that the error message is displayed when the email field is filled invalid");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Click to Email filed ");
		newCustomerPage.clickToTextboxByName(driver, "emailid");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Leave the Email field blank and move to the next field");
		newCustomerPage.clickToTextboxByName(driver, "telephoneno");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Email-ID must not be blank' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "emailid"), "Email-ID must not be blank");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter Email-ID is not valid ");
		newCustomerPage.inputToTextboxByName(driver, "teatingvn@", "emailid");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Email-ID is not valid' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "emailid"), "Email-ID is not valid");

		ExtentManager.getTest().log(Status.INFO, "Create customer: Enter blank space in Email field ");
		newCustomerPage.inputToTextboxByName(driver, "testing  automa 1@gmail.com", "emailid");

		ExtentManager.getTest().log(Status.INFO, "Create customer: An error message 'Email-ID is not valid' must shown");
		Assert.assertEquals(newCustomerPage.getErrorMessage(driver, "emailid"), "Email-ID is not valid");

	}

	@Description("Verify that all field label is displayed as requirement")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 9)  
	public void TC_09_VerifyAllFieldLabel(Method method) {

		ExtentManager.startTest(method.getName(), "Verify all field label is displayed correctly");
        
		ExtentManager.getTest().log(Status.INFO, "Create customer: The Customer Name field label is displayed correctly ");
		Assert.assertEquals(newCustomerPage.getFieldLabel(driver, "name"), "Customer Name");
		
		ExtentManager.getTest().log(Status.INFO, "Create customer: The City field label is displayed correctly");
		Assert.assertEquals(newCustomerPage.getFieldLabel(driver, "city"), "City");
		
		ExtentManager.getTest().log(Status.INFO, "Create customer: The State field label is displayed correctly");
		Assert.assertEquals(newCustomerPage.getFieldLabel(driver, "state"), "State");
		
		ExtentManager.getTest().log(Status.INFO, "Create customer: The PIN field label is displayed correctly");
		Assert.assertEquals(newCustomerPage.getFieldLabel(driver, "pinno"), "PIN");
		
		ExtentManager.getTest().log(Status.INFO, "Create customer: The Mobile Number field label is displayed correctly");
		Assert.assertEquals(newCustomerPage.getFieldLabel(driver, "telephoneno"), "Mobile Number");
		
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
