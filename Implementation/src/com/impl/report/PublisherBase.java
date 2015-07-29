package com.impl.report;

import java.nio.file.Path;

import com.impl.report.shared.DocumentType;
import com.impl.report.shared.Formats;

/**
 * Baseclass for publisher
 * 
 * @author Johannes
 *
 */
public abstract class PublisherBase {

	private Path _tempDir;

	/**
	 * The directory used for temporary files
	 * 
	 * @return
	 */
	public Path GetTempDir() {
		return _tempDir;
	}

	private DocumentType _type;

	/**
	 * The DocumentType this publisher is publishing
	 * 
	 * @return
	 */
	public DocumentType GetDocumentType() {
		return _type;
	}

	private Formats _format;

	/**
	 * The Format this publisher is publishing
	 * 
	 * @return
	 */
	public Formats GetFormat() {
		return _format;
	}

	private FormatSettings _sets;

	public FormatSettings GetSettings() {
		return _sets;
	}

	public void SetSettings(FormatSettings sets) {
		_sets = sets;
	}

	/**
	 * Creates a new instance
	 * 
	 * @param dir
	 *            The directory that should be used for temporary files
	 */
	public PublisherBase(Path dir, DocumentType type, Formats format) {
		_tempDir = dir;
		_type = type;
		_format = format;
	}

	public abstract void Publish(String inputFile, String outputFile);

}
