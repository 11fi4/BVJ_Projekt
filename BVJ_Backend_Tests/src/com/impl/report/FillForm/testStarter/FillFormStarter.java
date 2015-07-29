package com.impl.report.FillForm.testStarter;

import com.impl.report.shared.DocumentType;
import com.impl.report.FillForm.DocxFillForm;

public class FillFormStarter {

	/**
	 * For testing purposes
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new DocxFillForm(true, "T:\\").CreateNewDocx(DocumentType.Attest);
		new DocxFillForm(true, "T:\\").CreateNewDocx(DocumentType.Verweis);
	}

}