package com.impl.report.word;

import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.impl.report.DocumentType;
import com.impl.report.Formats;
import com.impl.report.PublisherBase;
import com.impl.report.FillForm.DocxFillForm;

public class WordPublisher extends PublisherBase {

	public WordPublisher(Path dir, DocumentType type) {
		super(dir, type, Formats.Word);
	}

	@Override
	public void Publish(String inputFile, String outputFile) {

		try {
			//TODO get parameters by Configuration
			DocxFillForm form = new DocxFillForm(true, "T:\\");
			form.CreateNewDocx(this.GetDocumentType());
		} catch (Exception ex) {
			Logger log = Logger.getGlobal();
			if (log != null) {
				// TODO get better message string from exception, write LoggingHelper in ReportPackage, called LoggingHelper
				String message = ex.toString();
				log.log(Level.WARNING, message);
			}
		}
	}

}
