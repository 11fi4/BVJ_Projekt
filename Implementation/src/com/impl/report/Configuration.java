package com.impl.report;

import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

public class Configuration {
	
	private static Path _tempDir;
	
	public static Path GetTempDir
	{
		return _tempDir;
	}
	
	private static boolean _isInitialized;
	
	public static boolean IsInitialized()
	{
		return _isInitialized;
	}
	
	//TODO initialise
	public static void Initialise()
	{
		File configFile = new File("/cfg/report.cfg.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		//TODO read doc
		Document doc = null;
		
		try
		{
			//read doc here
		}
		catch()
		{
			//TODO catch and log excepiton
		}
	}

}
