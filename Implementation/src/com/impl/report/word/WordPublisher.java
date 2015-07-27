package com.impl.report.word;

import java.nio.file.Path;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.impl.report.FillForm.DocxFillForm;
import com.impl.report.*;

public class WordPublisher extends PublisherBase {

	public WordPublisher(Path dir, DocumentType type) {
		super(dir, type, Formats.Word);
	}

	@Override
	public void Publish(String inputFile, String outputFile) {

		try {
			DocxFillForm form = new DocxFillForm();
			form.CreateNewDocx(this.GetDocumentType());
		} catch (Exception ex) {
			Logger log = Logger.getGlobal();
			if (log != null) {
				// TODO get better message string from exception
				String message = ex.toString();
				log.log(Level.WARNING, message);
			}
		}
	}

}
