package com.api.demo.RestApi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import com.relevantcodes.extentreports.LogStatus;

public class RequestBuilder {
	public static RequestSpecBuilder requestBuilder = null;
	public static RequestSpecification requestSpec;
	public static Response response;
	public static String[] keys;
	public static String[] keyValues;
	public static String errFlag;
	public static String Body;
	public static boolean flag = false;
	public static JsonPath jsonPath;
	//public static Properties IDConfig = FilesReader.loadxmlProperties(FilePath.ConfigPath("ID.xml"));

	public static void main(String[] args) {
		errFlag = "";
		BuildRequest();
	}

	public static void BuildRequest() {
		QueryParameter();
		QueryParameterValues();
		requestBuilder = new RequestSpecBuilder();
		requestBuilder.setBaseUri(DataHandler.getBaseuri());
		requestBuilder.setBasePath(DataHandler.getPath());

		if(!(DataHandler.getQueryParamkey() == null || DataHandler.getQueryParamvalue() ==null))
		{
		if (!(DataHandler.getQueryParamkey().isEmpty() || DataHandler.getQueryParamvalue().isEmpty())) {
			AddQueryParams(DataHandler.getQueryParamkey(), DataHandler.getQueryParamvalue());
		}
		}
		
		if(!(DataHandler.getHeader() == null || DataHandler.getHeaderVal() ==null))
		{
		if (!(DataHandler.getHeader().isEmpty() || DataHandler.getHeaderVal().isEmpty())) {
			AddHeader(DataHandler.getHeader(), DataHandler.getHeaderVal());
		}
		}
		if (DataHandler.getMethod().equalsIgnoreCase("post") || DataHandler.getMethod().equalsIgnoreCase("put")) {

			requestBuilder.setBody(DataHandler.getBody());
			requestBuilder.addHeader("Content-Type", "application/json");
		}

		requestSpec = requestBuilder.build().log().all();
	}

	public static void ExecuteRequest() {
		response = given().spec(requestSpec).request(DataHandler.getMethod());
		System.out.println(response.statusCode());
		System.out.println(response.body().print());
		Body = response.body().asString();
		jsonPath = new JsonPath(Body);
/*		if (DataHandler.getMethod().equalsIgnoreCase("post")) {
			StoreID();
		}*/
	}

	public static void ValidateResponse(String SchemaFile) {
		String FName = DataHandler.getTestCase() + "_" + "Output.json";
		DataHandler.setOutputFile(FName);
		OutputHandler.downloadResponse(FilePath.OutputFile(FName));
		flag = OutputHandler.compareJson(FilePath.OutputFile(DataHandler.getOutputFile()),
				FilePath.ExpectedFile(SchemaFile));
		/*
		 * if (DataHandler.getMethod().equalsIgnoreCase("get")) { flag =
		 * OutputHandler.compareJson(FilePath.OutputFile(DataHandler.getOutputFile()),
		 * FilePath.ExpectedFile("GETSchema.json")); } else if
		 * (DataHandler.getMethod().equalsIgnoreCase("post")) { flag =
		 * OutputHandler.compareJson(FilePath.OutputFile(DataHandler.getOutputFile()),
		 * FilePath.ExpectedFile("POSTSchema.json")); }
		 */
	}

	// Handling Multiple Parameters
	public static void QueryParameter() {
		String ParamKeys = DataHandler.getQueryParamkey();
		if (ParamKeys.contains("|")) {
			keys = ParamKeys.split("\\|");
		} else
			keys = new String[1];
	}

	// Handling Multiple Parameters
	public static void QueryParameterValues() {
		String ParamKeyValues = DataHandler.getQueryParamvalue();
		if (ParamKeyValues.contains("|")) {
			keyValues = ParamKeyValues.split("\\|");
		} else
			keyValues = new String[1];
	}

	/*public static void StoreID() {
		String id = jsonPath.getString("id");
		try {
			FileOutputStream out = new FileOutputStream(FilePath.ConfigPath("ID.xml"));
			IDConfig.setProperty(DataHandler.getTestCase(), id);
			IDConfig.storeToXML(out, null);
			System.out.println();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	public static void AddQueryParams(String Param, String Value) {
		if (!(Param.isEmpty() || Value.isEmpty())) {
			if (Param.contains("\\|") && Value.contains("\\|")) {
				keys = GenericMethods.SplitString(Param);
				keyValues = GenericMethods.SplitString(Value);
				if (keys.length != keyValues.length) {
					GenerateExtentReport.test.log(LogStatus.FAIL, "Parameters and values count mis-match");
				}
				else
				{
					for (int k = 0; k < keys.length; k++) {
						requestBuilder.addQueryParam(keys[k], keyValues[k]);
						System.out.println("");
					}
				}
			} else if (Param.contains("\\|") || Value.contains("\\|")) {

				GenerateExtentReport.test.log(LogStatus.FAIL, "Parameters and values count mis-match");
			} else {
				requestBuilder.addQueryParam(DataHandler.getQueryParamkey(), DataHandler.getQueryParamvalue());
			}
		} 

	}
	
	public static void AddHeader(String Param, String Value) {
		if (!(Param.isEmpty() || Value.isEmpty())) {
			if (Param.contains("\\|") && Value.contains("\\|")) {
				keys = GenericMethods.SplitString(Param);
				keyValues = GenericMethods.SplitString(Value);
				if (keys.length != keyValues.length) {
					GenerateExtentReport.test.log(LogStatus.FAIL, "Parameters and values count mis-match");
				}
				else
				{
					for (int k = 0; k < keys.length; k++) {
						requestBuilder.addHeader(keys[k], keyValues[k]);
					}
				}
			} else if (Param.contains("\\|") || Value.contains("\\|")) {

				GenerateExtentReport.test.log(LogStatus.FAIL, "Parameters and values count mis-match");
			} else {
				requestBuilder.addHeader(Param, Value);
			}
		} 

	}

}
