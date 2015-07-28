package com.impl.report;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

public class LoggingWrapper {

	// TODO configure logger?
	private final static String _loggerName = "ReportLogger";

	public static void LogError(String message, Exception ex) {
		if (ex != null) {
			String newMess = message + ConvertExceptionToString(ex);
			LogMessage(Level.ERROR, newMess);
		} else {
			LogMessage(Level.ERROR, message);
		}
	}

	public static void LogWarning(String message, Exception ex) {
		String mess = message;

		if (ex != null) {
			mess += ConvertExceptionToString(ex);
		}

		LogMessage(Level.WARN, mess);
	}

	private static void LogMessage(Level lvl, String message) {
		Logger log = Logger.getLogger(_loggerName);
		if (log != null) {
			log.log(lvl, message);
		}
	}

	private static String ConvertExceptionToString(Exception ex) {
		String mess = ex.getMessage();

		String stackString = null;
		for (StackTraceElement stack : ex.getStackTrace()) {
			stackString += stack.toString();
		}

		return String.format("'{0}' caused by: '{1}'", mess, stackString);
	}
}
