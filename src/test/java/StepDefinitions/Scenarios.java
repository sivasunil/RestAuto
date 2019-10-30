package StepDefinitions;

import java.util.ArrayList;
import java.util.List;

import com.api.demo.RestApi.DataHandler;
import com.api.demo.RestApi.ExcelDriver;
import com.api.demo.RestApi.GenerateExtentReport;
import com.api.demo.RestApi.GenericMethods;
import com.api.demo.RestApi.RequestBuilder;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class Scenarios extends RequestBuilder {

	@Given("^the endpoint details \"([^\"]*)\" from \"([^\"]*)\" Sheet \"([^\"]*)\"$")
	public void the_endpoint_details_from_Sheet(String TCName, String SheetName, String ScenarioDetails)
			throws Throwable {
		DataHandler.setTestCase(TCName);
		System.out.println("Sheet Name :" + TCName);
		System.out.println("Executed After :" + SheetName);
		ExcelDriver.DatatoHandler("TestData.xls", TCName, SheetName);
		if (errFlag == "SYNTAX ERROR") {
			GenerateExtentReport.TestAssert(true,
					"Given The Initial Setup is done for Test Condition " + TCName + " from " + SheetName);
			GenerateExtentReport.TestAssert(false,
					"Query params : The syxtax is incorrect with mismatch in parameter keys and values");
		} else {
			BuildRequest();
			GenerateExtentReport.StartTest(TCName + " : " + ScenarioDetails);
			GenerateExtentReport.TestAssert(true,
					"Given The Initial Setup is done for Test Condition " + TCName + " from " + SheetName);
			GenerateExtentReport.TestAssert(true,
					"Request URI : " + DataHandler.getBaseuri() + "/" + DataHandler.getPath());
			GenerateExtentReport.TestAssert(true,
					"Query params : " + DataHandler.getQueryParamkey() + "Value :" + DataHandler.getQueryParamvalue());
			GenerateExtentReport.TestAssert(true, "Body : " + DataHandler.getBody());
		}
	}

	@When("^the \"([^\"]*)\" req is Executed$")
	public void the_req_is_Executed(String RequestType) throws Throwable {
		ExecuteRequest();
		GenerateExtentReport.TestAssert(true, "When the " + RequestType + " Request is Executed");
	}

	@Then("^user should get Expected Status code \"([^\"]*)\"$")
	public void user_should_get_Expected_Status_code(int ResponseCode) throws Throwable {
		if (response.getStatusCode() == ResponseCode) {
			GenerateExtentReport.TestAssert(true,
					"Then user is getting Expected Result Status Code: " + response.getStatusCode());
		} else {
			GenerateExtentReport.TestAssert(false,
					"Then user is not getting Expected Result Status Code: " + response.getStatusCode());
			Assert.assertTrue(false);
		}
	}

	@Then("^Verify that no two movies has same image$")
	public void verify_that_no_two_movies_has_same_image() throws Throwable {
		Body = response.getBody().asString();
		flag = GenericMethods.verifyImages(Body);
		if (flag) {
			GenerateExtentReport.TestAssert(true, "No Duplicate images");
		} else {
			GenerateExtentReport.TestAssert(false, "There are duplicate images");
			Assert.assertTrue(false);
		}
	}

	@Then("^Verify whether the poster paths are valid$")
	public void verify_whether_the_poster_paths_are_valid() throws Throwable {
		List<String> AL = new ArrayList<String>();
		Body = response.getBody().asString();
		AL = GenericMethods.VerifyPaths(Body);
		if (AL.isEmpty()) {
			GenerateExtentReport.TestAssert(true, "All poster paths are valids");
		} else {
			GenerateExtentReport.TestAssert(false, "Movies which have invaid poster_paths are " + AL);
			Assert.assertTrue(false);
		}
	}

	@Then("^Verify whether the movies with sum of genere IDs greater than (\\d+) not more than (\\d+)$")
	public void verifyWhetherTheMoviesWithSumOfGenereIDsGreaterThanNotMoreThan(int arg1, int arg2) throws Throwable {
		flag = GenericMethods.VerifyGenereSum(Body);
		if (flag) {
			GenerateExtentReport.TestAssert(true, "sum of GenereIDs is 400 for not more than 7 movies");
		} else {
			GenerateExtentReport.TestAssert(false, "sum of GenereIDs is 400 for more than 7 movies");
			Assert.assertTrue(false);
		}

	}

	@Then("^Verify whether the movies sorted correctly$")
	public void verifyWhetherTheMoviesSortedCorrectly() throws Throwable {

		flag = GenericMethods.VerifySortlogic(Body);
		if (flag) {
			GenerateExtentReport.TestAssert(true, "movies are sorted properly");
		} else {
			GenerateExtentReport.TestAssert(false, "movies are not sorted correctly");
			Assert.assertTrue(false);
		}
	}

	@Then("^Verify at leaset one movie has a palindrome$")
	public void verifyAtLeasetOneMovieHasAPalindrome() throws Throwable {
		flag = GenericMethods.verifyPalindrome(Body);
		if (flag) {
			GenerateExtentReport.TestAssert(true, "At least on movie has a palindrome");
		} else {
			GenerateExtentReport.TestAssert(false, "no movie title has palindrome");
			Assert.assertTrue(false);
		}
	}

	@Then("^Verify at leaset two titles are subset of the other$")
	public void verifyAtLeasetTwoTitlesAreSubsetOfTheOther() throws Throwable {
		flag = GenericMethods.verifysubString(Body);
		if (flag) {
			GenerateExtentReport.TestAssert(true, "At least two titles are subset of the other");
		} else {
			GenerateExtentReport.TestAssert(false, "no movie title is subset of the other");
			Assert.assertTrue(false);
		}
	}

}
