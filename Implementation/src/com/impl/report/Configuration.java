package com.impl.report;

import java.io.File;
import java.util.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import com.impl.report.shared.Formats;
import com.impl.report.shared.XmlHelper;

public class Configuration {

	private static Path _tempDir;

	public static Path GetTempDir() {
		return _tempDir;
	}

	private static Path _publishDir;

	public static Path GetPublishDir() {
		return _publishDir;
	}

	private static boolean _deleteTempFolders;

	/**
	 * Indicates if the temporary directories should be deleted when publication
	 * is finished
	 * 
	 * @return Returns if the temporary directories should be deleted when
	 *         publication is finished
	 */
	public static boolean GetDeleteTempFolders() {
		return _deleteTempFolders;
	}

	private static Hashtable<Formats, FormatSettings> _formatSettings = new Hashtable<Formats, FormatSettings>();

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

		File configFile = new File("cfg\\Report.cfg.xml");
		File schemaFile = new File("cfg\\Report.cfg.xml.xsd");

		// validate config against shema
		if (XmlHelper.ValidateDocument(configFile, schemaFile)) {

			// create factory and builder
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = null;
			builder = factory.newDocumentBuilder();

			// parse document
			Document doc = null;
			doc = builder.parse(configFile);

			NodeList tempNodeList = doc.getElementsByTagName("TempDir");
			if (tempNodeList.getLength() == 1) {
				Node TempDirNode = tempNodeList.item(0);

				// get location as Path
				Node LocationAttribute = TempDirNode.getAttributes()
						.getNamedItem("Location");
				if (LocationAttribute != null) {
					_tempDir = Paths.get(new URI(LocationAttribute
							.getNodeValue()));
				}

				// get delete-attribute and parse as boolean
				Node DeleteFoldersAttribute = TempDirNode.getAttributes()
						.getNamedItem("DeleteTempFolders");
				if (DeleteFoldersAttribute != null) {
					String delVal = DeleteFoldersAttribute.getNodeValue();
					_deleteTempFolders = Boolean.parseBoolean(delVal);
				}
			}

			NodeList publishNodeList = doc.getElementsByTagName("Publish");
			if (publishNodeList.getLength() == 1) {

				// get publish location as path
				Node LocationAttribute = publishNodeList.item(0)
						.getAttributes().getNamedItem("Location");
				if (LocationAttribute != null) {
					_publishDir = Paths.get(new URI(LocationAttribute
							.getNodeValue()));
				}
			}

			// loops SettingsGroups
			NodeList grps = doc.getElementsByTagName("SettingsGroup");
			if (grps.getLength() != 0) {

				for (int i = 0; i < grps.getLength(); i++) {
					Node grp = grps.item(i);
					FormatSettings set = SettingsFromNode(grp);

					if (set != null) {

						if (!_formatSettings.containsKey(set.GetFormat())) {
							_formatSettings.put(set.GetFormat(), set);
						}
					}
				}
			}
			_isInitialized = true;
		} else {
			_isInitialized = false;
		}

	}

	/**
	 * Parses a Settings node and return a FormatSettings object if
	 * configuration was properly
	 * 
	 * @param node
	 *            The SettingsGroup node
	 * @return Returns FormatSettings object if settings are contained and
	 *         returns null if otherwise
	 */
	private static FormatSettings SettingsFromNode(Node node) {

		Node formatAttr = node.getAttributes().getNamedItem("Format");
		if (formatAttr != null) {

			// setup
			String fVal = formatAttr.getTextContent();
			Hashtable<String, String> ht = new Hashtable<String, String>();
			Formats format = Formats.valueOf(fVal);

			// loop setNodes
			NodeList setNodes = node.getChildNodes();
			for (int i = 0; i < setNodes.getLength(); i++) {
				Node setNode = setNodes.item(i);

				// TODO: bug: localname is null, causes iteration to fail
				String localName = setNode.getLocalName();
				if (localName == "Set") {
					Node keyNode = setNode.getAttributes().getNamedItem("Key");
					Node valueNode = setNode.getAttributes().getNamedItem(
							"Value");

					if (keyNode != null && valueNode != null) {
						ht.put(keyNode.getTextContent(),
								valueNode.getTextContent());
					}
				}
			}

			// if settings where found -> create and return/ else return null
			if (!ht.isEmpty()) {
				FormatSettings fs = new FormatSettings(ht, format);
				return fs;
			}
		}

		// should be unreachable code if validation and configuration work
		// together
		return null;
	}

	public static void Reset() {
		_isInitialized = false;
		_formatSettings.clear();
	}
}
