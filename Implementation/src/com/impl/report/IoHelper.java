package com.impl.report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * This class contains helpfull methods for IO stuff (combine directories,
 * generate new paths etc...)
 * 
 * @author Johannes
 *
 */
public class IoHelper {

	/**
	 * Ensures that the given Directory exists
	 * 
	 * @param tempDir
	 *            the directory to ensure
	 * @throws IOException
	 *             is thrown f.ex. in case of failure while creating the new
	 *             directory
	 */
	public static void EnsureDirctory(Path tempDir) throws IOException {
		if (!Files.exists(tempDir, LinkOption.NOFOLLOW_LINKS)) {
			Files.createDirectory(tempDir);
		}
	}

	/**
	 * Creates a new FilePath with a randomly generated Guid and the right
	 * extension for a format
	 * 
	 * @param directory
	 *            the directory for the file
	 * @param format
	 *            the format
	 * @return Returns a string that represents the calculated path.
	 */
	public static String getOutputPath(Path directory, Formats format) {
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
			// TODO refactor this dependancy
			PublishingController.TrowNotSupportedForFormat(format);
		}

		UUID ran = UUID.randomUUID();

		Path newPath = Paths.get(directory.getFileName().toString(),
				ran.toString(), extension);
		return newPath.getFileName().toString();
	}

}
