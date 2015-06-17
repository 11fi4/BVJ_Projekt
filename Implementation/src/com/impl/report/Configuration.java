package com.impl.report;

import java.io.File;
import java.io.IOException;
import java.util.*;
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

	private static Dictionary<Formats, FormatSettings> _formatSettings;

	public static FormatSettings GetSettingsForFormat(Formats format) {
		if (_formatSettings != null) {
			FormatSettings settings = _formatSettings.get(format);
			if (settings != null) {
				return null;
			}
		}

		// default if no settings where configured or no Settings for format
		// where found
		return null;
	}

	private static boolean _isInitialized;

	public static boolean IsInitialized() {
		return _isInitialized;
	}

	public static void Initialise() {
		File configFile = new File("/cfg/report.cfg.xml");
		File schemaFile = new File("/cfg/report.cfg.xml.xsd");

		if (ValidateDocument(configFile, schemaFile)) {

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = null;

			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			Document doc = null;

			try {
				doc = builder.parse(configFile);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			// TODO get this with XPAth
			NodeList tempNodeList = doc.getElementsByTagName("TempDir");
			if (tempNodeList.getLength() == 1) {
				String tmpVal = tempNodeList.item(0).getNodeValue();
				try {
					_tempDir = Paths.get(new URI(tmpVal));
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
		}
	}

	private static boolean ValidateDocument(File xmlFile, File xsdFile) {
		// TODO implement
		return false;
	}

}
