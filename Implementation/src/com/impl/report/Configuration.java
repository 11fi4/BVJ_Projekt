package com.impl.report;

import java.io.File;
import java.util.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.*;

import org.w3c.dom.*;

public class Configuration {

	private static Path _tempDir;

	public static Path GetTempDir() {
		return _tempDir;
	}

	private static Hashtable<Formats, FormatSettings> _formatSettings;

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

	/**
	 * Initializes the ConfigurationClass and loads its configuration
	 * 
	 * @throws Exception
	 */
	public static void Initialise() throws Exception {
		File configFile = new File("/cfg/report.cfg.xml");
		File schemaFile = new File("/cfg/report.cfg.xml.xsd");

		if (XmlHelper.ValidateDocument(configFile, schemaFile)) {

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = null;

			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				throw e;
			}

			Document doc = null;

			try {
				doc = builder.parse(configFile);
			} catch (Exception ex) {
				throw ex;
			}

			NodeList tempNodeList = doc.getElementsByTagName("TempDir");
			if (tempNodeList.getLength() == 1) {
				String tmpVal = tempNodeList.item(0).getNodeValue();
				try {
					_tempDir = Paths.get(new URI(tmpVal));
				} catch (Exception e) {
					throw e;
				}
			}

			// loops SettingsGroups
			NodeList grps = doc.getElementsByTagName("SettingsGroup");
			if (grps.getLength() != 0) {
				_formatSettings = new Hashtable<Formats, FormatSettings>();

				for (int i = 0; i < grps.getLength(); i++) {
					Node grp = grps.item(i);
					FormatSettings set = SettingsFromNode(grp);

					if (!_formatSettings.containsKey(set.GetFormat())) {
						_formatSettings.put(set.GetFormat(), set);
					} else {
						// TODO throw exception with message that settings
						// for a format a configured at least duplicate
					}
				}
			}
		}
	}

	private static FormatSettings SettingsFromNode(Node node) {
		// TODO implement
		return null;
	}
}
