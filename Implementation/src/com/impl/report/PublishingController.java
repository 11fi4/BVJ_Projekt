package com.impl.report;

import java.lang.UnsupportedOperationException;
import java.util.UUID;

//TODO: remove this warning later
@SuppressWarnings("unused")
public class PublishingController {

	public static void Publish(Formats format, DocumentType type, String inputXml) {

		try {

			PublisherBase publisher = getPublisher(format);

			// TODO generate inputFile
			// TODO calc outputpath (use UUID for random tempdir-generic)

			String publishedDocument = publisher.Publish("", "");

			// TODO implement
		} catch (Exception ex) {
			// TODO implement logging
		}
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

		default:
			String message = String.format(
					"No publisher has been implemented for Format '{0}'.",
					format.toString());
			throw new UnsupportedOperationException(message);
		}
	}

}
