package com.bankguru.customer;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import pageObject.navigation.PageGenerator;
import pageObject.user.RegisterPageObject;
import reportConfigs.ExtentManager;


//mngr454116/bAgared
public class RegisterPage extends BaseTest {
	WebDriver driver;
	RegisterPageObject registerPage;
	static String username;
	static String password;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		registerPage = PageGenerator.getRegisterPage(driver);
	}

	@Test
	public void registerWithValidEmail(Method method) {
		ExtentManager.startTest(method.getName(), "Get info to sign in ");
		
		registerPage.inputToTextboxByName(driver, "mentorqa7@gmail.com", "emailid");
		registerPage.clickToButtonByType(driver, "submit");
  
		Assert.assertTrue(registerPage.isSuccessPageDisplayed(driver));
		
		username = registerPage.getUserName(driver);
		password = registerPage.getPassword(driver);
	}
     
	
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
