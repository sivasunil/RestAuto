package StepDefinitions;

import java.util.List;
import java.util.Map;

import cucumber.api.DataTable;

import com.api.demo.RestApi.*;
import com.github.mkolisnyk.cucumber.runner.BeforeSuite;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class Builder extends RequestBuilder {
  
/*	@Before("@FinalReport")
	public void setUp() {
		// build the request
		System.out.println("***************************** Before Starting *************************************");
		GenerateExtentReport.startReport();
		// ExcelDriver.Datatoarray("C:\\TestData.xlsx", "L1", "Home");
	}*/
	
	@Given("^the Initial Setup is done for Test Condition \"([^\"]*)\" from \"([^\"]*)\" Sheet \"([^\"]*)\"$")
	public void the_Initial_Setup_is_done_for_Test_Condition_from_Sheet(String TCName, String SheetName,
			String ScenarioDetails) throws Throwable {
		DataHandler.setTestCase(TCName);
		System.out.println("Sheet Name :" + TCName);
		System.out.println("Executed After :" + SheetName);
		ExcelDriver.DatatoHandler("TestData.xls", TCName, SheetName);
		if (errFlag == "SYNTAX ERROR") {
			GenerateExtentReport.StartTest(TCName + " : " + ScenarioDetails);
			// GenerateExtentReport.TestAssert(true,"The Request URL is :
			// "+requestSpec.log().all());
			GenerateExtentReport.TestAssert(true,
					"Given The Initial Setup is done for Test Condition " + TCName + " from " + SheetName);
			GenerateExtentReport.TestAssert(false,
					"Query params : The syxtax is incorrect with mismatch in parameter keys and values");
		} else {
			BuildRequest();
			GenerateExtentReport.StartTest(TCName + " : " + ScenarioDetails);
			// GenerateExtentReport.TestAssert(true,"The Request URL is :
			// "+requestSpec.log().all());
			GenerateExtentReport.TestAssert(true,
					"Given The Initial Setup is done for Test Condition " + TCName + " from " + SheetName);
			GenerateExtentReport.TestAssert(true,
					"Request URI : " + DataHandler.getBaseuri() + "/" + DataHandler.getPath());
			GenerateExtentReport.TestAssert(true,
					"Query params : " + DataHandler.getQueryParamkey() + "Value :" + DataHandler.getQueryParamvalue());
			GenerateExtentReport.TestAssert(true, "Body : " + DataHandler.getBody());
		}
	}

	// generic method for Get, POST, PUT and Delete
	@When("^the \"([^\"]*)\" Request is Executed$")
	public void the_Get_Request_is_Executed(String RequestType) throws Throwable {
		ExecuteRequest();
		GenerateExtentReport.TestAssert(true, "When the " + RequestType + " Request is Executed");
	}

	@Then("^user should get Expected Status code \"([^\"]*)\" and Response should match with \"([^\"]*)\"$")
	public void user_should_get_Expected_Status_code_and_Response_should_match_with(int ResponseCode,
			String ResponseStatusLine) throws Throwable {

		// Assert.assertEquals(response.getStatusCode(), 200);
		if (response.getStatusCode() == ResponseCode) {
				GenerateExtentReport.TestAssert(true, "Then user is getting Expected Result Status Code: "
						+ response.getStatusCode());
		} else
		{
			GenerateExtentReport.TestAssert(false,
					"Then Response status code is not as Expected : Expected Status Code : 200 Actual Status Code :"
							+ response.getStatusCode());
		Assert.assertTrue(false);
		}
	}

/*	@Then("^Expected Data in Response should match$")
	public void expected_Data_in_Response_should_match(Map<String, String> table) throws Throwable {
		// Initialize data table
		int i=0;
		for (String key : table.keySet()) {
			if(i>0) {
			if (jsonPath.get(key).equals(table.get(key))) {
				GenerateExtentReport.TestAssert(true, "The actual result value"+ jsonPath.get(key)+ " for field "+key+" is same as expected ");
			}
			else
			{
				GenerateExtentReport.TestAssert(false,"The actual result value in response does not match with the expected");
				Assert.assertTrue(false);
			}
			}
			i++;
		}
	}*/

}
