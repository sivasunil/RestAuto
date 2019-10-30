package com.api.demo.RestApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;

public class SampleMain {

	public static FilePath FP = new FilePath();

	public static void main(String[] args) {
		try {
			String json = readFile(FilePath.ExpectedFile("GETSchema.json"));
			boolean temp = compareJson();
			System.out.println(json);
			System.out.println(temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean compareJson() {
		boolean flag = false;
		JsonNode data;
		JsonNode schema;
		try {
			//data = JsonLoader.fromString(readFile(FilePath.OutputFile("Output.json")));
			data = JsonLoader.fromString(readFile(FilePath.OutputFile("TC01_Output.json")));
			schema = JsonLoader.fromString(readFile(FilePath.ExpectedFile("GETSchema.json")));
			ObjectNode object = (ObjectNode) data;
			object.remove("id");
			//String str = data.get("id").toString();
			//System.out.println(str);
			JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
			JsonValidator validator = factory.getValidator();

			try {
				ProcessingReport report = validator.validate(schema, data);
				flag = report.isSuccess();
			} catch (ProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return flag;
	}

	public static String readFile(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			return stringBuilder.toString();
		} finally {
			reader.close();
		}
	}
	
//	public static void readXml()
//	{
//		try {
//		    File fXmlFile = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"temp.xml");
//		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//		    Document doc = dBuilder.parse(fXmlFile);
//		    doc.getDocumentElement().normalize();
//		}
//		catch(Exception e)
//		{
//			
//		}
//	}

}
