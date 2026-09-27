package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;




public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext) {
	
	String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
	 
	repName = "Test-Report-" + timestamp + ".html";
	sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report
	
	sparkReporter.config().setDocumentTitle("RestAPI Automation Report"); // Title of report
	sparkReporter.config().setReportName("REstAPI Functional Testing"); // name of the report
	sparkReporter.config().setTheme(Theme.DARK);
	 
	extent = new ExtentReports();
	extent.attachReporter(sparkReporter);
	extent.setSystemInfo("Application", "petStore");
	extent.setSystemInfo("Module", "Admin");
	extent.setSystemInfo("Sub Module", "Customers");
	extent.setSystemInfo("User Name", System.getProperty("user.name"));
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("user", "amruta");
	 
	
	}
	
	 
	public void onTestSuccess(ITestResult result) {
	 
	   test = extent.createTest(result.getTestClass().getName());
	   test.assignCategory(result.getMethod().getGroups()); // to display groups in report
	   test.createNode(result.getName());
	   test.log(Status.PASS, result.getName()+" got successfully executed");
	 
	}
	 
	public void onTestFailure(ITestResult result) {
	   test = extent.createTest(result.getTestClass().getName());
	   test.createNode(result.getName());
	   test.assignCategory(result.getMethod().getGroups());
	   test.log(Status.FAIL, result.getName()+" got failed");
	   test.log(Status.INFO, result.getThrowable().getMessage());
	 
	   
	}
	 
	public void onTestSkipped(ITestResult result) {
	   test = extent.createTest(result.getTestClass().getName());
	   test.createNode(result.getName());
	   test.assignCategory(result.getMethod().getGroups());
	   test.log(Status.SKIP, result.getName()+" got skipped");
	   test.log(Status.INFO, result.getThrowable().getMessage());
	}
	 
	public void onFinish(ITestContext testContext) {
	 
	   extent.flush();
	}
}
