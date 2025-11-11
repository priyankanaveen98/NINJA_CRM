package genericutilities;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener,ISuiteListener{

	ExtentReports report;
	ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		JavaUtility jLib=new JavaUtility();
		ExtentSparkReporter spark = new ExtentSparkReporter("./ExtentReports/report_"+jLib.getCurrentDateAndTime()+".html");
		spark.config().setDocumentTitle("CRM Reports");
		spark.config().setReportName("NINJA CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("Browser", "Chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		//System.out.println("Report Backup");	
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
	//	System.out.println(testCaseName+" execution Started");
		test=report.createTest(testCaseName);
		test.log(Status.INFO,testCaseName+"  execution Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
		//System.out.println(testCaseName+" Execution Success");
		test.log(Status.PASS, testCaseName+" Execution Success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
	//	JavaUtility  jLib=new JavaUtility();
		//System.out.println(testCaseName+" Execution Failed");
		test.log(Status.FAIL, testCaseName+" Execution Failed");
		TakesScreenshot ts=( TakesScreenshot)BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src);
//		File dest=new File("./Errorshots/"+testCaseName+"_"+jLib.getCurrentDateAndTime()+".png");
//		try {
//			FileHandler.copy(src, dest);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
		//System.out.println(testCaseName+" Execution Skipped");
		test.log(Status.SKIP, testCaseName+" Execution Skipped");
	}
}
