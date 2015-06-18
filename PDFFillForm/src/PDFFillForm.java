import java.util.Hashtable;
import java.util.Enumeration;
import java.io.*; 
import com.justformspdf.pdf.*;

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
		String anschrift = "Gudrun Müller"+"\\rMüllerstr. 7"+"\\r97080 Würzburg";
		String anrede = (genderParent? "geehrte Frau " : "geehrter Herr ") + "Müller";
		String name_schueler = "Tina";
		String fehltage_seit = "01.01.2014";
		String fehltage_gesamt = "19";
		String fehltage_unentschuldigt = "8";
		String attestpflicht_ab = "17.04.2014";
		
		Hashtable<String, String> ht = new Hashtable<String, String>(); 
	
		ht.put("Text1", anschrift); //0
		ht.put("Text2", brief_datum);
		ht.put("Text3", anrede + ",");
		ht.put("Text4", possesiv1 + " " + name_schueler);
		ht.put("Text5", possesiv1);
		ht.put("Text6", fehltage_seit);
		ht.put("Text7", fehltage_gesamt);
		ht.put("Text8", fehltage_unentschuldigt);
		ht.put("Text9", possesiv1);
		ht.put("Text10", possesiv0);
		ht.put("Text11", attestpflicht_ab);
		ht.put("Text12", possesiv1);
		ht.put("Text13", attestpflicht_ab);
		ht.put("Text14", brief_datum);
		ht.put("Text15", possesiv2 + " " + name_schueler);
		ht.put("Text16", fehltage_seit);
		ht.put("Text17", attestpflicht_ab);
		
		return ht;
	}
	
	/** Generates text table for a 'Verweis' document */ 
	private Hashtable<String, String> Generate_Verweis_Table(){ 
		Boolean gender = true;
		String schuelerBez = gender? "des Schülers" : "der Schülerin";
		String name_schueler = "Tina Müller";
		String brief_datum = "05.04.2014";
		String klasse = "10BJ1";
		String anschrift = "Gudrun Müller"+"\\rMüllerstr. 7"+"\\r97080 Würzburg";
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
		new PDFFillForm().CreateNewPDF(FormType.Attest);
		new PDFFillForm().CreateNewPDF(FormType.Verweis);
	}	

	/** Fills form of a given PDF formType and saves it to a new file. */	
	public void CreateNewPDF(FormType formType){
		try{
			if(debug) System.out.println("Trying to fill PDF form");

			String INPUT = GetPath(formType, true);
			String OUTPUT = GetPath(formType, false);
			
			FileInputStream fis = new FileInputStream(INPUT);
			PDFReader reader = new PDFReader(fis);
			PDF pdf = new PDF(reader);
			pdf.lk("43B78HB6");
			Form form = pdf.getForm();
				
			FillForm(form, formType);
			
			pdf.render();		
			FileOutputStream fos = new FileOutputStream(OUTPUT);
			pdf.writeTo(fos);
			fos.close();
			fis.close();
			
			if(debug) System.out.println("Output: '" + OUTPUT + "'");	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Fills a form depending on its FormType */
	private void FillForm(Form form, FormType formType) throws Exception{
		if(debug) System.out.println("Type: " + formType);

		Hashtable<String, String> ht = Generate_Table(formType);
		Enumeration<String> e = ht.keys();
		while(e.hasMoreElements()){
			String key = (String)e.nextElement();
			FillFormField(form, key, ht.get(key).toString());
		}
	}
	
	/** Fills a field of a form with a value. Skips if field is not found */
	private void FillFormField(Form form, String field, String value){
		try{
			((FormText) form.getElement(field)).setValue(value);
			if(debug) System.out.println("-> SUCCESS: set " + field + " to '" + value + "'");
		}catch(Exception e){
			if(debug) System.out.println("-> FAIL: set " + field + " to '" + value + "'");
		}	
	}
	
	/** Returns the input (in:true) or output (in:false) path for a formType 
	 *  IMPORTANT: Input file must be PDF version 1.4 (set Acrobat DC 5.0 Compatibility)! */
	private String GetPath(FormType formType, boolean in){
		switch(formType){
		case Verweis:
			if(in) return PATH + "Repo_Vorlage_Verweis.pdf";
			else return PATH + "Repo_Vorlage_Verweis_filled.pdf";
		case Attest:
			if(in) return PATH + "Repo_Vorlage_Attest.pdf";
			else return PATH + "Repo_Vorlage_Attest_filled.pdf";
		} return null;
	}
	
}
