package com.impl.report;

import java.util.*;
import java.io.*;
import java.nio.file.*;

//report.publisher
import com.impl.report.html.HtmlPublisher;
import com.impl.report.pdf.PdfPublisher;
import com.impl.report.word.WordPublisher;

/**
 * This is the controller for all publications
 * 
 * @author Johannes
 *
 */
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
			String inputXmlPath = Paths.get(tempDir.getFileName().toString(),
					"input.xml").toString();

			// TODO write inputXml to inputXmlPath

			String outputPath = getOutputPath(tempDir, format);

			EnsureDirctory(tempDir);

			publisher.Publish(inputXmlPath, outputPath);

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

		// check if settings are configured and add them to the publisher
		FormatSettings sets = Configuration.GetSettingsForFormat(publisher
				.GetFormat());
		if (sets != null) {
			publisher.SetSettings(sets);
		}

		// if (publisher instanceof HtmlPublisher) {
		// TODO: maybe add optional componants like a singleton XsdReader
		// f.ex??
		// }
	}

	/**
	 * Ensures that the given Directory exists
	 * 
	 * @param tempDir
	 *            the directory to ensure
	 * @throws IOException
	 *             is thrown f.ex. in case of failure while creating the new
	 *             directory
	 */
	private static void EnsureDirctory(Path tempDir) throws IOException {
		if (!Files.exists(tempDir, LinkOption.NOFOLLOW_LINKS)) {
			Files.createDirectory(tempDir);
		}
	}

	/**
	 * Creates a new FilePath with a randomly generated Guid and the right
	 * extension for a format
	 * 
	 * @param tempDir
	 *            the directory for the file
	 * @param format
	 *            the format
	 * @return Returns a string that represents the calculated path.
	 */
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

	/**
	 * Calculates a new tempDir under the configured TempDirectory. The
	 * Subdirectory is the configured directory plus randmomly generated Guid
	 * 
	 * @return A path object with the absolutePath to the calculated directory
	 */
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

	/**
	 * Throws Exception with message for not yet fully Supported formats
	 * 
	 * @param format
	 *            The format that is not supported
	 */
	private static void TrowNotSupportedForFormat(Formats format) {
		String message = String.format(
				"No publisher has been fully implemented for Format '{0}'.",
				format.toString());
		throw new UnsupportedOperationException(message);
	}

}
