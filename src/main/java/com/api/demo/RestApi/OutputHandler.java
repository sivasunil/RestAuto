package com.api.demo.RestApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;

public class OutputHandler extends RequestBuilder {
	
	public static void downloadResponse(String FilePath)
	{
		try {
			InputStream IS = IOUtils.toInputStream(Body, "UTF-8");
			FileOutputStream fos = new FileOutputStream(new File(FilePath));
			int inByte=0;
			byte[] buffer = new byte[32768];
			while((inByte = IS.read(buffer))> 0){
				fos.write(buffer, 0, inByte);
				//fos.write(inByte);
			}
			IS.close();
			fos.close();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
public static boolean validateResponseFiles(String ExpectedFile, String ActualFile)
{
	boolean isTwoEqual = false;
	try {
	File file1 = new File(ExpectedFile);
	File file2 = new File(ActualFile);
	
		isTwoEqual = FileUtils.contentEquals(file1, file2);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return isTwoEqual;
	}

public static boolean compareJson(String OutputFile, String Schema) {
	boolean flag = false;
	JsonNode data;
	JsonNode schema;
	try {
		//data = JsonLoader.fromString(readFile(FilePath.OutputFile("Output.json")));
		data = JsonLoader.fromString(readFile(OutputFile));
		schema = JsonLoader.fromString(readFile(Schema));
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


}