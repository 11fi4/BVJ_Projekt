package com.impl.report;

import java.util.Hashtable;

public class FormatSettings {

	private Hashtable<String, String> _sets;

	public Hashtable<String, String> GetSets() {
		return _sets;
	}

	private Formats _format;

	public Formats GetFormat() {
		return _format;
	}

	public FormatSettings(Hashtable<String, String> sets, Formats format) {
		_sets = sets;
		_format = format;
	}

}
