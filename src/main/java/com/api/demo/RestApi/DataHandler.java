package com.api.demo.RestApi;

public class DataHandler {
	public static FilesReader FReader = new FilesReader();

	public static String getBaseuri() {
		return Baseuri;
	}

	public static void setBaseuri(String baseuri) {
		Baseuri = baseuri;
	}

	public static String getPath() {
		return Path;
	}

	public static void setPath(String path) {
		Path = path;
	}

	public static String getQueryParamkey() {
		return QueryParamkey;
	}

	public static void setQueryParamkey(String queryParamkey) {
		QueryParamkey = queryParamkey;
	}

	public static String getQueryParamvalue() {
		return QueryParamvalue;
	}

	public static void setQueryParamvalue(String queryParamvalue) {
		QueryParamvalue = queryParamvalue;
	}

	public static String getBody() {
		return Body;
	}

	public static void setBody(String body) {
		Body = body;
	}

	public static String getMethod() {
		return Method;
	}

	public static void setMethod(String method) {
		Method = method;
	}

	public static void setAll(String Path, String QueryParamkey, String QueryParamvalue, String Header,
			String Headerval, String Body, String Method) {
		setBaseuri(FilesReader.loadxmlProperties(FilePath.ConfigPath("config.xml")).getProperty("host"));
		setPath(Path);
		setQueryParamkey(QueryParamkey);
		setQueryParamvalue(QueryParamvalue);
		if (!(Body.equalsIgnoreCase("Blank"))) {
			setBody(FilesReader.readFile(Body));
		}
		else
		{
			setBody("");
		}
		setMethod(Method);
		setHeader(Header);
		setHeaderVal(Headerval);
	}

	public static String getTestCase() {
		return TestCase;
	}

	public static void setTestCase(String testCase) {
		TestCase = testCase;
	}

	public static String getSchemafile() {
		return Schemafile;
	}

	public static void setSchemafile(String schemafile) {
		Schemafile = schemafile;
	}

	public static String getOutputFile() {
		return OutputFile;
	}

	public static void setOutputFile(String outputFile) {
		OutputFile = outputFile;
	}

	public static String getHeader() {
		return Header;
	}

	public static void setHeader(String header) {
		Header = header;
	}

	public static String getHeaderVal() {
		return HeaderVal;
	}

	public static void setHeaderVal(String headerVal) {
		HeaderVal = headerVal;
	}

	public static String TestCase;
	public static String Schemafile;
	public static String OutputFile;
	public static String Baseuri;
	public static String Path;
	public static String QueryParamkey;
	public static String QueryParamvalue;
	public static String Body;
	public static String Method;
	public static String Header;
	public static String HeaderVal;

}
