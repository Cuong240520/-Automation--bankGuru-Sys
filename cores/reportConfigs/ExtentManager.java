package reportConfigs;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import commons.GlobalConstants;

public class ExtentManager {
	public static final ExtentReports extentReports = new ExtentReports();
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = ExtentManager.createExtentReports();
	

	public synchronized static ExtentReports createExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstants.PROJECT_PATH + "/extentReport/Extent_Result.html");
		reporter.config().setReportName("TechPanda HTML Report");
		reporter.config().setDocumentTitle("TechPanda HTML Report");
		reporter.config().setTimelineEnabled(true);
		reporter.config().setEncoding("utf-8");
		reporter.config().setTheme(Theme.DARK);

		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Company", "Automation FC");
		extentReports.setSystemInfo("Project", "Bank Guru");
		extentReports.setSystemInfo("Team", "Bank Sys");
		extentReports.setSystemInfo("JDK version", GlobalConstants.JAVA_VERSION);
		return extentReports;
	}
	
	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((int) Thread.currentThread().getId());
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = extent.createTest(testName, desc);
		extentTestMap.put((int) Thread.currentThread().getId(), test);
		return test;
	}
}