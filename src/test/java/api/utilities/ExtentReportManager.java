package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager extends TestListenerAdapter
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest etest;
	public String repName;
		
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
		
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/"+repName);//specify location of the report
		
		sparkReporter.config().setDocumentTitle("InetBanking Test Project"); // Tile of report
		sparkReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
	extent=new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","Pet Store User API");
		extent.setSystemInfo("Operating System",System.getProperty("os.name"));
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","pavan");
	}
	
	public void onTestSuccess(ITestResult result)
	{
		etest=extent.createTest(result.getName());
		etest.assignCategory(result.getMethod().getGroups());
		etest.createNode(result.getName());
		etest.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		etest=extent.createTest(result.getName());
		etest.assignCategory(result.getMethod().getGroups());
		etest.createNode(result.getName());
		etest.log(Status.FAIL,"Test Failed");
		etest.log(Status.FAIL,result.getThrowable().getMessage());
				
	}
	
	public void onTestSkipped(ITestResult result)
	{
		etest=extent.createTest(result.getName());
		etest.assignCategory(result.getMethod().getGroups());
		etest.createNode(result.getName());
		etest.log(Status.SKIP,"Test Skipped");
		etest.log(Status.SKIP,result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}

