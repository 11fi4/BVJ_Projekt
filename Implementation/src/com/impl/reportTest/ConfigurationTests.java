package com.impl.reportTest;

import com.impl.report.Configuration;
import com.impl.report.FormatSettings;
import com.impl.report.Formats;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurationTests {

	private void ConfigurationInit() throws Exception {
		if (!Configuration.IsInitialized()) {
			Configuration.Initialise();
		}
	}

	private void ConfigurationReset() {
		Configuration.Reset();
	}

	@Test
	public void Configuration_Initialise() throws Exception {

		ConfigurationInit();

		assertTrue(Configuration.IsInitialized());

		ConfigurationReset();
	}

	@Test
	public void Configuration_GetFormatSettings() throws Exception {

		ConfigurationInit();

		// no setings for word
		assertNull(Configuration.GetSettingsForFormat(Formats.Word));

		FormatSettings pdfSet = Configuration.GetSettingsForFormat(Formats.Pdf);
		assertNotNull(pdfSet);

		ConfigurationReset();
	}
}
