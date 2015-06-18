package com.impl.report.word;

import java.nio.file.Path;

import com.impl.report.*;

public class WordPublisher extends PublisherBase {

	public WordPublisher(Path dir, DocumentType type) {
		super(dir, type, Formats.Word);
	}

	@Override
	public void Publish(String inputFile, String outputFile) {
		// TODO Auto-generated method stub
	}

}
