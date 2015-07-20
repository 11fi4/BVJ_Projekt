package com.impl.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 * This class contains helpfull methods for handling xml
 * 
 * @author Johannes
 *
 */
public class XmlHelper {

	/**
	 * Validates a Xml-file against a given Xsd-schema
	 * 
	 * @param xmlFilePath
	 *            path to xml
	 * @param xdfFilePath
	 *            path to xsd
	 * @return returns true if validation succeeded, throws exception if not
	 * @throws Exception
	 *             Exception is thrown if file is not valid or file can't be
	 *             Accessed
	 */
	public static boolean ValidateDocument(String xmlFilePath,
			String xdfFilePath) throws Exception {
		
		//nur eine fassaden-funktion
		File xmlFile = new File(xmlFilePath);
		File xsdFile = new File(xdfFilePath);

		return ValidateDocument(xmlFile, xsdFile);
	}

	/**
	 * Validates a Xml-file against a given Xsd-schema
	 * 
	 * @param xmlFile
	 * @param xsdFile
	 * @return returns true if validation succeeded, throws exception if not
	 * @throws Exception
	 *             Exception is thrown if file is not valid or file can't be
	 *             Accessed
	 */
	public static boolean ValidateDocument(File xmlFile, File xsdFile)
			throws Exception {

		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new StreamSource(xsdFile));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xmlFile));
			return true;
		} catch (Exception ex) {
			
			//TODO: the java-validator can't handle namespaces obviously....
			//xsd-prefix is hardcoded as default even tho xs-prefix is given
			
			throw ex;
		}
	}

	/**
	 * Writes a Xml as a File onto HDD
	 * 
	 * @param xml
	 *            The Xml-String
	 * @param path
	 *            The path of the xml-file to write
	 * @throws IOException
	 *             Is thrown when writing failed (f.ex insufficent privilges)
	 */
	public static void WriteXmlToFile(String xml, String path)
			throws IOException {

		BufferedWriter out = new BufferedWriter(new FileWriter(path));

		// dispose buffer
		try {
			out.write(xml);
		} finally {
			out.close();
		}

	}

}
