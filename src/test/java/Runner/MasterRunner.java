package Runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.sample.cucumber.annotations.AfterSuite;
import org.sample.cucumber.annotations.BeforeSuite;

import com.api.demo.RestApi.FilePath;
import com.api.demo.RestApi.GenerateExtentReport;
import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


import com.api.demo.RestApi.ExtendedCucumberRunner;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;




@RunWith(ExtendedCucumberRunner.class)
@CucumberOptions(features={"src/test/java/Features"},glue={"StepDefinitions"},monochrome=true,tags={"@Sanity"},plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"})



public class MasterRunner {
	
	@BeforeSuite
	public static void report()
	{
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@I am Before Suite@@@@@@@@@@@@@@@@@@@@@@@@");
		GenerateExtentReport.startReport();
	}
	
	@AfterSuite
	public static void tearDown() {
		System.out.println("Executed AfterSuite");
		GenerateExtentReport.ReportFlush();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@I am End@@@@@@@@@@@@@@@@@@@@@@@@");
		//Reporter.loadXMLConfig(FilePath.ExtentConfigPath());
		//Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
	}
}
