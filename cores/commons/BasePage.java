//CREATE HÀM 
package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import pageUI.user.BasePageUI;
public class BasePage {
	/* Web Browser */
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);

	}
	/**
	 * Get Cookie
	 */
	public Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	/**
	 * Set Cookie 
	 */
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);	
		}
		sleepInSecond(2);
		refreshCurrentPage(driver);
	}
	

	/**
	 * Get current Url
	 * @param driver
	 */
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	/**
	 * Get page title 
	 */
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();

	}
	/**
	 * Get page source
	 */
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	/**
	 * Back to page
	 */
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	/**
	 * Forward to page
	 */
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	/**
	 * Forward to page
	 */
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	/**
	 * Wait for alert presence
	 */
	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.alertIsPresent());
	}
	/**
	 * Accept alert
	 */
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	/**
	 * Cancel Alert
	 */
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	/**
	 * Send key to alert
	 */
	public void sendkeyToAlert(WebDriver driver, String valueToSendkey) {
		driver.switchTo().alert().sendKeys(valueToSendkey);
	}
	/**
	 * Get alert text
	 */
	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	/**
	 * Switch to window by ID
	 */
	public void switchToWindownByID(WebDriver driver, String currentWindownID) {
		Set<String> allWindownID = driver.getWindowHandles();
		for (String id : allWindownID) {
			if (!id.equals(currentWindownID)) {
				driver.switchTo().window(id);
			}
		}
	}
	/**
	 * Switch to window by Title
	 */
	public void switchToWindownByTitle(WebDriver driver, String currentTitle) {
		Set<String> allWindownID = driver.getWindowHandles();
		for (String id : allWindownID) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(currentTitle)) {
				break;
			}
		}
	}
	/**
	 * Switch to window by Link
	 */
	public void switchToWindownByLink(WebDriver driver, String currentContainsLink) {
		Set<String> allWindownID = driver.getWindowHandles();

		for (String id : allWindownID) {
			driver.switchTo().window(id);

			String actualTitle = driver.getTitle();
			if (actualTitle.contains(currentContainsLink)) {
				break;
			}
		}
	}
	/**
	 * Close all window without Parent
	 */
	public boolean closeAllWindownWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindownID = driver.getWindowHandles();
		for (String runWindown : allWindownID) {
			if (!runWindown.equals(parentID)) {
				driver.switchTo().window(runWindown);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	//=======================//==========================//

	/**
	 * Get by locator
	 */
	private By getByLocator(String locator) {
		By by = null;
		if (locator.startsWith("id=") || locator.startsWith("ID") || locator.startsWith("Id")) {
			by = By.id(locator.substring(3));
		} else if (locator.startsWith("Class=") || locator.startsWith("class=") || locator.startsWith("CLASS=")) {
			by = By.className(locator.substring(6));
		} else if (locator.startsWith("Name=") || locator.startsWith("name=") || locator.startsWith("NAME=")) {
			by = By.name(locator.substring(5));
		} else if (locator.startsWith("Xpath=") || locator.startsWith("xpath") || locator.startsWith("XPATH")) {
			by = By.xpath(locator.substring(6));
		} else if (locator.startsWith("css=") || locator.startsWith("CSS") || locator.startsWith("Css")) {
			by = By.cssSelector(locator.substring(4));
		} else {
			throw new RuntimeException("Locator is not valid");
		}
		return by;
	}
	/**
	 * Get web Element
	 */
	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}
	/**
	 * Dynamic method
	 */
	public String castRestParameter(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return locator;
	}
	/**
	 * Click to Element
	 */
	public void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}

	/**
	 * Click to Element applied dynamic locator
	 */
	public void clickToElement(WebDriver driver, String locator, String... dynamicLocator) {
		getWebElement(driver, castRestParameter(locator, dynamicLocator)).click();
	}
	/**
	 * Send key to element
	 */
	public void sendkeyToElement(WebDriver driver, String locator, String sendkeyValue) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(sendkeyValue);
	}

	/**
	 * Send key to element applied dynamic locator
	 */
	public void sendkeyToElement(WebDriver driver, String locator, String sendkeyValue, String dynamicLocator) {
		WebElement element = getWebElement(driver, castRestParameter(locator, dynamicLocator));
		element.clear();
		element.sendKeys(sendkeyValue);
	}
	/**
	 * Send key to element applied dynamic locator
	 */
	public List<WebElement> getListElement(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}
	/**
	 * Select default dropdown 
	 */
	public void selectItemDefaultDropdow(WebDriver driver, String locator, String itemText) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemText);
	}
	/**
	 * Select default dropdown applied dynamic locator 
	 */
	public void selectItemDefaultDropdow(WebDriver driver, String locator, String itemText ,String... dynamicLocator ) {
		Select select = new Select(getWebElement(driver, castRestParameter(locator, dynamicLocator)));
		select.selectByVisibleText(itemText);
	}
	/**
	 * Get first select text item
	 */
	public String getFirstSelectTextItem(WebDriver driver, String Locator) {
		Select select = new Select(getWebElement(driver, Locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String Locator) {
		Select select = new Select(getWebElement(driver, Locator));
		return select.isMultiple();
	}
	/**
	 * Select item in custom dropdown list
	 */
	public void selectItemInCustomDropdownList(WebDriver driver, String parentLocator, String childXpathLocator, String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(2);
		List<WebElement> childItem = new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childXpathLocator)));
		for (WebElement tempElement : childItem) {
			if (tempElement.getText().trim().equals(expectedItem)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
				sleepInSecond(2);
				tempElement.click();
				break;
			}
		}
	}
	/**
	 * Get text Element
	 */
	public String getTextElement(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}
	/**
	 * Get text Element applied dynamic locator
	 */
	public String getTextElement(WebDriver driver, String Locator, String...dynamicLocator) {
		return getWebElement(driver, castRestParameter(Locator, dynamicLocator)).getText();
	}
	/**
	 * Get text Element attribute value
	 */
	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	/**
	 * Get css value
	 */
	public String getCssValue(WebDriver driver, String Locator, String propertyName) {
		return getWebElement(driver, Locator).getAttribute(propertyName);
	}
	/**
	 * Get list element size
	 */
	public int getListElementSize(WebDriver driver, String Locator) {
		return getListElement(driver, Locator).size();
	}
	/**
	 * Check to check box or radio button
	 */
	public void checkToCheckBoxOrRadio(WebDriver driver, String Locator) {
		WebElement element = getWebElement(driver, Locator);
		if (!element.isDisplayed()) {
			element.click();
			;
		}
	}
	/**
	 * Uncheck to check box or radio button
	 */
	public void unCheckToCheckBoxOrRadio(WebDriver driver, String Locator) {
		WebElement element = getWebElement(driver, Locator);
		if (!element.isSelected()) {
			element.click();
		}
	}
	/**
	 * Is Element enable
	 */
	public boolean isElementEnable(WebDriver driver, String Locator) {
		return getWebElement(driver, Locator).isEnabled();
	}
	/**
	 * Element in DoM
	 * Invisible in DOM
	 */
	public boolean isElementDisplayed(WebDriver driver, String Locator) {
		return getWebElement(driver, Locator).isDisplayed();
	}
	/**
	 * Element in DoM
	 * Invisible in DOM
	 */
	public boolean isElementDisplayed(WebDriver driver, String Locator, String... dinamicLocator) {
		return getWebElement(driver, castRestParameter(Locator, dinamicLocator)).isDisplayed();
	}
	/**
	 * Element invisible in DOM (2.1)
	 * Element in visible not in DOM (2.2)
	 */
	public boolean isElementUnDisplayed(WebDriver driver, String Locator) {
		setImplicitTime(driver,shortTimeout);
		List<WebElement> elements = getListElement(driver, Locator);
		setImplicitTime(driver,longTimeOut);
		if (elements.size() == 0) {//(2.2)
			System.out.println("Element not in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {//(2.1)
			System.out.println("Element in DOM but not available/displayed");
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}
	/**
	 * Set implicit wait time
	 * Case use Element in visible not in DOM
	 */
	public void setImplicitTime(WebDriver driver, long timeInSencond) {
		driver.manage().timeouts().implicitlyWait(timeInSencond, TimeUnit.SECONDS);
	}
	/**
	 * Check element selected
	 */
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}
	/**
	 * Switch to Iframe 
	 */
	public void switchToIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));

	}
	/**
	 * Hover Mouse to Element
	 */
	public void hoverMouseToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}
	/**
	 * Right click to element
	 */
	public void rightClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.contextClick(getWebElement(driver, locator)).perform();
	}
	/**
	 * Double click to element
	 */
	public void doubleClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(driver, locator)).perform();

	}
	/**
	 * Drag and Drop element
	 */
	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		Actions action = new Actions(driver);
		action.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
	}
	/**
	 * Press key to Element
	 */
	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}
	/**
	 * Press key to Element dynamic
	 */
	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String dynamicLocator) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, castRestParameter(locator, dynamicLocator)), key).perform();
	}

	/* ==============JavaScript======================== */
	/**
	 * Highlight to Element
	 */
	public void hightlightElement(WebDriver driver, String sourceLocator, String locator) {
		WebElement element = getWebElement(driver, sourceLocator);
		String originalStyle = element.getAttribute("style");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}
	
	/**
	 * Click to Element by JS
	 */
	public void clickToElementByJS(WebDriver driver, String sourceLocator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, sourceLocator));
	}
	/**
	 * Scroll to element on top
	 */
	public void scrollToElementOnTop(WebDriver driver, String sourceLocator, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, sourceLocator));
	}
	/**
	 * Scroll to element on down
	 */
	public void scrollToElementOnDown(WebDriver driver, String sourceLocator, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, sourceLocator));
	}
	/**
	 * Send key to elemnt by JS
	 */
	public void sendkeyToElementByJSWebDriver(WebDriver driver, String sourceLocator, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, sourceLocator));
	}
	/**
	 * Remove attribute in DOM
	 */
	public void removeAttributeInDOM(WebDriver driver, String sourceLocator, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, sourceLocator));
	}
	/**
	 * Get element validation mss
	 */
	public String getElementValidationMessage(WebDriver driver, String sourceLocator, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, sourceLocator));
	}
	/**
	 * Image loaded
	 */
	public boolean isImageLoaded(WebDriver driver, String sourceLocator, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, sourceLocator));
		if (status) {
			return true;
		}
		return false;
	}

	/* ===========================Wait================================ */
	/**
	 * Wait for element Visible
	 */
	public void waitForElementVisible(WebDriver driver, String sourceLocator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(sourceLocator)));
	}

	/**
	 * Wait for element dynamic
	 */
	public void waitForElementVisible(WebDriver driver, String locator, String... dynamicLocator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(castRestParameter(locator, dynamicLocator))));
	}
	/**
	 * Wait for element invisible
	 */
	public void waitForElementInVisibleInDOM(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}
	/**
	 * Wait for element invisible dynamic
	 */
	public void waitForElementInVisibleInDOM(WebDriver driver,String locator, String... dynamicLocator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castRestParameter(locator, dynamicLocator))));
	}
	/**
	 * Wait for element invisible not in DOM
	 */
	public void waitForElementInVisibleNotInDOM(WebDriver driver, String sourceLocator) {
		setImplicitTime(driver, shortTimeout);
		new WebDriverWait(driver, shortTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(sourceLocator)));
		setImplicitTime(driver, longTimeOut);
		}
	/**
	 * Wait for element clickable
	 */
	public void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}
	/**
	 * Wait for element clickable dynamic
	 */
	public void waitForElementClickable(WebDriver driver, String locator, String... dynamicLocato) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.elementToBeClickable(getByLocator(castRestParameter(locator, dynamicLocato))));
	}
	/**
	 * Wait for element clickable
	 */
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.elementToBeClickable(element));
	}
	/*============= Common function for web component============================*/
	/**
	 * Enter text to any textboxs by name with dynamic locator
	 * @param sendkeyValue is a text and dynamicLocator is a dynamic locator
	 */
	@Step("Input to textbox: {2}")
	public void inputToTextboxByName(WebDriver driver, String sendkeyValue , String dynamicLocator) {
		waitForElementVisible(driver, BasePageUI.INPUT_TEXTBOX_BY_NAME, dynamicLocator);
		sendkeyToElement(driver, BasePageUI.INPUT_TEXTBOX_BY_NAME, sendkeyValue, dynamicLocator);
	}
	
	@Step("Click to button : {1}")
	public void clickToButtonByType(WebDriver driver, String dynamicLocator) {
		waitForElementVisible(driver, BasePageUI.CLICK_ON_BUTTON_BY_TYPE, dynamicLocator);
		clickToElement(driver, BasePageUI.CLICK_ON_BUTTON_BY_TYPE, dynamicLocator);
	}
	
	@Step("Click to textbox : {1}")
	public void clickToTextboxByName(WebDriver driver, String dynamicLocator) {
		waitForElementVisible(driver, BasePageUI.CLICK_TO_TEXTBOX_BY_NAME, dynamicLocator);
		clickToElement(driver, BasePageUI.CLICK_TO_TEXTBOX_BY_NAME, dynamicLocator);
	}

	@Step("Click to button in left menu: {1}")
	public void clickToMenuButton(WebDriver driver, String buttonMenuText) {
		waitForElementVisible(driver, BasePageUI.MENU_BUTTON,buttonMenuText);
		clickToElement(driver, BasePageUI.MENU_BUTTON,buttonMenuText);
	}

	public String getCustomerInformation(WebDriver driver, String infoValue) {
		waitForElementVisible(driver, BasePageUI.GET_VALUE_TABLE,infoValue);
		return getTextElement(driver, BasePageUI.GET_VALUE_TABLE,infoValue);
	}
	public String getSuccessMessage(WebDriver driver, String dynamicLocator) {
		waitForElementVisible(driver, BasePageUI.GET_SUCCESS_MESSAGE,dynamicLocator);
		return getTextElement(driver, BasePageUI.GET_SUCCESS_MESSAGE,dynamicLocator);
	}
	public String getErrorMessage(WebDriver driver, String dynamicLocator) {
		waitForElementVisible(driver, BasePageUI.GET_ERROR_MESSAGE,dynamicLocator);
		return getTextElement(driver, BasePageUI.GET_ERROR_MESSAGE,dynamicLocator);
	}
	
	private long longTimeOut = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SORT_TIMEOUT;
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
