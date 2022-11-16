package pageUI.user;

public class BasePageUI {
	public static final String INPUT_TEXTBOX_BY_NAME = "name=%s";
    public static final String CLICK_ON_BUTTON_BY_TYPE = "xpath=//input[@type='%s']";
    public static final String CLICK_TO_TEXTBOX_BY_NAME = "xpath=//input[@name='%s']";
    public static final String MENU_BUTTON = "xpath=//a[text()='%s']";
    public static final String GET_VALUE_TABLE = "xpath=//td[text()='%s']/following-sibling::td";
    public static final String GET_SUCCESS_MESSAGE = "xpath=//p[text()='%s']";
    public static final String GET_ERROR_MESSAGE = "xpath=//input[@name='%s']/following-sibling::label";
    public static final String GET_FIELD_LABEL = "xpath=//input[@name='%s']/parent::td/preceding-sibling::td";
    
    
}
