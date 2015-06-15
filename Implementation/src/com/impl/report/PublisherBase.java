package com.impl.report;

import java.nio.file.Path;

public abstract class PublisherBase {
	
	private Path _tempDir;
	
	public void SetTempDir(Path dir)
	{
		_tempDir = dir;
	}
	
	public abstract String Publish(String inputFile,String outputFile);
	
}
