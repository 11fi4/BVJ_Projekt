package com.impl.report;

import java.io.File;

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
	 * @param xmlFile
	 * @param xsdFile
	 * @return returns true if validation succeeded, throws exception if not
	 */
	public static boolean ValidateDocument(File xmlFile, File xsdFile) {

		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new StreamSource(xsdFile));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xmlFile));
			return true;
		} catch (Exception ex) {
			// TODO log exception;
			return false;
		}
	}

}
