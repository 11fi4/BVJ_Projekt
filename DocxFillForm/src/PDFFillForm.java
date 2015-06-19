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

/** Fills out the form of a given PDF and saves it to a new file */
public class PDFFillForm {
	public enum FormType {Verweis, Attest};
	final String PATH = "T:\\";
	private final boolean debug = true;

	/** Generates text table for a FormType */
	private Hashtable<String, String> Generate_Table(FormType formType){
		switch(formType){
		case Verweis:
			return Generate_Verweis_Table();
		case Attest:
			return Generate_Attest_Table();
		} return null;
	}

	/** Generates text table for an 'Attest' document */
	private Hashtable<String, String> Generate_Attest_Table(){
		Boolean gender = true;
		Boolean genderParent = true;
		String brief_datum = "05.04.2014";
		String possesiv0 = gender? "ihre Tochter" : "ihr Sohn";
		String possesiv1 = gender? "ihrer Tochter" : "ihres Sohnses";
		String possesiv2 = gender? "unserer Tochter" : "unseres Sohnes";
		String anschrift = "Gudrun Müller"+"\nMüllerstr. 7"+"\n97080 Würzburg";
		String anrede = (genderParent? "geehrte Frau " : "geehrter Herr ") + "Müller";
		String name_schueler = "Tina";
		String fehltage_seit = "01.01.2014";
		String fehltage_gesamt = "19";
		String fehltage_unentschuldigt = "8";
		String attestpflicht_ab = "17.04.2014";
		
		Hashtable<String, String> ht = new Hashtable<String, String>(); 
	
		ht.put("text01", anschrift); //0
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
	private Hashtable<String, String> Generate_Verweis_Table(){ 
		Boolean gender = true;
		String schuelerBez = gender? "des Schülers" : "der Schülerin";
		String name_schueler = "Tina Müller";
		String brief_datum = "05.04.2014";
		String klasse = "10BJ1";
		String anschrift = "Gudrun Müller"+"\rMüllerstr. 7"+"\r97080 Würzburg";
		String begruendung = "Unerlaubtes Fernbleiben vom Unterricht";
		
		Hashtable<String, String> ht = new Hashtable<String, String>(); 
	
		ht.put("Text1", anschrift); //0
		ht.put("Text2", brief_datum);
		ht.put("Text3", schuelerBez + " " + name_schueler + ", " + "Klasse " + klasse);
		ht.put("Text4", begruendung);
		
		return ht;
	}
	
	/** For testing purposes */
	public static void main(String[] args){		
		new PDFFillForm().CreateNewDocx(FormType.Attest);
		new PDFFillForm().CreateNewDocx(FormType.Verweis);
	}	
	
	/** Fills a field of a form with a value. Skips if field is not found */
	private void FillFormField(XWPFDocument doc, String identifier, String value){
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
							if (text != null)
							{
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
	
	/** Returns the input (in:true) or output (in:false) path for a formType 
	 *  IMPORTANT: Input file must be PDF version 1.4 (set Acrobat DC 5.0 Compatibility)! */
	private String GetPath(FormType formType, boolean in){
		switch(formType){
		case Verweis:
			if(in) return PATH + "Bericht.docx";
			else return PATH + "Repo_Vorlage_Verweis_filled.docx";
		case Attest:
			if(in) return PATH + "Bericht.docx";
			else return PATH + "Repo_Vorlage_Attest_filled.docx";
		} return null;
	}
	
	
	/* Fills forms of a given PDF and saves it to a new file.
	 * Note: Input file must be a PDF 1.4 (set Acrobat DC 5.0 Compatibility)! */	
	public void CreateNewDocx(FormType formType){
		try{
			String INPUT = GetPath(formType, true);
			String OUTPUT = GetPath(formType, false);
			
			XWPFDocument doc = new XWPFDocument(OPCPackage.open(INPUT));
			
			Hashtable<String, String> elementHT = Generate_Table(formType);
			Enumeration<String> elementKeys = elementHT.keys();
			while(elementKeys.hasMoreElements()){
				String key = (String)elementKeys.nextElement();
				FillFormField(doc, key, elementHT.get(key).toString());
			}

			FileOutputStream fileout = new FileOutputStream(OUTPUT);
			
			doc.write(fileout);
			fileout.flush();
			fileout.close();
			
			if(debug) System.out.println("Output: '" + OUTPUT + "'");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
