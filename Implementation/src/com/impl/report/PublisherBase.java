package com.impl.report;

import java.nio.file.Path;

public abstract class PublisherBase {
	
	private Path _tempDir;
	
	/**
	 * The directory used for temporary files
	 * 
	 * @return
	 */
	public Path GetTempDir()
	{
		return _tempDir;
	}
	
	/**
	 * Creates a new instance
	 * 
	 * @param dir The directory that should be used for temporary files
	 */
	public PublisherBase(Path dir)
	{
		_tempDir = dir;
	}
	
	public abstract String Publish(String inputFile,String outputFile);
	
}
