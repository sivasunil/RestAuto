package com.api.demo.RestApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FilesReader {

	public static String readFile(String FileName)
	
	{
		String FiletoText = "";
		String FileType = FileName.substring(FileName.indexOf(".")+1);
		/*
		if (FileType=="json"){
			FiletoText = readjsonFile(FilePath.BodyFilePath(FileName));
		}
		*/
		
		switch(FileType)
		{
		case "json" :
		FiletoText = readjsonFile(FilePath.BodyFilePath(FileName));
		break;
		}
		
		return FiletoText;
	}
	
	public static String readjsonFile(String FilePath)
	{
		 String Body="";
		 JSONParser parser = new JSONParser();
	     try {
	    	 Object obj = parser.parse(new FileReader(FilePath));
	    	 JSONObject jsonObject = (JSONObject) obj;
	    	 Body = jsonObject.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Body;
	}
	
	public static Properties loadxmlProperties(final String filename)
	{
		if(filename!=null)
		{
			FileInputStream FIS = null;
			try {
				File file = new File(filename);
				FIS= new FileInputStream(file);
			    Properties result = new Properties();
			    result.loadFromXML(FIS);
			    return result;
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return null;
	}
	
/*	public static void main(String[] args)
	{
		String body = readFile(FilePath.BodyFilePath("Body.json"));
		System.out.println(body);
	}*/
}


