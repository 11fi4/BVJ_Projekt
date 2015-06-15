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
	
	private DocumentType _type;
	
	public DocumentType GetDocumentType()
	{
		return _type;
	}
	
	/**
	 * Creates a new instance
	 * 
	 * @param dir The directory that should be used for temporary files
	 */
	public PublisherBase(Path dir,DocumentType type)
	{
		_tempDir = dir;
		_type = type;
	}
	
	public abstract String Publish(String inputFile,String outputFile);
	
}
