package commons;



public class GlobalConstants {
	/* System Info*/
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	/* Application Info User*/ 
	public static final String LIVE_USER_URL ="https://demo.guru99.com/";
	public static final String LIVE_LOGIN_URL ="https://demo.guru99.com/v4/";
	

	
	/*Wait info*/
	public static final long SORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 15;
	
	/*Retry Case Fail*/
	public static final int RETRY_NUMBER = 3;
	
	/*Browser Logs / Extension*/
	public static final String BROWSER_LOG_PATH = PROJECT_PATH + "/browserLogs/";
	public static final String BROWSER_EXTENTION_PATH = PROJECT_PATH + "/browserExtenstions/";
	
}
