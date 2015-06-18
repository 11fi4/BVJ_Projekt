package com.impl.report.pdf;

import java.nio.file.Path;

import com.impl.report.*;

public class PdfPublisher extends PublisherBase {

	public PdfPublisher(Path dir, DocumentType type) {
		super(dir, type, Formats.Pdf);
	}

	@Override
	public void Publish(String inputFile, String outputFile) {
		// TODO Auto-generated method stub
	}

}
