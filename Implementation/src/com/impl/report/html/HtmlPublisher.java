package com.impl.report.html;

import java.nio.file.Path;

import com.impl.report.*;

public class HtmlPublisher extends PublisherBase {

	public HtmlPublisher(Path dir, DocumentType type) {
		super(dir, type, Formats.Html);
	}

	@Override
	public void Publish(String inputFile, String outputFile) {
		// TODO Auto-generated method stub
	}

}
