package com.impl.report;

import java.util.*;
import java.nio.file.*;

//json
import org.json.*;

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

			// create JSOnObject from String
			JSONObject json = new JSONObject(inputParams);

			// validate
			if (JsonHelper.Validate(json)) {

				String inputXml = JsonHelper.ConvertToXml(json);
				String inputXmlPath = Paths.get(
						tempDir.getFileName().toString(), "input.xml")
						.toString();

				XmlHelper.WriteXmlToFile(inputXml, inputXmlPath);

				String outputPath = IoHelper.getOutputPath(tempDir, format);

				IoHelper.EnsureDirctory(tempDir);

				publisher.Publish(inputXmlPath, outputPath);
			} else {
				// TODO throw exception with good message
			}

		} catch (Exception ex) {
			String message = String
					.format("An error occured while Publishing an '{0}'(documentType) for '{1}'(format)",
							type.toString(), format.toString());
			LoggingWrapper.LogError(message, ex);
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
		// maybe add optional componants like a singleton XsdReader
		// f.ex??
		// }
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
	public static void TrowNotSupportedForFormat(Formats format) {
		String message = String.format(
				"No publisher has been fully implemented for Format '{0}'.",
				format.toString());
		throw new UnsupportedOperationException(message);
	}

}
