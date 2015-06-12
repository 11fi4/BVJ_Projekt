package com.impl.report;

import java.lang.UnsupportedOperationException;
import java.util.Iterator;
import java.util.UUID;
import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.FileSystem;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;

import com.impl.report.html.HtmlPublisher;
import com.impl.report.pdf.PdfPublisher;
import com.impl.report.word.WordPublisher;

//TODO: remove this warning later
@SuppressWarnings("unused")
public class PublishingController {

	public static void Publish(Formats format, DocumentType type,
			String inputXml) {

		try {
			
			if(!Configuration.IsInitialized())
			{
				Configuration.Initialise();
			}

			PublisherBase publisher = getPublisher(format);
			Path tempDir = getTempDirectory();

			// if directory exists -> create new
			while (Files.exists(tempDir, null)) {
				tempDir = getTempDirectory();
			}

			String inputXmlPath = Paths.get(tempDir.getFileName().toString(),
					"input.xml").toString();
			String outputPath = getOutputPath(tempDir, format);

			String publishedDocument = publisher.Publish("", "");

			// TODO implement
		} catch (Exception ex) {
			// TODO implement logging
		}
	}
	
	private static void PrepareFiles(Path tempDir,String inFile) throws IOException
	{
		//TODO remove nulls here
		
		if(!Files.exists(tempDir, null))
		{
			Files.createDirectory(tempDir, null);
		}
		
		//TODO copy inFile into TempDir
	}

	private static String getOutputPath(Path tempDir, Formats format) {
		String extension = null;

		switch (format) {
		case Html:
		case Pdf:
			extension = "." + format.toString();
			break;

		case Word:
			extension = "." + ".docx";
			break;

		default:
			TrowNotSupportedForFormat(format);
		}

		UUID ran = UUID.randomUUID();

		Path newPath = Paths.get(tempDir.getFileName().toString(),
				ran.toString(), extension);
		return newPath.getFileName().toString();
	}

	private static Path getTempDirectory() {
		UUID id = UUID.randomUUID();

		String tempDir = "TODO: make configurable";

		Path tempPath = Paths.get(tempDir, id.toString());

		// if configured path is not absolute -> normalize
		if (!tempPath.isAbsolute()) {
			tempPath = tempPath.toAbsolutePath();
		}

		return tempPath;
	}

	/**
	 * This either returns a right Publisher for the format or throws exception
	 * if format is not supported
	 * 
	 * @param format
	 *            The document format that should be published.
	 * @return returns a publisher that is derived from PublisherBase
	 */
	private static PublisherBase getPublisher(Formats format) {

		switch (format) {

		case Html:
			return new HtmlPublisher();

		case Pdf:
			return new PdfPublisher();

		case Word:
			return new WordPublisher();

		default:
			TrowNotSupportedForFormat(format);

			// unreachable but needed to compile.....
			return null;
		}
	}

	private static void TrowNotSupportedForFormat(Formats format) {
		String message = String.format(
				"No publisher has been implemented for Format '{0}'.",
				format.toString());
		throw new UnsupportedOperationException(message);
	}

}
