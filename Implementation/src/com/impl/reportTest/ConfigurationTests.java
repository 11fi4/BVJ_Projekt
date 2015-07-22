package com.impl.reportTest;

import com.impl.report.Configuration;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurationTests {

	@Test
	public void Configuration_Initialise_checkInitializedConfig()
			throws Exception {

		// check if Configuration is intialized( should be false)
		assertFalse(Configuration.IsInitialized());

		// read configuration
		Configuration.Initialise();

		// TODO check configured stuff
		assertTrue(Configuration.GetDeleteTempFolders());
	}

}
