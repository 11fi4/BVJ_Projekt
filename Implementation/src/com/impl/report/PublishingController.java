package com.impl.report;

import java.util.*;
import java.io.*;
import java.nio.file.*;

//report.publisher
import com.impl.report.html.HtmlPublisher;
import com.impl.report.pdf.PdfPublisher;
import com.impl.report.word.WordPublisher;

public class PublishingController {

	/**
	 * Starts a publication
	 * 
	 * @param format
	 *            Document format that is requested
	 * @param type
	 *            Type of Document requested
	 * @param inputParams
	 *            Json with input-parameters
	 */
	public static void Publish(Formats format, DocumentType type,
			String inputParams) {

		try {

			if (!Configuration.IsInitialized()) {
				Configuration.Initialise();
			}

			Path tempDir = getTempDirectory();

			// set up pblisher
			PublisherBase publisher = createPublisher(format, type, tempDir);
			AddOptionalComponents(publisher);

			// if directory exists -> create new
			while (Files.exists(tempDir, LinkOption.NOFOLLOW_LINKS)) {
				tempDir = getTempDirectory();
			}

			@SuppressWarnings("unused")
			String inputXml = JsonToXmlConverter.ConvertToXml(inputParams);
			@SuppressWarnings("unused")
			String inputXmlPath = Paths.get(tempDir.getFileName().toString(),
					"input.xml").toString();

			// TODO write inputXml to inputXmlPath

			@SuppressWarnings("unused")
			String outputPath = getOutputPath(tempDir, format);

			EnsureDirctory(tempDir);

			@SuppressWarnings("unused")
			String publishedDocument = publisher.Publish("", "");

		} catch (Exception ex) {
			// TODO implement logging
		}
	}

	/**
	 * Adds optional Parameters per format from configuration
	 * 
	 * @param publisher
	 *            the instance of the publisher
	 */
	private static void AddOptionalComponents(PublisherBase publisher) {
		
		if (publisher instanceof HtmlPublisher) {
			//TODO: maybe add optional componants like a singleton XsdReader f.ex??
		}
	}

	private static void EnsureDirctory(Path tempDir) throws IOException {
		if (!Files.exists(tempDir, LinkOption.NOFOLLOW_LINKS)) {
			Files.createDirectory(tempDir);
		}
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

		String tempDir = Configuration.GetTempDir().toString();

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
	private static PublisherBase createPublisher(Formats format,
			DocumentType type, Path tempDir) {

		switch (format) {

		case Html:
			return new HtmlPublisher(tempDir, type);

		case Pdf:
			return new PdfPublisher(tempDir, type);

		case Word:
			return new WordPublisher(tempDir, type);

		default:
			TrowNotSupportedForFormat(format);

			// unreachable but needed to compile.....
			return null;
		}
	}

	/*
	 * Throws Exception with message for not yet fully Supported formats
	 * 
	 * @param format The format that is not supported
	 */
	private static void TrowNotSupportedForFormat(Formats format) {
		String message = String.format(
				"No publisher has been fully implemented for Format '{0}'.",
				format.toString());
		throw new UnsupportedOperationException(message);
	}

}
