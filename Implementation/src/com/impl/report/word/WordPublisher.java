package com.impl.report.word;

import java.nio.file.Path;
import com.impl.report.shared.DocumentType;
import com.impl.report.shared.Formats;
import com.impl.report.shared.LoggingWrapper;
import com.impl.report.PublisherBase;
import com.impl.report.FillForm.DocxFillForm;

public class WordPublisher extends PublisherBase {

	public WordPublisher(Path dir, DocumentType type) {
		super(dir, type, Formats.Word);
	}

	@Override
	public void Publish(String inputFile, String outputFile) {

		try {
			// TODO get parameters by Configuration
			DocxFillForm form = new DocxFillForm(true, "T:\\");
			form.CreateNewDocx(this.GetDocumentType());
		} catch (Exception ex) {
			String mess = String
					.format("An error occured while publishing documentType '{0}' format '{1}'",
							this.GetDocumentType().toString(), this.GetFormat()
									.toString());
			LoggingWrapper.LogError(mess, ex);
		}
	}
}
