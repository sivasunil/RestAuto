package com.api.demo.RestApi;

import java.io.File;



import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class GenerateExtentReport extends RequestBuilder {
	public static ExtentReports extent;
	public static ExtentTest test;

	public static void startReport() {
		extent = new ExtentReports(System.getProperty("user.dir")
				+ "/test-output/MyReport.html", true);
		extent.addSystemInfo("HostName", "Vamsi")
				.addSystemInfo("Environment", "QA")
				.addSystemInfo("UserName", "Vamsi");
		extent.loadConfig(new File(System.getProperty("user.dir")
				+ "/extent-config.xml"));
	}

	public static void StartTest(String TestName) {
		test = extent.startTest(TestName);

	}

	public static void TestAssert(boolean flag, String logMessage) {
		if (flag) {
			test.log(LogStatus.PASS, logMessage);
		} else {
			test.log(LogStatus.FAIL, logMessage);
		}
		extent.flush();
	}
	
	public static void ReportFlush(){
		extent.flush();
	}
	/*
	 * @Test public void demoFailResult(){
	 * test=extent.startTest("DemoFaileport"); Assert.assertTrue(false);
	 * test.log(LogStatus.FAIL, "Assert Pass as condition is False"); }
	 * 
	 * @AfterMethod public void getResult(ITestResult result){ if
	 * (result.getStatus()==ITestResult.FAILURE){ test.log
	 * (LogStatus.FAIL,result.getThrowable()); } extent.endTest(test);
	 * 
	 * }
	 * 
	 * @AfterTest public void endReport(){ extent.flush(); extent.close(); }
	 */
}
