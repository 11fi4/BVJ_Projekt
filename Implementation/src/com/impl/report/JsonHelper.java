package com.impl.report;

//json
import org.json.*;

//our enums
import com.impl.report.DocumentType;
import com.impl.report.Formats;

public class JsonHelper {

	public static String ConvertToXml(JSONObject json) {
		// TODO implement
		return "";
	}

	/**
	 * Validates if the json is valid for further processing
	 * 
	 * @param json
	 *            the JsonObject to use
	 * @return returns true if validation succeed, else returns false, Excptions
	 *         will be logged
	 */
	public static boolean Validate(JSONObject json) {

		try {

			// check if header is there
			JSONObject headerObj = json.getJSONObject("header");
			if (headerObj == null) {
				return false;
			} else {
				// validate docType
				String DocType = headerObj.getString("documenttype");
				if (DocumentType.valueOf(DocType) == null) {
					return false;
				}

				// validate Formats
				String formatValue = headerObj.getString("format");
				if (Formats.valueOf(formatValue) == null) {
					return false;
				}
			}

			// finally return true if all succeed
			return true;

		} catch (Exception ex) {
			String mess = "An error occured while validation JSONObject";
			LoggingWrapper.LogError(mess, ex);
			return false;
		}

	}
}
