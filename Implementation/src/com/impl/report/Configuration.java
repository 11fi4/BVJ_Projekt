package com.impl.report;

import java.io.File;
import java.nio.file.Path;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

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

	// TODO initialise
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

		// TODO read doc
		Document doc = null;
	}

}
