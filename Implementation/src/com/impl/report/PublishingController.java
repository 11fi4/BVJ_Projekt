package com.impl.report;

import java.lang.UnsupportedOperationException;
import java.util.UUID;
import java.io.*;
import java.nio.file.*;

import com.impl.report.html.HtmlPublisher;
import com.impl.report.pdf.PdfPublisher;
import com.impl.report.word.WordPublisher;

//TODO: remove this warning later
@SuppressWarnings("unused")
public class PublishingController {

	public static void Publish(Formats format, DocumentType type, String inputXml) {

		try {

			PublisherBase publisher = getPublisher(format);
			Path tempDir = getTempDirectory();
			
			//TODO make sure dir doesn't exist -> loop new creation

			String inputXmlPath = Paths.get(tempDir.getFileName().toString(), "input.xml").toString();
			
			// TODO calc outputpath (use UUID for random tempdir-generic)

			String publishedDocument = publisher.Publish("", "");

			// TODO implement
		} catch (Exception ex) {
			// TODO implement logging
		}
	}
	
	private static Path getTempDirectory()
	{
		UUID id = UUID.randomUUID();
		
		String tempDir = "TODO: make configurable";
		
		Path tempPath = Paths.get(tempDir, id.toString());

		//if configured path is not absolute -> normalize
		if(!tempPath.isAbsolute())
		{
			tempPath = tempPath.toAbsolutePath();
		}
		
		return tempPath;
	}

	/**
	 * This either returns a right Publisher for the format 
	 * or throws exception if format is not supported
	 * 
	 * @param format The document format that should be published.
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
			String message = String.format(
					"No publisher has been implemented for Format '{0}'.",
					format.toString());
			throw new UnsupportedOperationException(message);
		}
	}

}
