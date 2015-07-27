package com.impl.report.FillForm;

import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.impl.report.DocumentType;

/** Fills out the form of a given PDF and saves it to a new file */
public class DocxFillForm {

	// TODO make this configurable
	final String PATH = "T:\\";
	private final boolean debug = true;

	/** Generates text table for a FormType */
	private Hashtable<String, String> Generate_Table(DocumentType type) {
		switch (type) {
		case Verweis:
			return Generate_Verweis_Table();
		case Attest:
			return Generate_Attest_Table();

		default:
			return null;
		}
	}

	/** Generates text table for an 'Attest' document */
	private Hashtable<String, String> Generate_Attest_Table() {
		Boolean gender = true;
		Boolean genderParent = true;
		String brief_datum = "05.04.2014";
		String possesiv0 = gender ? "ihre Tochter" : "ihr Sohn";
		String possesiv1 = gender ? "ihrer Tochter" : "ihres Sohnses";
		String possesiv2 = gender ? "unserer Tochter" : "unseres Sohnes";
		String anschrift = "Gudrun M�ller" + "\nM�llerstr. 7"
				+ "\n97080 W�rzburg";
		String anrede = (genderParent ? "geehrte Frau " : "geehrter Herr ")
				+ "M�ller";
		String name_schueler = "Tina";
		String fehltage_seit = "01.01.2014";
		String fehltage_gesamt = "19";
		String fehltage_unentschuldigt = "8";
		String attestpflicht_ab = "17.04.2014";

		Hashtable<String, String> ht = new Hashtable<String, String>();

		ht.put("text01", anschrift); // 0
		ht.put("text02", brief_datum);
		ht.put("text03", anrede);
		ht.put("text04", possesiv1 + " " + name_schueler);
		ht.put("text05", possesiv1);
		ht.put("text06", fehltage_seit);
		ht.put("text07", fehltage_gesamt);
		ht.put("text08", fehltage_unentschuldigt);
		ht.put("text09", possesiv1);
		ht.put("text10", possesiv0);
		ht.put("text11", attestpflicht_ab);
		ht.put("text12", possesiv1);
		ht.put("text13", attestpflicht_ab);
		ht.put("text14", brief_datum);
		ht.put("text15", possesiv2 + " " + name_schueler);
		ht.put("text16", fehltage_seit);
		ht.put("text17", attestpflicht_ab);
		ht.put("text18", possesiv0);

		return ht;
	}

	/** Generates text table for a 'Verweis' document */
	private Hashtable<String, String> Generate_Verweis_Table() {
		Boolean gender = true;
		String schuelerBez = gender ? "des Sch�lers" : "der Sch�lerin";
		String name_schueler = "Tina M�ller";
		String brief_datum = "05.04.2014";
		String klasse = "10BJ1";
		String anschrift = "Gudrun M�ller" + "\rM�llerstr. 7"
				+ "\r97080 W�rzburg";
		String begruendung = "Unerlaubtes Fernbleiben vom Unterricht";

		Hashtable<String, String> ht = new Hashtable<String, String>();

		ht.put("Text1", anschrift); // 0
		ht.put("Text2", brief_datum);
		ht.put("Text3", schuelerBez + " " + name_schueler + ", " + "Klasse "
				+ klasse);
		ht.put("Text4", begruendung);

		return ht;
	}

	/** Fills a field of a form with a value. Skips if field is not found */
	private void FillFormField(XWPFDocument doc, String identifier, String value) {
		System.out.println(identifier + " -> " + value);

		for (XWPFParagraph p : doc.getParagraphs()) {
			List<XWPFRun> runs = p.getRuns();
			if (runs != null) {
				for (XWPFRun r : runs) {
					String text = r.getText(0);
					if (text != null && text.contains(identifier)) {
						text = text.replace(identifier, value);
						r.setText(text, 0);
					}

				}
			}
		}
		for (XWPFTable tbl : doc.getTables()) {
			for (XWPFTableRow row : tbl.getRows()) {
				for (XWPFTableCell cell : row.getTableCells()) {
					for (XWPFParagraph p : cell.getParagraphs()) {
						for (XWPFRun r : p.getRuns()) {
							String text = r.getText(0);
							if (text != null) {
								if (text.contains(identifier)) {
									text = text.replace(identifier, value);
									r.setText(text, 0);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Returns the input (in:true) or output (in:false) path for a formType
	 * IMPORTANT: Input file must be PDF version 1.4 (set Acrobat DC 5.0
	 * Compatibility)!
	 * 
	 * @throws Exception
	 *             Throws exception if no path-creation is implemented for the
	 *             given DocumentType
	 */
	private String GetPath(DocumentType type, boolean in) throws Exception {
		switch (type) {

		// TODO make these to cases generic

		case Verweis:
			if (in)
				return this.PATH + "Bericht.docx";
			else
				return this.PATH + "Repo_Vorlage_Verweis_filled.docx";

		case Attest:
			if (in)
				return this.PATH + "Bericht.docx";
			else
				return this.PATH + "Repo_Vorlage_Attest_filled.docx";

		default:
			String message = String.format(
					"No path is implemented for DocumentType '{0}'",
					type.toString());
			throw new Exception(message);
		}
	}

	/*
	 * Fills forms of a given PDF and saves it to a new file. Note: Input file
	 * must be a PDF 1.4 (set Acrobat DC 5.0 Compatibility)!
	 */
	public void CreateNewDocx(DocumentType type) throws Exception {
		String INPUT = GetPath(type, true);
		String OUTPUT = GetPath(type, false);

		XWPFDocument doc = new XWPFDocument(OPCPackage.open(INPUT));

		Hashtable<String, String> elementHT = Generate_Table(type);
		Enumeration<String> elementKeys = elementHT.keys();
		while (elementKeys.hasMoreElements()) {
			String key = (String) elementKeys.nextElement();
			FillFormField(doc, key, elementHT.get(key).toString());
		}

		FileOutputStream fileout = new FileOutputStream(OUTPUT);

		doc.write(fileout);
		fileout.flush();
		fileout.close();

		if (debug)
			System.out.println("Output: '" + OUTPUT + "'");
	}

}
