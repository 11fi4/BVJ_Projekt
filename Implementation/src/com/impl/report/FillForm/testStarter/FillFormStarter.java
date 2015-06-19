package com.impl.report.FillForm.testStarter;

import com.impl.report.DocumentType;
import com.impl.report.FillForm.DocxFillForm;

public class FillFormStarter {

	/** For testing purposes */
	public static void main(String[] args) {
		new DocxFillForm().CreateNewDocx(DocumentType.Attest);
		new DocxFillForm().CreateNewDocx(DocumentType.Verweis);
	}

}