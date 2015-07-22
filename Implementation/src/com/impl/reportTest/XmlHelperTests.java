package com.impl.reportTest;

import static org.junit.Assert.*;
import org.junit.Test;

import com.impl.report.XmlHelper;

public class XmlHelperTests {

	@Test
	public void XmlHelper_ValidateDoc_validatesReportConfig() throws Exception {
		assertTrue(XmlHelper.ValidateDocument("cfg\\Report.cfg.xml",
				"cfg\\Report.cfg.xml.xsd"));
	}

}
