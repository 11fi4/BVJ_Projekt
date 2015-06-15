package com.impl.report;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.w3c.dom.*;

public class Configuration {

	private static Path _tempDir;

	public static Path GetTempDir() {
		return _tempDir;
	}

	private static boolean _isInitialized;

	public static boolean IsInitialized() {
		return _isInitialized;
	}

	public static void Initialise() {
		File configFile = new File("/cfg/report.cfg.xml");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;

		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		Document doc = null;
		
		try {
			doc = builder.parse(configFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		NodeList tempNodeList = doc.getElementsByTagName("TempDir");
		if(tempNodeList.getLength()==1)
		{
			String tmpVal = tempNodeList.item(0).getNodeValue();
			try {
				_tempDir = Paths.get(new URI(tmpVal));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}

}
