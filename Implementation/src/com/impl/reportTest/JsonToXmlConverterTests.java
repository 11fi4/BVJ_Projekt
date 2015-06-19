package com.impl.reportTest;

import com.impl.report.JsonToXmlConverter;

import static org.junit.Assert.*;
import org.junit.Test;

public class JsonToXmlConverterTests {

	@Test
	public void JsonToXmlConverter_ConvertToXml() {
		String source = SampleJson();

		String result = JsonToXmlConverter.ConvertToXml(source);

		String expected = CompareXml();

		assertEquals("strings are not equal", expected, result);
	}

	private String CompareXml() {
		// TODO create sampleXml
		return null;
	}

	private String SampleJson() {
		// TODO create sampleJSON
		return null;
	}

}
