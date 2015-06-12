package com.impl.report;

import java.io.File;

public class Configuration {
	
	//config file /cfg/report.cfg.xml
	
	private static boolean _isInitialized;
	
	public static boolean IsInitialized()
	{
		return _isInitialized;
	}
	
	public static void Initialise()
	{
		File configFile = new File("/cfg/report.cfg.xml");
		
		//TPDP implement
	}

}
