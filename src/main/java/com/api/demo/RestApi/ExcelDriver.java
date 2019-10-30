package com.api.demo.RestApi;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelDriver {

	public static Recordset recordset = null;

	public static Object[][] Datatoarray(String Excelpath, String QueryString, String SheetName) {
		readExcelData(Excelpath, QueryString, SheetName);
		String[][] data = null;
		try {
			data = new String[recordset.getCount()][recordset.getFieldNames().size()];
			int i = 0;
			while (recordset.next()) {
				for (int j = 0; j < recordset.getFieldNames().size(); j++) {
					data[i][j] = recordset.getField(j).toString();
				}
				i++;
			}
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public static void DatatoHandler(String Excelpath, String QueryString, String SheetName) {
		try {
			readExcelData(Excelpath, QueryString, SheetName);
			recordset.next();
			DataHandler.setAll(recordset.getField("Path"), recordset.getField("QueryParamkey"),
					recordset.getField("QueryParamvalue"), recordset.getField("Header"), recordset.getField("HeaderValue"), recordset.getField("Body"), recordset.getField("Method"));
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String BuildQuery(String QueryString, String SheetName) {
		String Query = "SELECT * from " + SheetName + " where TestCaseName=" + "\'" + QueryString + "\'";
		return Query;
	}

	public static void readExcelData(String Excelpath, String QueryString, String SheetName) {
		Fillo fillo = new Fillo();
		try {
			Connection connection = fillo.getConnection(FilePath.TestDataPath(Excelpath));
			String Query = BuildQuery(QueryString, SheetName);
			recordset = connection.executeQuery(Query);
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
