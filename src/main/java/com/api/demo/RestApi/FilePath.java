package com.api.demo.RestApi;

public class FilePath {
	
	public static String BodyFilePath(String FileName)
	{
		String path = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"test"+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"TestData"+System.getProperty("file.separator")+"Body"+System.getProperty("file.separator")+FileName;
		return path;
	}

	public static String TestDataPath(String FileName)
	{
		String path = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"test"+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"TestData"+System.getProperty("file.separator")+FileName;
		return path;
	}
	
	public static String ConfigPath(String FileName)
	{
		String path = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"test"+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"Config"+System.getProperty("file.separator")+FileName;
		return path;
	}
	public static String OutputFile(String FileName)
	{
		String path = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"test"+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"Output"+System.getProperty("file.separator")+FileName;
		return path;
	}
	public static String ExpectedFile(String FileName)
	{
		String path = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"test"+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")+"Expected"+System.getProperty("file.separator")+FileName;
		return path;
	}
	public static String ExtentConfigPath()
	{
		String path = System.getProperty("user.dir")+System.getProperty("file.separator")+"extent-config.xml";
		return path;
	}
}
